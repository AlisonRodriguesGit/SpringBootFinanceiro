package br.com.devspring.services;

import br.com.devspring.domain.Parceiro;
import br.com.devspring.repository.ParceiroRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

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
}
