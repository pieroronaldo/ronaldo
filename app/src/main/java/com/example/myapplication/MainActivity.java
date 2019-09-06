package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView estado;
    private EditText email;
    private  EditText contraseña;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.txtemail);
        contraseña=(EditText) findViewById(R.id.txtcontraseña);
        registrar=(Button)findViewById(R.id.btnregistrar);
        estado=(TextView) findViewById(R.id.lblestado);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            }
        });
    }
}

