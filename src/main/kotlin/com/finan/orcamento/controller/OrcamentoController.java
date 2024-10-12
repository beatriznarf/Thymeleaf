package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/orcamentos")
public class OrcamentoController {
    @Autowired
    private OrcamentoService orcamentoService;
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    // Método para retornar a página orcamento.html
    @GetMapping
    public String mostraOrcamentos(Model model) {
        List<OrcamentoModel> orcamentos = orcamentoService.buscarCadastro();
        model.addAttribute("orcamentos", orcamentos); // Adiciona a lista de orçamentos ao modelo
        return "orcamento"; // Nome da view que será renderizada (orcamento.html)
    }

    @GetMapping(path = "/pesquisaid/{id}")
    public ResponseEntity<OrcamentoModel> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(orcamentoService.buscaId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrcamentoModel> cadastraOrcamento(@RequestBody OrcamentoModel orcamentoModel) {
        return ResponseEntity.ok(orcamentoService.cadastrarOrcamento(orcamentoModel));
    }

    @PostMapping(path = "/put/{id}")
    public ResponseEntity<OrcamentoModel> atualizaOrcamento(@RequestBody OrcamentoModel orcamentoModel, @PathVariable Long id) {
        OrcamentoModel orcamentoNewObj = orcamentoService.atualizaCadastro(orcamentoModel, id);
        return ResponseEntity.ok().body(orcamentoNewObj);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteOrcamento(@PathVariable Long id) {
        orcamentoService.deletaOrcamento(id);
    }
}
