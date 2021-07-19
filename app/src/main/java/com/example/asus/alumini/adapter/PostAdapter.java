package com.example.asus.alumini.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.Post;
import com.example.asus.alumini.pojo.User;
import com.example.asus.alumini.viewHolder.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostViewHolder> {
    private AppCompatActivity activity;
    private String uid;
    public PostAdapter(AppCompatActivity activity,String uid, @NonNull FirebaseRecyclerOptions<Post> options) {
        super(options);
        this.activity=activity;
        this.uid=uid;
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostViewHolder holder, final int position, @NonNull final Post model) {
        holder.setCaption(model.getCaption());
        holder.setCommentButton(activity,getRef(position).getKey());
        holder.setPostImageView(activity,model.getPhoto(),getRef(position).getKey());
        final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("users").child(model.getUser());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dbRef.removeEventListener(this);
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    holder.setName(activity, user.getName(), model.getUser());
                    holder.setDp(user.getPhoto());
                    holder.setAlumni(user.getType());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference likesRef=getRef(position).child("likes");
        likesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likesRef.removeEventListener(this);
                holder.setLikeButton(dataSnapshot.hasChild(uid),likesRef,uid);
                holder.setLikesText(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(activity).inflate(R.layout.post_row,parent,false));
    }
}
