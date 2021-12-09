package br.com.banco.devinhouse.conta;

import br.com.banco.devinhouse.database.LogOperacoesDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LogOperacoes {
    private int contaOrigem;
    private int contaDestino;
    private double valor;
    private String data;

    public LogOperacoes(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem.getConta();
        this.contaDestino = contaDestino.getConta();
        this.valor = valor;

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.data = fmt.format(new Date());

        this.salvarLogOperacao();
    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public int getContaDestino() {
        return contaDestino;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public void salvarLogOperacao() {
        LogOperacoesDB.addLogOperacoes(this);
    }
}
