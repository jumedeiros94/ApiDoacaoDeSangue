package com.doacaosangue.api.service;

import com.doacaosangue.api.dto.AgendamentoDTO;
import com.doacaosangue.api.model.AgendamentoModel;
import com.doacaosangue.api.model.DoadorModel;
import com.doacaosangue.api.model.HospitalModel;
import com.doacaosangue.api.repository.AgendamentoRepository;
import com.doacaosangue.api.repository.DoadorRepository;
import com.doacaosangue.api.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final DoadorRepository doadorRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository, DoadorRepository doadorRepository, HospitalRepository hospitalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.doadorRepository = doadorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<AgendamentoModel> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Optional<AgendamentoModel> encontrarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public AgendamentoModel salvarAgendamento(AgendamentoDTO agendamentoDTO) {
        AgendamentoModel agendamento = new AgendamentoModel();
        agendamento.setData(agendamentoDTO.getData());
        agendamento.setHora(agendamentoDTO.getHora());

        DoadorModel doador = doadorRepository.findById(agendamentoDTO.getDoadorId())
                .orElseThrow(() -> new NoSuchElementException("Doador não encontrado com o ID: " + agendamentoDTO.getDoadorId()));
        agendamento.setDoador(doador);

        HospitalModel hospital = hospitalRepository.findById(agendamentoDTO.getHospitalId())
                .orElseThrow(() -> new NoSuchElementException("Hospital não encontrado com o ID: " + agendamentoDTO.getHospitalId()));
        agendamento.setHospital(hospital);

        return agendamentoRepository.save(agendamento);
    }

    public AgendamentoModel atualizarAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        Optional<AgendamentoModel> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isPresent()) {
            AgendamentoModel agendamento = optionalAgendamento.get();
            return agendamentoRepository.save(agendamento);
        }
        throw new NoSuchElementException("Agendamento não encontrado com o ID: " + id);
    }

    public void deletarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
