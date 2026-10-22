package br.com.bancotech.model;
public class ContaCorrente extends Conta {
    public ContaCorrente(String numero, Cliente titular) { super(numero, titular); }
    @Override public String getTipo() { return "Conta Corrente"; }
}
