package com.doacaosangue.api.service;

import com.doacaosangue.api.dto.HospitalDTO;
import com.doacaosangue.api.model.HospitalModel;
import com.doacaosangue.api.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<HospitalModel> listarHospitais() {
        return hospitalRepository.findAll();
    }

    public Optional<HospitalModel> encontrarHospitalPorId(Long id) {
        return hospitalRepository.findById(id);
    }

    public HospitalModel salvarHospital(HospitalDTO hospitalDTO) {
        HospitalModel hospital = new HospitalModel();
        hospital.setNome(hospitalDTO.getNome());
        hospital.setEndereco(hospitalDTO.getEndereco());
        hospital.setContato(hospitalDTO.getContato());
        return hospitalRepository.save(hospital);
    }

    public HospitalModel atualizarHospital(Long id, HospitalDTO hospitalDTO) {
        Optional<HospitalModel> optionalHospital = hospitalRepository.findById(id);
        if (optionalHospital.isPresent()) {
            HospitalModel hospital = optionalHospital.get();
            hospital.setNome(hospitalDTO.getNome());
            hospital.setEndereco(hospitalDTO.getEndereco());
            hospital.setContato(hospitalDTO.getContato());
            return hospitalRepository.save(hospital);
        }
        throw new NoSuchElementException("Hospital não encontrado com o ID: " + id);
    }

    public void deletarHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
