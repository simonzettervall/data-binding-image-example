package com.example.simon.rxdatabinding;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<DefaultModel> models = createModels();
        String[] urls = new String[]{"https://regmedia.co.uk/2015/08/18/android_6_marshmallow_logo.jpg", "http://images.techhive.com/images/article/2015/05/android-lollipop-nexus-9-1-100583974-primary.idge.png", "http://www.technobaboy.com/wp-content/uploads/Android-KitKat-Logo.jpg"};

        setupRecyclerView(models);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String randomUrl = urls[((int) (Math.random() * urls.length))];
                DefaultModel randomModel = models.get((int) (Math.random() * models.size()));

                randomModel.setTitle(randomModel.getTitle() + ".");
                randomModel.setUrl(randomUrl);

                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private List<DefaultModel> createModels() {
        List<DefaultModel> models = new ArrayList<>();
        models.add(new DefaultModel("First model", ""));
        models.add(new DefaultModel("Second model", ""));
        models.add(new DefaultModel("Third model", ""));

        return models;
    }

    private void setupRecyclerView(List<DefaultModel> models) {
        RecyclerView.Adapter adapter = new DefaultAdapter(models);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}
