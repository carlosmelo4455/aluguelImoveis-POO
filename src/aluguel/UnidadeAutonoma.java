package aluguel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UnidadeAutonoma extends Imovel implements AluguelImovel {
    private float areaUtil;
    private float areaConstruida;
    private int type;

    public UnidadeAutonoma(Address address, float iptu, String application, float areaUtil, float areaConstruida) {
        super(address, iptu, application);
        this.areaUtil = areaUtil;
        this.areaConstruida = areaConstruida;
        this.type = 1;
    }

    @Override
    public float calcularValorReferencia() {
        return areaConstruida * 15;
    }

    @Override
    public float calcularAluguel(int indiceSazonalidade) {
        float valorReferencia = calcularValorReferencia();
        float valorSazonalidade = calcularValorSazonalidade(indiceSazonalidade);
        return valorReferencia + valorReferencia * valorSazonalidade;
    }

    private float calcularValorSazonalidade(int indiceSazonalidade) {

        return switch (indiceSazonalidade) {
            case 1 -> 0.2f;
            case 2 -> 0.15f;
            case 3 -> 0.1f;
            case 4 -> 0.05f;
            default -> 0;
        };
    }

    public float getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(float areaUtil) {
        this.areaUtil = areaUtil;
    }

    public float getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(float areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Tipo do imovel: Unidade autonoma / Aplicação: " + getApplication() + "/ iptu: " + "R$ " + getIptu() + "/ endereço: " + "rua " + getAddress().getStreet() + " Nº" + getAddress().getImovelNumber() + " " + getAddress().getCity() + getAddress().getState();
    }

    @Override
    public boolean isDisponivel(String dataInicio, String dataFim) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = dateFormat.parse(dataInicio);
            Date end = dateFormat.parse(dataFim);
            Calendar calendarStart = Calendar.getInstance();
            Calendar calendarEnd = Calendar.getInstance();
            calendarStart.setTime(start);
            calendarEnd.setTime(end);

            List<Calendar> lista = getAgenda().getAvailableDates();
            for (Calendar calendar : lista) {
                if (calendar.compareTo(calendarStart) >= 0 && calendar.compareTo(calendarEnd) <= 0) {
                    return false;
                }
            }
        } catch (ParseException e) {
            System.out.println("Data inválida");
        }
        return true;
    }

    @Override
    public float calcularPreco(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dateFormat.format(date);
            int mes = calendar.get(Calendar.MONTH);
            return calcularAluguel(mes);
        } catch (ParseException e) {
            System.out.println("Data inválida");
            return 0;
        }
    }

    @Override
    public float calcularPreco(String dataInicio, String dataFim) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = dateFormat.parse(dataInicio);
            Date end = dateFormat.parse(dataFim);
            Calendar calendarStart = Calendar.getInstance();
            Calendar calendarEnd = Calendar.getInstance();
            calendarStart.setTime(start);
            calendarEnd.setTime(end);
            LocalDate localDateStart = LocalDate.of(
                    calendarStart.get(Calendar.YEAR),
                    calendarStart.get(Calendar.MONTH) + 1,
                    calendarStart.get(Calendar.DAY_OF_MONTH)
            );
            LocalDate localDateEnd = LocalDate.of(
                    calendarEnd.get(Calendar.YEAR),
                    calendarEnd.get(Calendar.MONTH) + 1,
                    calendarEnd.get(Calendar.DAY_OF_MONTH)
            );
            long dias = ChronoUnit.DAYS.between(localDateStart, localDateEnd);
            return dias * calcularAluguel(calendarEnd.get(Calendar.MONTH));

        } catch (ParseException e) {
            System.out.println("Data inválida");
            return 0;
        }
    }
}
