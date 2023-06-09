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
import com.example.projeto2springwebmvc.models.enums.TamanhoRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;
import com.example.projeto2springwebmvc.modelsHelp.LinhaDoacoes;
import com.example.projeto2springwebmvc.modelsHelp.LinhaRoupa;
import com.example.projeto2springwebmvc.util.HelpAddDoacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/addDoacao")
    public String addDoacao(Model model) {
        model.addAttribute("doacao", new Doacao());
        model.addAttribute("roupaDoada", new Roupa_Doacao());
        model.addAttribute("roupa", new Roupa());
        model.addAttribute("pageTitle", "Nova Doação");

        return "addDoacao";
    }

    @PostMapping("/adicionarDoacao")
    public String adicionarDoacao(Doacao doacao, Roupa_Doacao roupa_doacao, Roupa roupa) {
        roupaDoacaoService.salvarRoupa_Doacao(roupa_doacao);

        doacao.setRoupa_doacao(roupa_doacao);
        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        Utilizador utilizadorLogadoGuardado = utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado);
        doacao.setUtilizador(utilizadorLogadoGuardado);
        doacao.setDataDaDoacao(LocalDate.now());

        TipoRoupa tipoRoupa = roupa.getTipoRoupa();
        TamanhoRoupa tamanhoRoupa = roupa.getTamanhoRoupa();
        CategoriaRoupa categoriaRoupa = helpAddDoacoes.adicionarAssociarCategoria(tipoRoupa);
        roupa.setCategoriaRoupa(categoriaRoupa);
        String imgSrc = helpAddDoacoes.adicionarAssociarImagem(tipoRoupa);
        roupa.setImageSrc(imgSrc);
        roupa.setRoupa_doacao(roupa_doacao);

        doacaoService.salvarDoacao(doacao);
        roupaService.salvarRoupa(roupa);

        int soma = 0;
        for (LinhaRoupa linhaRoupa : roupaService.buscarDadosParaStock()) {
            if (linhaRoupa.getTipoRoupa().equals(String.valueOf(tipoRoupa)) && linhaRoupa.getTamanhoRoupa().equals(String.valueOf(tamanhoRoupa))) {
                soma = soma + linhaRoupa.getQuantidade();
            }
        }

        for (Roupa r : roupaService.buscarTodas()) {
            if (r.getTipoRoupa().equals(tipoRoupa) && r.getTamanhoRoupa().equals(tamanhoRoupa)) {
                roupaService.atualizarRoupaStock(r, soma);
            }
        }

        return "redirect:/doacoes";
    }

    @GetMapping("/editar/{id}")
    public String mostraFormEditarDoacao(@PathVariable("id") Integer id, Model model) {
        Doacao doacao = doacaoService.getDoacaoPorId(id);
        Roupa_Doacao roupa_doacao = new Roupa_Doacao();
        Roupa roupa = new Roupa();

        List<Roupa_Doacao> roupa_doacaoList = roupaDoacaoService.roupa_doacaoList();
        List<Roupa> roupaList = roupaService.roupaList();

        for (Roupa_Doacao r : roupa_doacaoList) {
            if (r.getId_roupa_doacao() == doacaoService.getDoacaoPorId(id).getRoupa_doacao().getId_roupa_doacao()) {
                roupa_doacao = roupaDoacaoService.getRoupa_DoacaoPoId(r.getId_roupa_doacao());
            }
        }

        for (Roupa r : roupaList) {
            if (r.getRoupa_doacao().getId_roupa_doacao() == doacaoService.getDoacaoPorId(id).getRoupa_doacao().getId_roupa_doacao()) {
                roupa = roupaService.getRoupaPorId(r.getIdRoupa());
            }
        }

        model.addAttribute("roupaDoada", roupa_doacao);
        model.addAttribute("roupa", roupa);
        model.addAttribute("doacao", doacao);
        model.addAttribute("pageTitle", "Edita Doação (ID: " + id + ")");

        return "editarDoacao";
    }

    @PostMapping("/guardarEdicaoDoacao/{id}")
    public String guardarEdicaoDoacao(@PathVariable("id") Integer id, @ModelAttribute("doacao") Doacao doacao, @ModelAttribute("roupaDoada") Roupa_Doacao roupa_doacao, @ModelAttribute("roupa") Roupa roupa) {
        Doacao doacaoExistente = doacaoService.getDoacaoPorId(id);
        Roupa_Doacao roupaDoacaoExistente = roupaDoacaoService.getRoupa_DoacaoPoId(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());

        doacaoExistente.setDataDaDoacao(LocalDate.now());
        roupaDoacaoExistente.setQuantidade(roupa_doacao.getQuantidade());

        doacaoService.salvarDoacao(doacaoExistente);
        roupaDoacaoService.salvarRoupa_Doacao(roupaDoacaoExistente);
        roupaService.atualizarRoupa(doacaoExistente.getRoupa_doacao().getId_roupa_doacao(), String.valueOf(roupa.getTipoRoupa()), String.valueOf(roupa.getTamanhoRoupa()));

        TipoRoupa tipoRoupa = roupa.getTipoRoupa();
        TamanhoRoupa tamanhoRoupa = roupa.getTamanhoRoupa();
        int soma = 0;
        for (LinhaRoupa linhaRoupa : roupaService.buscarDadosParaStock()) {
            if (linhaRoupa.getTipoRoupa().equals(String.valueOf(tipoRoupa)) && linhaRoupa.getTamanhoRoupa().equals(String.valueOf(tamanhoRoupa))) {
                soma = soma + linhaRoupa.getQuantidade();
            }
        }

        for (Roupa r : roupaService.buscarTodas()) {
            if (r.getTipoRoupa().equals(tipoRoupa) && r.getTamanhoRoupa().equals(tamanhoRoupa)) {
                roupaService.atualizarRoupaStock(r, soma);
            }
        }

        return "redirect:/doacoes";
    }
}
