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
import org.george.pocket.db.common.Paging;
import org.george.pocket.db.model.Payment;

import java.util.List;

public class PaymentFragment extends Fragment {

    private PullToRefreshListView pullToRefreshListView;
    private Paging paging = new Paging();
    private View listView;
    private PaymentListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.REPORT_FRAGMENT, "onCreate");

        if (savedInstanceState != null) {
            // TODO [GG] save instance state
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(C.TAG.REPORT_FRAGMENT, "onCreateView");

        listView = inflater.inflate(R.layout.payment_list_view, container, false);

        pullToRefreshListView = (PullToRefreshListView)listView.findViewById(R.id.payment_pull_to_refresh_list_view);
        View emptyView = inflater.inflate(R.layout.empty_list_view, container, false);
        pullToRefreshListView.getRefreshableView().setEmptyView(emptyView);
        pullToRefreshListView.getRefreshableView().setDivider(null);
        pullToRefreshListView.getLoadingLayoutProxy().setPullLabel(getResources().getString(R.string.loading));
        pullToRefreshListView.getLoadingLayoutProxy().setReleaseLabel(getResources().getString(R.string.payment_next_page));
        pullToRefreshListView.getLoadingLayoutProxy().setRefreshingLabel(getResources().getString(R.string.refresh));
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
                Paging next = paging.getNext();
                Logger.log(C.TAG.REPORT_FRAGMENT, "loading:: " + "offset: " + next.getOffset() + " limit: " + next.getLimit());

                List<Payment> list = PocketApi.getPaymentService().find(next);
                if (list.size() > 0) {
                    adapter.addAll(list);
                    paging.update();
                    pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                    updateStatus();
                }
                pullToRefreshListView.onRefreshComplete();
            }
        });

        List<Payment> payments = PocketApi.getPaymentService().find(paging);
        if (payments.size() > 0) {
            updateStatus();
        }

//            pullToRefreshListView.setOnItemClickListener(this);
        adapter = new PaymentListAdapter(getActivity(), getActivity().getLayoutInflater(), payments);
        pullToRefreshListView.getRefreshableView().setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return listView;
    }

    private void updateStatus() {
        if (listView != null) {
            TextView status = (TextView) listView.findViewById(R.id.search_result_footer_text_view);
            String value = getResources().getString(R.string.page) + " " + paging.getPageNumber();
            status.setText(value);
        }
    }
}
