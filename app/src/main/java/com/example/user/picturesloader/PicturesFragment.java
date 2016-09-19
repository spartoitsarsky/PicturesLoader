package com.example.user.picturesloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 13.09.2016.
 */
public class PicturesFragment extends Fragment {
    RecyclerView mRecyclerView;
    final static String TAG = "PicturesFragment";
    List<Picture> mPictures;
    ThumbnailDownloader<PictureHolder> mThumbnailDownloader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Downloader downloader= new Downloader();
        downloader.execute();
        mThumbnailDownloader= new ThumbnailDownloader<>();
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pictures_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.pictures_fragment_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        Log.e(TAG, "On createView    ");
        Log.e(TAG, "On create b4 Downloader");

        return v;
    }

    public static PicturesFragment newInstance() {
        Log.e(TAG, "PicturesFragment newInstance");
        Bundle args = new Bundle();

        PicturesFragment fragment = new PicturesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class Downloader extends AsyncTask<Void, Void, List<Picture>> {
        @Override
        protected void onPreExecute() {
            Log.e(TAG, "On pre execute");
            super.onPreExecute();
        }

        @Override
        protected List<Picture> doInBackground(Void... params) {
            PicturesLab lab = new PicturesLab();
            return lab.fetchItems();
        }

        @Override
        protected void onPostExecute(List<Picture> pictures) {
            mPictures=pictures;
            setupAdapter(mPictures);
        }
    }

    public class PicturesAdapter extends RecyclerView.Adapter<PictureHolder> {
        List<Picture> mPictures;

        public PicturesAdapter(List pictures) {
            mPictures = pictures;
        }

        @Override
        public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PictureHolder(new TextView(getActivity()));
        }

        @Override
        public void onBindViewHolder(PictureHolder holder, int position) {
            mThumbnailDownloader.enqueueDownload(holder,mPictures.get(position).getUrl());
            holder.bindUrl(mPictures.get(position).getUrl());
        }

        @Override
        public int getItemCount() {
            return mPictures.size();
        }
    }

    public class PictureHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public PictureHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }

        public void bindUrl(String url) {
            mTextView.setText(url);
        }
    }
    public void setupAdapter(List<Picture> pics){
        mPictures=pics;
        mRecyclerView.setAdapter(new PicturesAdapter(mPictures));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
    }
}
