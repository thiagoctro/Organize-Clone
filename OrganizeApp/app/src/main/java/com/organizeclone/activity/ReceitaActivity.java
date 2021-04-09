package com.organizeclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.organizeclone.R;
import com.organizeclone.config.ConfigFirebase;
import com.organizeclone.helper.Base64Custom;
import com.organizeclone.helper.DateCustom;
import com.organizeclone.model.Movimentacao;
import com.organizeclone.model.Usuario;

public class ReceitaActivity extends AppCompatActivity {

    private EditText campoValor, campoData, campoCategoria, campoDescricao;
    private Movimentacao movimentacao;
    private DatabaseReference databaseReference = ConfigFirebase.getFirebaseDatabase();
    private FirebaseAuth auth = ConfigFirebase.getAutenticacao();
    private double receitaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        //exibir data atual
        campoData.setText( DateCustom.showDate() );
        recuperarReceita();

    }

    public void salvarReceita(View view){

        if ( validarReceita() ){

            movimentacao = new Movimentacao();
            movimentacao.setValor( Double.parseDouble( campoValor.getText().toString() ) );
            movimentacao.setData( campoData.getText().toString() );
            movimentacao.setCategoria( campoCategoria.getText().toString() );
            movimentacao.setDescricao( campoDescricao.getText().toString() );
            movimentacao.setTipo( "r" );

            double receitaRecuperada = Double.parseDouble( campoValor.getText().toString() );
            double receitaAtualizada = receitaTotal + receitaRecuperada;
            atualizarReceita( receitaAtualizada );

            String dataEscolhida = campoData.getText().toString();
            movimentacao.salvarMovimentacao( dataEscolhida );

        }

    }

    public boolean validarReceita(){

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if ( !textoValor.isEmpty() ) {
            if ( !textoData.isEmpty() ) {
                if ( !textoCategoria.isEmpty() ) {
                    if ( !textoDescricao.isEmpty() ){

                        return true;
                    }else {
                        Toast.makeText(this,
                                "Descrição não foi preenchida!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }else {
                    Toast.makeText(this,
                            "Categoria não foi preenchida!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }

            }else {
                Toast.makeText(this,
                        "Data não foi preenchida!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

        }else {
            Toast.makeText(this,
                    "Valor não foi preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarReceita(){
        String idUser = Base64Custom.codificarBase64( auth.getCurrentUser().getEmail() );
        DatabaseReference userRef = databaseReference.child( "usuarios" ).child( idUser );

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );
                receitaTotal = usuario.getTotalReceita();
                Log.i("RECEITA TOTAL", "Receita total ===========> " + receitaTotal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void atualizarReceita( double receitaAtualizada ){
        String idUser = Base64Custom.codificarBase64( auth.getCurrentUser().getEmail() );
        DatabaseReference userRef = databaseReference.child( "usuarios" ).child( idUser );

        userRef.child( "totalReceita" ).setValue( receitaAtualizada );

    }

}