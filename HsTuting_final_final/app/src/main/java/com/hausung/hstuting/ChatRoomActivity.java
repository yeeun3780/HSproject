package com.hausung.hstuting;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

// 버튼 누르면 채팅방 개설 정보 입력할 수 있게하는 클래스
public class ChatRoomActivity extends Fragment {
    private DatabaseReference mDatabase;
    private EditText subject;
    private EditText eachClass;
    private EditText date;
    private EditText time;
    private EditText tutor;
    private EditText outline;
    private String subjectString;
    private String eachClassString;
    private String dateString;
    private String timeString;
    private String tutorString;
    private String outlineString;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int count;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_chatroom , container, false);
        View v = inflater.inflate(R.layout.activity_chatroom, container, false);
        db.collection("chattingRoom")
                .addSnapshotListener((value, e) -> {

                    if (e != null) {
                        Log.w("TAG", "Listen failed.", e);
                        return;
                    }

                    count = value.size();
                });

        //버튼 클릭시 입력 정보 받아와 데이터베이스에 저장
        rootView.findViewById(R.id.register).setOnClickListener(
                v1 -> {
                    //컬렉션 가져오기
                    subject= rootView.findViewById(R.id.subject);
                    eachClass= rootView.findViewById(R.id.eachClass);
                    date= rootView.findViewById(R.id.date);
                    time= rootView.findViewById(R.id.time);
                    tutor= rootView.findViewById(R.id.tutor);
                    outline=rootView.findViewById(R.id.outline);
                    subjectString=subject.getText().toString();
                    eachClassString=eachClass.getText().toString();
                    dateString=date.getText().toString();
                    timeString=time.getText().toString();
                    tutorString=tutor.getText().toString();
                    outlineString=outline.getText().toString();
                    //eachTutoring은 데이터베이스 문서에 값들을 넣어준다. 각각은 또 맵으로 구현해서 넣어줘야 한다
                    Map<String,String> eachTutoring=new HashMap<>();
                    eachTutoring.put("subject",subjectString);
                    eachTutoring.put("eachClass",eachClassString);
                    eachTutoring.put("date",dateString);
                    eachTutoring.put("time",timeString);
                    eachTutoring.put("tutor",tutorString);
                    eachTutoring.put("outline",outlineString);
                    String newCount = String.format("%03d",count + 1);
                    //문서 newCount붙여서 새로 추가
                    db.collection("chattingRoom").document("tutoring"+ newCount)
                            .set(eachTutoring)
                            .addOnSuccessListener(aVoid -> Log.d("TAG", "DocumentSnapshot successfully written!"))
                            .addOnFailureListener(e -> Log.w("TAG", "Error writing document", e));
                    Toast.makeText(getContext(), "튜터링방이 개설되었습니다.", Toast.LENGTH_LONG).show();
                }
        );
        return rootView;
    }

}