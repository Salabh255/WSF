package com.example.wsf;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.HashSet;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationClient;
    String myLocation = "", numberCall;
    SmsManager manager = SmsManager.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
        boolean isWelcomeToastShown = sharedPreferences.getBoolean("isWelcomeToastShown", false);

        if (!isWelcomeToastShown) {
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

            // Update the flag to indicate that the toast has been shown
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isWelcomeToastShown", true);
            editor.apply();
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        CardView contact = findViewById(R.id.first);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
            }
        });

        CardView sms = findViewById(R.id.second);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, smsActivity.class));
            }
        });

        CardView laws = findViewById(R.id.third);
        laws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LawsActivity.class));
            }
        });

        CardView defense = findViewById(R.id.fourth);
        defense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SelfDefenseActivity.class));
            }
        });

        CardView commerce = findViewById(R.id.fifth);
        commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, E_CommerceActivity.class));
            }
        });

        CardView voicerecog = findViewById(R.id.sixth);
        voicerecog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,VoiceRecognitionActivity.class));
            }
        });

        Button panic = findViewById(R.id.panicBtn);
        panic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                location.getAltitude();
                                location.getLongitude();
                                myLocation = "http://maps.google.com/maps?q=loc:" + location.getLatitude() + "," + location.getLongitude();
                            } else {
                                myLocation = "Unable to Find Location :(";
                            }
                            sendMsg();
                        });

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                numberCall = sharedPreferences.getString("firstNumber", "None");
                if (!numberCall.equalsIgnoreCase("None")) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + numberCall));
                    startActivity(intent);
                }
            }

            private void sendMsg() {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new HashSet<>());
                if (!oldNumbers.isEmpty()) {
                    for (String ENUM : oldNumbers) {
                        manager.sendTextMessage(ENUM, null, "Hello, I am in Trouble!\nAm sharing my current Location :\n" + myLocation, null, null);
                    }
                }
            }
        });
        ImageView logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Prevent returning back to the home page after logging out
        Toast.makeText(this, "You are logged out", Toast.LENGTH_SHORT).show();
    }
}