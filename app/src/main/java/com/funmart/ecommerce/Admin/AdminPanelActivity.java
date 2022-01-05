package com.funmart.ecommerce.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.funmart.ecommerce.Fragments.AdminHomeFragment;
import com.funmart.ecommerce.Fragments.AdminVerifyProductsFragment;
import com.funmart.ecommerce.Fragments.AdminViewSellersFragment;
import com.funmart.ecommerce.Fragments.AdminViewUsersFragment;
import com.funmart.ecommerce.Fragments.OrdersFragment;
import com.funmart.ecommerce.R;
import com.funmart.ecommerce.StartActivity;
import com.funmart.ecommerce.User.UserMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class AdminPanelActivity extends AppCompatActivity {


    private final String TAG ="theartist";
    DatabaseReference mRootRef;
    FirebaseAuth mAuth;
    //Texte

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AdminHomeFragment()).commit();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectorFragment = new AdminHomeFragment();
                        break;
                    case R.id.nav_order:
                        selectorFragment = new OrdersFragment();
                        break;
                    case R.id.nav_product:
                        selectorFragment = new AdminVerifyProductsFragment();
                        break;
                    case R.id.nav_seller:
                       selectorFragment = new AdminViewSellersFragment();
                        break;
                    case R.id.nav_user:
                    selectorFragment = new AdminViewUsersFragment();
                        break;
                }
                if (selectorFragment !=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectorFragment).commit();
                }
                return true;
            }
        });

//
//        Bundle intent = getIntent().getExtras();
//        if(intent!=null){
//            String profileId = intent.getString("publisherId");
//            getSharedPreferences("PROFILE",MODE_PRIVATE).edit().putString("profileId",profileId).apply();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
//            bottomNavigationView.setSelectedItemId(R.id.person);
//        }else {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//        }

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type", "none");
        editor.apply();

        startActivity(new Intent(AdminPanelActivity.this, StartActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}


//        total orders this month
//        current online users
//todays slaes and transition hightligts