package com.example.NetflixClone.Controllers.Payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Payments.CreatePlanPaymentBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToCreatePlanPaymentException;
import com.example.NetflixClone.CustomExceptions.FailToGetAccountException;
import com.mercadopago.resources.payment.Payment;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:5173" }, allowCredentials = "true")
@RestController
@RequestMapping("/api/payment")
public class CreatePlanPaymentController {
    @Autowired
    CreatePlanPaymentBusiness createPlanPaymentBusiness;

    @PostMapping("/createPlanPayment")
    public ResponseEntity<Object> createPlanPayment(@RequestBody CreatePlanPaymentDTO data) {

        try {

            Payment payment = createPlanPaymentBusiness.execute(data);

            return ResponseErrorHandler.generateResponse("Conta criada com sucesso.", HttpStatus.INTERNAL_SERVER_ERROR,
                    payment);

        } catch (FailToCreatePlanPaymentException | FailToGetAccountException  e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null,
                    FailToCreatePlanPaymentException.getErrorCode());
        }

    }
}
