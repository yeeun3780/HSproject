package com.hausung.hstuting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ChattingListActivity extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ChattingItemAdapterActivity adapter;
    private Button createChatting;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int count;
    HansungTutingActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (HansungTutingActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //이제 더이상 엑티비티 참초가안됨
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //Toast.makeText(getActivity(),"hello", Toast.LENGTH_SHORT).show();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_chattinglist , container, false);

        //이 코드를 써줘야 한다
        View v = inflater.inflate(R.layout.activity_chattinglist, container, false);
        recyclerView = rootView.findViewById(R.id.chattingList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ChattingItemAdapterActivity(getActivity());

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
                                        doc.getString("date"), doc.getString("time"), doc.getString("tutor")));
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
                Toast.makeText(getActivity(), "해당 채팅방 선택됨 " + item.getsubject(), Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattinglist);
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
                                        doc.getString("date"), doc.getString("time"), doc.getString("tutor")));
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
    }*/
}
