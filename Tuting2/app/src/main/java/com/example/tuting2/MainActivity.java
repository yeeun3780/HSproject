package com.example.tuting2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; //로그메시지 tag

    //view 및 btn 선언
    private TextView textView;
    private EditText editText;
    private Button button;

    String test;

    DatabaseReference conditionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id 정의
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        button = (Button) findViewById(R.id.button);

        // 버튼 클릭시 작동
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // conditionRef에 setValue를 해줘서 버튼 클릭시마다 설정해 둔 경로 안으로 editText 값이 업데이트 됨
                //conditionRef.setValue(editText.getText().toString());
                test=editText.getText().toString();

                HashMap result = new HashMap<>();
                result.put("text",test);

                // firebase 정의
                conditionRef = FirebaseDatabase.getInstance().getReference();
                // firebase에 저장
                conditionRef.child("text").push().setValue(result);
            }
        });



    }

    //이 이후의 코드는 상위 코드 작동 시 처리 할 코드입니다 여기까진 안봐주셔도 됩니다.

/*
    //데이터 변화를 알기위한 onStart() 메서드
    @Override
    protected void onStart() {
        super.onStart();

        // child로 설정해준 경로 안의 데이터 상태 변화 시 작동하는 .addValueEventListener()
        conditionRef.addValueEventListener(new ValueEventListener() {

            // 데이터 값이 변했을 때마다 작동
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // text 안에 받아온 문자열을 넣어줌
                String text = dataSnapshot.getValue(String.class);
                // 선언해둔 textView의 text를 이 문자열로 바꿔줌
                // 데이터의 값을 입력해 데이터가 들어올 때마다 textView의 텍스트가 바뀜
                textView.setText(text);
                //Log.i(TAG,"data changed");
            }

            // 에러 발생 시 작동
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.d(TAG,databaseError.getMessage());
            }
        });

        // 버튼 클릭시 작동
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // conditionRef에 setValue를 해줘서 버튼 클릭시마다 설정해 둔 경로 안으로 editText 값이 업데이트 됨
                conditionRef.setValue(editText.getText().toString());
            }
        });
    }
*/
}
