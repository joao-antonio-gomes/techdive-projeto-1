package br.com.banco.devinhouse.menu;

import br.com.banco.devinhouse.conta.Conta;
import br.com.banco.devinhouse.database.ContaDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuPrincipal {
    static Scanner scanner = new Scanner(System.in);

    public static void listarMenuPrincipal() {
        System.out.println("\nQual menu deseja acessar?");
        System.out.println("1 - Cadastro de Contas");
        System.out.println("2 - Operações de Contas");
        System.out.println("3 - Consultar Registros");
        System.out.println("0 - Sair do Sistema");

        int opcao = scanner.nextInt();
        ArrayList<Integer> opcoes = new ArrayList<>(Arrays.asList(1, 2, 3, 0));
        while (!opcoes.contains(opcao)) {
            System.out.println("\nOpção inválida!\n");
            listarMenuPrincipal();
        }

        switch (opcao) {
            case 1:
                MenuCadastro.menuPrincipalCadastro();
                break;
            case 2:
                System.out.println("\nDigite o número de uma conta para acessar o menu de operações:");
                int numeroConta = scanner.nextInt();
                Conta conta = ContaDB.getContaByNumero(numeroConta);
                if (conta == null) {
                    System.out.println("\nConta não encontrada!\n");
                    listarMenuPrincipal();
                }
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
