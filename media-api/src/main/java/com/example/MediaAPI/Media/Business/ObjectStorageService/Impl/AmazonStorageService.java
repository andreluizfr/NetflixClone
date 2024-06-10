package com.example.MediaAPI.Media.Business.ObjectStorageService.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.MediaAPI.Media.Business.ObjectStorageService.IObjectStorageService;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudfront.CloudFrontUtilities;
import software.amazon.awssdk.services.cloudfront.cookie.CookiesForCannedPolicy;
import software.amazon.awssdk.services.cloudfront.model.CannedSignerRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@Qualifier("AmazonStorageService")
public class AmazonStorageService implements IObjectStorageService {

    private static final Logger logger = LogManager.getLogger(LocalStorageService.class);

    private final S3Client client = S3Client.builder().region(Region.SA_EAST_1).build();

    CloudFrontUtilities cloudFrontUtilities = CloudFrontUtilities.create();

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.mediaStreamingDir}")
    private String mediaStreamingDir;

    @Value("${aws.cloudFrontDomain}")
    private String cloudFrontDomain;

    @Value("${aws.cloudfrontPrivateKey}")
    private String cloudfrontPrivateKey;

    @Value("${aws.cloudfrontKeyPairId}")
    private String cloudfrontKeyPairId;

    @Override
    public void listBuckets() {
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = client.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
    }

    @Override
    public void listObjects() {
        ListObjectsRequest request = ListObjectsRequest.builder().bucket(bucketName).build();

        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();

        ListIterator<S3Object> listIterator = objects.listIterator();

        while (listIterator.hasNext()) {
            S3Object s3Object = listIterator.next();
            logger.info("\n");
            logger.info("Key: " + s3Object.key());
            logger.info("Owner: " + s3Object.owner());
            logger.info("Size: " + s3Object.size());
        }
    }

    @Override
    public void deleteBucket() {

        this.deleteObjectsInBucket();

        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();

        client.deleteBucket(deleteBucketRequest);
    }

    @Override
    public void deleteObjectsInBucket() {
        try {
            // To delete a bucket, all the objects in the bucket must be deleted first.
            ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build();
            ListObjectsV2Response listObjectsV2Response;

            do {
                listObjectsV2Response = client.listObjectsV2(listObjectsV2Request);
                for (S3Object s3Object : listObjectsV2Response.contents()) {
                    DeleteObjectRequest request = DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(s3Object.key())
                            .build();
                    client.deleteObject(request);
                }
            } while (listObjectsV2Response.isTruncated());

            DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            client.deleteBucket(deleteBucketRequest);

        } catch (S3Exception e) {
            logger.error(e.awsErrorDetails().errorMessage());
        }
    }

    @Override
    public void getObject() {

    }

    public String createPresignedGetUrl(String keyName) {
        try (S3Presigner presigner = S3Presigner.create()) {

            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10)) // The URL will expire in 10 minutes.
                    .getObjectRequest(objectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
            logger.info("Presigned URL: [{}]", presignedRequest.url().toString());
            logger.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

            return presignedRequest.url().toExternalForm();
        }
    }

    public String createPresignedUrl(String keyName, Map<String, String> metadata) {
        try (S3Presigner presigner = S3Presigner.create()) {

            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .metadata(metadata)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10)) // The URL expires in 10 minutes.
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
            String myURL = presignedRequest.url().toString();
            logger.info("Presigned URL to upload a file to: [{}]", myURL);
            logger.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

            return presignedRequest.url().toExternalForm();
        }
    }

    public CannedSignerRequest createRequestForCannedPolicy()
            throws MalformedURLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String cloudFrontUrl = new URL("https", cloudFrontDomain, "").toString();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        Instant expirationDate = now.toInstant(ZoneId.of("Europe/London").getRules().getOffset(now));

        PrivateKey privateKey = decodeCloudfrontPrivateKey();

        return CannedSignerRequest.builder()
                .resourceUrl(cloudFrontUrl)
                .privateKey(privateKey)
                .keyPairId(cloudfrontKeyPairId)
                .expirationDate(expirationDate)
                .build();
    }

    private PrivateKey decodeCloudfrontPrivateKey()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        StringBuilder pkcs8Lines = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new StringReader(cloudfrontPrivateKey));
        String line;
        while ((line = rdr.readLine()) != null) {
            pkcs8Lines.append(line);
        }

        String pkcs8Pem = pkcs8Lines.toString();
        pkcs8Pem = pkcs8Pem.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replace("-----END RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
        byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);

        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
        v2.add(DERNull.INSTANCE);
        v.add(new DERSequence(v2));
        v.add(new DEROctetString(pkcs8EncodedBytes));
        ASN1Sequence seq = new DERSequence(v);
        byte[] bytePrivateKey = seq.getEncoded("DER");

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = kf.generatePrivate(keySpec);

        return privateKey;
    }

    public void addMediaAccessCookieToResponse(HttpServletResponse response)
            throws MalformedURLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        CannedSignerRequest cannedSignerRequest = this.createRequestForCannedPolicy();
        CookiesForCannedPolicy cookiesForCannedPolicy = cloudFrontUtilities
                .getCookiesForCannedPolicy(cannedSignerRequest);

        Cookie expireCookie = new Cookie(cookiesForCannedPolicy.expiresHeaderValue().split("=")[0],
                cookiesForCannedPolicy.expiresHeaderValue().split("=")[1]);
        expireCookie.setPath("/");
        expireCookie.setSecure(true);
        expireCookie.setHttpOnly(true);
        response.addCookie(expireCookie);

        Cookie signatureCookie = new Cookie(cookiesForCannedPolicy.signatureHeaderValue().split("=")[0],
                cookiesForCannedPolicy.signatureHeaderValue().split("=")[1]);
        signatureCookie.setPath("/");
        signatureCookie.setSecure(true);
        signatureCookie.setHttpOnly(true);
        response.addCookie(signatureCookie);

        Cookie keyPairIdCookie = new Cookie(cookiesForCannedPolicy.keyPairIdHeaderValue().split("=")[0],
                cookiesForCannedPolicy.keyPairIdHeaderValue().split("=")[1]);
        keyPairIdCookie.setPath("/");
        keyPairIdCookie.setSecure(true);
        keyPairIdCookie.setHttpOnly(true);
        response.addCookie(keyPairIdCookie);
    }
}
