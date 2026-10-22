package br.com.bancotech.service;
import br.com.bancotech.exception.*;
import br.com.bancotech.model.*;
import br.com.bancotech.operation.*;
import br.com.bancotech.repository.BancoRepository;
import java.util.List;
public class BancoService {
    private final BancoRepository repository;
    public BancoService(BancoRepository repository) { this.repository = repository; }
    public Conta criarConta(String tipo, String numero, String nome, String cpf) throws ExcecaoBancaria {
        if (numero == null || numero.isBlank()) throw new ValorInvalidoException("Número da conta obrigatório.");
        if (repository.existe(numero.trim())) throw new ValorInvalidoException("Já existe uma conta com esse número.");
        Cliente cliente;
        try { cliente = new Cliente(nome, cpf); }
        catch (IllegalArgumentException e) { throw new ValorInvalidoException(e.getMessage()); }
        Conta conta;
        if ("1".equals(tipo)) conta = new ContaCorrente(numero, cliente);
        else if ("2".equals(tipo)) conta = new ContaPoupanca(numero, cliente);
        else throw new ValorInvalidoException("Tipo de conta inválido.");
        repository.adicionar(conta); return conta;
    }
    public Conta buscarConta(String numero) throws ContaNaoEncontradaException {
        return repository.buscar(numero == null ? "" : numero.trim())
            .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada: "+numero));
    }
    public List<Conta> listarContas() { return repository.listarTodas(); }
    public void depositar(String numero, double valor) throws ExcecaoBancaria { buscarConta(numero).depositar(valor); }
    public void sacar(String numero, double valor) throws ExcecaoBancaria { buscarConta(numero).sacar(valor); }
    public void desativarConta(String numero) throws ExcecaoBancaria { buscarConta(numero).desativar(); }
    public List<Operacao> consultarHistorico(String numero) throws ExcecaoBancaria { return buscarConta(numero).getHistorico(); }
    public void transferir(String origemNumero, String destinoNumero, double valor) throws ExcecaoBancaria {
        Conta origem = buscarConta(origemNumero), destino = buscarConta(destinoNumero);
        validarTransferencia(origem, destino, valor);
        origem.registrarDebitoDeTransferencia(valor, new Transferencia(valor, destino.getNumero()));
        destino.registrarCreditoDeTransferencia(valor, new Transferencia(valor, origem.getNumero()));
    }
    public void realizarPix(String origemNumero, String destinoNumero, double valor) throws ExcecaoBancaria {
        Conta origem = buscarConta(origemNumero), destino = buscarConta(destinoNumero);
        validarTransferencia(origem, destino, valor);
        origem.registrarDebitoDeTransferencia(valor, new Pix(valor, destino.getNumero()));
        destino.registrarCreditoDeTransferencia(valor, new Pix(valor, origem.getNumero()));
    }
    private void validarTransferencia(Conta origem, Conta destino, double valor) throws ExcecaoBancaria {
        if (origem.getNumero().equals(destino.getNumero())) throw new ValorInvalidoException("Origem e destino devem ser contas diferentes.");
        origem.validarAtiva(); destino.validarAtiva(); origem.validarValor(valor); origem.validarSaldo(valor);
    }
}
