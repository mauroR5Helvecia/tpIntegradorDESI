package com.tpdesi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpdesi.entity.Familia;
import com.tpdesi.service.familiaService.FamiliaServiceImplement;

@RestController
@RequestMapping("/api/familia")
public class FamiliaController {

    private final FamiliaServiceImplement familiaService;

    public FamiliaController(FamiliaServiceImplement familiaService) {
        this.familiaService = familiaService;
    }

    @PostMapping("/crear")
    public Familia crearFamilia(@RequestBody Familia familia) {
        return familiaService.crearFamilia(familia);
    }

    @GetMapping("/listaractivas")
    public List<Familia> listar() {
        return familiaService.listarActivas();
    }

    @PutMapping("/modificar/{id}")
    public Familia modificar(@PathVariable Long id, @RequestBody Familia familia) {
        familia.setId(id);
        return familiaService.modificarFamilia(familia);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        familiaService.eliminarLogicamente(id);
    }

    @GetMapping("/traerporid/{id}")
    public Familia obtenerPorId(@PathVariable Long id) {
        return familiaService.obtenerPorId(id);
    }
}
