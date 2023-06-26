package aluguel;

public abstract class Imovel {
    private float iptu;
    private Address address;
    private String application;
    private Agenda agenda;

    public Imovel(Address address, float iptu, String application) {
        this.address = address;
        this.iptu = iptu;
        this.application = application;
        this.agenda = new Agenda();
    }

    public abstract float calcularValorReferencia();

    public abstract float calcularAluguel(int indiceSazonalidade);

    @Override
    public abstract String toString();

    public abstract int getType();

    public float getIptu() {
        return iptu;
    }

    public void setIptu(float iptu) {
        this.iptu = iptu;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Agenda getAgenda() {
        return agenda;
    }


}