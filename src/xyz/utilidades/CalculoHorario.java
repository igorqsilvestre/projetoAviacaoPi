/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Igor
 */
public class CalculoHorario {

    public String[] calculaHorario(String cidadeOrigem, String cidadeDestino, String dataIda, int horasIda, int minutosIda) throws Exception {
        int tempoViagemHora;
        int tempoViagemMinutos;

        SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");

        String chegadas[] = new String[2];

        if (cidadeOrigem.equals("Goiânia") && cidadeDestino.equals("Uberlândia")) {
            tempoViagemHora = 3;
            tempoViagemMinutos = 52;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

          
        }

        else if (cidadeOrigem.equals("Goiânia") && cidadeDestino.equals("Uberaba")) {
            tempoViagemHora = 5;
            tempoViagemMinutos = 50;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

            
        }

        else if (cidadeOrigem.equals("Goiânia") && cidadeDestino.equals("São Paulo")) {
            tempoViagemHora = 11;
            tempoViagemMinutos = 42;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

           
        }

        else if (cidadeOrigem.equals("Uberlândia") && cidadeDestino.equals("Uberaba")) {
            tempoViagemHora = 1;
            tempoViagemMinutos = 26;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

            
        }

        else if (cidadeOrigem.equals("Uberlândia") && cidadeDestino.equals("São Paulo")) {
            tempoViagemHora = 7;
            tempoViagemMinutos = 8;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

           
        }

       else if (cidadeOrigem.equals("Uberlândia") && cidadeDestino.equals("Goiânia")) {
            tempoViagemHora = 7;
            tempoViagemMinutos = 8;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

           
        }
        
        else if (cidadeOrigem.equals("Uberaba") && cidadeDestino.equals("Uberlândia")) {
            tempoViagemHora = 1;
            tempoViagemMinutos = 28;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

          
        }
        
         else if (cidadeOrigem.equals("Uberaba") && cidadeDestino.equals("São Paulo")) {
            tempoViagemHora = 5;
            tempoViagemMinutos = 51;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

           
        }
        
        else if (cidadeOrigem.equals("Uberaba") && cidadeDestino.equals("Goiânia")) {
            tempoViagemHora = 5;
            tempoViagemMinutos = 56;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

          
        }
        
        else if (cidadeOrigem.equals("São Paulo") && cidadeDestino.equals("Uberaba")) {
            tempoViagemHora = 6;
            tempoViagemMinutos = 00;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

          
        }
        
        else if (cidadeOrigem.equals("São Paulo") && cidadeDestino.equals("Uberlândia")) {
            tempoViagemHora = 7;
            tempoViagemMinutos = 17;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

            
        }
        
        else if (cidadeOrigem.equals("São Paulo") && cidadeDestino.equals("Goiânia")) {
            tempoViagemHora = 11;
            tempoViagemMinutos = 33;
            Calendar calendar = realizarOperacaoHora(dataIda, horasIda, minutosIda, tempoViagemHora, tempoViagemMinutos);

            chegadas[0] = formatarData.format(calendar.getTime());
            chegadas[1] = formatarHora.format(calendar.getTime());

        }


        return chegadas;
    }

    private Calendar realizarOperacaoHora(String dataIda, int horasIda, int minutosIda, int tempoViagemHora, int tempoViagemMinuto) throws Exception {
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(format.parse(dataIda));

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(ano, mes, dia, horasIda, minutosIda);
        calendar.add(Calendar.HOUR_OF_DAY, tempoViagemHora);
        calendar.add(Calendar.MINUTE, tempoViagemMinuto);

        return calendar;
    }

}
