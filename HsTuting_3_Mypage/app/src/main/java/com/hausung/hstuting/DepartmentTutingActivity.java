package com.hausung.hstuting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DepartmentTutingActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_tuting);
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(this);
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.relative_layt_btm_nav, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.navigation_list:
                Intent intent = new Intent(DepartmentTutingActivity.this,ChattingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.navigation_chat:
                Intent intent2= new Intent(DepartmentTutingActivity.this, ChatRoomActivity.class);
                startActivity(intent2);
                return true;
            case R.id.navigation_account:
                Intent intent3 = new Intent(DepartmentTutingActivity.this, MypageActivity.class);
                startActivity(intent3);
                return true;
        }
        return false;
    }
}