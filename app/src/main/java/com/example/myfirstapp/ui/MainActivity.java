package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.ExampleItem;
import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.ExampleAdapter;

import java.util.ArrayList;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, "Campaign name", "Character name", "Level"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager((this));
        mAdapter=new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openCharacterActivity();
            }
        });
    }

    public void openCharacterActivity(){
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
    }

}