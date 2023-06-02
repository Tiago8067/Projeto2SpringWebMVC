package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.EncomendaService;
import com.example.projeto2springwebmvc.Services.LinhaEncomendaService;
import com.example.projeto2springwebmvc.Services.RoupaDasEncomendasService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.*;
import com.example.projeto2springwebmvc.models.enums.EstadoEncomenda;
import com.example.projeto2springwebmvc.modelsHelp.LinhaEncomendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/encomendas")
public class EncomendaDasRoupasController {
    @Autowired
    private EncomendaService encomendaService;
    @Autowired
    private LinhaEncomendaService linhaEncomendaService;
    @Autowired
    private RoupaDasEncomendasService roupaDasEncomendasService;
    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping()
    public String mostraEncomendaList(Model model) {
        List<LinhaEncomendas> roupasEncomendadas = new ArrayList<>();

        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        for (LinhaEncomendas l : encomendaService.encomendasDasRoupas()) {
            if (l.getUsernameCliente().equals(utilizadorUsernameLogadoGuardado)) {
                roupasEncomendadas.add(l);
            }
        }

        model.addAttribute("roupasEncomendadas", roupasEncomendadas);

        return "listEncomendas";
    }

    @GetMapping("/addEncomenda")
    public String addEncomenda(Model model) {
        model.addAttribute("encomenda", new Encomenda());
        model.addAttribute("linhaEncomenda", new LinhaEncomendas());
        model.addAttribute("roupaDasEncomendas", new RoupaDasEncomendas());

        return "addEncomenda";
    }

    @PostMapping("/addEncomenda")
    public String adicionarEncomenda(Encomenda encomenda, LinhaEncomenda linhaEncomenda, RoupaDasEncomendas roupaDasEncomendas) {
        linhaEncomendaService.salvarLinhaEncomenda(linhaEncomenda);

        encomenda.setEstadoEncomenda(EstadoEncomenda.EMPREPARACAO);
        encomenda.setLinha_encomenda(linhaEncomenda);
        String utilizadorUsernameLogadoGuardado = UtilizadorAutenticacaoController.utilizadorUsernameLogado;
        Utilizador utilizadorLogadoGuardado = utilizadorService.retornaUtilizadorLogado(utilizadorUsernameLogadoGuardado);
        encomenda.setUtilizador(utilizadorLogadoGuardado);

        roupaDasEncomendas.setLinha_encomenda(linhaEncomenda);

        encomendaService.salvarEncomenda(encomenda);
        roupaDasEncomendasService.salvarRoupaDasEncomendas(roupaDasEncomendas);

        return "redirect:/encomendas";
    }

    @GetMapping("/editar/{id}")
    public String mostraFormEditarEncomenda(@PathVariable("id") Integer id, Model model) {
        Encomenda encomenda = encomendaService.getEncomendaPorId(id);

        LinhaEncomenda linhaEncomenda = new LinhaEncomenda();
        RoupaDasEncomendas roupaDasEncomendas = new RoupaDasEncomendas();

        List<LinhaEncomenda> linhaEncomendaList = linhaEncomendaService.linhaEncomendaList();
        List<RoupaDasEncomendas> roupaDasEncomendasList = roupaDasEncomendasService.roupaDasEncomendasList();

        for (LinhaEncomenda l : linhaEncomendaList) {
            if (l.getIdLinhaEncomenda() == encomendaService.getEncomendaPorId(id).getLinha_encomenda().getIdLinhaEncomenda()) {
                linhaEncomenda = linhaEncomendaService.getLinhaEncomendaPorId(l.getIdLinhaEncomenda());
            }
        }

        for (RoupaDasEncomendas r : roupaDasEncomendasList) {
            if (r.getLinha_encomenda().getIdLinhaEncomenda() == encomendaService.getEncomendaPorId(id).getLinha_encomenda().getIdLinhaEncomenda()) {
                roupaDasEncomendas = roupaDasEncomendasService.getRoupaDasEncomendasPorId(r.getIdroupaDasEncomendas());
            }
        }

        model.addAttribute("encomenda", encomenda);
        model.addAttribute("linhaEncomenda", linhaEncomenda);
        model.addAttribute("roupaDasEncomendas", roupaDasEncomendas);
        model.addAttribute("pageTitle", "Edita Encomenda (ID: " + id + ")");

        return "editarEncomenda";
    }

    @PostMapping("/guardarEdicaoEncomenda/{id}")
    public String guardarEdicaoDoacao(@PathVariable("id") Integer id, @ModelAttribute("encomenda") Encomenda encomenda,
                                      @ModelAttribute("linhaEncomenda") LinhaEncomenda linhaEncomenda,
                                      @ModelAttribute("roupaDasEncomendas") RoupaDasEncomendas roupaDasEncomendas) {
        Encomenda encomendaExistente = encomendaService.getEncomendaPorId(id);
        LinhaEncomenda linhaEncomendaExistente = linhaEncomendaService.getLinhaEncomendaPorId(encomendaExistente.getLinha_encomenda().getIdLinhaEncomenda());

        linhaEncomendaExistente.setQuantidade(linhaEncomenda.getQuantidade());

        linhaEncomendaService.salvarLinhaEncomenda(linhaEncomendaExistente);
        roupaDasEncomendasService.atualizarRoupaDasEncomendas(encomendaExistente.getLinha_encomenda().getIdLinhaEncomenda(), String.valueOf(roupaDasEncomendas.getTipoRoupa()), String.valueOf(roupaDasEncomendas.getTamanhoRoupa()));

        return "redirect:/encomendas";
    }
}
