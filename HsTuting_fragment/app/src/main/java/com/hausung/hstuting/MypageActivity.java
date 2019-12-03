package com.hausung.hstuting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.annotations.Nullable;

public class MypageActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @com.google.firebase.database.annotations.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_mypage , container, false);
        View v = inflater.inflate(R.layout.activity_mypage, container, false);
        return rootView;
    }
}
