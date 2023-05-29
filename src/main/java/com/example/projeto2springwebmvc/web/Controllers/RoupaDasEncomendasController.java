package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.repositories.RoupaDasEncomendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class RoupaDasEncomendasController {
    private RoupaDasEncomendasRepository roupaDasEncomendasRepository;
}
