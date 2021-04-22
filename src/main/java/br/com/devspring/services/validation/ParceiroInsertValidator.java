package br.com.devspring.services.validation;

import br.com.devspring.domain.enums.TipoParceiro;
import br.com.devspring.dto.ParceiroNewDTO;
import br.com.devspring.repository.ParceiroRepository;
import br.com.devspring.resources.exception.FieldMessage;
import br.com.devspring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ParceiroInsertValidator implements ConstraintValidator<ParceiroInsert, ParceiroNewDTO> {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Override
    public void initialize(ParceiroInsert ann) {
    }

    @Override
    public boolean isValid(ParceiroNewDTO objDto, ConstraintValidatorContext context) {
        //Objeto criado para tratar a validação de campos.
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista
        /*if (objDto.getTipoParceiro() == null){
            list.add(new FieldMessage("tipo", "Tipo nao pode ser nulo"));
        }*/

        if(objDto.getTipoParceiro().equals(TipoParceiro.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CPF Inválido"));
        }
        if(objDto.getTipoParceiro().equals(TipoParceiro.PESSOJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido"));
        }

        if (parceiroRepository.findByEmail(objDto.getEmail()) != null){
            list.add(new FieldMessage("cpfOuCnpj","Email existente"));
        }

        //Percorrendo a lista de erro personalizada e inserindo na lista do Spring
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFielName()).addConstraintViolation();
        }

        //se alguma validação não for atendida, será adicionado um objeto na lista, logo o método será falso.
        return list.isEmpty();
    }
}