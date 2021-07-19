package com.example.asus.alumini;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.asus.alumini.adapter.PostAdapter;
import com.example.asus.alumini.pojo.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView postList;
    private PostAdapter adapter;
    private BottomAppBar bottomAppBar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        postList=findViewById(R.id.postList);
        bottomAppBar=findViewById(R.id.bottom_app_bar);
        toolbar=findViewById(R.id.toolBar);
        toolbar.setTitle("CET Alumni");
        getMenuInflater().inflate(R.menu.top_menu,toolbar.getMenu());
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_profile:
                        startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
                        break;
                    case R.id.action_share:
                        final String appPackageName = getPackageName();
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Download the CET Alumni app from https://play.google.com/store/apps/details?id=" + appPackageName;
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share us");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    default:
                        new AlertDialog.Builder(HomeActivity.this)
                                .setMessage("Do you really want to logout ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                                        finish();
                                    }
                                }).setNegativeButton("No,Don't",null)
                                .create()
                                .show();

                }
                return true;
            }
        });
        setSupportActionBar(bottomAppBar);
        postList.setHasFixedSize(true);
        postList.setLayoutManager(new LinearLayoutManager(this));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        DatabaseReference postRef= FirebaseDatabase.getInstance().getReference().child("post");
        FirebaseRecyclerOptions<Post> options=new FirebaseRecyclerOptions.Builder<Post>().setQuery(postRef,Post.class).build();
        adapter=new PostAdapter(this, FirebaseAuth.getInstance().getCurrentUser().getUid(),options);
        postList.setAdapter(adapter);

        adapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                startActivity(new Intent(this,SearchActivity.class));
                break;
            case R.id.action_notice:
                startActivity(new Intent(this,NoticeActivity.class));
                break;
            default:
                startActivity(new Intent(this,EventsActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }

    public void gotoAddPost(View view) {
        startActivity(new Intent(this,AddPostActivity.class));
    }
}
