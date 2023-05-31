package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.LocalizacaoService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.Localizacao;
import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class UtilizadorController {
    private UtilizadorRepository utilizadorRepository;
    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/novo")
    public String mostraNovoForm(Model model) {

        for (Utilizador u : utilizadorService.utilizadorList()) {
            if (u.getTipoUtilizador().equals(TipoUtilizador.CLIENTE)){
                System.out.println(u.getNome());
            }
        }

        model.addAttribute("utilizador", new Utilizador());
        model.addAttribute("localizacao", new Localizacao());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrar(Utilizador utilizador, Localizacao localizacao) {
        localizacaoService.salvarLocalizacao(localizacao);
        utilizador.setLocalizacao(localizacao);
        utilizadorService.salvarCliente(utilizador);

        return "login";
    }

    @GetMapping(path = "/homePage")
    public String homePage() {
        return "homePage";
    }

    @GetMapping(path = "/index")
    public String utilizadores(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "2") int size,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword
    ) {
        Page<Utilizador> utilizadorPage = utilizadorRepository.findByUsernameContains(keyword, PageRequest.of(page, size));
        //Page<Utilizador> utilizadorPage = utilizadorRepository.findAll(PageRequest.of(page, size));
        //List<Utilizador> utilizadors = utilizadorRepository.findAll();
        model.addAttribute("listUtilizadores", utilizadorPage.getContent());
        model.addAttribute("pages", new int[utilizadorPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "utilizadores";
    }

    @GetMapping("/delete")
    public String delete(Integer id, String keyword, int page) {
        utilizadorRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Utilizador> utilizadorList() {
        return utilizadorRepository.findAll();
    }

    @GetMapping("/formUtilizadors")
    public String formUtilizadors(Model model) {
        model.addAttribute("utilizador", new Utilizador());
        return "formUtilizadors";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Utilizador utilizador, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formUtilizadors";
        utilizadorRepository.save(utilizador);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editUtilizador")
    public String editUtilizador(Model model, Integer id, String keyword, int page) {
        Utilizador utilizador = utilizadorRepository.findById(id).orElse(null);
        if (utilizador == null) throw new RuntimeException("Utilizador Necessario");
        model.addAttribute("utilizador", utilizador);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editUtilizador";
    }
}
