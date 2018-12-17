package com.demo.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.ecommerce.adapter.ClinicHAdapter;
import com.demo.ecommerce.adapter.MenuListAdapter;
import com.demo.ecommerce.adapter.ProductHeaderAdapter;
import com.demo.ecommerce.adapter.ViewPagerAdapter;
import com.demo.ecommerce.utils.Utilities;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    RecyclerView recycler_header;
    ProductHeaderAdapter headerAdapter;

    private ViewPager introPager;
    private LinearLayout sliderDotspanel;

    private int dotscount;
    private ImageView[] dots;

    private TextView search;

    private RecyclerView new_products_rc;
    private ClinicHAdapter clinicHAdapter;

    MenuListAdapter menuAdapter;
    ListView list_menus;
    private LinearLayout clinic_doc_layout, chat_doctor_layout, order_medicine_layout, book_test_layout, my_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        my_order = findViewById(R.id.my_order);
        my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetails.class));
            }
        });
        list_menus = findViewById(R.id.list_menus);
        menuAdapter = new MenuListAdapter(this);
        list_menus.setAdapter(menuAdapter);
        list_menus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchProductsActivity.class));
            }
        });
        recycler_header = findViewById(R.id.recycler_header);
        recycler_header.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        headerAdapter = new ProductHeaderAdapter(this, "");

        recycler_header.setAdapter(headerAdapter);

        Utilities.setPreference(HomeActivity.this, Utilities.USER_NAME, "User");

        //slider
        introPager = findViewById(R.id.home_banner_pager);
        sliderDotspanel = findViewById(R.id.home_banner_pager_indicator);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        introPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        // Horizontal RV

        new_products_rc = findViewById(R.id.new_products_rc);

        new_products_rc.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        new_products_rc.setAdapter(new ClinicHAdapter(this));

        setUpSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 4000);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // cards
        clinic_doc_layout = findViewById(R.id.clinic_doc_layout);
        chat_doctor_layout = findViewById(R.id.chat_doctor_layout);
        chat_doctor_layout.setOnClickListener(this);
        order_medicine_layout = findViewById(R.id.order_medicine_layout);
        order_medicine_layout.setOnClickListener(this);

        book_test_layout = findViewById(R.id.book_test_layout);
        book_test_layout.setOnClickListener(this);
        clinic_doc_layout.setOnClickListener(this);

    }

    private void setUpSlider() {


        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nonactive_dot));

        introPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.nonactive_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clinic_doc_layout:
                startActivity(new Intent(HomeActivity.this, SearchProductsActivity.class));
                break;

            case R.id.chat_doctor_layout:
                startActivity(new Intent(HomeActivity.this, OfferActivity.class));
                break;

            case R.id.order_medicine_layout:
                startActivity(new Intent(HomeActivity.this, SearchProductsActivity.class));
                break;

            case R.id.book_test_layout:
                startActivity(new Intent(HomeActivity.this, SearchProductsActivity.class));
                break;
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            /*if (HomeActivity.this == null)
                return;*/
           runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (introPager.getCurrentItem() == 0) {
                        introPager.setCurrentItem(1);
                    } else if (introPager.getCurrentItem() == 1) {
                        introPager.setCurrentItem(2);
                    } else if (introPager.getCurrentItem() == 2) {
                        introPager.setCurrentItem(3);
                    } else {
                        introPager.setCurrentItem(0);
                    }

                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
