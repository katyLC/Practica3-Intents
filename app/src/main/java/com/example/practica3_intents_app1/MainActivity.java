package com.example.practica3_intents_app1;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CheckBox cbArroz;
    CheckBox cbPapa;
    CheckBox cbLomo;
    Button btnEnviar;
    ArrayList<String> pedido;
    int idCb[];
    CheckBox[] miCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idCb = new int[]{R.id.cbArroz, R.id.cbLomo, R.id.cbPapa};
        miCb = new CheckBox[idCb.length];

        btnEnviar = findViewById(R.id.enviar);

        for (int i = 0; i < miCb.length; i++) {
            miCb[i] = findViewById(idCb[i]);
        }

        pedido = new ArrayList<>();
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarComida(v);
                pedido.clear();
            }
        });

    }

    private void agregarComida(View view) {

        for (int i = 0; i < idCb.length; i++) {
            if(miCb[i].isChecked()) {
                pedido.add((String) miCb[i].getText());
            }
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, pedido);
        intent.setType("text/plain");
        if (null != intent.resolveActivity(getPackageManager())) {
            startActivity(Intent.createChooser(intent, "Abrir con:"));
        }
    }
}
