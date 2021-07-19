package com.example.asus.alumini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asus.alumini.adapter.NoticeAdapter;
import com.example.asus.alumini.adapter.PostAdapter;
import com.example.asus.alumini.pojo.Notice;
import com.example.asus.alumini.pojo.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeActivity extends AppCompatActivity {
    private RecyclerView noticeList;
    private NoticeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        noticeList=findViewById(R.id.noticeList);
        noticeList.setHasFixedSize(true);
        noticeList.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        DatabaseReference noticeRef= FirebaseDatabase.getInstance().getReference().child("notice");
        FirebaseRecyclerOptions<Notice> options=new FirebaseRecyclerOptions.Builder<Notice>().setQuery(noticeRef,Notice.class).build();
        adapter=new NoticeAdapter(this, options);
        noticeList.setAdapter(adapter);

        adapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
}
