package org.george.pocket.db;

import org.george.pocket.db.currency.CurrencyService;
import org.george.pocket.db.currency.CurrencyServiceImpl;
import org.george.pocket.db.payment.PaymentService;
import org.george.pocket.db.payment.PaymentServiceImpl;
import org.george.pocket.db.paymentType.PaymentTypeService;
import org.george.pocket.db.paymentType.PaymentTypeServiceImpl;

public class PocketApi {

    private static PaymentService paymentService;

    private static PaymentTypeService paymentTypeService;

    private static CurrencyService currencyService;

    public static PaymentService getPaymentService() {
        if (paymentService == null) {
            paymentService = PaymentServiceImpl.newInstance();
        }
        return paymentService;
    }

    public static PaymentTypeService getPaymentTypeService() {
        if (paymentTypeService == null) {
            paymentTypeService = PaymentTypeServiceImpl.newInstance();
        }
        return paymentTypeService;
    }

    public static CurrencyService getCurrencyService() {
        if (currencyService == null) {
            currencyService = CurrencyServiceImpl.newInstance();
        }
        return currencyService;
    }
}
