package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.LocalizacaoService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.Localizacao;
import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@NoArgsConstructor
@AllArgsConstructor
public class UtilizadorAutenticacaoController {
    private UtilizadorRepository utilizadorRepository;
    @Autowired
    private UtilizadorService utilizadorService;
    @Autowired
    private LocalizacaoService localizacaoService;

    public static String utilizadorUsernameLogado;

    @GetMapping(path = "/login")
    public String login(Model model) {
        model.addAttribute("loginResquest", new Utilizador());
        return "login";
    }

    @GetMapping("/novo")
    public String mostraNovoForm(Model model) {
        model.addAttribute("utilizador", new Utilizador());
        model.addAttribute("localizacao", new Localizacao());

        return "registro";
    }

    @PostMapping("/registar")
    public String registrar(Utilizador utilizador, Localizacao localizacao) {
        localizacaoService.salvarLocalizacao(localizacao);
        utilizador.setLocalizacao(localizacao);
        utilizadorService.salvarCliente(utilizador);

        return "login";
    }

    @PostMapping("/entrar")
    public String entrar(@ModelAttribute Utilizador utilizador, Model model) {
        Optional<Utilizador> autenticado = utilizadorService.verificaDadosLogin(utilizador.getUsername(), utilizador.getPassword());

        if (autenticado.isPresent() && autenticado.get().getTipoUtilizador().equals(TipoUtilizador.CLIENTE) && autenticado.get().getEstadoUtilizador().equals(EstadoUtilizador.ATIVO)) {

            utilizadorUsernameLogado = autenticado.get().getUsername();
            // todo -> mandar username do user logado para o dropdopwn

            return "redirect:/homePage";
        } else {
            return "login";
        }
    }
}
