package com.hausung.hstuting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TutingChooseActivity extends AppCompatActivity {
    Button hansungBtn, departmentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuting_choose);
        initializeViews();

        hansungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutingChooseActivity.this, HansungTutingActivity.class);
                startActivity(intent);
            }
        });
        departmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutingChooseActivity.this, DepartmentTutingActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initializeViews() {
        hansungBtn = findViewById(R.id.hansung_tuting);
        departmentBtn = findViewById(R.id.department_tuting);
    }
}