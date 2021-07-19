package com.example.asus.alumini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.asus.alumini.adapter.UserAdapter;
import com.example.asus.alumini.pojo.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity {
    private EditText searchText;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchText=findViewById(R.id.searchText);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapter("");
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text=charSequence.toString();
                setAdapter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter(String text) {
        FirebaseRecyclerOptions<User> options=new FirebaseRecyclerOptions.Builder<User>().setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("name").startAt(text),User.class).build();
        if(adapter!=null)
            adapter.stopListening();
        adapter=new UserAdapter(SearchActivity.this,options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapter!=null)
            adapter.stopListening();
    }
}
