package com.example.asus.alumini.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.alumini.CommentsActivity;
import com.example.asus.alumini.PostDetailsActivity;
import com.example.asus.alumini.ProfileActivity;
import com.example.asus.alumini.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView captionView;
    private ImageView postImageView;
    private ToggleButton postLikeButton;
    private AppCompatImageView commentText;
    private CircleImageView dpImage;
    private TextView nameText;
    private ImageView alumniMark;
    private TextView likesText;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        captionView=itemView.findViewById(R.id.postCaption);
        postImageView=itemView.findViewById(R.id.postImage);
        postLikeButton=itemView.findViewById(R.id.postLikeButton);
        commentText=itemView.findViewById(R.id.postCommentButton);
        nameText=itemView.findViewById(R.id.feed_name);
        dpImage=itemView.findViewById(R.id.feed_dp);
        alumniMark=itemView.findViewById(R.id.feed_check);
        likesText=itemView.findViewById(R.id.feedLikesCount);
    }
    public void setAlumni(String type){
        if(type.equals("alumni"))
            alumniMark.setVisibility(View.VISIBLE);
        else
            alumniMark.setVisibility(View.GONE);
    }
    public void setName(final AppCompatActivity activity, String name, final String id){
        nameText.setText(name);
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, ProfileActivity.class).putExtra("id",id));
            }
        });
    }
    public void setDp(String url){
        Picasso.get().load(url).placeholder(R.drawable.dummy_dp).into(dpImage);
    }
    public void setCaption(String caption){
        captionView.setText(caption);
    }
    public void setPostImageView(final AppCompatActivity activity, String url, final String id){
        if(url.equals(" ")){
            postImageView.setVisibility(View.GONE);
        }else{
            postImageView.setVisibility(View.VISIBLE);
            Picasso.get().load(url).placeholder(R.drawable.ic_image_black_24dp).into(postImageView);
            postImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(itemView.getContext(), PostDetailsActivity.class);
                    i.putExtra("id",id);
                    ActivityOptionsCompat options = makeSceneTransitionAnimation(activity,postImageView, "image");
                    activity.startActivity(i, options.toBundle());

                }
            });
        }
    }
    public void setLikeButton(final boolean clicked, final DatabaseReference likeRef, final String uid){
        postLikeButton.setChecked(clicked);
        postLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postLikeButton.isChecked()){
                    likeRef.child(uid).child("time").setValue(ServerValue.TIMESTAMP).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postLikeButton.setChecked(!postLikeButton.isChecked());
                        }
                    });
                }else{
                    likeRef.child(uid).removeValue().addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postLikeButton.setChecked(!postLikeButton.isChecked());
                        }
                    });
                }
            }
        });
    }
    public void setCommentButton(final AppCompatActivity activity, final String postId){
        commentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, CommentsActivity.class).putExtra("post",postId));
            }
        });
    }
    public void setLikesText(long likesCount){
        likesText.setText(String.valueOf(likesCount));
    }
}
