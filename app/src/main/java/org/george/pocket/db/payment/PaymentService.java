package org.george.pocket.db.payment;

import org.george.pocket.db.common.Paging;
import org.george.pocket.db.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment get(long paymentId);

    void add(Payment payment);

    void delete(long payment);

    List<Payment> find(Paging paging);
}
