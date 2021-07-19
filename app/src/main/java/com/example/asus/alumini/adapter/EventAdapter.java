package com.example.asus.alumini.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.Notice;
import com.example.asus.alumini.viewHolder.EventViewHolder;
import com.example.asus.alumini.viewHolder.NoticeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventAdapter extends FirebaseRecyclerAdapter<Notice, EventViewHolder> {
    private AppCompatActivity activity;
    public EventAdapter(AppCompatActivity activity, @NonNull FirebaseRecyclerOptions<Notice> options) {
        super(options);
        this.activity=activity;
    }
    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Notice model) {
        holder.setBody(model.getBody());
        holder.setTitle(model.getTitle());
        holder.setPhoto(model.getLink());
        holder.setClick(activity,model);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(activity).inflate(R.layout.events_row,parent,false));
    }
}
