package com.remedios.amber.curso.remedios.repositories;

import com.remedios.amber.curso.remedios.dtos.DadosListagemRemedio;
import com.remedios.amber.curso.remedios.entities.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

// Passamos entre os sinais de <> o tipo de objeto mapeado e o tipo do identificador desse objeto
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    List<Remedio> findAllByAtivoTrue();
}
