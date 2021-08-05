package com.example.myapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_account_circle_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_directions_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_share_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_contact_support_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1 :
                        fragment = new UserFragment();
                        break;
                    case 2 :
                        fragment = new EstimateFragment();
                        break;
                    case 3 :
                        fragment = new HomeFragment();
                        break;
                    case 4 :
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String Body = "Share The App";
                        String Sub = "We Appreciate Your Love And Support Over Us! To Spread This, You Could Follow The Link : https://autorichshawfaremeter.com";
                        intent.putExtra(Intent.EXTRA_TEXT, Body);
                        intent.putExtra(Intent.EXTRA_TEXT, Sub);
                        startActivity(Intent.createChooser(intent, "Share Via"));
                        fragment = new HomeFragment();
                        break;
                    case 5 :
                        fragment = new AboutFragment();
                        break;
                }
                loadFragement(fragment);
            }
        });
//        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(3,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if(item.getId() == 1) {
                    Toast.makeText(MainActivity.this, "User Details", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 2) {
                    Toast.makeText(MainActivity.this, "Estimate Fare", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 3) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 4) {
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 5) {
                    Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if(item.getId() == 1) {
                    Toast.makeText(MainActivity.this, "User Details", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 2) {
                    Toast.makeText(MainActivity.this, "Estimate Fare", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 3) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 4) {
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                }
                else if(item.getId() == 5) {
                    Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadFragement(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
    }
}