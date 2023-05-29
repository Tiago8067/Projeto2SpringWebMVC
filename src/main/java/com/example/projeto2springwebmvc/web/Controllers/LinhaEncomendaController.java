package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.repositories.LinhaEncomendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class LinhaEncomendaController {
    private LinhaEncomendaRepository linhaEncomendaRepository;
}
