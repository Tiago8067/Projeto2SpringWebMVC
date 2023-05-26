package com.example.projeto2springwebmvc.web;

import com.example.projeto2springwebmvc.repositories.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FornecedorController {
    private FornecedorRepository fornecedorRepository;
}
