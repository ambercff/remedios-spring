package com.remedios.amber.curso.remedio;

import org.springframework.data.jpa.repository.JpaRepository;

// Passamos entre os sinais de <> o tipo de objeto mapeado e o tipo do identificador desse objeto
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

}
