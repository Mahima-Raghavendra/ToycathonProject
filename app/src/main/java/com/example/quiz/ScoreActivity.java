package com.example.quiz;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ScoreActivity extends AppCompatActivity {

    Bundle bundle;
    ArrayList<ArrayList<String>> urlList = new ArrayList<>();
    TextView score_view;
    ArrayList<ArrayList<String>> score_list;
    int score,here_level;
    Button prize, next;
    private int PERMISSION_STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final KonfettiView konfettiView = findViewById(R.id.konfettiView);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.GRAY)
                .setDirection(270.0, 270.0)
                .setSpeed(2f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(1000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(8,1f))
                .setPosition(0f,konfettiView.getWidth()+1500f, -20f,-20f)
                .streamFor(300,5000L);

        bundle = getIntent().getExtras();
        here_level = (int) bundle.get("level");
        score = (int) bundle.get("scores");
        //score = Integer.parseInt(score_list.get(here_level).get(0));
        score_view = findViewById(R.id.score);
        score_view.setText(String.valueOf(score*5)+ " / 20");
        //score_view.setText(String.valueOf(20));
        //here_level = 1;
        prize = findViewById(R.id.prize_btn);
        next = findViewById(R.id.next_btn);
        for(int i=0;i<6;i++){
            urlList.add(new ArrayList<String>());
        }

        urlList.get(0).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/001-HIDE-AND-SEEK-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589846897");
        urlList.get(1).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/002-GINGER-THE-GIRAFFE-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589846892");
        urlList.get(2).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/003-DOING_MY_CHORES-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589846895");
        urlList.get(3).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/004-ABE-THE-SERVICE-DOG-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589884748");
        urlList.get(4).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/005-SUNNY-MEADOWS-WOODLAND-SCHOOL-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589846892");
        urlList.get(5).add(0,"https://cdn.shopify.com/s/files/1/2081/8163/files/006-TOOTH-FAIRY-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589884746");

        /*prize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String permissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        requestPermissions(new String[]{permissions}, PERMISSION_STORAGE_CODE);
                    }
                    else {
                        startDownloading();
                    }
                }
                else {
                    startDownloading();
                }
            }
        });*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreActivity.this, QuizActivity.class));
            }
        });
        prize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlList.get(here_level).get(0).trim();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                String title = URLUtil.guessFileName(url,null,null);
                request.setTitle("Download");
                request.setDescription("Downloading file...");
                String cookie = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);

                DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(ScoreActivity.this,"Downloading started", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*private void startDownloading() {
        String url = urlList.get(here_level).get(0).trim();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI| DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+ System.currentTimeMillis());

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_STORAGE_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startDownloading();
            }
            else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:
                {
                    if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        startDownloading();
                    }
                    else{
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }*/

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //finish();
        Intent new_intent = new Intent(ScoreActivity.this, MainActivity.class);
        startActivity(new_intent);
        return;
    }
}
