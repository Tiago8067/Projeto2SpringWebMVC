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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editar/{id}")
    public String mostraFormEditarDoacao(@PathVariable("id") Integer id, Model model) {
        Doacao doacao = doacaoService.getDoacaoPorId(id);
        /*Roupa_Doacao roupa_doacao = roupaDoacaoService.getRoupa_Doacao(doacao);
        Roupa roupa = roupaService.getRoupa(roupa_doacao);*/
        Roupa_Doacao roupa_doacao = new Roupa_Doacao();
        Roupa roupa = new Roupa();

        List<Roupa_Doacao> roupa_doacaoList = roupaDoacaoService.roupa_doacaoList();
        List<Roupa> roupaList = roupaService.roupaList();

        //Integer idRoupa_doacao = doacaoService.getDoacaoPorId(id).getIdDoacao();

        /*System.out.println(roupaDoacaoService.roupa_doacaoList());*/

        for (Roupa_Doacao r : roupa_doacaoList) {
            System.out.println(r.getId_roupa_doacao());
            if (r.getId_roupa_doacao() == doacaoService.getDoacaoPorId(id).getRoupa_doacao().getId_roupa_doacao()) {
                /*System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(r.getId_roupa_doacao());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++");*/
                roupa_doacao = roupaDoacaoService.getRoupa_DoacaoPoId(r.getId_roupa_doacao());
            }
        }

        for (Roupa r : roupaList) {
            System.out.println(r.getIdRoupa());
            if (r.getRoupa_doacao().getId_roupa_doacao() == doacaoService.getDoacaoPorId(id).getRoupa_doacao().getId_roupa_doacao()) {
                /*System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(r.getIdRoupa());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++");*/
                roupa = roupaService.getRoupaPorId(r.getIdRoupa());
            }
        }

        /*System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(roupa_doacao.getId_roupa_doacao());*/
        //System.out.println(roupa_doacao.toString());
        model.addAttribute("roupaDoada", roupa_doacao);

        //System.out.println(roupa.getIdRoupa());
        //System.out.println(roupa.toString());
        model.addAttribute("roupa", roupa);

        model.addAttribute("doacao", doacao);
        model.addAttribute("pageTitle", "Edita Doação (ID: " + id + ")");

        return "editarDoacao";
    }

    @PostMapping("/guardarEdicaoDoacao/{id}")
    public String guardarEdicaoDoacao(@PathVariable("id") Integer id, @ModelAttribute("doacao") Doacao doacao, @ModelAttribute("roupaDoada") Roupa_Doacao roupa_doacao, @ModelAttribute("roupa") Roupa roupa) {

        System.out.println(doacaoService.getDoacaoPorId(id).getIdDoacao());
        System.out.println(doacaoService.getDoacaoPorId(id).getUtilizador().getUsername());

        Doacao doacaoExistente = doacaoService.getDoacaoPorId(id);
        Roupa_Doacao roupaDoacaoExistente = roupaDoacaoService.getRoupa_DoacaoPoId(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());
        //Roupa roupaExistente = roupaService.buscarRoupaPorIdChaveEstrangeira(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());
        Roupa roupaExistente = roupaService.getRoupaPorIdChaveEstrangeira(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());

        System.out.println(roupaDoacaoExistente.getQuantidade());

        System.out.println("-------------------------------------");
        System.out.println(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());
        System.out.println("-------------------------------------");

        /*Roupa roupaExistente = roupaService.getRoupaPorId(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());*/
        //Roupa roupaExistente = roupaService.buscarRoupaPorId(doacaoExistente.getRoupa_doacao().getId_roupa_doacao());

        roupaDoacaoExistente.setQuantidade(roupa_doacao.getQuantidade());

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(roupaExistente.getIdRoupa());
        System.out.println(roupaExistente.getTipoRoupa());
        System.out.println(roupaExistente.getTamanhoRoupa());
        System.out.println(roupaExistente.getCategoriaRoupa());
        System.out.println(roupaExistente.getImageSrc());
        System.out.println(roupaExistente.getStock());
        //System.out.println(roupa.getRoupa_doacao().getId_roupa_doacao()); => lança ERRO
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(roupaDoacaoExistente.getId_roupa_doacao());
        System.out.println(roupaDoacaoExistente.getQuantidade());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");


        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(doacaoExistente.getIdDoacao());
        System.out.println(doacaoExistente.getDataDaDoacao());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");

        /*System.out.println(roupaExistente.getTipoRoupa());
        System.out.println(roupaExistente.getTamanhoRoupa());
        System.out.println(roupaExistente.getCategoriaRoupa());*/
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(roupa.getIdRoupa());
        System.out.println(roupa.getTipoRoupa());
        System.out.println(roupa.getTamanhoRoupa());
        System.out.println(roupa.getCategoriaRoupa());
        System.out.println(roupa.getImageSrc());
        System.out.println(roupa.getStock());
        //System.out.println(roupa.getRoupa_doacao().getId_roupa_doacao()); => lança ERRO
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(roupa_doacao.getId_roupa_doacao());
        System.out.println(roupa_doacao.getQuantidade());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(doacao.getIdDoacao());
        System.out.println(doacao.getDataDaDoacao());
        //System.out.println(doacao.getRoupa_doacao().getId_roupa_doacao());
        //System.out.println(doacao.getUtilizador().getUsername());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");

        //doacaoExistente.setRoupa_doacao(doacao.getRoupa_doacao());
        //doacaoExistente.setUtilizador(doacao.getUtilizador());

        //roupaExistente.setCategoriaRoupa(roupa.getCategoriaRoupa());
        //roupaExistente.setImageSrc(roupa.getImageSrc());
        //roupaExistente.setStock(roupa.getStock());
        /*roupaExistente.setTamanhoRoupa(roupa.getTamanhoRoupa());
        roupaExistente.setTipoRoupa(roupa.getTipoRoupa());
        roupaExistente.setRoupa_doacao(roupa.getRoupa_doacao());*/

        roupaDoacaoService.salvarRoupa_Doacao(roupaDoacaoExistente);

        //doacao.setRoupa_doacao(roupa_doacao);
        //String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        //Utilizador utilizadorLogadoGuardado = utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado);
        //doacao.setUtilizador(utilizadorLogadoGuardado);

        //TipoRoupa tipoRoupa = roupa.getTipoRoupa();
        //CategoriaRoupa categoriaRoupa = helpAddDoacoes.adicionarAssociarCategoria(tipoRoupa);
        //roupa.setCategoriaRoupa(categoriaRoupa);
        //String imgSrc = helpAddDoacoes.adicionarAssociarImagem(tipoRoupa);
        //roupa.setImageSrc(imgSrc);
        //roupa.setRoupa_doacao(roupa_doacao);

        //doacaoService.salvarDoacao(doacaoExistente);
        //roupaService.salvarRoupa(roupaExistente);

        //roupaDoacaoService.atualizarRoupa_Doacao(roupa_doacao.getId_roupa_doacao(), roupa_doacao.getQuantidade());
        //System.out.println("---------------------------------------------");
        //System.out.println(roupa_doacao.getId_roupa_doacao());

        return "redirect:/doacoes";
    }
}
