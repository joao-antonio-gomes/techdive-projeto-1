package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.database.LogOperacoesDB;
import br.com.banco.devinhouse.enumerators.TipoOperacoesEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogOperacoes {
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;
    private String operacao;
    private String dataString;
    private Date data;

    public LogOperacoes(Conta contaOrigem, Conta contaDestino, double valor, TipoOperacoesEnum operacao) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.operacao = operacao.getDescricao();

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.data = new Date();
        this.dataString = fmt.format(this.data);

        this.salvarLogOperacao();
    }

    public Date getData() {
        return data;
    }

    public int getNumeroContaOrigem() {
        return this.contaOrigem.getNumeroConta();
    }

    public int getNumeroContaDestino() {
        return this.contaDestino.getNumeroConta();
    }

    public double getValor() {
        return valor;
    }

    public String getDataString() {
        return dataString;
    }

    public void salvarLogOperacao() {
        LogOperacoesDB.addLogOperacoes(this);
    }

    @Override
    public String toString() {
        return "Conta Origem: " + this.contaOrigem.getNumeroConta() + "\n" + "Titular Conta Origem: " + this.contaOrigem.getNome() + "\nConta Destino: " + this.contaDestino.getNumeroConta() + "\nTitular Conta Destino: " + this.contaDestino.getNome() + "\nValor: " + this.valor + "\nOperação: " + this.operacao + "\nData: " + this.dataString;
    }
}
