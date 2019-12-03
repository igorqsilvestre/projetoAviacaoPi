/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

/**
 *
 * @author Igor
 */
public class  ValidaData {
    
    public static boolean validarDataValida(String data){
        String dateFormat = "dd/MM/uuuu";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
        
        try{
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }
    
    public static boolean validarDataLimite(String data)throws Exception{
        String limite = "01/01/2040";
        SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataIda = formatarData.parse(data);
        Date dataAtual = new Date();
        Date dataLimite = formatarData.parse(limite);
        
        if(dataIda.before(dataAtual) || dataIda.after(dataLimite)){
            return false;
        }else{
            return true;
        }
        
    }
    
}
