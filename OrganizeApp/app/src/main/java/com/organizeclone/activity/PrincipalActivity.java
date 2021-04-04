package com.organizeclone.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.organizeclone.R;

public class PrincipalActivity extends AppCompatActivity {

    private FloatingActionButton fab1, fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set FloatingActionButton
        FloatingActionButton fabDespesa = findViewById(R.id.fab_despesa);
        fabDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( PrincipalActivity.this, DespesaActivity.class ) );
            }
        });

        FloatingActionButton fabReceita = findViewById( R.id.fab_receita );
        fabReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( PrincipalActivity.this, ReceitaActivity.class ) );
            }
        });


    }



}