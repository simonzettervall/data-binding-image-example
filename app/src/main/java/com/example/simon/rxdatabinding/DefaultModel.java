package com.example.simon.rxdatabinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class DefaultModel extends BaseObservable {
    @Bindable
    private String mTitle;

    @Bindable
    private String mUrl;

    public DefaultModel(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    @BindingAdapter("bind:loadImage")
    public static void loadImageAsync(ImageView imageView, String url) {
        LoadImageTask task = new LoadImageTask(url, imageView);
        task.execute();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;

        notifyPropertyChanged(com.example.simon.rxdatabinding.BR.title);
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;

        notifyPropertyChanged(com.example.simon.rxdatabinding.BR.url);
    }
}