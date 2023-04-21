public class Main {
    public static void main(String[] args) {
        Address endereco1 = new Address("teofilo falcão", 88, "41950-200", "Salvador","SP");
        Address endereco2 = new Address("capitão Aristeu", 86, "41940-155", "Jacobina", "RJ");
        Address endereco3 = new Address("Elias Kalile", 16, "40450-205");
        Imovel casaDoCarlos = new Imovel("aluguel", endereco2, "casa", 2500);
        Imovel casaDeAlice = new Imovel("venda", endereco3, "casa", 1000);
        Owner carlos = new Owner("carlos", "085.413.785-86", "20.628.352-05", endereco1, casaDoCarlos);
        carlos.addImovelToList(casaDeAlice);
        carlos.showAllImovel("casa");
}}