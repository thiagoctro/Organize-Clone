package com.organizeclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.organizeclone.R;
import com.organizeclone.config.ConfigFirebase;
import com.organizeclone.helper.Base64Custom;
import com.organizeclone.model.Usuario;

import java.util.Base64;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button buttonCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById( R.id.editNome );
        campoEmail = findViewById( R.id.editEmailLogin);
        campoSenha = findViewById( R.id.editSenhaLogin);
        buttonCadastrar = findViewById( R.id.buttonCadastrar );

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperar valores digitados pelo usuario
                String textNome = campoNome.getText().toString();
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                //validação dos campos
                if ( !textNome.isEmpty() ){

                    if ( !textEmail.isEmpty() ){

                        if ( !textSenha.isEmpty() ){

                            usuario = new Usuario();
                            usuario.setNome( textNome );
                            usuario.setEmail( textEmail );
                            usuario.setSenha( textSenha );

                            cadastrarUsuario();

                        }else {

                            Toast.makeText( getApplicationContext(),
                                    "Preencha o campo Senha",
                                    Toast.LENGTH_LONG).show();

                        }

                    }else {

                        Toast.makeText( getApplicationContext(),
                                "Preencha o campo Email",
                                Toast.LENGTH_LONG).show();

                    }

                }else {

                    Toast.makeText( getApplicationContext(),
                            "Preencha o campo Nome",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void cadastrarUsuario(){

        autenticacao = ConfigFirebase.getAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){

                    String idUsuario = Base64Custom.codificarBase64( usuario.getEmail() );
                    usuario.setId( idUsuario );
                    usuario.salvar();
                    finish();

                }else {

                    //tratamento de exceção de cadastro
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch( FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    } catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor digite um e-mail valido!";
                    } catch ( FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta ja foi cadastrada!";
                    } catch ( Exception e ) {
                        excecao = "Erro ao cadastrar usuario; " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText( getApplicationContext(),
                            "Erro ao cadastrar usuario!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}