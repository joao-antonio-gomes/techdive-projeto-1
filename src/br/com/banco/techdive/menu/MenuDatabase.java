package br.com.banco.techdive.menu;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.database.ContaDB;
import br.com.banco.techdive.database.LogOperacoesDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuDatabase {
    static Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> escolhasContaDB = new ArrayList<>(Arrays.asList(
            "1 - Listar Todas as Contas",
            "2 - Listar Todas as Contas Correntes",
            "3 - Listar Todas as Contas Poupanças",
            "4 - Listar Todas as Contas Investimento",
            "5 - Listar Todas Contas Com Saldo Negativo",
            "6 - Listar Contas Investimento Com Valor Investido",
            "7 - Listar Detalhes De Conta Específica",
            "0 - Voltar ao menu banco de dados"
    ));

    public static void menuPrincipalDatabase() {
        Utils.separaParagrafo();
        ArrayList<Integer> escolhas = new ArrayList<>(Arrays.asList(1, 2, 0));
        System.out.println("\nQual banco de dados você deseja acessar: ");
        System.out.println("1 - Contas");
        System.out.println("2 - Log de transações");
        System.out.println("0 - Voltar ao menu principal");

        int escolha = scanner.nextInt();

        while (!escolhas.contains(escolha)) {
            Utils.separaParagrafo();
            System.out.println("\nEscolha inválida! Digite novamente: ");
            System.out.println("1 - Contas");
            System.out.println("2 - Log de transações");
            System.out.println("0 - Voltar ao menu principal");
        };

        switch (escolha) {
            case 0:
                MenuPrincipal.listarMenuPrincipal();
                break;
            case 1:
                MenuDatabase.listarEscolhasDatabaseContas();
                break;
            case 2:
                MenuDatabase.listarEscolhasDatabaseLog();
                break;
        }
    }

    private static void listarEscolhasDatabaseLog() {
        Utils.separaParagrafo();
        System.out.println("\nQual log você deseja acessar: ");
        System.out.println("1 - Log de transações");
        System.out.println("0 - Voltar ao menu banco de dados");

        int escolha = scanner.nextInt();
        ArrayList<Integer> escolhas = new ArrayList<>(Arrays.asList(1, 0));
        while (!escolhas.contains(escolha)) {
            Utils.separaParagrafo();
            System.out.println("\nEscolha inválida! Digite novamente: ");
            System.out.println("1 - Log de transações");
            System.out.println("0 - Voltar ao menu banco de dados");
            escolha = scanner.nextInt();
        }
        Utils.separaParagrafo();
        switch (escolha) {
            case 0:
                MenuDatabase.menuPrincipalDatabase();
                break;
            case 1:
                System.out.println("\nDigite o número da conta: ");
                int numeroConta = scanner.nextInt();
                Conta conta = ContaDB.getContaByNumero(numeroConta);
                if (conta == null) {
                    System.out.println("Conta não encontrada!");
                    MenuDatabase.menuPrincipalDatabase();
                    break;
                }
                System.out.println("\nLog de Operações da Conta:\n");
                System.out.println(conta.toString());
                LogOperacoesDB.getLogOperacoesByConta(conta);
                break;
        }
        Utils.pressioneEnter();

        System.out.println("\nVocê deseja realizar outra operação? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            MenuDatabase.menuPrincipalDatabase();
        }
        MenuPrincipal.listarMenuPrincipal();
    }

    private static void listarEscolhasDatabaseContas() {
        Utils.separaParagrafo();

        System.out.println("\nQual operação você deseja realizar: ");
        listaEscolhasContaDB();

        int escolha = scanner.nextInt();
        ArrayList<Integer> escolhas = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0));
        while (!escolhas.contains(escolha)) {
            Utils.separaParagrafo();
            System.out.println("\nEscolha inválida! Digite novamente: ");
            listaEscolhasContaDB();
        }

        escolhasDatabaseContas(escolha);
    }

    private static void escolhasDatabaseContas(int escolha) {
        Utils.separaParagrafo();
        switch (escolha) {
            case 0:
                MenuDatabase.menuPrincipalDatabase();
                break;
            case 1:
                ContaDB.listarTodasContas();
                break;
            case 2:
                ContaDB.listarTodasContasCorrentes();
                break;
            case 3:
                ContaDB.listarTodasContasPoupanca();
                break;
            case 4:
                ContaDB.listarTodasContasInvestimento();
                break;
            case 5:
                ContaDB.listarContaComSaldoNegativo();
                break;
            case 6:
                ContaDB.listarContaPorValorInvestido();
                break;
            case 7:
                System.out.println("Digite o número da conta que deseja verificar os detalhes:");
                int numeroConta = scanner.nextInt();
                Conta conta = ContaDB.getContaByNumero(numeroConta);
                if (conta == null) {
                    System.out.println("Conta não encontrada!");
                    Utils.pressioneEnter();
                    listarEscolhasDatabaseContas();
                    break;
                }
                System.out.println("\nConta encontrada!");
                Utils.pressioneEnter();
                System.out.println("\nDetalhes da conta: ");
                conta.toString();
                break;
        }

        Utils.pressioneEnter();

        System.out.println("\nVocê deseja realizar outra operação? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            MenuDatabase.menuPrincipalDatabase();
        }
        MenuPrincipal.listarMenuPrincipal();
    }

    private static void listaEscolhasContaDB() {
        for (String escolha : escolhasContaDB) {
            System.out.println(escolha);
        }
    }


}
