package com.remedios.amber.curso.services.remedios;

import com.remedios.amber.curso.dtos.remedios.RemedioCreateDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioDetalhamentoDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioListagemDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioUpdateDTO;
import com.remedios.amber.curso.entities.remedios.Remedio;
import com.remedios.amber.curso.exceptions.NotFoundException;
import com.remedios.amber.curso.repositories.remedios.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemedioService {

    @Autowired
    private RemedioRepository repository;

    public RemedioDetalhamentoDTO cadastro(RemedioCreateDTO data) {
        Remedio remedio = new Remedio(data);

        repository.save(remedio);

        return new RemedioDetalhamentoDTO(remedio);
    }

    public List<RemedioListagemDTO> getAll(){
        // A sintaxe DadosListagemRemedio::new serve para chamar o construtor
        return repository.findAllByAtivoTrue().stream().map(RemedioListagemDTO::new).toList();
    }

    public RemedioDetalhamentoDTO atualizar(RemedioUpdateDTO dados) {
        Remedio remedio = repository.findById(dados.id()).orElseThrow(() -> new NotFoundException("Remédio não encontrado!"));

        remedio.atualizarInformacoes(dados);

        return new RemedioDetalhamentoDTO(remedio);
    }

    public RemedioDetalhamentoDTO inativar(Long id_remedio) {
        Remedio remedio = repository.getReferenceById(id_remedio);

        remedio.setAtivo(false);

        return new RemedioDetalhamentoDTO(remedio);
    }

    public RemedioDetalhamentoDTO reativar(Long id_remedio) {
        Remedio remedio = repository.getReferenceById(id_remedio);

        remedio.setAtivo(true);

        return new RemedioDetalhamentoDTO(remedio);
    }

    public RemedioDetalhamentoDTO getById(Long id_remedio) {
        Remedio remedio = repository.getReferenceById(id_remedio);

        return new RemedioDetalhamentoDTO(remedio);
    }
}
