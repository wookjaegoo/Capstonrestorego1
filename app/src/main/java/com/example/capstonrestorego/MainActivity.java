package com.example.capstonrestorego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.capstonrestorego.Fragment.HomeFragment;
import com.example.capstonrestorego.Model.User;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

//일단 스마트컨트랙트 부터 해결하자

    Fragment selectFragment = null;
    static  Bundle bundle=new Bundle();
    static Intent intent1=new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView navigationBarView= findViewById(R.id.bottom_navigation);

        intent1=getIntent();
        User userinfo= (User)intent1.getSerializableExtra("userinfo");

        Fragment homefragment;
        homefragment= new HomeFragment();

        if(intent1!=null)
        {

            bundle.putString("userinfo", userinfo.getAddress());
            homefragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homefragment).commit();

        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        }



        navigationBarView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.nav_home:
                    Fragment homefragment1;
                    homefragment1= new HomeFragment();
                    homefragment1.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homefragment1).commit();

                    return true;
                case R.id.nav_mint:
                    selectFragment = null;
                    Intent intent = new Intent(MainActivity.this,PostNftActivity.class);
                    intent.putExtra("userinfo",userinfo);
                    startActivity(intent);
//                    startActivity (new Intent( MainActivity.this, PostNftActivity.class));
//                case R.id.nav_wallet:
//                    selectFragment = null;
//                    startActivity (new Intent( MainActivity.this, WalletActivity.class));



            }
            if (selectFragment !=null)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();

            }
            return false;
        });
    }
}