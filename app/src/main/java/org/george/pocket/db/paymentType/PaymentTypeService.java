package org.george.pocket.db.paymentType;

import org.george.pocket.db.model.PaymentType;

import java.util.List;

public interface PaymentTypeService {

    PaymentType get(long paymentId);

    void add(PaymentType type);

    List<PaymentType> getPaymentTypes();

    void delete(long typeId);

    PaymentType getDefault();
}
