package com.example.yury.myflashcardsproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Yury on 19.04.2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    public String[] textdata;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvListItemText;

        public ViewHolder(View itemView) {
            super(itemView);
            tvListItemText = (TextView) itemView.findViewById(R.id.tvListItemText);
        }
    }

    public RVAdapter () {
        textdata = new String[] {"menu 1", "menu 2", "menu 3"};
        // TODO
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RVAdapter.ViewHolder holder, int position) {

        holder.tvListItemText.setText(textdata[position]);
    }

    @Override
    public int getItemCount() {
        return textdata.length;
    }


}
