package com.example.waste_drug;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.security.MessageDigest;

public class MainActivity extends FragmentActivity {

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }



    TabLayout tabs;

    waste_drug_box fragment1;
    business_time fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getAppKeyHash();
        
        //프래그먼트 생성
        fragment1 = new waste_drug_box();
        fragment2 = new business_time();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        //탭바 추가
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("폐의약품 수거함"));
        tabs.addTab(tabs.newTab().setText("약국 운영 시간"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //프래그먼트 전환
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0)
                    selected = fragment1;
                else if (position == 1)
                    selected = fragment2;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}