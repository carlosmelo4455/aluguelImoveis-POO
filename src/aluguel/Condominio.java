package aluguel;

import java.util.ArrayList;

public class Condominio {
    private Address address;
    private ArrayList<String> itensLazer;

    public Condominio(Address address) {
        this.address = address;
        this.itensLazer = new ArrayList<>();
    }

    public void adicionarItemLazer(String itemLazer) {
        itensLazer.add(itemLazer);
    }

    public boolean temItensLazer() {
        return !itensLazer.isEmpty();
    }

    public int getQuantidadeItensLazer() {
        return itensLazer.size();
    }

    public Address getAddress() {
        return address;
    }

}
