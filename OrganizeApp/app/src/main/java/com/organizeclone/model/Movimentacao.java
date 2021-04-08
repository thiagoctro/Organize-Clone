package com.organizeclone.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.organizeclone.config.ConfigFirebase;
import com.organizeclone.helper.Base64Custom;
import com.organizeclone.helper.DateCustom;

public class Movimentacao {

    private double valor;
    private String data;
    private String categoria;
    private String descricao;
    private String tipo;

    public Movimentacao() {
    }

    public void salvarMovimentacao( String dataEscolhida ){

        DatabaseReference firebase = ConfigFirebase.getFirebaseDatabase();
        FirebaseAuth auth = ConfigFirebase.getAutenticacao();
        String idUsuario = Base64Custom.codificarBase64( auth.getCurrentUser().getEmail() );
        String mesAno = DateCustom.mesAnoDataEscolhida( dataEscolhida );

        firebase.child( "movimentacoes" )
                .child( idUsuario )
                .child( mesAno )
                .push()
                .setValue( this );

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
