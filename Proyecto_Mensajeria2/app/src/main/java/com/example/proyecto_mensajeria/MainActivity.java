package com.example.proyecto_mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewpager;
    private TabLayout myTablayout;
    private TabsAccessorAdapter myTabsAccesorAdapter;

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser =mAuth.getCurrentUser();

        myTablayout = findViewById(R.id.tableLayout);
        myViewpager = findViewById(R.id.viewpager);
        myTablayout.setupWithViewPager(myViewpager);
        TabsAccessorAdapter vpAdpter = new TabsAccessorAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdpter.addFragment(new ChatsFragment(), "Chats");
        vpAdpter.addFragment(new GroupsFragment(), "Groups");
        vpAdpter.addFragment(new ContactsFragment(), "Contacts");

        myViewpager.setAdapter(vpAdpter);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Onitech Hub :3");


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser == null){
            SendUserToLoginActivity();
        }
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent =  new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);

    }
}