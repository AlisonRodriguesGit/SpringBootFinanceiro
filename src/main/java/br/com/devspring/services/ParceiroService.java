package br.com.devspring.services;

import br.com.devspring.domain.*;
import br.com.devspring.domain.enums.TipoParceiro;
import br.com.devspring.dto.ParceiroDTO;
import br.com.devspring.dto.ParceiroNewDTO;
import br.com.devspring.repository.EnderecoRepository;
import br.com.devspring.repository.ParceiroRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<Parceiro> findAll(Pageable pageable) {
        return parceiroRepository.findAll(pageable);
    }

    private void verifyIfParceiroExist(Long id){
        if (parceiroRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Parceiro.class.getName());
        }
    }

    public Parceiro findById(Long id){
        Optional<Parceiro> parceiro = parceiroRepository.findById(id);
        return parceiro.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Parceiro.class.getName()));
    }

    @Transactional
    public Parceiro save(Parceiro parceiro){
        parceiro.setId(null);
        parceiro = parceiroRepository.save(parceiro);
        enderecoRepository.saveAll(parceiro.getEnderecos());
        return parceiro;
    }

    public Parceiro DTOfromEntidade(ParceiroNewDTO objDTO){
        Parceiro parceiro = new Parceiro(objDTO.getName(),objDTO.getEmail(),objDTO.getCpfOuCnpj(), TipoParceiro.toEnum(objDTO.getTipoParceiro()));
        Cidade cidade = new Cidade(objDTO.getCidadeID(),null,null);
        Endereco endereco = new Endereco(objDTO.getLogradouro(),objDTO.getNumero(),objDTO.getComplemento(),objDTO.getBairro()
                                        , objDTO.getCep(), parceiro, cidade);
        parceiro.getEnderecos().add(endereco);
        parceiro.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2() != null){
            parceiro.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3() != null){
            parceiro.getTelefones().add(objDTO.getTelefone3());
        }

        return parceiro;
    }

    public ParceiroDTO fromDTO(Parceiro obj){
        ParceiroDTO parceiroDTO = new ParceiroDTO(obj.getName(),obj.getEmail(),obj.getCpfOuCnpj(), obj.getTipoParceiro());
        parceiroDTO.getEnderecos().addAll(obj.getEnderecos().stream().collect(Collectors.toList()));
        parceiroDTO.getTelefones().addAll(obj.getTelefones().stream().collect(Collectors.toList()));

        return parceiroDTO;
    }
}
