package com.rqueiros.authenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ValidationActivity extends AppCompatActivity {

    public final String TAG = "Authenticator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        // O objeto Intent guarda os pares chave-valor dentro de um objeto Bundle
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String pass = extras.getString("pass");

            Log.d(TAG, name + pass);
            String msg = "";

            // Valida os dados e cria mensagem a enviar para a atividade principal
            if((name.equals("rui") && pass.equals("12345")) || (name.equals("maria") && pass.equals("12345"))) {
                msg = "Login efetuado com sucesso!";
            }
            else if (!name.equals("rui") && !name.equals("maria") && pass.equals("12345")) {
                msg = "Nome do utilizador inválido!";
            }
            else if ((name.equals("rui") || name.equals("maria")) && !pass.equals("12345")) {
                msg = "Password inválida!";
            } else {
                msg = "Credenciais inválidas!";
            }

            //Envio de resposta para a atividade principal
            Intent data = new Intent();
            data.putExtra("response", msg);
            setResult(RESULT_OK, data);
            finish();
        }
    }


}
