package br.com.bancotech.operation;
public class Pix extends Operacao {
    private final String contaDestino;
    public Pix(double valor, String contaDestino) { super(valor, "PIX simulado para "+contaDestino); this.contaDestino = contaDestino; }
    public String getContaDestino() { return contaDestino; }
}
