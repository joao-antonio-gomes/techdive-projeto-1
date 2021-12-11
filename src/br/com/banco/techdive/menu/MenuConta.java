package br.com.banco.techdive.menu;

import br.com.banco.techdive.conta.Conta;
import br.com.banco.techdive.conta.ContaCorrente;
import br.com.banco.techdive.conta.ContaInvestimento;
import br.com.banco.techdive.conta.ContaPoupanca;
import br.com.banco.techdive.database.ContaDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuConta {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipalConta(Conta conta) {
        ArrayList<Integer> escolhas = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        if (conta instanceof ContaInvestimento) {
            escolhas = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        }
        Utils.separaParagrafo();
        System.out.println("Você está acessando o menu com a conta: \n");
        System.out.println(conta.toString());
        Utils.separaLinha();
        System.out.println("Selecione a operação desejada:");
        System.out.println("1 - Alterar Dados Cadastrais");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Saldo");

        if (conta instanceof ContaInvestimento) {
            System.out.println("6 - Calcular Rendimento Anual");
            System.out.println("7 - Verificar Tipo Rendimento");
        }

        if (conta instanceof ContaPoupanca) {
            System.out.println("6 - Calcular Rentabilidade Por Meses");
        }

        System.out.println("0 - Voltar ao menu principal");
        int escolha = scanner.nextInt();
        while (!escolhas.contains(escolha)) {
            Utils.separaLinha();
            System.out.println("Opção inválida!");
            Utils.pressioneEnter();
            menuPrincipalConta(conta);
        }

        getTipoOperacaoSelecionada(conta, escolha);
    }

    public static void getTipoOperacaoSelecionada(Conta conta, int tipoOperacao) {
        Utils.separaLinha();
        switch (tipoOperacao) {
            case 0:
                MenuPrincipal.listarMenuPrincipal();
                break;
            case 1:
                conta.alterarDadosCadastrais();
                break;
            case 2:
                System.out.println("Digite o valor a ser depositado:");
                conta.depositar(scanner.nextDouble());
                break;
            case 3:
                System.out.println("Digite o valor a ser sacado:");
                conta.sacar(scanner.nextDouble());
                break;
            case 4:
                System.out.println("Digite o valor a ser transferido:");
                double valor = scanner.nextDouble();
                Utils.separaLinha();
                System.out.println("Digite o número da conta para a qual deseja transferir:");
                int numeroConta = scanner.nextInt();
                Conta contaDestino = ContaDB.getContaByNumero(numeroConta);
                if (contaDestino == null) {
                    Utils.separaLinha();
                    System.out.println("Conta não encontrada!");
                    Utils.pressioneEnter();
                    MenuConta.menuPrincipalConta(conta);
                    break;
                }
                Utils.separaLinha();
                System.out.println("Conta Destino: \n");
                contaDestino.toString();
                Utils.separaLinha();
                conta.transferir(contaDestino, valor);
                break;
            case 5:
                System.out.println("Saldo: " + conta.getSaldo());
                break;
            case 6:
                if (conta instanceof ContaCorrente) {
                    System.out.println("Opção inválida");
                    Utils.pressioneEnter();
                    MenuConta.menuPrincipalConta(conta);
                    break;
                }
                if (conta instanceof ContaInvestimento) {
                    ((ContaInvestimento) conta).calculaRendimentoAnual();
                }
                if (conta instanceof ContaPoupanca) {
                    System.out.println("Digite o número de meses para calcular a rentabilidade:");
                    ((ContaPoupanca) conta).calculaRentabilidadePorNumeroDeMeses(scanner.nextInt());
                }
                break;
            case 7:
                if (!(conta instanceof ContaInvestimento)) {
                    System.out.println("Opção inválida");
                    Utils.pressioneEnter();
                    MenuConta.menuPrincipalConta(conta);
                    break;
                }
                ((ContaInvestimento) conta).getTipoInvestimento();
                break;
            default:
                System.out.println("Opção inválida");
                Utils.pressioneEnter();
                MenuConta.menuPrincipalConta(conta);
                break;
        }
        Utils.pressioneEnter();

        System.out.println("Deseja realizar outra operação? (S/N)");
        if (scanner.next().equalsIgnoreCase("S")) {
            MenuConta.menuPrincipalConta(conta);
        }
        MenuPrincipal.listarMenuPrincipal();
    }
}
