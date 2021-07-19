package com.example.asus.alumini.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.Notice;
import com.example.asus.alumini.viewHolder.NoticeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NoticeAdapter extends FirebaseRecyclerAdapter<Notice, NoticeViewHolder> {
    private AppCompatActivity activity;
    public NoticeAdapter(AppCompatActivity activity,@NonNull FirebaseRecyclerOptions<Notice> options) {
        super(options);
        this.activity=activity;
    }
    @Override
    protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull Notice model) {
        holder.setBody(model.getBody());
        holder.setTitle(model.getTitle());
        holder.setLinkButton(model.getLink(),activity);
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(activity).inflate(R.layout.notice_card,parent,false));
    }
}
