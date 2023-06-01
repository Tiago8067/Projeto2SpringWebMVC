package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.DoacaoService;
import com.example.projeto2springwebmvc.Services.RoupaService;
import com.example.projeto2springwebmvc.Services.Roupa_DoacaoService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.CategoriaRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;
import com.example.projeto2springwebmvc.modelsHelp.LinhaDoacoes;
import com.example.projeto2springwebmvc.util.HelpAddDoacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/doacoes")
public class DoacaoDasRoupasController {
    @Autowired
    private DoacaoService doacaoService;
    @Autowired
    private Roupa_DoacaoService roupaDoacaoService;
    @Autowired
    private RoupaService roupaService;
    @Autowired
    private UtilizadorService utilizadorService;
    private final HelpAddDoacoes helpAddDoacoes;

    public DoacaoDasRoupasController() {
        this.helpAddDoacoes = new HelpAddDoacoes();
    }

    @GetMapping()
    public String mostraDoacaoList(Model model) {
        /*List<LinhaDoacoes> roupasDoadas = doacaoService.doacoesDasRoupas();*/
        List<LinhaDoacoes> roupasDoadas = new ArrayList<>();

        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        for (LinhaDoacoes l : doacaoService.doacoesDasRoupas()) {
            if (l.getUsername().equals(utilizadorUsernameLogadoGuardado)) {
                roupasDoadas.add(l);
            }
        }

        model.addAttribute("roupasDoadas", roupasDoadas);

        return "listDoacoes";
    }

    /*@GetMapping()
    public String mostraDoacaoList(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size,
                                   @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<LinhaDoacoes> roupasDoadas = (Page<LinhaDoacoes>) doacaoService.doacoesDasRoupas(keyword, PageRequest.of(page, size));
        model.addAttribute("roupasDoadas", roupasDoadas);
        model.addAttribute("pages", new int[roupasDoadas.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "listDoacoes";
    }*/

    @GetMapping("/addDoacao")
    public String addDoacao(Model model) {
        model.addAttribute("doacao", new Doacao());
        model.addAttribute("roupaDoada", new Roupa_Doacao());
        model.addAttribute("roupa", new Roupa());

        return "addDoacao";
    }

    @PostMapping("/addDoacao")
    public String adicionarDoacao(Doacao doacao, Roupa_Doacao roupa_doacao, Roupa roupa) {
        roupaDoacaoService.salvarRoupa_Doacao(roupa_doacao);

        doacao.setRoupa_doacao(roupa_doacao);
        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        Utilizador utilizadorLogadoGuardado = utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado);
        doacao.setUtilizador(utilizadorLogadoGuardado);

        TipoRoupa tipoRoupa = roupa.getTipoRoupa();
        CategoriaRoupa categoriaRoupa = helpAddDoacoes.adicionarAssociarCategoria(tipoRoupa);
        roupa.setCategoriaRoupa(categoriaRoupa);
        String imgSrc = helpAddDoacoes.adicionarAssociarImagem(tipoRoupa);
        roupa.setImageSrc(imgSrc);
        roupa.setRoupa_doacao(roupa_doacao);

        doacaoService.salvarDoacao(doacao);
        roupaService.salvarRoupa(roupa);

        return "redirect:/doacoes";
    }
}
