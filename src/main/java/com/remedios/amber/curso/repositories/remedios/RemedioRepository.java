package com.remedios.amber.curso.repositories.remedios;

import com.remedios.amber.curso.entities.remedios.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Passamos entre os sinais de <> o tipo de objeto mapeado e o tipo do identificador desse objeto
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    List<Remedio> findAllByAtivoTrue();
}
