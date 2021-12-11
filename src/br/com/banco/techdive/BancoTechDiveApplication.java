package br.com.banco.techdive;

import br.com.banco.techdive.conta.ContaCorrente;
import br.com.banco.techdive.conta.ContaInvestimento;
import br.com.banco.techdive.conta.ContaPoupanca;
import br.com.banco.techdive.conta.Transacao;
import br.com.banco.techdive.enumerators.AgenciaEnum;
import br.com.banco.techdive.enumerators.InvetimentosEnum;
import br.com.banco.techdive.enumerators.TipoOperacoesEnum;
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
        new Transacao(joao, 1000, TipoOperacoesEnum.DEPOSITO);
        new Transacao(mario, 1000, TipoOperacoesEnum.DEPOSITO);
        new Transacao(adalberto, 300, TipoOperacoesEnum.DEPOSITO);
        new Transacao(vanderlei, 500, TipoOperacoesEnum.DEPOSITO);
        new Transacao(maria, 1000, TipoOperacoesEnum.DEPOSITO);
        new Transacao(lucas, 550, TipoOperacoesEnum.DEPOSITO);
        new Transacao(joao, 200, TipoOperacoesEnum.SAQUE);
        new Transacao(rodolfo, 150, TipoOperacoesEnum.SAQUE);
        new Transacao(vanderlei, 300, TipoOperacoesEnum.SAQUE);
        new Transacao(joao, roberta, 300, TipoOperacoesEnum.TRANSFERENCIA);
        new Transacao(adalberto, marcia, 300, TipoOperacoesEnum.TRANSFERENCIA);

        MenuPrincipal.listarMenuPrincipal();
    }
}
