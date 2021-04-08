package com.organizeclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organizeclone.R;
import com.organizeclone.helper.DateCustom;
import com.organizeclone.model.Movimentacao;

public class DespesaActivity extends AppCompatActivity {

    private EditText campoValor, campoData, campoCategoria, campoDescricao;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        campoValor = findViewById( R.id.editValor );
        campoData = findViewById( R.id.editData );
        campoCategoria = findViewById( R.id.editCategoria );
        campoDescricao = findViewById( R.id.editDescricao );

        //exibir data atual
        campoData.setText(DateCustom.showDate() );

    }


    public void salvarDespesa(View view){

        movimentacao = new Movimentacao();

        movimentacao.setValor( Double.parseDouble( campoValor.getText().toString() ) );
        movimentacao.setData( campoData.getText().toString() );
        movimentacao.setCategoria( campoCategoria.getText().toString() );
        movimentacao.setDescricao( campoDescricao.getText().toString() );
        movimentacao.setTipo( "d" );

        String data = campoData.getText().toString();
        movimentacao.salvarMovimentacao( data );



    }
}