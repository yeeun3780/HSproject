package com.hausung.hstuting;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MypageActivity extends Fragment {

    private ArrayList<String> rankingData = new ArrayList<>();
    private Spinner rankSpin;
    private TextView userid;
    private EditText timeset;



    @Override
    public View onCreateView(LayoutInflater inflater, @com.google.firebase.database.annotations.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_mypage , container, false);
        View v = inflater.inflate(R.layout.activity_mypage, container, false);

        //튜터링 수준 데이터 추가
        rankingData.add("매우 낮음");
        rankingData.add("낮음");
        rankingData.add("보통");
        rankingData.add("높음");
        rankingData.add("매우 높음");

        //튜터링 수준 spinner 초기화
        rankSpin = (Spinner) v.findViewById(R.id.rank_spin);
        ArrayAdapter<String> adapterRank = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, rankingData);
        adapterRank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rankSpin.setAdapter(adapterRank);





        return rootView;
    }


}
