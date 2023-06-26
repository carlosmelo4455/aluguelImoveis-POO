package aluguel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UnidadeCompartilhada extends Imovel implements AluguelImovel {
    private String identificacao;
    private Condominio condominio;
    private int type;

    public UnidadeCompartilhada(Address address, float iptu, String application, String identificacao, Condominio condominio) {
        super(address, iptu, application);
        this.identificacao = identificacao;
        this.condominio = condominio;
        this.type = 2;
    }

    @Override
    public float calcularValorReferencia() {
        float valorReferencia = getIptu();

        if (condominio.temItensLazer()) {
            valorReferencia *= condominio.getQuantidadeItensLazer();
        } else {
            valorReferencia *= 0.1f;
        }

        return valorReferencia;
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

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Tipo do imovel: Unidade compartilhada / Aplicação: " + getApplication() + "/ iptu: " + "R$ " + getIptu() + "/ endereço: " + "rua " + getAddress().getStreet() + " Nº" + getAddress().getImovelNumber() + " " + getAddress().getCity() + getAddress().getState();
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
