package com.example.asus.alumini.viewHolder;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.alumini.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class NoticeViewHolder extends RecyclerView.ViewHolder {
    private TextView titleText,bodyText;
    private CircleImageView linkButton;
    public NoticeViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText=itemView.findViewById(R.id.noticeTitle);
        bodyText=itemView.findViewById(R.id.noticeBody);
        linkButton=itemView.findViewById(R.id.noticeLinkButton);
    }
    public  void setTitle(String title){
        titleText.setText(title);
    }
    public void setBody(String body){
        bodyText.setText(body);
    }
    public void setLinkButton(final String link, final AppCompatActivity activity){
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(link));
                activity.startActivity(i);
            }
        });
    }

}
