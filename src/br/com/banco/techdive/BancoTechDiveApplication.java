package br.com.banco.techdive;

import br.com.banco.techdive.conta.ContaCorrente;
import br.com.banco.techdive.conta.ContaInvestimento;
import br.com.banco.techdive.conta.ContaPoupanca;
import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.InvetimentosEnum;
import br.com.banco.techdive.menu.MenuPrincipal;

public class BancoTechDiveApplication {
    public static void main(String[] args) {

        /* CRIAÇÃO DE CONTAS */
        ContaCorrente joao = new ContaCorrente("João", "09355872925", 2500, AgenciaEnum.FLORIANOPOLIS);
        ContaCorrente roberta = new ContaCorrente("Roberta", "56535777073", 3300, AgenciaEnum.SAO_JOSE);
        ContaCorrente rodolfo = new ContaCorrente("Rodolfo", "53228160033", 2500, AgenciaEnum.FLORIANOPOLIS);
        ContaPoupanca mario = new ContaPoupanca("Mario", "67049134082", 12000, AgenciaEnum.SAO_JOSE);
        ContaPoupanca marcia = new ContaPoupanca("Marcia", "61041458096", 4000, AgenciaEnum.FLORIANOPOLIS);
        ContaPoupanca adalberto = new ContaPoupanca("Adalberto", "58811113032", 5000, AgenciaEnum.SAO_JOSE);
        ContaInvestimento vanderlei = new ContaInvestimento("Vanderlei", "36274718060", 4300, AgenciaEnum.FLORIANOPOLIS, InvetimentosEnum.TESOURO_DIRETO);
        ContaInvestimento maria = new ContaInvestimento("Maria", "16398001079", 8000, AgenciaEnum.SAO_JOSE, InvetimentosEnum.CDB);
        ContaInvestimento lucas = new ContaInvestimento("Lucas", "31849843031", 9000, AgenciaEnum.SAO_JOSE, InvetimentosEnum.LCI);

        /* CRIAÇÃO TRANSAÇÕES */
        joao.depositar(1000);
        joao.sacar(500);
        joao.transferir(roberta, 500);
        rodolfo.sacar(200);
        mario.depositar(1000);
        adalberto.depositar(300);
        adalberto.transferir(marcia, 200);
        vanderlei.depositar(1000);
        vanderlei.sacar(500);
        maria.depositar(1000);
        lucas.depositar(550);

        MenuPrincipal.listarMenuPrincipal();
    }
}
