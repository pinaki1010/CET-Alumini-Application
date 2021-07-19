package com.example.asus.alumini.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.alumini.R;
import com.example.asus.alumini.pojo.Comment;
import com.example.asus.alumini.viewHolder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CommentAdapter extends FirebaseRecyclerAdapter<Comment, CommentViewHolder> {
    private String myId,postId;
    private AppCompatActivity activity;
    public CommentAdapter(@NonNull FirebaseRecyclerOptions<Comment> options, AppCompatActivity activity,String myId,String postiId) {
        super(options);
        this.myId=myId;
        this.activity=activity;
        this.postId=postiId;
    }

    @Override
    protected void onBindViewHolder(@NonNull final CommentViewHolder holder, final int position, @NonNull final Comment model) {
        final DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("users").child(model.getUser());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userRef.removeEventListener(this);
                holder.setDp(dataSnapshot.child("photo").getValue().toString());
                holder.setName(dataSnapshot.child("name").getValue().toString());
                holder.setComment(model.getComment());
                holder.setTime(model.getTime());
                if(myId.equals(model.getUser())){
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            new AlertDialog.Builder(activity)
                                    .setMessage("Do you really want to delete our comment ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes, delete", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            String commentId=getRef(position).getKey();
                                            FirebaseDatabase.getInstance().getReference().child("post").child(postId).child("comments").child(commentId).removeValue();
                                        }
                                    }).setNegativeButton("Nope",null).create().show();
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(activity).inflate(R.layout.comment_row,parent,false));
    }
}
