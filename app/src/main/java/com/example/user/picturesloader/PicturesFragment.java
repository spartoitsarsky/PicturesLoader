package com.example.user.picturesloader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 13.09.2016.
 */
public class PicturesFragment extends Fragment {
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pictures_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.pictures_fragment_recycler_view);
        return v;
    }

    public static PicturesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PicturesFragment fragment = new PicturesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
