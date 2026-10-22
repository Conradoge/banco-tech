package br.com.bancotech.view;
import br.com.bancotech.exception.ExcecaoBancaria;
import br.com.bancotech.model.Conta;
import br.com.bancotech.operation.Operacao;
import br.com.bancotech.service.BancoService;
import java.util.Scanner;
public class MenuPrincipal {
    private final BancoService service;
    private final Scanner scanner = new Scanner(System.in);
    public MenuPrincipal(BancoService service) { this.service = service; }
    public void executar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== BancoTech ===");
            System.out.println("1-Criar conta  2-Listar contas  3-Depositar  4-Sacar");
            System.out.println("5-Transferir  6-PIX simulado  7-Histórico  8-Desativar  0-Sair");
            String opcao = lerTexto("Opção: ");
            try {
                switch (opcao) {
                    case "1" -> criarConta();
                    case "2" -> service.listarContas().forEach(System.out::println);
                    case "3" -> service.depositar(lerTexto("Conta: "), lerValor("Valor: "));
                    case "4" -> service.sacar(lerTexto("Conta: "), lerValor("Valor: "));
                    case "5" -> service.transferir(lerTexto("Origem: "), lerTexto("Destino: "), lerValor("Valor: "));
                    case "6" -> service.realizarPix(lerTexto("Origem: "), lerTexto("Destino: "), lerValor("Valor do PIX: "));
                    case "7" -> service.consultarHistorico(lerTexto("Conta: ")).forEach(System.out::println);
                    case "8" -> service.desativarConta(lerTexto("Conta: "));
                    case "0" -> continuar = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (ExcecaoBancaria e) {
                System.out.println("Operação não realizada: "+e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Entrada numérica inválida. Tente novamente.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: "+e.getMessage());
            }
        }
        System.out.println("BancoTech encerrado.");
    }
    private void criarConta() throws ExcecaoBancaria {
        System.out.println("1-Conta Corrente  2-Conta Poupança");
        Conta conta = service.criarConta(lerTexto("Tipo: "), lerTexto("Número: "), lerTexto("Nome do titular: "), lerTexto("CPF: "));
        System.out.println("Conta criada: "+conta);
    }
    private String lerTexto(String mensagem) { System.out.print(mensagem); return scanner.nextLine().trim(); }
    private double lerValor(String mensagem) { return Double.parseDouble(lerTexto(mensagem).replace(',', '.')); }
}
