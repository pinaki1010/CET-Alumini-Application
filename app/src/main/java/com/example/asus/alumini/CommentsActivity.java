package com.example.asus.alumini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.alumini.adapter.CommentAdapter;
import com.example.asus.alumini.pojo.Comment;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {
    private String postId;
    private RecyclerView commentsList;
    private DatabaseReference commentsRef;
    private EditText commentText;
    private String myId;
    private CommentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        postId=getIntent().getStringExtra("post");
        commentText=findViewById(R.id.comment_text);
        myId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        commentsList=findViewById(R.id.commentsList);
        commentsRef= FirebaseDatabase.getInstance().getReference().child("post").child(postId).child("comments");
        FirebaseRecyclerOptions<Comment> options=new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(commentsRef,Comment.class)
                .build();
        adapter=new CommentAdapter(options,this, myId,postId);
        commentsList.setHasFixedSize(true);
        commentsList.setLayoutManager(new LinearLayoutManager(this));
        commentsList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopListening();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void postComment(View view) {
        final String comment=commentText.getText().toString().trim();
        if(!comment.isEmpty()){
            Map<String,Object> map=new HashMap<>();
            map.put("user",myId);
            map.put("time", ServerValue.TIMESTAMP);
            map.put("comment",comment);
            commentText.setText("");
            commentsRef.push().updateChildren(map).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CommentsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    commentText.setText(comment);
                    commentText.findFocus();
                }
            });
        }
    }
}
