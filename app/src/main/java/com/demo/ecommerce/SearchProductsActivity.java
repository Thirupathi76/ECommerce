package com.demo.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.ecommerce.adapter.ProductOrderAdapter;
import com.demo.ecommerce.database.DatabaseCart;
import com.demo.ecommerce.interfaces.ClickListener;

public class SearchProductsActivity extends AppCompatActivity {

    LinearLayout bottom_cart_layout;
    TextView text_cart;
    private int total;
    String[] prod_name = {"Galaxy A9 ", "Mi Tv", " Beauty and grooming set", "Seekit edge", "Google mini", "Xmas Toys","Timex watch"};
    String[] prod_price = {"36000", "7999", "1200", "1299", "1999", "99", "289"};
    String[] num_items = {"3 tablet", "1 tablet", "4 tablet","4 tablet","10 tablet","10 tablet", "4 tablet"};
    int [] images = {};
    DatabaseCart databaseCart;
    EditText search_medicines;
    int count;
    private ProductOrderAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        databaseCart = new DatabaseCart(this);

        bottom_cart_layout = findViewById(R.id.bottom_cart_layout);
        search_medicines = findViewById(R.id.search_products);
        search_medicines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() >= 3){
                    Toast.makeText(SearchProductsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        text_cart = findViewById(R.id.cart_text);
//        String product_name = getIntent().getStringExtra("PRODUCT_NAME");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Order Products");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*if (quantity == 0){
            databaseCart.removeItemsFromCart(prod_name[pos]);
        }*///                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
/*if (quantity == 0){
    databaseCart.removeItemsFromCart(prod_name[pos]);
}*///                    databaseCart.removeItemsFromCart(prod_name[pos]);
//                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
        adapter = new ProductOrderAdapter(this, prod_name, prod_price, images,
                new ClickListener() {
                    @Override
                    public void itemClick(View view, int pos, int quantity) {
                        if (view.getId() == R.id.item_add) {

                            Log.e("Click ", "databaseCart " + pos);
                            total = total + Integer.parseInt(prod_price[pos]);
                            bottom_cart_layout.setVisibility(View.VISIBLE);
                            text_cart.setText("Add to Cart items \u20B9 " + total);


                            if (!databaseCart.checkIfExists(prod_name[pos])) {

                                databaseCart.addItemsToCart(prod_name[pos], prod_price[pos], String.valueOf(quantity));
                            } else if (quantity == 0){
                                databaseCart.removeItemsFromCart(prod_name[pos]);
                            }
                            else
                                databaseCart.updateCartItems(prod_name[pos], prod_price[pos], String.valueOf(quantity));

                    /*if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    }*/
                            if (total == 0) {
//                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
                                bottom_cart_layout.setVisibility(View.GONE);
                            }

                        } else if (view.getId() == R.id.item_sub) {
                            Log.e("Click ", "databaseCart " + pos);

                    /*if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    }*/
//                    databaseCart.removeItemsFromCart(prod_name[pos]);
                            total = total - Integer.parseInt(prod_price[pos]);
                            bottom_cart_layout.setVisibility(View.VISIBLE);
                            text_cart.setText("Add to databaseCart items " + total);
                            if (!databaseCart.checkIfExists(prod_name[pos])) {
                                databaseCart.addItemsToCart(prod_name[pos], prod_price[pos], String.valueOf(quantity));
                            } else if (quantity == 0){
                                databaseCart.removeItemsFromCart(prod_name[pos]);
                            } else
                                databaseCart.updateCartItems(prod_name[pos], prod_price[pos], String.valueOf(quantity));


                            if (total == 0) {
//                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
                                bottom_cart_layout.setVisibility(View.GONE);
                            }
                        }
                    }
                });
        recyclerView.setAdapter(adapter);
        bottom_cart_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (total == 0) {
//                    databaseCart.removeItemsFromCart(prod_name[0]);
                }
                Intent intent = new Intent(SearchProductsActivity.this, CartActivity.class);
                intent.putExtra("CART", String.valueOf(total));
                startActivity(intent);

            }
        });

        text_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchProductsActivity.this, CartActivity.class);
                intent.putExtra("CART", String.valueOf(total));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filters_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.filter:
                View vItem = findViewById(R.id.filter);
                PopupMenu popup = new PopupMenu(SearchProductsActivity.this, vItem);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        dialog = new ProgressDialog(SearchProductsActivity.this);

                        dialog.setTitle("Please wait");
                        dialog.setMessage("Loading ...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
//                        text.setVisibility(View.VISIBLE);
                            }
                        }, 1500);
//                        Toast.makeText(EventManagerActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

}
