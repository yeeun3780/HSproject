package com.hausung.hstuting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ChattingListOutlineActivity extends AppCompatActivity {
    String subject;
    String eachClass;
    String date;
    String time;
    String tutor;
    String outline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattinglist_outline);

        Intent intent = new Intent(this.getIntent());
        //intent넘어올 때 같이 넘겨준 값들 받기
        subject=intent.getStringExtra("subject");
        eachClass=intent.getStringExtra("eachClass");
        date=intent.getStringExtra("date");
        time=intent.getStringExtra("time");
        tutor=intent.getStringExtra("tutor");
        outline = intent.getStringExtra("outline");
        //화면에 출력
        TextView subjectView = findViewById(R.id.subject);
        TextView eachClassView = findViewById(R.id.eachClass);
        TextView dateView = findViewById(R.id.date);
        TextView timeView = findViewById(R.id.time);
        TextView tutorView = findViewById(R.id.tutor);
        TextView outlineView = findViewById(R.id.outline);

        subjectView.setText(subject);
        eachClassView.setText(eachClass);
        dateView.setText(date);
        timeView.setText(time);
        tutorView.setText(tutor);
        outlineView.setText(outline);
    }
}
