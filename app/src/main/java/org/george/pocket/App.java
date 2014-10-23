package org.george.pocket;

import android.app.Application;
import android.os.Build;

import com.activeandroid.ActiveAndroid;

public class App extends Application {

    public static int BUILD_VERSION;
    private static App self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        App.BUILD_VERSION = Build.VERSION.SDK_INT;
        ActiveAndroid.initialize(getApplicationContext());
    }
}
