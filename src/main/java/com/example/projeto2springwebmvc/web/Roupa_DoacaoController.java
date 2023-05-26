package com.example.projeto2springwebmvc.web;

import com.example.projeto2springwebmvc.repositories.Roupa_DoacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class Roupa_DoacaoController {
    private Roupa_DoacaoRepository roupa_doacaoRepository;
}
