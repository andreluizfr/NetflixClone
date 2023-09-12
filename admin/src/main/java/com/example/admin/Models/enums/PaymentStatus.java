package com.example.admin.Models.enums;

public enum PaymentStatus {
    pending, // The user has not concluded the payment process (for example, to generate a
             // payment by boleto, this payment will be concluded at the moment in which the
             // user makes the non-corresponding payment selected);
    approved, // The payment was approved and credited;
    authorized, // The payment was authorized, but still was not captured;
    in_process, // The payment is in analysis;
    in_mediation, // The user started a dispute;
    rejected, // The payment was rejected. (The user can try the payment again);
    cancelled, // Either the payment was canceled by one of the parties or the payment expired;
    refunded, // The payment was returned to the user;
    charged_back, // A chargeback was placed on the buyer's credit card.
}
