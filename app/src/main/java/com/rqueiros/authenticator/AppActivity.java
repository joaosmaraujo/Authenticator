package com.rqueiros.authenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);


        TextView txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        txtWelcome.setText("Olá, " + name + "!");

    }
}
