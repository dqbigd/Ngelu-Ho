package com.example.ngeluho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ngeluho.adapter.FindAdapter;
import com.example.ngeluho.FindModel;
import com.example.ngeluho.R;

import java.util.ArrayList;

public class FindTargetActivity extends AppCompatActivity {
    private RecyclerView rvFind;
    private ArrayList<FindModel> list;
    private String[] data_nama;
    private String[] data_desc;
    private TypedArray data_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_target);

        ImageButton imgButton = (ImageButton) findViewById(R.id.imgBackButton);

        prepare();
        rvFind = findViewById(R.id.rvFind);
        recyclerProperties();


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void recyclerProperties() {
        rvFind.setLayoutManager(new LinearLayoutManager(this));
        FindAdapter findAdapter = new FindAdapter(list);
        rvFind.setAdapter(findAdapter);
    }

    private void prepare() {
        data_nama = getResources().getStringArray(R.array.data_title_find);
        data_desc = getResources().getStringArray(R.array.data_price_find);
        data_photo = getResources().obtainTypedArray(R.array.data_photo_find);

        list = new ArrayList<>();

        for (int i = 0 ; i<data_nama.length; i++){
            FindModel findModel = new FindModel();

            findModel.setTitle(data_nama[i]);
            findModel.setDescrib(data_desc[i]);
            findModel.setPhoto(data_photo.getResourceId(i, -1));
            list.add(findModel);
        }
    }
}
