package com.example.asus.alumini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.alumini.pojo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameText,emailText,phoneText,rollText,genderText,batchText,ageText;
    private CircleImageView dp;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameText=findViewById(R.id.profileName);
        emailText=findViewById(R.id.profileEmail);
        phoneText=findViewById(R.id.profilePhone);
        rollText=findViewById(R.id.profileRoll);
        genderText=findViewById(R.id.profileGender);
        ageText=findViewById(R.id.profileAge);
        batchText=findViewById(R.id.profileYear);
        dp=findViewById(R.id.profileDp);
        uid=getIntent().getStringExtra("id");
        if(uid==null){
            uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpProfile();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    private void setUpProfile() {
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    nameText.setText(user.getName());
                    emailText.setText(user.getEmail());
                    phoneText.setText(user.getPhone());
                    genderText.setText(user.getGender());
                    ageText.setText(user.getAge());
                    Picasso.get().load(user.getPhoto()).into(dp);
                    if(user.getType().equals("student")){
                        rollText.setText(user.getRoll());
                        batchText.setVisibility(View.GONE);
                    }else{
                        batchText.setText(user.getYear());
                        rollText.setVisibility(View.GONE);
                    }
                    getSupportActionBar().setTitle(user.getType());
                }else{
                    Toast.makeText(ProfileActivity.this, "null", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
