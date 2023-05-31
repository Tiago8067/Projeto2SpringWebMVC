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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    /*UtilizadorAutenticacaoController utilizadorAutenticacaoController = new UtilizadorAutenticacaoController();
    String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
    String utilizadorPasswordLogadoGuardado = UtilizadorAutenticacaoController.utilizadorPasswordLogado;*/


    public DoacaoDasRoupasController() {
        this.helpAddDoacoes = new HelpAddDoacoes();
    }

    @GetMapping()
    public String mostraDoacaoList(Model model) {
        List<LinhaDoacoes> roupasDoadas = doacaoService.doacoesDasRoupas();
        model.addAttribute("roupasDoadas", roupasDoadas);

        return "listDoacoes";
    }

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
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println(UtilizadorAutenticacaoController.utilizadorUsernameLogado);
        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        System.out.println(utilizadorUsernameLogadoGuardado);
        System.out.println(utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado).getUsername());
        Utilizador utilizadorLogadoGuardado = utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado);
        //System.out.println(utilizadorUsernameLogadoGuardado);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");

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
