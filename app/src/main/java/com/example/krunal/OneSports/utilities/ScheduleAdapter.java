package com.example.krunal.OneSports.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krunal.OneSports.R;
import com.example.krunal.OneSports.model.ScheduleModel;

import java.util.ArrayList;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ItemHolder>{


    private ArrayList<ScheduleModel> data;
    ItemClickListener listener;

    public ScheduleAdapter(ArrayList<ScheduleModel> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }



    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.schedule_items, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView date;
        TextView homeTeam;
        TextView awayTeam;
        TextView gameTime;

        ItemHolder(View view){
            super(view);
            date = (TextView)view.findViewById(R.id.date);
            homeTeam = (TextView)view.findViewById(R.id.homeTeam);
            awayTeam = (TextView)view.findViewById(R.id.awayTeam);
            gameTime = (TextView)view.findViewById(R.id.time);
//            tittle = (TextView)view.findViewById(R.id.tittle);
//            desc = (TextView)view.findViewById(R.id.desc);
//            date = (TextView)view.findViewById(R.id.date);
//            url = (TextView)view.findViewById(R.id.url);
          view.setOnClickListener(this);
        }

        public void bind(int pos){
//            NewsItem items = data.get(pos);
//            author.setText("Author: "+ items.getAuthor());
//            tittle.setText("Tittle: "+ items.getTittle());
//            desc.setText("Description: "+items.getDescription());
//            url.setText("URL: "+items.getUrl());
//            date.setText("Date: "+items.getDate());

            ScheduleModel items = data.get(pos);
            homeTeam.setText("HOME:\t\t\t\t\t"+items.getHomeTeam());
            awayTeam.setText("AWAY:\t\t\t\t\t"+items.getAwayTeam());
            date.setText("DATE:\t\t\t\t\t\t"+items.getGameDate());
            gameTime.setText("TIME:\t\t\t\t\t\t\t"+items.getGameTime() + "\n");
        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }

}