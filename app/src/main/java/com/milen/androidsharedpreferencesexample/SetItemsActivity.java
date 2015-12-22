package com.milen.androidsharedpreferencesexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetItemsActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etInputValues;
    Button btnSetVales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_items);
        etInputValues = (EditText) findViewById(R.id.etInputValues);
        btnSetVales = (Button) findViewById(R.id.btnSetValues);
        btnSetVales.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String etInputValuesText = String.valueOf(etInputValues.getText());
        if (!etInputValuesText.equals("")){
            MainActivity.putStringInPreferences("customValues", etInputValuesText);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("customValues", etInputValuesText);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }else{
            Toast.makeText(SetItemsActivity.this, R.string.no_values, Toast.LENGTH_SHORT).show();
        }



    }
}
