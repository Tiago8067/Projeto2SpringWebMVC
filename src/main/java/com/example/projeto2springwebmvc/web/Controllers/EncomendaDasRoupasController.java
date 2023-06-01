package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.EncomendaService;
import com.example.projeto2springwebmvc.Services.LinhaEncomendaService;
import com.example.projeto2springwebmvc.Services.RoupaDasEncomendasService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.Encomenda;
import com.example.projeto2springwebmvc.models.LinhaEncomenda;
import com.example.projeto2springwebmvc.models.RoupaDasEncomendas;
import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.EstadoEncomenda;
import com.example.projeto2springwebmvc.modelsHelp.LinhaDoacoes;
import com.example.projeto2springwebmvc.modelsHelp.LinhaEncomendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        /*List<LinhaEncomendas> roupasEncomendadas = encomendaService.encomendasDasRoupas();*/
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
}
