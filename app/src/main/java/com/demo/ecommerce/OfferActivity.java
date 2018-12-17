package com.demo.ecommerce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.demo.ecommerce.adapter.OfferAdapter;

public class OfferActivity extends AppCompatActivity {
ListView listView;
String[] reasons={"Products start at @99","Get products delivered for free.","Upto 50% off on 1st Product order"};
String[] descriptions={"Order any product? chat with a Vendor now.","Get free Product delivery on every product.",
        "40%=25% OFF + 15% on electronics orderMax cashbackworth Rs.75 per order." +
                "Cashback given within 24hrs of order delivery, amount as Points.Limited Period Offer."};
String[] coupons={"AT99","FIRSTFREE","ORDER75"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers);
        listView=findViewById(R.id.listview);
        OfferAdapter adapter=new OfferAdapter(OfferActivity.this,reasons,descriptions,coupons);
        listView.setAdapter(adapter);
    }
}
