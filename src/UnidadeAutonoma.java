public class UnidadeAutonoma extends Imovel {
    private float areaUtil;
    private float areaConstruida;

    public UnidadeAutonoma(Address address, float iptu,String application, float areaUtil, float areaConstruida) {
        super(address, iptu, application);
        this.areaUtil = areaUtil;
        this.areaConstruida = areaConstruida;
    }

    @Override
    public float calcularValorReferencia() {
        return areaConstruida * 15;
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
}
