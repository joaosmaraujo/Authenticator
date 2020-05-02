package com.rqueiros.authenticator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public final String TAG = "Authenticator";
    private final int REQUEST_CODE = 1;
    String name, pass;
    int count = 0;
    Button btn;
    EditText txtName, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.button);
        txtName = (EditText)findViewById(R.id.txtName);
        txtPass = (EditText)findViewById(R.id.txtPass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(count == 3) {
            btn.setEnabled(false);
        }
    }

    public void login(View view) {
        // Obtém os valores incluídos nas caixas de texto
        name = txtName.getText().toString();
        pass = txtPass.getText().toString();

        // Define uma intent explícita passando a informação dos valores da caixa de texto
        Intent intent = new Intent(this, ValidationActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("pass", pass);
        //Envia a intent para a atividade ValidationActivity (e aguarda resultado)
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "guardei count a " + count);
        outState.putString("count", String.valueOf(count));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = Integer.parseInt(savedInstanceState.getString("count"));
        Log.d(TAG, "recuperei count a " + count);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra("response")) {
                Log.d(TAG, "entrei");
                String theResponse = data.getExtras().getString("response");
                if (theResponse.equals("Login efetuado com sucesso!")) {
                    Intent intent = new Intent(this, AppActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                } else {
                    Log.d(TAG, theResponse);
                    count++;
                }
                if (count==3) {
                    btn.setEnabled(false);
                }
            }
        }
    }
}
