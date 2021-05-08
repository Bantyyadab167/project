package com.example.bet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameAdapter extends ArrayAdapter<GamesM> {

    private Activity context;
    private List<GamesM> gameList;

    public GameAdapter(Activity context,ArrayList<GamesM> gameList){
        super(context,R.layout.gametile);
        this.gameList = gameList;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();

        View gameView = inflater.inflate(R.layout.gametile,null,true);

        ShapeableImageView banner = (ShapeableImageView) gameView.findViewById(R.id.gametile);

        GamesM gamesM = gameList.get(position);

        Glide.with(context).load(gameList.get(position).getBanner()).into(banner);

        return gameView;

    }
}