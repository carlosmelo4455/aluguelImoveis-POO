import java.util.Scanner;

public class Cadastro {
    static Scanner scanner = new Scanner(System.in);

    private Cadastro() {

    }

    public static Imovel cadastroImovel(int tipo) {
        Imovel imovel = null;
        System.out.println("\n=== Cadastro de Imóvel ===");
        float iptu = Read.floatInput("Valor do IPTU: ");
        String street = Read.stringInput("Nome da rua: ");
        int imovelNumber = Read.intInput("Número da casa/prédio: ");
        scanner.nextLine();
        String cep = Read.stringInput("CEP: ");
        String stateName = Read.stringInput("Nome do estado: ");
        String cityName = Read.stringInput("Nome da cidade: ");
        System.out.print("Utilização do imóvel: ");
        String imovelApplication = Read.stringInput("Utilização do imovel: ");
        Address imovelAddress = new Address(street, imovelNumber, cep, cityName, stateName);
        if (tipo == 1) {
            float areaUtil = Read.floatInput("Area util: ");
            float areaConst = Read.floatInput("Area construida: ");
            imovel = new UnidadeAutonoma(imovelAddress, iptu, imovelApplication, areaUtil, areaConst);
        } else {
            String identificacao = Read.stringInput("Identificação do imovel: ");
            Condominio condominio = new Condominio(imovelAddress);
            imovel = new UnidadeCompartilhada(imovelAddress, iptu, imovelApplication, identificacao, condominio);
        }
        return imovel;
    }

    public static Owner cadastroOwner() {
        Owner owner = null;
        System.out.println("\n=== Olá, seja bem vindo, preencha as informações abaixo ===");
        String ownerName = Read.stringInput("Nome: ");
        String cpf = Read.stringInput("CPF: ");
        String id = Read.stringInput("RG: ");
        System.out.print("Preencha seu endereço atual");
        String ownerState = Read.stringInput("\nSigla do seu estado: ");
        String ownerCity = Read.stringInput("Cidade: ");
        String ownerCep = Read.stringInput("CEP: ");
        String ownerStreet = Read.stringInput("Rua: ");
        int ownerImovelNumber = Read.intInput("Número do imovel: ");
        scanner.nextLine();
        Address ownerAddress = new Address(ownerStreet, ownerImovelNumber, ownerCep, ownerCity, ownerState);
        owner = new Owner(ownerName, cpf, id, ownerAddress);
        return owner;
    }
}
