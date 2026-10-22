package br.com.bancotech.model;
import br.com.bancotech.exception.*;
import br.com.bancotech.operation.*;
import java.util.*;
public abstract class Conta {
    private final String numero;
    private final Cliente titular;
    private double saldo;
    private boolean ativa = true;
    private final List<Operacao> historico = new ArrayList<>();
    protected Conta(String numero, Cliente titular) {
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("Número da conta obrigatório.");
        this.numero = numero.trim(); this.titular = Objects.requireNonNull(titular);
    }
    public String getNumero() { return numero; }
    public Cliente getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public boolean isAtiva() { return ativa; }
    public abstract String getTipo();
    public void depositar(double valor) throws ExcecaoBancaria {
        validarAtiva(); validarValor(valor);
        saldo += valor; historico.add(new Deposito(valor));
    }
    public void sacar(double valor) throws ExcecaoBancaria {
        validarAtiva(); validarValor(valor);
        if (saldo < valor) throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        saldo -= valor; historico.add(new Saque(valor));
    }
    public void desativar() { ativa = false; }
    public List<Operacao> getHistorico() { return Collections.unmodifiableList(historico); }
    public void validarAtiva() throws ContaInativaException {
        if (!ativa) throw new ContaInativaException("A conta "+numero+" está inativa.");
    }
    public void validarValor(double valor) throws ValorInvalidoException {
        if (!Double.isFinite(valor) || valor <= 0) throw new ValorInvalidoException("O valor deve ser maior que zero.");
    }
    public void validarSaldo(double valor) throws SaldoInsuficienteException {
        if (saldo < valor) throw new SaldoInsuficienteException("Saldo insuficiente.");
    }
    // Operações internas acessíveis apenas ao serviço por meio de métodos públicos controlados.
    public void registrarCreditoDeTransferencia(double valor, Operacao operacao) {
        saldo += valor; historico.add(operacao);
    }
    public void registrarDebitoDeTransferencia(double valor, Operacao operacao) {
        saldo -= valor; historico.add(operacao);
    }
    @Override public String toString() {
        return numero+" | "+titular.getNome()+" | "+getTipo()+" | Saldo: R$ "+String.format("%.2f", saldo)+" | "+(ativa?"Ativa":"Inativa");
    }
}
