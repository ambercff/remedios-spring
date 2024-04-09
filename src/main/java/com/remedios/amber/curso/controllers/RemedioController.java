package com.remedios.amber.curso.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indicando para o Spring que ele tem que inicilizar essa classe como um controller
@RequestMapping("/remedios") // Mapear o endpoint
public class RemedioController {
    @PostMapping
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro
    public void cadastrar(@RequestBody String json){
        System.out.println(json);
    }
}
