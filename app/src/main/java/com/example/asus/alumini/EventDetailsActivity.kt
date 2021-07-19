package com.example.asus.alumini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.asus.alumini.pojo.Notice
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val notice:Notice = intent.getParcelableExtra("event")
        eventName.text="Name : ${notice.title}"
        eventVenue.text="Venue : ${notice.body}"
        Picasso.get().load(notice.link).into(eventPhoto)
        supportActionBar?.title = "Event Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}
