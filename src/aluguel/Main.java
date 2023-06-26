package aluguel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class Main {
    private static ArrayList<Owner> listaCadastrados;

    public static void main(String[] args) {
        listaCadastrados = new ArrayList<>();
        System.out.println("Bem-vindo ao sistema de cadastro de imóveis!");
        Owner selectedOwner = login();
        if (selectedOwner != null) {
            runApplication(selectedOwner);
        }
    }

    private static Owner login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String resposta = Read.stringInput("\n=== Selecione a opção: ===\n (1) Cadastrar-se \n (2) Login\n");
            if (resposta.equals("1")) {
                Owner owner = Cadastro.cadastroOwner();
                listaCadastrados.add(owner);
                int tipo = Read.intInput("\n=== Qual seu tipo de imovel: ===\n (1) Unidade autonoma \n (2) Unidade compartilhada");
                Imovel imovel = Cadastro.cadastroImovel(tipo);
                owner.addImovelToList(imovel);
                System.out.println("\naluguel.Cadastro de imóvel e proprietário concluído!");
                System.out.println("Proprietário cadastrado: " + owner.getName());
                System.out.println("Imóvel cadastrado: " + imovel.toString());
                System.out.println("\n=== Voltar ao menu principal ===");
            } else if (resposta.equals("2")) {
                String cpf = Read.stringInput("\nDigite seu CPF: ");
                for (Owner owner : listaCadastrados) {
                    if (cpf.equalsIgnoreCase(owner.getCpf())) {
                        System.out.println("\nLogin realizado com sucesso!");
                        return owner;
                    }
                }
                System.out.println("\nCPF não cadastrado, voltando ao menu principal");
            }
        }
    }

    private static void runApplication(Owner selectedOwner) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOlá " + selectedOwner.getName() + ", seja bem-vindo. O que gostaria de fazer?");
            System.out.println("\n=== Selecione a opção: ===");
            System.out.println(" (1) Cadastrar um novo imovel");
            System.out.println(" (2) Visualizar imoveis");
            System.out.println(" (3) Agendar bloqueio de imovel");

            String resposta = scanner.nextLine();
            if (resposta.equals("1")) {
                int tipo = Read.intInput("\n=== Qual seu tipo de imovel: ===\n (1) Unidade autonoma \n (2) Unidade compartilhada\n");
                Imovel imovel = Cadastro.cadastroImovel(tipo);
                selectedOwner.addImovelToList(imovel);
                System.out.println("Imóvel cadastrado: " + imovel.toString());
                System.out.println("\n=== Voltar ao menu principal ===");
            } else if (resposta.equals("2")) {
                System.out.println("\n=== Qual tipo de imovel gostaria de consultar?: ===");
                System.out.println(" (1) Unidade autonoma");
                System.out.println(" (2) Unidade compartilhada");
                int type = scanner.nextInt();
                selectedOwner.showAllImovel(type);
            } else if (resposta.equals("3")) {
                System.out.print("Digite a data de bloqueio (dd/MM/yyyy): ");
                String blockDateStr = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date blockDate = dateFormat.parse(blockDateStr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(blockDate);
                    System.out.print("\nQual imovel você gostaria de bloquear:\n");
                    selectedOwner.showAllImovel();
                    int i = scanner.nextInt();
                    selectedOwner.getImovel(i).getAgenda().addBlockedDate(calendar);
                    System.out.println("Imóvel bloqueado na data " + dateFormat.format(blockDate));
                } catch (ParseException e) {
                    System.out.println("Data inválida! O bloqueio do imóvel não pôde ser agendado.");
                }
            }
        }
    }
}