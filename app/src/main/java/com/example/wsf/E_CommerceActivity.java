package com.example.wsf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class E_CommerceActivity extends AppCompatActivity implements View.OnClickListener{
//Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);

        findViewById(R.id.first).setOnClickListener(this);
        findViewById(R.id.second).setOnClickListener(this);
        findViewById(R.id.third).setOnClickListener(this);
        findViewById(R.id.fourth).setOnClickListener(this);
        findViewById(R.id.fifth).setOnClickListener(this);
        findViewById(R.id.sixth).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.first) {
            String url = "https://www.amazon.in/IMPOWER-Defence-Pepper-Spray-safety/dp/B075KK63FC";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else if (id == R.id.second) {
            String url = "https://www.amazon.com/Stun-Guns/b?ie=UTF8&node=7824770011";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else if (id == R.id.third) {
            String url = "https://www.amazon.in/Victorinox-Huntsman-Swiss-Knife-1-3713/dp/B0001P151W";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else if (id == R.id.fourth) {
            String url = "https://www.amazon.in/baton-self-defence-stick/s?k=baton+self+defence+stick";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }  else if (id == R.id.fifth) {
            String url = "https://www.amazon.com/dart-launcher/s?k=dart+launcher";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }  else if (id == R.id.sixth) {
            String url = "https://www.amazon.in/Tactical-Self-Defense-Flashlight-Ballpoint-Survival/dp/B07S2ZCN8L";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else if (id == R.id.backBtn) {
            onBackPressed();
        }
    }
}