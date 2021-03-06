package com.example.musicstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.musicstore.ui.library.LibraryFragment;
import com.example.musicstore.ui.store.StoreFragment;
import com.example.musicstore.ui.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.musicstore.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,new StoreFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navigation_store:
                    selectedFragment = new StoreFragment();

                    break;
                case R.id.navigation_library:
                    selectedFragment = new LibraryFragment();
                    break;
                case R.id.navigation_user:
                    selectedFragment = new UserFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            navView.setSelectedItemId(R.id.navigation_library);
//            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,new LibraryFragment()).commit();
        }
    }
}