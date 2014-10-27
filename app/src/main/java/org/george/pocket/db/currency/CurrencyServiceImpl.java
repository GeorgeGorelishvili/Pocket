package org.george.pocket.db.currency;

import com.activeandroid.query.Select;

import org.george.pocket.db.model.Currency;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {

    public static CurrencyService newInstance() {
        return new CurrencyServiceImpl();
    }

    @Override
    public void add(Currency currency) {
        currency.save();
    }

    @Override
    public void delete(Currency currency) {
        currency.delete();
    }

    @Override
    public List<Currency> find() {
        return new Select().from(Currency.class).execute();
    }

    @Override
    public Currency getDefault() {
        /*Currency currency = new Select().from(Currency.class).where("default_currency = ?", 1).executeSingle();
        if (currency == null) {
            currency = new Currency(C.DEF.DEFAULT_CURRENCY_CODE);
            currency = Currency.load(Currency.class, currency.save());
        }
        return currency;*/
        return null;
    }
}
