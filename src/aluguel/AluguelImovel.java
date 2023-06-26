package aluguel;

public interface AluguelImovel {
    boolean isDisponivel(String dataInicio, String dataFim);

    float calcularPreco(String data);

    float calcularPreco(String dataInicio, String dataFim);
}
