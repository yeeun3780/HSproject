package com.hausung.hstuting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HansungTutingActivity extends AppCompatActivity {

    ChattingListActivity fragment1;
    ChatRoomActivity fragment2;
    MypageActivity fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hs_tuting);

        fragment1=new ChattingListActivity();
        fragment2=new ChatRoomActivity();
        fragment3=new MypageActivity();

        Button button1 = findViewById(R.id.chattingRoom);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //프래그먼트 추가하거나 할떄는 여러개 명령을 한꺼번에 쓸 수 있으므로
                //beginTransaction을 사용함
                //fragment1를 R.id.container에 넣어달라(add 또는 replace, replace는 기존에있던걸 대체해줌)
                //트랜잭션안에서 수행되는것이므로 마지막에 꼭 commit을 해줘야 실행이된다.
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                /*getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();*/

            }
        });

        Button button2 = findViewById(R.id.createChatting);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //프래그먼트 추가하거나 할떄는 여러개 명령을 한꺼번에 쓸 수 있으므로
                //beginTransaction을 사용함
                //fragment1를 R.id.container에 넣어달라(add 또는 replace, replace는 기존에있던걸 대체해줌)
                //트랜잭션안에서 수행되는것이므로 마지막에 꼭 commit을 해줘야 실행이된다.
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                /*getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();*/

            }
        });

        Button button3 = findViewById(R.id.profile);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //프래그먼트 추가하거나 할떄는 여러개 명령을 한꺼번에 쓸 수 있으므로
                //beginTransaction을 사용함
                //fragment1를 R.id.container에 넣어달라(add 또는 replace, replace는 기존에있던걸 대체해줌)
                //트랜잭션안에서 수행되는것이므로 마지막에 꼭 commit을 해줘야 실행이된다.
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                /*getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();*/

            }
        });

    }
}