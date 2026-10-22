package br.com.bancotech.model;
public class ContaPoupanca extends Conta {
    public ContaPoupanca(String numero, Cliente titular) { super(numero, titular); }
    @Override public String getTipo() { return "Conta Poupança"; }
}
