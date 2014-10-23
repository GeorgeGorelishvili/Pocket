package org.george.pocket.db.currency;

import org.george.pocket.db.model.Currency;

import java.util.List;

public interface CurrencyService {

    void add(Currency currency);

    void delete(Currency currency);

    List<Currency> find();


}
