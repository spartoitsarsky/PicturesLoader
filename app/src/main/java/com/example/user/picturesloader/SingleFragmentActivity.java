package com.example.user.picturesloader;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 13.09.2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    @LayoutRes
    private int getResId() {
        return R.layout.acitivtiy_with_one_fragment;
    }

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment==null){
            fragment=createFragment();
            fm.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }
}
