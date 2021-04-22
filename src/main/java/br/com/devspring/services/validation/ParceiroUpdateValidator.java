package br.com.devspring.services.validation;

import br.com.devspring.domain.Parceiro;
import br.com.devspring.domain.enums.TipoParceiro;
import br.com.devspring.dto.ParceiroDTO;
import br.com.devspring.dto.ParceiroNewDTO;
import br.com.devspring.dto.ParceiroUpdateDTO;
import br.com.devspring.repository.ParceiroRepository;
import br.com.devspring.resources.exception.FieldMessage;
import br.com.devspring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParceiroUpdateValidator implements ConstraintValidator<ParceiroUpdate, ParceiroUpdateDTO> {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Autowired
    private HttpServletRequest request; //Permite recuperar o ID informado na URI

    @Override
    public void initialize(ParceiroUpdate ann) {
    }

    @Override
    public boolean isValid(ParceiroUpdateDTO objDto, ConstraintValidatorContext context) {
        //Como pode ter vários atributos na URI é necessario cria um mapa.
        Map<String, String> map =  (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        //Recuperando o Id passado na URI
        Long uriId = Long.parseLong(map.get("id"));

        //Objeto criado para tratar a validação de campos.
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista
        /*if (objDto.getTipoParceiro() == null){
            list.add(new FieldMessage("tipo", "Tipo nao pode ser nulo"));
        }*/

        Parceiro parceiro = parceiroRepository.findByEmail(objDto.getEmail());

        if (parceiro != null && !parceiro.getId().equals(uriId)){
            list.add(new FieldMessage("email","Email existente"));
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