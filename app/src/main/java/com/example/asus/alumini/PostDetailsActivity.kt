package com.example.asus.alumini

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {
    lateinit var postId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        //ActivityCompat.postponeEnterTransition(this)
        postId=intent.getStringExtra("id")
        FirebaseDatabase.getInstance().reference.child("post").child(postId).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
                val caption=data.child("caption").value.toString()
                val photo=data.child("photo").value.toString()
                val user=data.child("user").value.toString()
                val likes=data.child("likes").childrenCount
                val isLiked=data.child("likes").hasChild(user)
                FirebaseDatabase.getInstance().reference.child("users").child(user).addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val name=p0.child("name").value.toString()
                        val dp=p0.child("photo").value.toString()
                        postCaption.text=caption
                        Picasso.get().load(photo).into(postImage)
                        feedLikesCount.text=likes.toString()
                        postLikeButton.isChecked=isLiked
                        postCommentButton.setOnClickListener {
                            startActivity(Intent(this@PostDetailsActivity,CommentsActivity::class.java).putExtra("post",postId))
                        }
                        postName.text=name
                        Picasso.get().load(dp).into(postDp)
                    }

                })
            }

        })
    }
}
