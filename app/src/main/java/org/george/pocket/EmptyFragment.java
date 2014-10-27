package org.george.pocket;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.george.pocket.model.NavMenuItem;

public class EmptyFragment extends Fragment {
    // Returns a new instance of this fragment for the given section number.
    public static EmptyFragment newInstance(NavMenuItem item) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putSerializable(C.ARG.NAV_MENU_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public EmptyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.empty_layout, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) getActivity()).onSectionAttached((NavMenuItem)getArguments().getSerializable(C.ARG.NAV_MENU_ITEM));
    }
}