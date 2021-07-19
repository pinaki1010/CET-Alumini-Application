package com.example.asus.alumini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asus.alumini.adapter.EventAdapter;
import com.example.asus.alumini.adapter.NoticeAdapter;
import com.example.asus.alumini.pojo.Notice;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsActivity extends AppCompatActivity {
    private RecyclerView eventList;
    private EventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventList=findViewById(R.id.allEventsList);
        eventList.setHasFixedSize(true);
        eventList.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupRecyclerView();

    }
    private void setupRecyclerView() {
        DatabaseReference eventRef= FirebaseDatabase.getInstance().getReference().child("event");
        FirebaseRecyclerOptions<Notice> options=new FirebaseRecyclerOptions.Builder<Notice>().setQuery(eventRef,Notice.class).build();
        adapter=new EventAdapter(this, options);
        eventList.setAdapter(adapter);

        adapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
}
