package com.hausung.hstuting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class HansungActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ChattingItemAdapterActivity adapter;
    private Button createChatting;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hansung);
        // 리사이클러뷰는 껍데기일뿐 어뎁터를 달아줘야된다.
        recyclerView = (RecyclerView) findViewById(R.id.chattingList);
        // 확장성을 위한 메소드, 일단 그냥 적음
        recyclerView.setHasFixedSize(true);
        //리니어 레이아웃 매니저 사용
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ChattingItemAdapterActivity(getApplicationContext());

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
                        adapter.clear();//clear해줘야 중복 생성 안됨
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("subject") != null) {
                                adapter.addItem(new ChattingItemActivity(doc.getString("subject"), doc.getString("eachClass"),
                                        doc.getString("date"),doc.getString("time"),doc.getString("tutor")));
                            }
                        }
                        //어답터 갱신
                        adapter.notifyDataSetChanged();
                    }
                });

        //어댑터에 연결
        recyclerView.setAdapter(adapter);

        //리스트뷰처럼 구현되어있지 않기 때문에
        //어댑터 클래스에 직접 이벤트 처리 관련 코드 작성
        //setOnItemClickListener라는 이름으로 이벤트 메소드 정의
        adapter.setOnItemClickListener(new ChattingItemAdapterActivity.OnItemClickListener() {
            @Override
            public void onItemClick(ChattingItemAdapterActivity.ViewHolder holder, View view, int position) {
                ChattingItemActivity item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "해당 채팅방 선택됨 " + item.getsubject(), Toast.LENGTH_LONG).show();
            }
        });

        //채팅방 개설 버튼
        findViewById(R.id.createChatting).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(HansungActivity.this, CreateChattingActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
