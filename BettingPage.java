package com.example.bet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.Intent;
import android.icu.number.NumberRangeFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.bet.Admin_Game_Adapter.BANNER;
import static com.example.bet.Admin_Game_Adapter.GAME_ID;

public class BettingPage extends AppCompatActivity {


    public CardView matchCard;
    ImageView gamebanner;
    TabLayout tabLayout;
    ViewPager2 pager2;
    bettingpage_tabviewer adapter;
//    private RecyclerView recyclerView;
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
    private MatchAdapter matchAdapter;
    private ArrayList<MatchModel> gList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting_page);


        String gameid = getIntent().getStringExtra(GAME_ID);
        String banner = getIntent().getStringExtra(BANNER);





//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("Match").child(gameid);

        gamebanner = findViewById(R.id.gamebanner);
        tabLayout = findViewById(R.id.bettingtabs);
        pager2 = findViewById(R.id.bettingpagetab);

//        getIncomingint();

//        matchCard = findViewById(R.id.matchdetail);
//        recyclerView = findViewById(R.id.recyclerview);
//        gList = new ArrayList<>();

        Glide.with(this).load(banner).into(gamebanner);
//


        FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        final matchtab myFragment = new matchtab();
        adapter = new bettingpage_tabviewer(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Match Tab"));
        tabLayout.addTab(tabLayout.newTab().setText("Result Tab"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



        // Getting Intent

//        gamebanner = findViewById(R.id.gamebanner);
//        Bundle bundle = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle.getInt("pubgm");
//            gamebanner.setImageResource(resID);
//        }
//        gamebanner2 = findViewById(R.id.gamebanner2);
//        Bundle bundle1 = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle1.getInt("ff");
//            gamebanner2.setImageResource(resID);
//        }
//        gamebanner3 = findViewById(R.id.gamebanner3);
//        Bundle bundle2 = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle2.getInt("codm");
//            gamebanner3.setImageResource(resID);
//        }
//        gamebanner4 = findViewById(R.id.gamebanner4);
//        Bundle bundle3 = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle3.getInt("valorant");
//            gamebanner4.setImageResource(resID);
//        }
//        gamebanner5 = findViewById(R.id.gamebanner5);
//        Bundle bundle4 = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle4.getInt("fortnite");
//            gamebanner5.setImageResource(resID);
//        }
//        gamebanner6 = findViewById(R.id.gamebanner6);
//        Bundle bundle5 = getIntent().getExtras();
//        if(bundle!=null){
//            int resID = bundle5.getInt("dota2");
//            gamebanner6.setImageResource(resID);
//        }

    }

//private void getIncomingint(){
//    if (getIntent().hasExtra("banner")){
//        String banner = getIntent().getStringExtra("banner");
//        setImage(banner);
//    }
//
//}
//    private  void setImage(String banner){
//        Glide.with(this)
//                .load(banner)
//                .into(gamebanner);
    }

