package com.example.NetflixClone.Payment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Exceptions.FailToCreatePlanPaymentException;
import com.example.NetflixClone.Exceptions.FailToFindAccountException;
import com.example.NetflixClone.Payment.Business.PaymentBusiness;
import com.example.NetflixClone.Payment.Controller.Models.CreatePlanPaymentDTO;
import com.example.NetflixClone.Util.ResponseHandler;
import com.mercadopago.resources.preference.Preference;

@RestController
@RequestMapping("/api/payment")
public class CreatePlanPaymentController {

    @Autowired
    PaymentBusiness paymentBusiness;

    @PostMapping("/createPlanPayment")
    public ResponseEntity<Object> createPlanPayment(@RequestBody CreatePlanPaymentDTO data) {

        try {

            Preference preference = paymentBusiness.createPlanPayment(data);

            return ResponseHandler.generateResponse("Pagamento iniciado com sucesso.", HttpStatus.CREATED,
                    preference);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null,
                    FailToCreatePlanPaymentException.getErrorCode());

        } catch (FailToFindAccountException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null,
                    FailToCreatePlanPaymentException.getErrorCode());

        } catch (FailToCreatePlanPaymentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null,
                    FailToCreatePlanPaymentException.getErrorCode());

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
