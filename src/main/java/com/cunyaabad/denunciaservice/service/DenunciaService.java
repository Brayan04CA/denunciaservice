package com.cunyaabad.denunciaservice.service;

import com.cunyaabad.denunciaservice.entity.Denuncia;
import com.cunyaabad.denunciaservice.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository repository;

    public Denuncia registrar(Denuncia denuncia) {
        denuncia.setFecha(LocalDateTime.now());
        denuncia.setEstado(1);
        return repository.save(denuncia);
    }

    public List<Denuncia> listarPaginado(int offset, int limit) {
        int page = offset / limit;
        return repository.findAll(PageRequest.of(page, limit)).getContent();
    }

    public Optional<Denuncia> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public List<Denuncia> obtenerPorDni(String dni) {
        return repository.findByDni(dni);
    }

    public Denuncia atenderDenuncia(Integer id) {
        Optional<Denuncia> optional = repository.findById(id);
        if (optional.isPresent()) {
            Denuncia d = optional.get();
            d.setEstado(0);
            return repository.save(d);
        }
        return null;
    }
}