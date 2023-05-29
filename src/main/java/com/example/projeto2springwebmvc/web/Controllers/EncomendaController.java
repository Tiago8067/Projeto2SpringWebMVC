package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.repositories.EncomendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EncomendaController {
    private EncomendaRepository encomendaRepository;
}
