package org.george.pocket.payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.george.pocket.C;
import org.george.pocket.Logger;
import org.george.pocket.R;
import org.george.pocket.adapter.PaymentListAdapter;
import org.george.pocket.db.PocketApi;
import org.george.pocket.db.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends Fragment {

    private PullToRefreshListView pullToRefreshListView;
    private List<Payment> payments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.REPORT_FRAGMENT, "onCreate");

        payments = PocketApi.getPaymentService().find();
        if (savedInstanceState != null) {
            // TODO [GG] save instance state
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(C.TAG.REPORT_FRAGMENT, "onCreateView");

        View emptyView = inflater.inflate(R.layout.empty_list_view, container, false);

        TextView emptyText = (TextView)emptyView.findViewById(R.id.empty_text);
        emptyText.setText(R.string.payment_list_empty);

        if (payments.size() > 0) {
            Logger.log(C.TAG.REPORT_FRAGMENT, "List View");
            View listView = inflater.inflate(R.layout.payment_list_view, container, false);

            TextView status = (TextView)listView.findViewById(R.id.search_result_footer_text_view);
            status.setText(R.string.app_name);

            pullToRefreshListView = (PullToRefreshListView)listView.findViewById(R.id.payment_pull_to_refresh_list_view);
            pullToRefreshListView.getRefreshableView().setEmptyView(emptyView);
            pullToRefreshListView.getRefreshableView().setDivider(null);
            pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
            pullToRefreshListView.getLoadingLayoutProxy().setPullLabel(getResources().getString(R.string.loading));
            pullToRefreshListView.getLoadingLayoutProxy().setReleaseLabel(getResources().getString(R.string.payment_next_page));
            pullToRefreshListView.getLoadingLayoutProxy().setRefreshingLabel(getResources().getString(R.string.refresh));
            pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);

            pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                    payments = PocketApi.getPaymentService().find();
                    pullToRefreshListView.onRefreshComplete();
                }
            });

            PaymentListAdapter adapter = new PaymentListAdapter(getActivity(), getActivity().getLayoutInflater(), payments);
            pullToRefreshListView.getRefreshableView().setAdapter(adapter);
//            pullToRefreshListView.setOnItemClickListener(this);
            adapter.notifyDataSetChanged();
            return listView;
        } else {
            Logger.log(C.TAG.REPORT_FRAGMENT, "Empty View");
            return emptyView;
        }
    }
}
