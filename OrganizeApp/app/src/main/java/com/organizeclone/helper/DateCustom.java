package com.organizeclone.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    //recupera data atual
    public static String showDate(){

        double date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
         String dateString = simpleDateFormat.format( date );

        return dateString;
    }

    //separa data
    public static String mesAnoDataEscolhida( String data ){

        String retornoData[] = data.split( "/" );
        String mesAno = retornoData[1] + retornoData[2];

        return mesAno;
    }

}
