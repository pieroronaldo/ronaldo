package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView estado;
    private EditText email;
    private EditText contraseña;
    private Button registrar;
    private Button Login;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.txtemail);
        contraseña = (EditText) findViewById(R.id.txtcontraseña);
        registrar = (Button) findViewById(R.id.btnregistrar);
        estado = (TextView) findViewById(R.id.lblestado);
        Login=(Button)findViewById(R.id.buttonLogin);
        Logout=(Button)findViewById(R.id.buttonSalir);


mAuth =FirebaseAuth.getInstance();
registrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        RegistrarUser();
    }
});
Login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        loginUser();
    }
});

Logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
    }
});



mAuthListener = new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String mail = user.getEmail();
            estado.setText("Loqueando como " + email);
        } else {
            estado.setText("Usuario no logueado ");
        }
    }


};

    }

    private void  RegistrarUser(){
        String mail= email.getText().toString().trim();
        String contraa= contraseña.getText().toString().trim();
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"Ingresar Email",Toast.LENGTH_SHORT).show();
            return;


    }

        if(TextUtils.isEmpty(contraa)){
            Toast.makeText(this,"Ingresar contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(mail,contraa)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Usuario registrado",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,"No se pudo registrar",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
}

private  void  loginUser(){
        String emaill= email.getText().toString().trim();
        String password = contraseña.getText().toString().trim();
        if(TextUtils.isEmpty(emaill)){
            Toast.makeText(this,"Ingresar Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingresar contraseña",Toast.LENGTH_SHORT).show();
        return;
        }
        mAuth.signInWithEmailAndPassword(emaill,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Usuario Logueado",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"No se pudo Loguear",Toast.LENGTH_SHORT).show();
                }
            }
        });
}

private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


public  void  onStart(){
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
}


public void onStrop(){
    super.onStop();
    if(mAuthListener != null){
        mAuth.removeAuthStateListener(mAuthListener);
    }

}




}

