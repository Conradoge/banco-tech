package br.com.bancotech.model;
public class Cliente {
    private final String nome;
    private final String cpf;
    public Cliente(String nome, String cpf) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF obrigatório.");
        this.nome = nome.trim(); this.cpf = cpf.trim();
    }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}
