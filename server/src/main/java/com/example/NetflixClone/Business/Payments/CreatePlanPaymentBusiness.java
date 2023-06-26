package com.example.NetflixClone.Business.Payments;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Payments.CreatePlanPaymentDTO;
import com.example.NetflixClone.CustomExceptions.FailToCreatePlanPaymentException;
import com.example.NetflixClone.CustomExceptions.FailToGetAccountException;
import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Models.enums.Plan;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;

import com.mercadopago.client.payment.PaymentOrderRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;

@Service
public class CreatePlanPaymentBusiness {
    @Autowired
    AccountRepositoryDAO accountRepository;

    public Payment execute(CreatePlanPaymentDTO data)
            throws FailToCreatePlanPaymentException, FailToGetAccountException {

        MercadoPagoConfig.setAccessToken("TEST-2848880495686725-032423-6170e1caacf3f2d838b937f4f69efbfa-1321642718");

        BigDecimal price = this.getPriceByPlan(data.plan());

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createdRequest = this.getRequestByPaymentMethodId(data.paymentForm().payment_method_id(),
                data, price);

        try {

            Optional<Account> optionalAccount = accountRepository.findById(data.accountId());

            if (optionalAccount.isPresent()) {

                Payment payment = client.create(createdRequest);

                System.out.println(payment);

                return payment;

            } else {

                throw new FailToGetAccountException("o id não é de uma conta registrada.");

            }

        } catch (MPApiException e) {

            throw new FailToCreatePlanPaymentException(
                    e.getMessage() + "\n"
                            + e.getApiResponse().getContent() + "\n"
                            + e.getApiResponse().getStatusCode());

        } catch (MPException e) {

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
            .paymentMethodId(data.paymentForm().payment_method_id())
            .transactionAmount(price)
            .notificationUrl("https://eoifqric780iqcx.m.pipedream.net") // em dev
            .build();

        return createdRequest;
    }

}
