package com.organizeclone.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.organizeclone.config.ConfigFirebase;

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private double totalDespesa = 0.00;
    private double totalReceita = 0.00;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfigFirebase.getFirebaseDatabase();
        firebase.child( "usuarios" )
                .child( this.id )
                .setValue( this );
    }

    public double getTotalDespesa() {
        return totalDespesa;
    }

    public void setTotalDespesa(double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    public double getTotalReceita() {
        return totalReceita;
    }

    public void setTotalReceita(double totalReceita) {
        this.totalReceita = totalReceita;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
