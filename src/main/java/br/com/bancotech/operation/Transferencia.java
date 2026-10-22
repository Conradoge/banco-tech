package br.com.bancotech.operation;
public class Transferencia extends Operacao {
    private final String contaDestino;
    public Transferencia(double valor, String contaDestino) { super(valor, "Transferência para "+contaDestino); this.contaDestino = contaDestino; }
    public String getContaDestino() { return contaDestino; }
}
