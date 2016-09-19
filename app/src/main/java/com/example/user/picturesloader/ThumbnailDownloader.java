package com.example.user.picturesloader;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by user on 19.09.2016.
 */
public class ThumbnailDownloader<T> extends HandlerThread {
    Handler mRequestHandler, mResponseHandler;
    private static final String TAG = "Thumbnail Downloader";


    public ThumbnailDownloader() {
        super(TAG);
    }

    public void enqueueDownload(T object, String url) {
        Log.i(TAG, "enqueued Object");
    }
}
