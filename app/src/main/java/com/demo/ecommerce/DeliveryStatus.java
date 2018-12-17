package com.demo.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DeliveryStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String pos = getIntent().getStringExtra("POSITION");
        if (pos.equals("0")) {
            setContentView(R.layout.layout_product_det);
        } else if (pos.equals("1")){
            setContentView(R.layout.delivered_product);
        } else {
            setContentView(R.layout.layout_product_det);
        }
    }
}
