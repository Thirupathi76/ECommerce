package com.demo.ecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomerAdapter extends BaseAdapter {
    Context context;
    String[] arr,arr1;
    int[] img;
    ListView listView;
    public CustomerAdapter(Context context, String[] arr,int[] img,String[] arr1)

    {
        this.context=context;
        this.arr=arr;
        this.img=img;
this.arr1=arr1;

    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customview,view;
        view=LayoutInflater.from(context).inflate(R.layout.layout,parent,false);

        TextView name=view.findViewById(R.id.info);
        TextView name1=view.findViewById(R.id.info1);
        ImageView image=view.findViewById(R.id.img);
        ImageView image1=view.findViewById(R.id.img1);
        image.setImageResource(img[position]);
        name.setText(arr[position]);
        name1.setText(arr1[position]);
        image1.setImageResource(R.drawable.ic_chevron_right_black_24dp);

customview=view;


        return customview;
    }
}
