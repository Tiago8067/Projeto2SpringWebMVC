package com.example.projeto2springwebmvc.web;

import com.example.projeto2springwebmvc.repositories.DoacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class DoacaoController {
    private DoacaoRepository doacaoRepository;
}
