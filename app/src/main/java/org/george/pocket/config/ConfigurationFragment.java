package org.george.pocket.config;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.george.pocket.C;
import org.george.pocket.Logger;
import org.george.pocket.R;

public class ConfigurationFragment extends Fragment {

    public ConfigurationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.CONFIGURATION_FRAGMENT, "onCreate");
        if (savedInstanceState != null) {
            // TODO [GG] save instance state
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(C.TAG.CONFIGURATION_FRAGMENT, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_conofiguration, container, false);
        return rootView;
    }
}
