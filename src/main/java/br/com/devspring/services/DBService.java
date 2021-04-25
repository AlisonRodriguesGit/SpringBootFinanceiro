package br.com.devspring.services;

import br.com.devspring.domain.*;
import br.com.devspring.domain.enums.Perfil;
import br.com.devspring.domain.enums.TipoCusto;
import br.com.devspring.domain.enums.TipoLancamento;
import br.com.devspring.domain.enums.TipoParceiro;
import br.com.devspring.repository.*;
import br.com.devspring.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Service
public class DBService {

    @Autowired
    private BandeiraCartaoRepository bandeiraCartaoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;
    @Autowired
    private CentroResultadoRepository centroResultadoRepository;
    @Autowired
    private FinancerioUserRepository financerioUserRepository;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ParceiroRepository parceiroRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private PedidoRepository pedidoepository;

    public void instantiateTestDataBase(){
        BandeiraCartao bandeiraMasterCard = new BandeiraCartao("MASTERCARD");
        BandeiraCartao bandeiraVisa = new BandeiraCartao("VISA");
        BandeiraCartao bandeiraElo = new BandeiraCartao("ELO");

        FormaPagamento formaPagamento1 = new FormaPagamento("Dinheiro");

        FormaPagamentoCartao formaPagamento2 = new FormaPagamentoCartao("Cartão",TipoLancamento.CREDITO,bandeiraMasterCard);
        FormaPagamentoCartao formaPagamento3 = new FormaPagamentoCartao("Cartão",TipoLancamento.CREDITO,bandeiraVisa);
        FormaPagamentoCartao formaPagamento4 = new FormaPagamentoCartao("Cartão",TipoLancamento.CREDITO,bandeiraElo);

        FormaPagamentoCartao formaPagamento5 = new FormaPagamentoCartao("Cartão",TipoLancamento.DEBITO,bandeiraMasterCard);
        FormaPagamentoCartao formaPagamento6 = new FormaPagamentoCartao("Cartão",TipoLancamento.DEBITO,bandeiraVisa);
        FormaPagamentoCartao formaPagamento7 = new FormaPagamentoCartao("Cartão",TipoLancamento.DEBITO,bandeiraElo);

        bandeiraMasterCard.getFormasPagamento().addAll(Arrays.asList(formaPagamento2));
        bandeiraVisa.getFormasPagamento().addAll(Arrays.asList(formaPagamento3));
        bandeiraElo.getFormasPagamento().addAll(Arrays.asList(formaPagamento4));

        bandeiraCartaoRepository.saveAll(Arrays.asList(bandeiraMasterCard,bandeiraVisa,bandeiraElo));

        FinanceiroUser user1 = new FinanceiroUser("Alison", pe.encode("dev"),"Alison","alison@gmail.com");
        FinanceiroUser user2 = new FinanceiroUser("Joao", pe.encode("123456"),"Joao","joao@gmail.com");
        user2.addPerfil(Perfil.ADMIN);

        financerioUserRepository.saveAll(Arrays.asList(user1,user2));

        Estado estado1 = new Estado("Ceará");
        Estado estado2 = new Estado("São Paulo");

        Cidade cidade1 = new Cidade(null,"Fortaleza",estado1);
        Cidade cidade2 = new Cidade(null,"São Paulo",estado2);
        Cidade cidade3 = new Cidade(null,"Campinas",estado2);

        estado1.getCidades().addAll(Arrays.asList(cidade1));
        estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));

        estadoRepository.saveAll(Arrays.asList(estado1,estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));

        Parceiro parceiro1 = new Parceiro("CAGECE", "cagece@gmail.com", "94805951095", TipoParceiro.PESSOJURIDICA);
        parceiro1.getTelefones().addAll(Arrays.asList("85986854545","85986854456"));

        Endereco endereco1 = new Endereco("Rua 1117", "178","Altos","CC", "60533354", parceiro1,cidade1);
        Endereco endereco2 = new Endereco("Av. Matos", "999","sala 30","Centro", "38533354", parceiro1,cidade2);

        parceiro1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));

        Parceiro parceiro2 = new Parceiro("COELCE", "coelce@gmail.com", "94805951095", TipoParceiro.PESSOJURIDICA);
        parceiro2.getTelefones().addAll(Arrays.asList("85986854545","85986854456"));

        Endereco endereco3 = new Endereco("Rua CENTRO", "178","Altos","CENTRO", "60533354", parceiro2,cidade1);

        parceiro2.getEnderecos().addAll(Arrays.asList(endereco3));

        Parceiro parceiro3 = new Parceiro("SANTANDER", "santander@gmail.com", "94805951095", TipoParceiro.PESSOJURIDICA);
        parceiro3.getTelefones().addAll((Arrays.asList("859585457557")));
        parceiro3.getEnderecos().addAll(Arrays.asList(endereco3));

        Parceiro parceiro4 = new Parceiro("ITAU", "itau@gmail.com", "94805951095", TipoParceiro.PESSOJURIDICA);
        parceiro4.getTelefones().addAll((Arrays.asList("859585457557")));
        Endereco endereco4 = new Endereco("Rua do Itau", "178","Altos","CENTRO", "60533354", parceiro2,cidade1);
        parceiro4.getEnderecos().addAll(Arrays.asList(endereco4));

        parceiroRepository.saveAll(Arrays.asList(parceiro1,parceiro2,parceiro3,parceiro4));
        enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2,endereco3,endereco4));


        String datateste = "22/04/2021 12:08:00";
        LocalDateTime datepagamento = LocalDateTimeUtils.converterStringParaLocalDateTime(datateste);
        Date dataAtual = LocalDateTimeUtils.converterStringParaDate("21/04/2021 14:15");

        MovimentacaoFinanceira mov1 = new MovimentacaoFinanceira(parceiro1,"pagamento CAGECE",dataAtual,datepagamento,100.00,100.00,TipoCusto.FIXO);
        MovimentacaoFinanceira mov2 = new MovimentacaoFinanceira(parceiro2,"pagamento ENEL",dataAtual,datepagamento,50.00,50.00,TipoCusto.FIXO);
        MovimentacaoFinanceira mov3 = new MovimentacaoFinanceira(parceiro3,"pagamento SANTANDER",dataAtual,datepagamento,450.00,450.00,TipoCusto.VARIAVEL);
        MovimentacaoFinanceira mov4 = new MovimentacaoFinanceira(parceiro4,"pagamento ITAU",dataAtual,datepagamento,1000.00,1000.00,TipoCusto.VARIAVEL);

        formaPagamento1.getMovimetacoesFinanceira().addAll(Arrays.asList(mov1,mov2,mov3));
        formaPagamento2.getMovimetacoesFinanceira().addAll(Arrays.asList(mov3));
        formaPagamento3.getMovimetacoesFinanceira().addAll(Arrays.asList(mov4));
        formaPagamento4.getMovimetacoesFinanceira().addAll(Arrays.asList(mov4));

        mov1.getFormasPagamento().addAll(Arrays.asList(formaPagamento1));
        mov2.getFormasPagamento().addAll(Arrays.asList(formaPagamento1));
        mov3.getFormasPagamento().addAll(Arrays.asList(formaPagamento1,formaPagamento2));
        mov4.getFormasPagamento().addAll(Arrays.asList(formaPagamento3,formaPagamento4));

        formaPagamentoRepository.saveAll(Arrays.asList(formaPagamento1,formaPagamento2,formaPagamento3,formaPagamento4
                ,formaPagamento5,formaPagamento6,formaPagamento7));
        movimentacaoFinanceiraRepository.saveAll(Arrays.asList(mov1,mov2,mov3,mov4));

        Parceiro parceiroPedido = new Parceiro("Alison", "alison5@gmail.com", "94805951095", TipoParceiro.PESSOAFISICA);
        parceiroPedido.getTelefones().addAll(Arrays.asList("85986854545","85986854456"));

        Endereco endereco = new Endereco("Rua 1117", "178","Altos","CC", "60533354", parceiroPedido,cidade1);

        parceiroPedido.getEnderecos().addAll(Arrays.asList(endereco));

        parceiroRepository.saveAll(Arrays.asList(parceiro1,parceiro2,parceiro3,parceiro4,parceiroPedido));
        enderecoRepository.saveAll(Arrays.asList(endereco));

        Categoria cat1 = new Categoria("Cabelo");
        Produto produto1 = new Produto("Corte",10.00,cat1);
        Produto produto2 = new Produto("Hidratacao",20.00,cat1);
        Produto produto3 = new Produto("Corte Criança",30.00,cat1);
        Produto produto4 = new Produto("Corte Especial",30.00,cat1);
        cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3,produto4));
        Categoria cat2 = new Categoria("Barba");
        Produto produto5 = new Produto("Design",5.00,cat2);
        cat2.getProdutos().add(produto5);
        Categoria cat3 = new Categoria("Pele");
        Produto produto6 = new Produto("Limpeza",5.00,cat3);
        cat3.getProdutos().add(produto6);
        Categoria cat4 = new Categoria("Outros");
        Produto produto7 = new Produto("Disign Sobrancelha",10.00,cat4);
        cat4.getProdutos().add(produto7);

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4));
        produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,produto7));

        Pedido ped1 = new Pedido(dataAtual,parceiroPedido);
        ItemPedido ip1 = new ItemPedido(ped1,produto1,0.00,1.00,10.00);
        ItemPedido ip2 = new ItemPedido(ped1,produto2,0.00,1.00,20.00);

        Pedido ped2 = new Pedido(dataAtual,parceiroPedido);
        ItemPedido ip3 = new ItemPedido(ped2,produto1,0.00,1.00,10.00);
        ItemPedido ip4 = new ItemPedido(ped2,produto3,0.00,1.00,05.00);

        ped1.getItensPedido().addAll(Arrays.asList(ip1,ip2));
        ped2.getItensPedido().addAll(Arrays.asList(ip3,ip4));

        produto1.getItensPedido().addAll(Arrays.asList(ip1,ip3));
        produto2.getItensPedido().addAll(Arrays.asList(ip2));
        produto3.getItensPedido().addAll(Arrays.asList(ip4));

        CentroResultado centroResultado = new CentroResultado("PEDIDOS");
        MovimentacaoFinanceira mov = new MovimentacaoFinanceira(parceiroPedido,"Recebimento Pedido1",dataAtual,null,100.00,null,TipoCusto.VARIAVEL);
        formaPagamento1.getMovimetacoesFinanceira().addAll(Arrays.asList(mov));

        mov.getFormasPagamento().addAll(Arrays.asList(formaPagamento1));
        mov.setPedido(ped1);
        mov.getCentrosResultado().addAll(Arrays.asList(centroResultado));
        centroResultado.getMovimetacoesFinanceira().addAll(Arrays.asList(mov));

        ped1.getMovimentacoesFinanceira().addAll(Arrays.asList(mov));


        pedidoepository.saveAll(Arrays.asList(ped1,ped2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4));
        centroResultadoRepository.saveAll(Arrays.asList(centroResultado));
        movimentacaoFinanceiraRepository.saveAll(Arrays.asList(mov));
        formaPagamentoRepository.saveAll(Arrays.asList(formaPagamento1));
    }
}
