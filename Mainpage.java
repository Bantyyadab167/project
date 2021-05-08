package com.example.bet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Mainpage extends AppCompatActivity {

    public ImageView searcnbtn,ac,matches,games,leaderboard,livestream,newsfeed;
    public TextView fullName,verifymsg;
    public Button resendCode,setImg,uploadImg;
    public CardView wallet;
    ImageSlider newsfeedslider;
    // Firebase Details
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first = databaseReference.child("Newsfeed");
//    private DatabaseReference root =  FirebaseDatabase.getInstance().getReference("Images");
//    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String UserId;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        ac = (ImageView) findViewById(R.id.acbtn);
//        newsfeed = (ImageView) findViewById(R.id.newsfeedbtn);
        matches = (ImageView) findViewById(R.id.matchesbtn);
        games = (ImageView) findViewById(R.id.gamesbtn);
        leaderboard = (ImageView) findViewById(R.id.leaderbtn);
        livestream = (ImageView) findViewById(R.id.livestreambtn);
        fullName = findViewById(R.id.fullname);
        verifymsg = findViewById(R.id.verficationtxt);
        resendCode = findViewById(R.id.verifybtn);
        wallet = findViewById(R.id.wallet);
        newsfeedslider = findViewById(R.id.newsfeedslider);
        // Firestore Details
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        storageReference = FirebaseStorage.getInstance().getReference();
        final FirebaseUser fuser = mAuth.getCurrentUser();
        UserId = mAuth.getCurrentUser().getUid();

        if (!fuser.isEmailVerified()) {
            verifymsg.setVisibility(View.VISIBLE);
            resendCode.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification Link has been sent to your email", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: ContactsContract.CommonDataKinds.Email not sent" + e.getMessage());
                        }
                    });
                }
            });
        }
        DocumentReference documentReference = fStore.collection("users").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fullName.setText(documentSnapshot.getString("fullname"));
            }
        });



        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this, Admin.class);
                startActivity(intent);
            }
        });
        matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainpage.this, Matches.class);
                startActivity(intent);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this, Games.class);
                startActivity(intent);
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this, Match.class);
                startActivity(intent);
            }
        });
        livestream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this, Match.class);
                startActivity(intent);
            }
        });
//        newsfeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(Mainpage.this,Newsfeed.class);
//                startActivity(intent);
//            }
//        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainpage.this, Admin_Add_Match.class);
                startActivity(intent);
            }
        });

        // Image Slider

        ArrayList<SlideModel> images =  new ArrayList<>();
        images.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/esports-f0b71.appspot.com/o/Newsfeed%2Fmatches.png?alt=media&token=372d8573-7d22-480c-b684-8eb1da5bd13e",null));
        images.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/esports-f0b71.appspot.com/o/news1.jpeg?alt=media&token=c5e28514-ce70-4ab1-b88e-95e5793ce4c0",null));
        images.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/esports-f0b71.appspot.com/o/Newsfeed%2Flivestream.png?alt=media&token=8e7a6fc1-4248-40cb-9df4-ce5af2710d27",null));

        newsfeedslider.setImageList(images);

    }
}