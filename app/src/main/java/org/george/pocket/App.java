package org.george.pocket;

import android.app.Application;
import android.os.Build;

import com.activeandroid.ActiveAndroid;

import org.george.pocket.db.PocketApi;
import org.george.pocket.db.model.Currency;
import org.george.pocket.db.model.PaymentType;

public class App extends Application {
    public Currency currency;
    public PaymentType type;
    public static int BUILD_VERSION;
    public static App self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        App.BUILD_VERSION = Build.VERSION.SDK_INT;
        ActiveAndroid.initialize(getApplicationContext());
//        initApp();
    }

    private void initApp() {
        initCurrency();
        initType();
    }

    private void initCurrency() {
        this.currency = PocketApi.getCurrencyService().getDefault();
    }

    private void initType() {
        this.type = PocketApi.getPaymentTypeService().getDefault();
    }
}
