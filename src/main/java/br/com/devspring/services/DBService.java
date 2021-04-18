package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.repository.FinancerioUserRepository;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.repository.MovimentacaoFinanceiraRepository;
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

        FinanceiroUser user1 = new FinanceiroUser("Alison", new BCryptPasswordEncoder().encode("dev"),"Alison",false);
        FinanceiroUser user2 = new FinanceiroUser("Joao", new BCryptPasswordEncoder().encode("123456"),"Joao",true);

        financerioUserRepository.saveAll(Arrays.asList(user1,user2));
    }
}
