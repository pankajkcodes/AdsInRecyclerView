package com.simplifiededtech.adsinrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_PER_AD = 4;
    private static final String BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111";
    private List<Object> recyclerItems = new ArrayList<>();


    RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getdataitems();
        getItems();
        loadBannerAds();

        adapter = new Adapter(recyclerItems, this);
        recyclerView.setAdapter(adapter);
    }

    public void getdataitems() {

        Model ob1 = new Model();
        ob1.setHeader("C Programming");
        ob1.setDesc("Desktop Programming");

        recyclerItems.add(ob1);

        Model ob2 = new Model();
        ob2.setHeader("C++ Programming");
        ob2.setDesc("Desktop Progamming Language");

        recyclerItems.add(ob2);

        Model ob3 = new Model();
        ob3.setHeader("Java Programming");
        ob3.setDesc("Desktop Progamming Language");

        recyclerItems.add(ob3);

        Model ob4 = new Model();
        ob4.setHeader("PHP Programming");
        ob4.setDesc("Web Progamming Language");

        recyclerItems.add(ob4);

        Model ob5 = new Model();
        ob5.setHeader(".NET Programming");
        ob5.setDesc("Desktop/Web Progamming Language");

        recyclerItems.add(ob5);

        Model ob6 = new Model();
        ob6.setHeader("Wordpress Framework");
        ob6.setDesc("PHP based Blogging Framework");

        recyclerItems.add(ob6);

        Model ob7 = new Model();
        ob7.setHeader("Magento Framework");
        ob7.setDesc("PHP Based e-Comm Framework");

        recyclerItems.add(ob7);

        Model ob8 = new Model();
        ob8.setHeader("Shopify Framework");
        ob8.setDesc("PHP based e-Comm Framework");

        recyclerItems.add(ob8);

        Model ob9 = new Model();
        ob9.setHeader("Angular Programming");
        ob9.setDesc("Web Programming");

        recyclerItems.add(ob9);

        Model ob10 = new Model();
        ob10.setHeader("Python Programming");
        ob10.setDesc("Desktop/Web based Progamming");

        recyclerItems.add(ob10);
        Model ob11 = new Model();
        ob11.setHeader("Node JS Programming");
        ob11.setDesc("Web based Programming");

        recyclerItems.add(ob11);

    }

    public void getItems() {
        for (int i = 0; i < recyclerItems.size(); i += ITEM_PER_AD) // i=i+4
        {
            AdView adView = new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_AD_ID);
            recyclerItems.add(i, adView); // 0 4 8 12...
        }
    }

    public void loadBannerAds() {
        for (int i = 0; i < recyclerItems.size(); i++) {
            Object item = recyclerItems.get(i);
            if (item instanceof AdView) {
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }

}