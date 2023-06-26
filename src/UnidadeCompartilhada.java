public class UnidadeCompartilhada extends Imovel {
    private String identificacao;
    private Condominio condominio;
    private int type;

    public UnidadeCompartilhada(Address address, float iptu,String application, String identificacao, Condominio condominio) {
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
    public float calcularValorReferenciaSazonalidade(int indiceSazonalidade) {
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
}
