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
                Owner owner = Cadastro.CadastroOwner();
                listaCadastrados.add(owner);
                System.out.print("\n=== Qual seu tipo de imovel: ===\n (1) Unidade autonoma \n (2) Unidade compartilhada");
                int tipo = scanner.nextInt();
                Imovel imovel = Cadastro.CadastroImovel(tipo);
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
                System.out.print("\n=== Qual seu tipo de imovel: ===\n (1) Unidade autonoma \n (2) Unidade compartilhada");
                int tipo = scanner.nextInt();
                Imovel imovel = Cadastro.CadastroImovel(tipo);
                selectedOwner.addImovelToList(imovel);
                System.out.println("Imóvel cadastrado: " + imovel.toString());
                System.out.println("\n=== Voltar ao menu principal ===");
            }
            if (resposta.equals("2")) {
                System.out.print("\n=== Qual tipo de imovel gostaria de consultar?: ===\n (1) Unidade autonoma \n (2) Unidade compartilhada");
                int type = scanner.nextInt();
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