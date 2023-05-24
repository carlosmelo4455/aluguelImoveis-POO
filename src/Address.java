public class Address {
    private String street;
    private int imovelNumber;
    private String city;
    private State state;
    private String cep;

    public Address(String street, int imovelNumber, String cep, String city, String state) {
        this.street = street;
        this.imovelNumber = imovelNumber;
        this.cep = cep;
        this.state = State.valueOf(state);
        this.city = city;
    }

    public Address(String street, int imovelNumber, String cep) {
        this.city = "Salvador";
        this.state = State.BA;
        this.street = street;
        this.imovelNumber = imovelNumber;
        this.cep = cep;
    }

    public Address() {
        this.street = null;
        this.imovelNumber = 0;
        this.cep = null;
        this.state = null;
        this.city = null;

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getImovelNumber() {
        return imovelNumber;
    }

    public void setImovelNumber(int imovelNumber) {
        this.imovelNumber = imovelNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state.toString();
    }

    public void setState(String state) {
        this.state = State.valueOf(state);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
