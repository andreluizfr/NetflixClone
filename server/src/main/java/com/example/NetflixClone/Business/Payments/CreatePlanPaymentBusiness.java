package com.example.NetflixClone.Business.Payments;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Payments.CreatePlanPaymentDTO;
import com.example.NetflixClone.CustomExceptions.FailToCreatePlanPaymentException;
import com.example.NetflixClone.CustomExceptions.FailToGetAccountException;
import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Models.enums.Plan;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.MercadoPagoConfig;

@Service
public class CreatePlanPaymentBusiness {
    @Autowired
    AccountRepositoryDAO accountRepository;

    public Preference execute(CreatePlanPaymentDTO data) throws FailToCreatePlanPaymentException, FailToGetAccountException, IllegalArgumentException{

        if(data.plan() == null) throw new IllegalArgumentException("plan is null");
        if(data.accountId() == null) throw new IllegalArgumentException("accountId is null");
        if(data.paymentType() == null) throw new IllegalArgumentException("paymentType is null");

        MercadoPagoConfig.setAccessToken("TEST-8648720384457418-062712-0ab43f56c05f8ff06f555474f5e80e57-1321642718");

        PreferenceClient client = new PreferenceClient();

        BigDecimal price = this.getPriceByPlan(data.plan());
        
        List<PreferenceItemRequest> items = new ArrayList<>(); 
        
        PreferenceItemRequest item = PreferenceItemRequest
            .builder()
            .title(data.plan().toString())
            .quantity(1)
            .unitPrice(price)
            .build();

        items.add(item);

        PreferenceRequest request = PreferenceRequest
            .builder()
            .purpose("wallet_purchase")
            .notificationUrl("https://eoifqric780iqcx.m.pipedream.net") //em dev
            .items(items).build();

        try {
            
            Optional<Account> optionalAccount = accountRepository.findById(data.accountId());

            if (optionalAccount.isPresent()) {

                Preference preference = client.create(request);

                System.out.println(preference);

                Account account = optionalAccount.get();

                List<Preference> paymentHistory = account.getPaymentHistory();
                paymentHistory.add(preference);
                account.setPaymentHistory(paymentHistory);
                //System.out.println(account);
                //accountRepository.save(account);

                return preference;

            } else {

                throw new FailToGetAccountException("o id não é de uma conta registrada.");
            }

        } catch (MPApiException e) {

            throw new FailToCreatePlanPaymentException(
                e.getMessage() + "\n"
                + e.getApiResponse().getContent() + "\n"
                + e.getApiResponse().getStatusCode()
            );

        } catch (MPException e) {

            throw new FailToCreatePlanPaymentException(e.getMessage());
        } catch (Exception e) {

            throw new FailToCreatePlanPaymentException(e.getMessage());
        }
    }

    private BigDecimal getPriceByPlan(Plan plan) {
        switch (plan) {
            case BASIC_WITH_ADS:
                return new BigDecimal("19.90");
            case BASIC:
                return new BigDecimal("34.90");
            case PREMIUM:
                return new BigDecimal("59.90");

            default:
                return new BigDecimal("19.90");
        }
    }
        
    /* 

    private PaymentCreateRequest getRequestByPaymentMethodId(String paymentMethod, CreatePlanPaymentDTO data,
            BigDecimal price) {
        switch (paymentMethod) {
            case "pix":
                return this.generatePixPaymentRequest(data, price);
            case "bolbradesco":
                return this.generateBolBradescoPaymentRequest(data, price);

            default:
                return this.generateVisaMastercardPaymentRequest(data, price);
        }
    }

    private PaymentCreateRequest generatePixPaymentRequest(CreatePlanPaymentDTO data, BigDecimal price) {

        // Pix tem 24 hrs para pagar
        LocalDateTime dateOfExpiration = LocalDateTime.now();
        dateOfExpiration = dateOfExpiration.plusDays(1);

        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateOfExpiration, ZoneOffset.UTC);

        PaymentCreateRequest createdRequest = PaymentCreateRequest.builder()
                .description(data.paymentForm().description())
                .externalReference("account_id: " + data.accountId())
                .installments(data.paymentForm().installments())
                .order(PaymentOrderRequest.builder().type("mercadolibre").id((new Date()).getTime()).build())
                .payer(PaymentPayerRequest.builder().email(data.paymentForm().payer().email()).build())
                .paymentMethodId(data.paymentForm().payment_method_id().toString())
                .transactionAmount(price)
                .notificationUrl("http://localhost:8080/api/payment/feedback") // em dev
                .dateOfExpiration(offsetDateTime)
                .build();

        return createdRequest;
    }

    private PaymentCreateRequest generateBolBradescoPaymentRequest(CreatePlanPaymentDTO data, BigDecimal price) {

        // Pix tem 3 dias para pagar
        LocalDateTime dateOfExpiration = LocalDateTime.now();
        dateOfExpiration = dateOfExpiration.plusDays(3);

        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateOfExpiration, ZoneOffset.UTC);

        PaymentCreateRequest createdRequest = PaymentCreateRequest.builder()
                .description(data.paymentForm().description())
                .externalReference("account_id: " + data.accountId())
                .installments(data.paymentForm().installments())
                .order(PaymentOrderRequest.builder().type("mercadolibre").id((new Date()).getTime()).build())
                .payer(PaymentPayerRequest.builder().email(data.paymentForm().payer().email()).build())
                .paymentMethodId(data.paymentForm().payment_method_id().toString())
                .transactionAmount(price)
                .notificationUrl("http://localhost:8080/api/payment/feedback") // em dev
                .dateOfExpiration(offsetDateTime)
                .build();

        return createdRequest;
    }

    private PaymentCreateRequest generateVisaMastercardPaymentRequest(CreatePlanPaymentDTO data, BigDecimal price) {

        System.out.println(data.paymentForm().payment_method_id());
        System.out.println(data.paymentForm().token());

        PaymentCreateRequest createdRequest = PaymentCreateRequest.builder()
                .token(data.paymentForm().token())
                .description(data.paymentForm().description())
                .installments(data.paymentForm().installments())
                .payer(PaymentPayerRequest.builder().email(data.paymentForm().payer().email()).build())
                .payer(
                    PaymentPayerRequest.builder()
                        .email(data.paymentForm().payer().email())
                        //.firstName(data.payer().firstName())
                        .identification(
                            IdentificationRequest
                                .builder()
                                .type(data.paymentForm().payer().identification().type())
                                .number(data.paymentForm().payer().identification().number())
                                .build())
                        .build())
                .paymentMethodId(data.paymentForm().payment_method_id())
                .transactionAmount(price)
                .notificationUrl("https://eoifqric780iqcx.m.pipedream.net") // em dev
                .build();

        return createdRequest;
    }
         */
   
}
