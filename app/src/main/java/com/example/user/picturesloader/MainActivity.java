package com.example.user.picturesloader;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return PicturesFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Downloader().execute();

    }

    private class Downloader extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            PicturesLab lab = new PicturesLab();
            lab.fetchItems();
            return null;
        }
    }
}
