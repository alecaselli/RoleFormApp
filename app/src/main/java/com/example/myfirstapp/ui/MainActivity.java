package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    private ImageButton createNewCharacter;
    private ImageButton editItems;
    private ImageButton info;

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

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager((this));
        mAdapter=new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener (new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openCharacterActivity();
            }
        });

        createNewCharacter = (ImageButton) findViewById(R.id.create);
        createNewCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateNewCharacterActivity();
            }
        });
    }

    public void openCharacterActivity(){
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
    }

    public void openCreateNewCharacterActivity(){
        Intent intent =new Intent (this, CreateNewCharacterActivity.class);
        startActivity(intent);
    }

}