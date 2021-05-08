package com.example.bet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Games extends AppCompatActivity {



    private RecyclerView   recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Games Tile");
    private GameTileHolder adapter;
    private ArrayList<GameModel> gList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // Firebase Settings

        recyclerView = findViewById(R.id.gamesrecyclerview);
        gList = new ArrayList<>();


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GameModel model = dataSnapshot.getValue(GameModel.class);
                    gList.add( new GameModel(model.banner,model.gameid));

                    Log.i("THE_CURRENT_USER:::", model.toString());
                }
                LinearLayoutManager im = new LinearLayoutManager((Games.this));
                recyclerView.setLayoutManager(im);
                adapter = new GameTileHolder(Games.this,gList);
                recyclerView.setAdapter(adapter);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.showShimmer = false;
                        adapter.notifyDataSetChanged();
                    }
                },1000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}