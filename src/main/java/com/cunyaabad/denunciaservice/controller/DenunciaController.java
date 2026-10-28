package com.cunyaabad.denunciaservice.controller;

import com.cunyaabad.denunciaservice.entity.Denuncia;
import com.cunyaabad.denunciaservice.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    @Autowired
    private DenunciaService service;

    @PostMapping("/")
    public ResponseEntity<Denuncia> registrar(@RequestBody Denuncia denuncia) {
        return ResponseEntity.ok(service.registrar(denuncia));
    }

    @GetMapping("/")
    public ResponseEntity<List<Denuncia>> listarPaginado(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(service.listarPaginado(offset, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudadano/{dni}")
    public ResponseEntity<List<Denuncia>> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(service.obtenerPorDni(dni));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atender(@PathVariable Integer id) {
        Denuncia actualizada = service.atenderDenuncia(id);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }
}