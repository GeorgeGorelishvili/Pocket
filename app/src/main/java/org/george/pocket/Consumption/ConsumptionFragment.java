package org.george.pocket.Consumption;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.george.pocket.App;
import org.george.pocket.C;
import org.george.pocket.Logger;
import org.george.pocket.R;
import org.george.pocket.db.PocketApi;
import org.george.pocket.db.model.Payment;
import org.george.pocket.model.NavMenuItem;

public class ConsumptionFragment extends Fragment {

    private Spinner typeSpinner;
    private TextView amountEdit;
    private Button addButton;
    private TextView currency;

    public ConsumptionFragment() {
    }

    // Returns a new instance of this fragment for the given section number.
    public static ConsumptionFragment newInstance(NavMenuItem item) {
        ConsumptionFragment fragment = new ConsumptionFragment();
        Bundle args = new Bundle();
        args.putSerializable(C.ARG.NAV_MENU_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.CONSUMPTION_FRAGMENT, "onCreate");

        if (savedInstanceState != null) {
            // TODO [GG] save instance state
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(C.TAG.CONSUMPTION_FRAGMENT, "onCreateView");
        View rootView = inflater.inflate(R.layout.consumption_layout, container, false);

        typeSpinner = (Spinner) rootView.findViewById(R.id.payment_type);
        amountEdit = (EditText) rootView.findViewById(R.id.amount_field);
        addButton = (Button) rootView.findViewById(R.id.add_consumption);
        currency = (TextView) rootView.findViewById(R.id.currency);

        initComponents();
         /*typeSpinner.setAdapter(new SimpleCursorAdapter(getActivity(),
                 android.R.layout.simple_expandable_list_item_1,
                 null,
                 new String[] {"name"},
                 new int[] {android.R.id.text1},
                 0));*/

         /*getActivity().getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
             @Override
             public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                 return new CursorLoader(getActivity(),
                         ContentProvider.createUri(PaymentType.class, null), // TODO [GG] default value
                            null, null, null, null);
             }

             @Override
             public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
                 ((SimpleCursorAdapter)typeSpinner.getAdapter()).swapCursor(cursor);
             }

             @Override
             public void onLoaderReset(Loader<Cursor> cursorLoader) {
                 ((SimpleCursorAdapter)typeSpinner.getAdapter()).swapCursor(null);
             }
         });*/



        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO [GG] save state
    }

    private void initComponents() {
        amountEdit.setText("100");
        currency.setText(App.self.currency.getCode());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PocketApi.getPaymentService().add(new Payment(getIntegerValue(), App.self.type.getName(), App.self.currency.getCode()));
            }
        });
    }

    private Integer getIntegerValue() {
        int value = 0;
        if (amountEdit.getText() != null) {
            value = Integer.parseInt(amountEdit.getText().toString().trim());
        }
        return value;
    }

     /*@Override
     public void onAttach(Activity activity) {
         Logger.log(C.TAG.CONSUMPTION_FRAGMENT, "onAttach");
         super.onAttach(activity);
         ((MainActivity) getActivity()).onSectionAttached((NavMenuItem)getArguments().getSerializable(C.ARG.NAV_MENU_ITEM));
     }*/
}
