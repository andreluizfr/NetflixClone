package com.example.NetflixClone.Business.Payments;

import com.example.NetflixClone.Controllers.Payments.CreatePlanPaymentDTO;
import com.example.NetflixClone.CustomExceptions.FailToGetPaymentMethodsException;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.paymentmethod.PaymentMethod;

public class GetPaymentMethodsBusiness {

    public MPResourceList<PaymentMethod> execute(CreatePlanPaymentDTO data) throws FailToGetPaymentMethodsException {

        PaymentMethodClient client = new PaymentMethodClient();
        try {

            return client.list();

        } catch (MPApiException e) {

            throw new FailToGetPaymentMethodsException(
                    e.getMessage() + "\n"
                            + e.getApiResponse().getContent() + "\n"
                            + e.getApiResponse().getStatusCode());

        } catch (MPException e) {

            throw new FailToGetPaymentMethodsException(e.getMessage());
        }

    }

}
