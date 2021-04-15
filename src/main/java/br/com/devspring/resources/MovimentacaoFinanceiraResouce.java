package br.com.devspring.resources;

import br.com.devspring.services.MovimentacaoFinanceiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movimentacaoFinanceira")
public class MovimentacaoFinanceiraResouce {

    @Autowired
    private MovimentacaoFinanceiraService movimentacaoFinanceiraService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(movimentacaoFinanceiraService.get(), HttpStatus.OK);
    }
}
