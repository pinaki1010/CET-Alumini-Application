package com.example.asus.alumini.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.User;
import com.example.asus.alumini.viewHolder.UserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserAdapter extends FirebaseRecyclerAdapter<User, UserViewHolder> {
    private AppCompatActivity activity;
    public UserAdapter(AppCompatActivity activity, @NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
        this.activity =activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        holder.setUp(model,getRef(position).getKey());

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserViewHolder(LayoutInflater.from((activity)).inflate(R.layout.user_row,parent,false));
    }

}
