package com.example.projeto2springwebmvc.web;

import com.example.projeto2springwebmvc.repositories.RoupaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class RoupaController {
    private RoupaRepository roupaRepository;
}
