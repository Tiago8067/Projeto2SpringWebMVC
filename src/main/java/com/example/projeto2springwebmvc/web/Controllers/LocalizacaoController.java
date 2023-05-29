package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.repositories.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class LocalizacaoController {
    private LocalizacaoRepository localizacaoRepository;
}
