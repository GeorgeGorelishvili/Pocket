package org.george.pocket.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.george.pocket.R;
import org.george.pocket.db.model.Payment;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PaymentListAdapter extends ArrayAdapter<Payment> {

	private Context context;

	private LayoutInflater inflater;

	private List<Payment> payments;

    private static final String DATE_TIME_FORMAT = "dd/MMM/yyyy hh:mm";

	public PaymentListAdapter(Context context, LayoutInflater inflater, List<Payment> payments) {
        super(context, R.layout.payment_list_item_view, payments);
		this.context = context;
		this.inflater = inflater;
		this.payments = payments;
	}

    @Override
    public Payment getItem(int position) {
        return payments.get(position);
    }

    @Override
    public int getCount() {
        return payments.size();
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.payment_list_item_view, parent, false);

        Payment payment = payments.get(position);
        if (payment != null) {
            TextView date = (TextView) convertView.findViewById(R.id.payment_date);
            date.setText(getFormattedDate(payment.getCreationDate()));

            TextView amount = (TextView) convertView.findViewById(R.id.payment_amount);
            amount.setText(payment.getAmount().toString());

            TextView currency = (TextView) convertView.findViewById(R.id.payment_currency);
            currency.setText(payment.getCurrency());

            TextView type = (TextView) convertView.findViewById(R.id.payment_type);
            type.setText(payment.getType());
        }
		return convertView;
	}

    @Override
    public void addAll(Collection<? extends Payment> collection) {
        super.addAll(collection);
    }

    private String getFormattedDate(Date date) {
        return DateFormat.format(DATE_TIME_FORMAT, date).toString();
    }
}