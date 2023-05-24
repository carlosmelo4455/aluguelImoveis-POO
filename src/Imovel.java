public class Imovel {
    private float iptu;
    private Address imovelAddress;
    private String type;
    private String application;
    private Agenda agenda;

    public Imovel(String application, Address imovelAddress, String type, float iptu) {
        this.iptu = iptu;
        this.imovelAddress = imovelAddress;
        this.type = type;
        this.application = application;
        this.agenda = new Agenda();
    }
    public float getIptu() {
        return iptu;
    }

    public void setIptu(int iptu) {
        this.iptu = iptu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Tipo do imovel: " + type + "/ Aplicação: " + application + "/ iptu: " + "R$ " + iptu + "/ endereço: " + "rua " + imovelAddress.getStreet() + " Nº" + imovelAddress.getImovelNumber() + " " + imovelAddress.getCity() + imovelAddress.getState();
    }
}