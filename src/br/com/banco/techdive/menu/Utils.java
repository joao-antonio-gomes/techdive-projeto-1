package br.com.banco.techdive.menu;

import java.util.Scanner;

public class Utils {
    public static void separaLinha() {
        System.out.println("\n========================================================\n");
    }

    public static void separaParagrafo() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }


    public static void pressioneEnter(){
        separaLinha();
        System.out.println("Pressione \"ENTER\" para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        separaParagrafo();
    }
}
