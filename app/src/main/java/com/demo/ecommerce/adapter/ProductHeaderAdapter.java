package com.demo.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.ecommerce.R;
import com.demo.ecommerce.SearchProductsActivity;


public class ProductHeaderAdapter extends RecyclerView.Adapter<ProductHeaderAdapter.MyViewHolder> {

    String[] bike_name = {"Grocery", "Mobiles","Fashion","Electronics","Appliances","Beauty","Books","Sports"};
    Context context;
    String type;
    int[] images = {R.drawable.groceries,R.drawable.mobiles,R.drawable.fasion,R.drawable.electronic,
            R.drawable.appliances,R.drawable.beauty,R.drawable.books,R.drawable.sports};

    public ProductHeaderAdapter(Context productActivity, String type) {
        context = productActivity;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_header_list, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.bike_name.setText(bike_name[i]);

        myViewHolder.image.setImageResource(images[i]);//images[i]
        myViewHolder.bike_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, SearchProductsActivity.class);
               context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bike_name.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bike_name;
        ImageView image;
        int sum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bike_name = itemView.findViewById(R.id.bike_image);
            image = itemView.findViewById(R.id.image);

        }
    }
}
