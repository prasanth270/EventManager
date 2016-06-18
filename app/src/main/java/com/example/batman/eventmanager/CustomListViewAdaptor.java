package com.example.batman.eventmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import model.ListViewModel;

/**
 * Created by Batman on 3/25/16.
 */
public class CustomListViewAdaptor extends BaseAdapter {

    private static List<ListViewModel> listViewModelList;
    private LayoutInflater mInflater;
    private TextView eventName;
    private TextView eventDate;

    public CustomListViewAdaptor(Context context, List<ListViewModel> modelList){
        listViewModelList = modelList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listViewModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.custom_home_list_view, null);
            holder = new CustomViewHolder();
            holder.eventName = (TextView) convertView.findViewById(R.id.showEventName);
            holder.eventDate = (TextView) convertView.findViewById(R.id.showEventDate);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }
        holder.eventName.setText(listViewModelList.get(position).getEventName());
        holder.eventDate.setText(listViewModelList.get(position).getEventDate());

        return convertView;
    }
    static class CustomViewHolder {
        TextView eventName;
        TextView eventDate;
        //TextView txtPhone;
    }
}
