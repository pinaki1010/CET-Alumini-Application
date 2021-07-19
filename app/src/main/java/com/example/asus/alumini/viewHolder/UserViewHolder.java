package com.example.asus.alumini.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.alumini.ProfileActivity;
import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.User;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView nameText,typeText;
    private CircleImageView dp;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText=itemView.findViewById(R.id.userName);
        dp=itemView.findViewById(R.id.userDp);
        typeText=itemView.findViewById(R.id.userType);
    }
    public void setUp(User user, final String id){
        nameText.setText(user.getName());
        if(!user.getPhoto().equals("na"))
            Picasso.get().load(user.getPhoto()).into(dp);
        typeText.setText(user.getType());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), ProfileActivity.class).putExtra("id",id));
            }
        });

    }
}
