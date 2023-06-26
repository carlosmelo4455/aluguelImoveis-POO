package teste;

import java.time.LocalDate;
import java.time.Month;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class teste {

    public static void obterValorSazonalidade(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dateFormat.format(date);
            int a = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.println(a);
        } catch (ParseException e) {
            System.out.println("Data inválida! O bloqueio do imóvel não pôde ser agendado.");
        }
    }
    public static void main(String[] args) {
        SimpleDateFormat date = new SimpleDateFormat(){
        }; // Exemplo de obtenção da data atual

        obterValorSazonalidade("27/08/2001");

        //System.out.println("Valor de sazonalidade: " + valorSazonalidade);
    }
}
