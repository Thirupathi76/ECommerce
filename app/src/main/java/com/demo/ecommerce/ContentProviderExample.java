package com.demo.ecommerce;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentProviderExample extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ContentProviderDemo";

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;


    private boolean firstTimeLoaded = false;

    private TextView textViewQueryResult;
    private Button buttonLoadData;

    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};

    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";

    private String[] mSelectionArguments = new String[]{"Bro"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_example);

        textViewQueryResult = findViewById(R.id.textViewQueryResult);

       /* buttonLoadData = (Button) findViewById(R.id.buttonLoadData);
        buttonLoadData.setOnClickListener(this);*/

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                null);

        if (cursor != null && cursor.getCount() > 0) {

            StringBuilder stringBuilderQueryResult = new StringBuilder("");
            while (cursor.moveToNext()) {
                stringBuilderQueryResult.append(cursor.getString(0) + "\n");

            }
            textViewQueryResult.setText(stringBuilderQueryResult.toString());
        } else {
            textViewQueryResult.setText("No Contacts in device");
        }

//        userDictionary();
    }

    @Override
    public void onClick(View view) {

    }


}
