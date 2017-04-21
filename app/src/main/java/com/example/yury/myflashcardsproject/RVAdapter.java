package com.example.yury.myflashcardsproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    public static List<MainActivity.Card> cardList = new ArrayList<MainActivity.Card>();

    public static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvListItemText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvListItemText = (TextView) itemView.findViewById(R.id.tvListItemText);
        }

        @Override
        public void onClick(View v) {
            //TODO
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("answer", cardList.get(position).answer);
            intent.putExtra("question", cardList.get(position).question);
            context.startActivity(intent);
        }
    }

    public RVAdapter (List<MainActivity.Card> list, Context context) {
        this.context = context;
        cardList.addAll(list);
        cardList.add(new MainActivity.Card("1", "2"));
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RVAdapter.ViewHolder holder, int position) {

        holder.tvListItemText.setText(cardList.get(position).question);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public void update (List<MainActivity.Card> list) {
        cardList.clear();
        cardList.addAll(list);
        notifyDataSetChanged();
    }

}
