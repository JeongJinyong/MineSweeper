package com.example.jjy.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JJY on 2018-04-02.
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {

    Context context;
    ArrayList<MineClass> mineClasses;

    public MineAdapter(Context context, ArrayList<MineClass> mineClasses){
        this.context = context;
        this.mineClasses = mineClasses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setVisibility(mineClasses.get(position).visibility);
        switch (mineClasses.get(position).info){
            case "*":
                holder.textView.setTextColor(Color.parseColor("#FF3636"));
                break;
            case "1":
                holder.textView.setTextColor(Color.parseColor("#CC723D"));
                break;
            case "2":
                holder.textView.setTextColor(Color.parseColor("#FFBB00"));
                break;
            case "3":
                holder.textView.setTextColor(Color.parseColor("#ABF200"));
                break;
            case "4":
                holder.textView.setTextColor(Color.parseColor("#1DDB16"));
                break;
            case "5":
                holder.textView.setTextColor(Color.parseColor("#00D8FF"));
                break;
            case "6":
                holder.textView.setTextColor(Color.parseColor("#0054FF"));
                break;
            case "7":
                holder.textView.setTextColor(Color.parseColor("#5F00FF"));
                break;
            case "8":
                holder.textView.setTextColor(Color.parseColor("#FF00DD"));
                break;
        }
        holder.textView.setText(mineClasses.get(position).info);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mineClasses.get(position).visibility = View.VISIBLE;
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mineClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
