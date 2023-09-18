package com.doacaosangue.api.service;

import com.doacaosangue.api.dto.DoadorDTO;
import com.doacaosangue.api.model.DoadorModel;
import com.doacaosangue.api.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    @Autowired
    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public List<DoadorModel> listarDoadores() {
        return doadorRepository.findAll();
    }

    public Optional<DoadorModel> encontrarDoadorPorId(Long id) {
        return doadorRepository.findById(id);
    }

    public DoadorModel salvarDoador(DoadorDTO doadorDTO) {
        DoadorModel doador = new DoadorModel();
        doador.setNome(doadorDTO.getNome());
        doador.setTipoSanguineo(doadorDTO.getTipoSanguineo());
        return doadorRepository.save(doador);
    }

    public DoadorModel atualizarDoador(Long id, DoadorDTO doadorDTO) {
        Optional<DoadorModel> optionalDoador = doadorRepository.findById(id);
        if (optionalDoador.isPresent()) {
            DoadorModel doador = optionalDoador.get();
            doador.setNome(doadorDTO.getNome());
            doador.setTipoSanguineo(doadorDTO.getTipoSanguineo());
            return doadorRepository.save(doador);
        }
        throw new NoSuchElementException("Doador não encontrado com o ID: " + id);
    }

    public void deletarDoador(Long id) {
        doadorRepository.deleteById(id);
    }
}
