package com.example.asus.alumini.viewHolder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.alumini.R;
import com.example.asus.alumini.TimeAgo;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    private TextView nameText,commentText,timeText;
    private CircleImageView dp;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText=itemView.findViewById(R.id.comments_name);
        dp=itemView.findViewById(R.id.comments_dp);
        commentText=itemView.findViewById(R.id.comments_comment);
        timeText=itemView.findViewById(R.id.comments_time);
    }
    public void setName(String name){
        nameText.setText(name);
    }
    public void setDp(String url){
        Picasso.get().load(url).placeholder(R.drawable.ic_person_black_24dp).into(dp);
    }
    public void setComment(String comment){
        commentText.setText(comment);
    }
    public void setTime(long time){
        timeText.setText(TimeAgo.getTimeAgo(time));

    }
}
