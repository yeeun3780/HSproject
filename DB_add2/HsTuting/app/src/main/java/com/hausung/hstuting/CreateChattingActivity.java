package com.hausung.hstuting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

// 버튼 누르면 채팅방 개설 정보 입력할 수 있게하는 클래스
public class CreateChattingActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText subject;
    private EditText eachClass;
    private EditText date;
    private EditText time;
    private EditText tutor;
    private String subjectString;
    private String eachClassString;
    private String dateString;
    private String timeString;
    private String tutorString;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chatting);

        db.collection("chattingRoom")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {

                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }

                        count = value.size();
                        }
                });

        //버튼 클릭시 입력 정보 받아와 데이터베이스에 저장
        findViewById(R.id.register).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //컬렉션 가져오기
                        subject=findViewById(R.id.subject);
                        eachClass=findViewById(R.id.eachClass);
                        date=findViewById(R.id.date);
                        time=findViewById(R.id.time);
                        tutor=findViewById(R.id.tutor);
                        subjectString=subject.getText().toString();
                        eachClassString=eachClass.getText().toString();
                        dateString=date.getText().toString();
                        timeString=time.getText().toString();
                        tutorString=tutor.getText().toString();
                        //eachTutoring은 데이터베이스 문서에 subject와 tutor값을 넣어준다. 각각은 또 맵으로 구현해서 넣어줘야 한다
                        Map<String,String> eachTutoring=new HashMap<>();
                        eachTutoring.put("subject",subjectString);
                        eachTutoring.put("eachClass",eachClassString);
                        eachTutoring.put("date",dateString);
                        eachTutoring.put("time",timeString);
                        eachTutoring.put("tutor",tutorString);
                        String newCount = String.format("%03d",count + 1);
                        //문서 newCount붙여서 새로 추가
                        db.collection("chattingRoom").document("tutoring"+ newCount)
                                .set(eachTutoring)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG", "DocumentSnapshot successfully written!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("TAG", "Error writing document", e);
                                    }
                                });
                        finish();
                    }
                }
        );
    }

}
