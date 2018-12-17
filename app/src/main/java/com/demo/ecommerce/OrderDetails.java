package com.demo.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class OrderDetails extends AppCompatActivity {
    ListView listView;
    String []arr={"Arriving Monday","Delivered Wednesday","Return started","return received"};
    String []arr1={"Dispatched","","your refund will be processed when we receive your item",""};
    int[] img={R.drawable.bags,R.drawable.watches,R.drawable.tshirts,R.drawable.trousers};
    CustomerAdapter customerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_layout);

        listView=findViewById(R.id.listView);
        customerAdapter=new CustomerAdapter(this,arr,img,arr1);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(OrderDetails.this,DeliveryStatus.class);
                intent.putExtra("POSITION", String.valueOf(i));
                startActivity(intent);
            }
        });
    }
}
