package org.george.pocket.db.payment;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.george.pocket.db.model.Payment;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    public static PaymentService newInstance() {
        return new PaymentServiceImpl();
    }

    @Override
    public Payment get(long paymentId) {
        return new Select().from(Payment.class).where("id = ?", paymentId).executeSingle();
    }

    @Override
    public void add(Payment payment) {
        payment.save();
    }

    @Override
    public void delete(long paymentId) {
        new Delete().from(Payment.class).where("id = ?", paymentId).execute();
    }

    @Override
    public List<Payment> find() {
        return new Select().from(Payment.class).execute();
    }
}
