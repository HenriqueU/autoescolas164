package br.com.senai.autoescolas164.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthy-check")
public class HealthyCheckController {

    @GetMapping
    public String healthyCheck() {
        return "Verificação de integridade da Auto-Escola S164 ok!";
    }
}
