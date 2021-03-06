package com.organizeclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.organizeclone.R;
import com.organizeclone.activity.CadastroActivity;
import com.organizeclone.activity.LoginActivity;
import com.organizeclone.config.ConfigFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonNextVisible(false);
        setButtonBackVisible(false);

        addSlide( new FragmentSlide.Builder()
                    .background(R.color.design_default_color_on_primary)
                    .fragment(R.layout.intro_1)
                    .build()
        );

        addSlide( new FragmentSlide.Builder()
                    .background(R.color.design_default_color_on_primary)
                    .fragment( R.layout.intro_2 )
                    .build()
        );

        addSlide( new FragmentSlide.Builder()
                    .background( R.color.design_default_color_on_primary )
                    .fragment( R.layout.intro_3 )
                    .build()
        );

        addSlide( new FragmentSlide.Builder()
                    .background(R.color.design_default_color_on_primary)
                    .fragment( R.layout.intro_4 )
                    .build()
        );

        addSlide( new FragmentSlide.Builder()
                    .background( R.color.design_default_color_on_primary )
                    .fragment( R.layout.intro_cadastro )
                    .canGoForward(false)
                    .build()
        );

    }

    public void btEntrar(View view){
        startActivity( new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity( new Intent( this, CadastroActivity.class) );
    }

    public void verificarUsuarioLogado(){
        auth = ConfigFirebase.getAutenticacao();
        //auth.signOut();
        if ( auth.getCurrentUser() != null ){
            abrirActivity();
        }

    }

    public void abrirActivity(){
        startActivity( new Intent( this, PrincipalActivity.class ));
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }
}