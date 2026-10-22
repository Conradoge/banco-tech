package br.com.bancotech.repository;
import br.com.bancotech.model.Conta;
import java.util.*;
public class BancoRepository {
    private final Map<String, Conta> contas = new LinkedHashMap<>();
    public boolean existe(String numero) { return contas.containsKey(numero); }
    public void adicionar(Conta conta) { contas.put(conta.getNumero(), conta); }
    public Optional<Conta> buscar(String numero) { return Optional.ofNullable(contas.get(numero)); }
    public List<Conta> listarTodas() { return Collections.unmodifiableList(new ArrayList<>(contas.values())); }
}
