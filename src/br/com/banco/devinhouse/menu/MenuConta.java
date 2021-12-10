package br.com.banco.devinhouse.menu;

import br.com.banco.devinhouse.conta.Conta;
import br.com.banco.devinhouse.conta.ContaCorrente;
import br.com.banco.devinhouse.conta.ContaInvestimento;
import br.com.banco.devinhouse.conta.ContaPoupanca;
import br.com.banco.devinhouse.database.ContaDB;

import java.util.Scanner;

public class MenuConta {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipalConta(Conta conta) {
        System.out.println("Selecione a operação desejada:");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Saldo");

        if (conta instanceof ContaInvestimento) {
            System.out.println("5 - Calcular Rendimento Anual");
            System.out.println("6 - Verificar Tipo Rendimento");
        }

        if (conta instanceof ContaPoupanca) {
            System.out.println("5 - Calcular Rentabilidade Por Meses");
        }

        System.out.println("0 - Voltar ao menu principal");
        getTipoOperacaoSelecionada(conta, scanner.nextInt());
    }

    public static void getTipoOperacaoSelecionada(Conta conta, int tipoOperacao) {
        switch (tipoOperacao) {
            case 0:
                MenuPrincipal.listarMenuPrincipal();
                break;
            case 1:
                System.out.println("Digite o valor a ser depositado:");
                conta.depositar(scanner.nextDouble());
                break;
            case 2:
                System.out.println("Digite o valor a ser sacado:");
                conta.sacar(scanner.nextDouble());
                break;
            case 3:
                System.out.println("Digite o valor a ser transferido:");
                double valor = scanner.nextDouble();
                System.out.println("Digite o número da conta para a qual deseja transferir:");
                int numeroConta = scanner.nextInt();
                Conta contaDestino = ContaDB.getContaByNumero(numeroConta);
                if (contaDestino == null) {
                    System.out.println("Conta não encontrada!");
                    MenuConta.menuPrincipalConta(conta);
                    break;
                }
                conta.transferir(contaDestino, valor);
                break;
            case 4:
                System.out.println("Saldo: " + conta.getSaldo());
                break;
            case 5:
                if (conta instanceof ContaCorrente) {
                    System.out.println("Opção inválida");
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
            case 6:
                if (!(conta instanceof ContaInvestimento)) {
                    System.out.println("Opção inválida");
                    MenuConta.menuPrincipalConta(conta);
                    break;
                }
                ((ContaInvestimento) conta).getTipoInvestimento();
                break;
            default:
                System.out.println("Opção inválida");
                MenuConta.menuPrincipalConta(conta);
                break;
        }

        System.out.println("Deseja realizar outra operação? (S/N)");
        if (scanner.next().equalsIgnoreCase("S")) {
            MenuConta.menuPrincipalConta(conta);
        }
        MenuPrincipal.listarMenuPrincipal();
    }
}
