package com.dharani.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SubCategoryAsync ad = new SubCategoryAsync();
//        ad.execute();

        ProductModelAsync adapter = new ProductModelAsync();
        adapter.execute();
    }
}
