package br.com.bancotech;
import br.com.bancotech.repository.BancoRepository;
import br.com.bancotech.service.BancoService;
import br.com.bancotech.view.MenuPrincipal;
public class Main {
    public static void main(String[] args) {
        BancoRepository repository = new BancoRepository();
        BancoService service = new BancoService(repository);
        new MenuPrincipal(service).executar();
    }
}
