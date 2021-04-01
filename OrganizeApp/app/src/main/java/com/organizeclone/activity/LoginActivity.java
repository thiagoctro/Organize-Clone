package com.organizeclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.organizeclone.R;
import com.organizeclone.config.ConfigFirebase;
import com.organizeclone.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail , campoSenha;
    private Button buttonEntrar;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById( R.id.editEmailLogin);
        campoSenha = findViewById( R.id.editSenhaLogin );
        buttonEntrar = findViewById( R.id.buttonEntrar );

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperar valores digitados pelo usuario
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                //validar os campos
                if ( !textEmail.isEmpty() ){
                    if ( !textSenha.isEmpty() ){

                        usuario = new Usuario();
                        usuario.setEmail( textEmail );
                        usuario.setSenha( textSenha );
                        validarLogin();

                    } else {
                        Toast.makeText( getApplicationContext(),
                                "Preencha o campo de Senha",
                                Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText( getApplicationContext(),
                            "Preencha o campo de Email",
                            Toast.LENGTH_LONG).show();

                }



            }
        });

    }

    public void validarLogin(){
        auth = ConfigFirebase.getAutenticacao();

        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha() )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){

                    abrirActivity();

                } else {

                    String excecao = "";
                    try {
                        throw task.getException();

                    } catch ( FirebaseAuthInvalidUserException e ){
                        excecao = "Usuario não está cadastrado";

                    } catch ( FirebaseAuthInvalidCredentialsException e ){
                        excecao = "Email ou senha incorretos!";

                    } catch ( Exception e ) {
                        excecao = "Erro ao fazer login: " + e.getMessage();
                        e.printStackTrace();

                    }

                    Toast.makeText( getApplicationContext(),
                            excecao,
                            Toast.LENGTH_LONG).show();


                }

            }
        });

    }

    public void abrirActivity(){
        startActivity( new Intent( this, PrincipalActivity.class ));
        finish();
    }

}