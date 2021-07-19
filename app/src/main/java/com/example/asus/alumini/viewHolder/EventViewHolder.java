package com.example.asus.alumini.viewHolder;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.alumini.EventDetailsActivity;
import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.Notice;
import com.squareup.picasso.Picasso;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView titleText,bodyText;
    private ImageView dp;
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText=itemView.findViewById(R.id.eventTitle);
        bodyText=itemView.findViewById(R.id.eventBody);
        dp=itemView.findViewById(R.id.eventPhoto);
    }
    public  void setTitle(String title){
        titleText.setText("Name : "+title);
    }
    public void setBody(String body){
        bodyText.setText("Venue : "+body);
    }
    public void setPhoto(final String link){
        Picasso.get().load(link).into(dp);
    }
    public void setClick(final AppCompatActivity activity, final Notice notice){

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, EventDetailsActivity.class);
                intent.putExtra("event",notice);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                        Pair.create(((View)titleText), "name"),
                        Pair.create(((View)bodyText), "venue"),
                        Pair.create(((View)dp), "image"));
                activity.startActivity(intent,options.toBundle());
            }
        });
    }

}
