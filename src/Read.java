import java.util.Scanner;
public class Read {
    private static Scanner scanner = new Scanner(System.in);
    public static String stringInput(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public static float floatInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextFloat();
    }
    public static int intInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

}
