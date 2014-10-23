package org.george.pocket.db.paymentType;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.george.pocket.db.model.PaymentType;

import java.util.List;

public class PaymentTypeServiceImpl implements PaymentTypeService {

    public static PaymentTypeService newInstance() {
        return new PaymentTypeServiceImpl();
    }


    @Override
    public PaymentType get(long paymentId) {
        return new Select().from(PaymentType.class).where("id = ?", paymentId).executeSingle();
    }

    @Override
    public void add(PaymentType type) {
        type.save();
    }

    @Override
    public List<PaymentType> getPaymentTypes() {
        return new Select().from(PaymentType.class).execute();
    }

    @Override
    public void delete(long typeId) {
        new Delete().from(PaymentType.class).where("id = ?", typeId).execute();
    }
}
