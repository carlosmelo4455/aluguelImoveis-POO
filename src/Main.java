import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class Main {
    static ArrayList<Owner> listaCadastrados;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        listaCadastrados = new ArrayList<>();
        System.out.println("Bem-vindo ao sistema de cadastro de imóveis!");
        Owner selectedOwner = null;
        boolean login = true;
        while (login) {
            System.out.println("\n=== Selecione a opção: ===\n (1) Cadastrar-se \n (2) Login");
            String resposta = scanner.nextLine();
            if (resposta.equals("1")) {
                System.out.println("\n=== Olá, seja bem vindo, preencha as informações abaixo ===");

                System.out.print("Nome: ");
                String ownerName = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("RG: ");
                String id = scanner.nextLine();
                System.out.print("Preencha seu endereço atual");
                System.out.print("\nSigla do seu estado: ");
                String ownerState = scanner.nextLine();
                System.out.print("cidade: ");
                String ownerCity = scanner.nextLine();
                System.out.print("CEP: ");
                String ownerCep = scanner.nextLine();
                System.out.print("rua: ");
                String ownerStreet = scanner.nextLine();
                System.out.print("Número da casa/prédio: ");
                int ownerImovelNumber = scanner.nextInt();
                scanner.nextLine();
                Address ownerAddress = new Address(ownerStreet, ownerImovelNumber, ownerCep, ownerCity, ownerState);
                Owner owner = new Owner(ownerName, cpf, id, ownerAddress);
                listaCadastrados.add(owner);

                System.out.println("\n=== Cadastro de Imóvel ===");
                System.out.print("Valor do IPTU: ");
                float iptu = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("Nome da rua: ");
                String street = scanner.nextLine();
                System.out.print("Número da casa/prédio: ");
                int imovelNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("CEP: ");
                String cep = scanner.nextLine();
                System.out.print("Nome do estado: ");
                String stateName = scanner.nextLine();
                System.out.print("Nome da cidade: ");
                String cityName = scanner.nextLine();
                System.out.print("Tipo de imóvel: ");
                String imovelType = scanner.nextLine();
                System.out.print("Utilização do imóvel: ");
                String imovelApplication = scanner.nextLine();
                Address imovelAddress = new Address(street, imovelNumber, cep, cityName, stateName);
                Imovel imovel = new Imovel(imovelApplication, imovelAddress, imovelType, iptu);
                owner.addImovelToList(imovel);
                System.out.println("\nCadastro de imóvel e proprietário concluído!");
                System.out.println("Proprietário cadastrado: " + owner.getName());
                System.out.println("Imóvel cadastrado: " + imovel.toString());
                System.out.println("\n=== Voltar ao menu principal ===");
            }
            if (resposta.equals("2")) {
                System.out.print("Digite seu CPF\n");
                String cpf = scanner.nextLine();
                boolean search = true;
                for (Owner owner : listaCadastrados) {
                    if (cpf.equalsIgnoreCase(owner.getCpf())) {
                        login = false;
                        selectedOwner = owner;
                        search = false;
                        break;
                    }
                }
                if (search) {
                    System.out.println("\n CPF não cadastrado, voltando ao menu principal");
                }
            }
        }
        boolean sistema = true;
        while (sistema) {
            System.out.print("\n 0lá " + selectedOwner.getName() + ", seja bem vindo. o que gostaria de fazer?:\n");
            System.out.println("""

                    === Selecione a opção: ===
                     (1) Cadastrar um novo imovel\s
                     (2) Visualizar imoveis:\s
                     (3) agendar bloqueio de imovel \s
                     """);
            String resposta = scanner.nextLine();
            if (resposta.equals("1")) {
                System.out.println("\n=== Cadastro de Imóvel ===");
                System.out.print("Valor do IPTU: ");
                float iptu = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("Nome da rua: ");
                String street = scanner.nextLine();
                System.out.print("Número da casa/prédio: ");
                int imovelNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("CEP: ");
                String cep = scanner.nextLine();
                System.out.print("Nome do estado: ");
                String stateName = scanner.nextLine();
                System.out.print("Nome da cidade: ");
                String cityName = scanner.nextLine();
                System.out.print("Tipo de imóvel: ");
                String imovelType = scanner.nextLine();
                System.out.print("Utilização do imóvel: ");
                String imovelApplication = scanner.nextLine();
                Address imovelAddress = new Address(street, imovelNumber, cep, cityName, stateName);
                Imovel imovel = new Imovel(imovelApplication, imovelAddress, imovelType, iptu);
                selectedOwner.addImovelToList(imovel);
                System.out.println("Imóvel cadastrado: " + imovel.toString());
                System.out.println("\n=== Voltar ao menu principal ===");
            }
            if (resposta.equals("2")) {
                System.out.print("\n Que tipo de imovel você gostaria de ver:\n");
                String type = scanner.nextLine();
                selectedOwner.showAllImovel(type);
            }
            if (resposta.equals("3")) {
                System.out.print("Digite a data de bloqueio (dd/MM/yyyy): ");
                String blockDateStr = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date blockDate = dateFormat.parse(blockDateStr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(blockDate);
                    System.out.print("\n Qual imovel você gostaria de bloquear:\n");
                    selectedOwner.showAllImovel();
                    int i = scanner.nextInt();
                    selectedOwner.getImovel(i).getAgenda().addBlockedDate(calendar);
                    System.out.println("Imóvel bloqueado na data " + dateFormat.format(blockDate));
                } catch (ParseException e) {
                    System.out.println("Data inválida! O bloqueio do imóvel não pôde ser agendado.");
                }
            }
        }
        scanner.close();
    }
}