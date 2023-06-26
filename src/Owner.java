import java.util.ArrayList;

public class Owner {
    private String name;
    private String cpf;
    private String id;
    private Address ownerAddress;
    private ArrayList<Imovel> imovelList;

    public Owner(String name, String cpf, String id, Address ownerAddress) {
        this.name = name;
        this.cpf = cpf;
        this.id = id;
        this.ownerAddress = ownerAddress;
        this.imovelList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void changeAddress(String street, int imovelNumber, String cep, String state, String city) {
        this.ownerAddress.setStreet(street);
        this.ownerAddress.setImovelNumber(imovelNumber);
        this.ownerAddress.setCep(cep);
        this.ownerAddress.setState(state);
        this.ownerAddress.setCity(city);
    }

    public void changeAddress(String street, int imovelNumber, String cep) {
        this.ownerAddress.setStreet(street);
        this.ownerAddress.setImovelNumber(imovelNumber);
        this.ownerAddress.setCep(cep);
    }

    public void addImovelToList(Imovel imovel) {
        imovelList.add(imovel);
    }

    public void showAllImovel(int type) {
        int i = 0;
        for (Imovel imovel : imovelList) {
            if (imovel.getType() == type) {
                i += 1;
                System.out.printf("(" + i + ") " + imovel.toString() + "\n");
            }
        }
    }

    public void showAllImovel() {
        int i = 0;
        for (Imovel imovel : imovelList) {
            System.out.printf("(" + i + ") " + imovel.toString() + "\n");
        }
    }

    public Imovel getImovel(int i) {
        return imovelList.get(i);
    }
}