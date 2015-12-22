package com.milen.androidsharedpreferencesexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView lwItems;
    private Button btnChangeItems;
    private Button btnExit;
    private String DEFAULT_ITEMS_VALUES_STRING = "Item 1; Item 2";
    private String customItemString;
    private static SharedPreferences preferenceItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChangeItems = (Button) findViewById(R.id.btnChangeItems);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnChangeItems.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        lwItems = (ListView) findViewById(R.id.lwItems);
        preferenceItems = getSharedPreferences("items", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle setItemsIntent = getIntent().getExtras();
        if (setItemsIntent != null){
            customItemString = getIntent().getExtras().getString("customValues");
        }

        String[] items;
        String preferenceItemsString = preferenceItems.getString("customValues", null);
        if( preferenceItemsString != null){
            items = getSplitAndTrim(preferenceItemsString);
        }else{
            items = getSplitAndTrim(DEFAULT_ITEMS_VALUES_STRING);
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, items);
        lwItems.setAdapter(arrayAdapter);
        lwItems.setOnItemClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(customItemString != null){
            putStringInPreferences("customValues", customItemString);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnExit){
            System.exit(0);
        }

        Intent intent = new Intent(this, SetItemsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String textOfItem = String.valueOf(((TextView) view).getText());
        Toast.makeText(MainActivity.this, textOfItem, Toast.LENGTH_SHORT).show();
    }

    public static void putStringInPreferences(String key, String customItemString) {
        SharedPreferences.Editor editor = preferenceItems.edit();
        editor.putString(key, customItemString);
        editor.commit();
    }

    @NonNull
    private String[] getSplitAndTrim(String preferenceItemsString) {
        return preferenceItemsString.trim().split("\\s*;\\s*");
    }
}
