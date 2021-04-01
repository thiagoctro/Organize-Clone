package com.organizeclone.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfigFirebase {

    private static FirebaseAuth autenticacao;

    public static FirebaseAuth getAutenticacao(){

        if ( autenticacao == null ){
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

}
