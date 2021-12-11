package br.com.banco.techdive.menu;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.database.ContaDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuPrincipal {
    static Scanner scanner = new Scanner(System.in);

    public static void listarMenuPrincipal() {
        Utils.separaParagrafo();
        System.out.println("\nQual menu deseja acessar?");
        System.out.println("1 - Cadastro de Contas");
        System.out.println("2 - Operações de Contas");
        System.out.println("3 - Consultar Registros");
        System.out.println("0 - Sair do Sistema");

        int opcao = scanner.nextInt();
        ArrayList<Integer> opcoes = new ArrayList<>(Arrays.asList(1, 2, 3, 0));
        while (!opcoes.contains(opcao)) {
            Utils.separaLinha();
            System.out.println("Opção inválida!");
            Utils.pressioneEnter();
            listarMenuPrincipal();
        }

        Utils.separaLinha();

        switch (opcao) {
            case 1:
                MenuCadastro.menuPrincipalCadastro();
                break;
            case 2:
                System.out.println("Digite o número de uma conta para acessar o menu de operações:");
                int numeroConta = scanner.nextInt();
                Conta conta = ContaDB.getContaByNumero(numeroConta);
                if (conta == null) {
                    System.out.println("Conta não encontrada!");
                    Utils.pressioneEnter();
                    listarMenuPrincipal();
                }
                System.out.println("Conta encontrada!");
                Utils.pressioneEnter();
                MenuConta.menuPrincipalConta(conta);
                break;
            case 3:
                MenuDatabase.menuPrincipalDatabase();
                break;
            case 0:
                System.out.println("\nSaindo do sistema...\n");
                System.exit(0);
                break;
        }
    }
}
