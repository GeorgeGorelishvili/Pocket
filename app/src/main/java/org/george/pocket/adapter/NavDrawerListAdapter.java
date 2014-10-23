package org.george.pocket.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.george.pocket.R;
import org.george.pocket.model.NavMenuItem;

import java.util.ArrayList;

public class NavDrawerListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<NavMenuItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavMenuItem> navDrawerItems){
        super(context, R.layout.drawer_list_item, navDrawerItems);
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public NavMenuItem getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.list_item_icon);
        TextView title = (TextView) convertView.findViewById(R.id.list_item_title);

        NavMenuItem item = getItem(position);

        icon.setImageResource(item.getIcon());
        title.setText(item.getTitle());

        // displaying count
        // check whether it set visible or not
//        if (item.getCounterVisibility()) {
//            newsCount.setText(item.getCount());
//        } else {
//            // hide the counter view
//            newsCount.setVisibility(View.GONE);
//        }

        return convertView;
    }
}
