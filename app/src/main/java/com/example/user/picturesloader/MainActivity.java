package com.example.user.picturesloader;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends SingleFragmentActivity {
    final String TAG="MainActivity";


    @Override
    public Fragment createFragment() {
        return PicturesFragment.newInstance();
    }




}
