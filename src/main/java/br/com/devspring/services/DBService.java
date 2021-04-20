package br.com.devspring.services;

import br.com.devspring.domain.*;
import br.com.devspring.domain.enums.Perfil;
import br.com.devspring.domain.enums.TipoCliente;
import br.com.devspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
public class DBService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoDAO;
    @Autowired
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraDAO;
    @Autowired
    private FinancerioUserRepository financerioUserRepository;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public void instantiateTestDataBase(){
        FormaPagamento formaPagamento1 = new FormaPagamento("Dinheiro");
        FormaPagamento formaPagamento2 = new FormaPagamento("Cartão");

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();

        MovimentacaoFinanceira mov1 = new MovimentacaoFinanceira("pagamento CAGECE",data,data,100.00,100.00);
        MovimentacaoFinanceira mov2 = new MovimentacaoFinanceira("pagamento ENEL",data,data,50.00,50.00);
        MovimentacaoFinanceira mov3 = new MovimentacaoFinanceira("pagamento SANTANDER",data,data,450.00,450.00);

        formaPagamento1.getMovimetacoesFinanceira().addAll(Arrays.asList(mov1,mov2,mov3));
        formaPagamento1.getMovimetacoesFinanceira().addAll(Arrays.asList(mov3));

        mov1.getFormasPagamento().addAll(Arrays.asList(formaPagamento1));
        mov2.getFormasPagamento().addAll(Arrays.asList(formaPagamento1));
        mov3.getFormasPagamento().addAll(Arrays.asList(formaPagamento1,formaPagamento2));

        formaPagamentoDAO.saveAll(Arrays.asList(formaPagamento1,formaPagamento2));
        movimentacaoFinanceiraDAO.saveAll(Arrays.asList(mov1,mov2,mov3));

        FinanceiroUser user1 = new FinanceiroUser("Alison", pe.encode("dev"),"Alison","alison@gmail.com");
        FinanceiroUser user2 = new FinanceiroUser("Joao", pe.encode("123456"),"Joao","joao@gmail.com");
        user2.addPerfil(Perfil.ADMIN);

        financerioUserRepository.saveAll(Arrays.asList(user1,user2));

        Estado estado1 = new Estado("Ceará");
        Estado estado2 = new Estado("São Paulo");

        Cidade cidade1 = new Cidade("Fortaleza",estado1);
        Cidade cidade2 = new Cidade("São Paulo",estado2);
        Cidade cidade3 = new Cidade("Campinas",estado2);

        estado1.getCidades().addAll(Arrays.asList(cidade1));
        estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));

        estadoRepository.saveAll(Arrays.asList(estado1,estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));

        Cliente cliente1 = new Cliente("Alison", "alison@gmail.com", "94805951095", TipoCliente.PESSOAFISICA);
        cliente1.getTelefones().addAll(Arrays.asList("85986854545","85986854456"));

        Endereco endereco1 = new Endereco("Rua 1117", "178","Altos","CC", "60533354",cliente1,cidade1);
        Endereco endereco2 = new Endereco("Av. Matos", "999","sala 30","Centro", "38533354",cliente1,cidade2);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));

        clienteRepository.saveAll(Arrays.asList(cliente1));
        enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));

    }
}
