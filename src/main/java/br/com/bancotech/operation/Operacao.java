package br.com.bancotech.operation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public abstract class Operacao {
    private final LocalDateTime dataHora = LocalDateTime.now();
    private final double valor;
    private final String descricao;
    protected Operacao(double valor, String descricao) { this.valor = valor; this.descricao = descricao; }
    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
    public String getDataHoraFormatada() { return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); }
    @Override public String toString() { return getDataHoraFormatada()+" | "+descricao+" | R$ "+String.format("%.2f", valor); }
}
