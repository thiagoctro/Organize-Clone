package com.organizeclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebaseDatabase;

    //retorna a instania do firebaseDataBase
    public static DatabaseReference getFirebaseDatabase(){
        if ( firebaseDatabase == null ){
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        }

        return firebaseDatabase;
    }

    //retorna a instancia do firebaseAuth
    public static FirebaseAuth getAutenticacao(){
        if ( autenticacao == null ){
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

}
