package com.example.simon.rxdatabinding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadImageTask extends AsyncTask<Void, Void, Void> {
    private static final LruCache<String, Bitmap> IMAGE_CACHE;

    static {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;

        IMAGE_CACHE = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    private String mUrl;
    private ImageView mImageView;

    private Bitmap mBitmap;

    public LoadImageTask(String url, ImageView imageView) {
        mUrl = url;
        mImageView = imageView;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (TextUtils.isEmpty(mUrl)) {
            return null;
        }

        try {
            mBitmap = IMAGE_CACHE.get(mUrl);

            if (mBitmap == null) {
                fetchBitmap();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void fetchBitmap() throws IOException {
        URL url = new URL(mUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        mBitmap = BitmapFactory.decodeStream(input);

        IMAGE_CACHE.put(mUrl, mBitmap);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mImageView.setImageBitmap(mBitmap);
    }
}