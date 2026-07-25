package com.example.Nutricion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Nutricion.model.Alimentacion;
import com.example.Nutricion.repository.AlimentacionRepository;

@Service
public class AlimentacionService {


    @Autowired
    private AlimentacionRepository alimentacionRepository;


    public ResponseEntity<?> agregarAlimento(Alimentacion alimento) {

        if (alimento.getNombre() == null || alimento.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del alimento es obligatorio");
        }

        if (alimento.getCantidadGramos() <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor a 0");
        }

        alimentacionRepository.save(alimento);
        return ResponseEntity.ok().body("Alimento agregado exitosamente");
    }

    public ResponseEntity<?> listarPorEmail(String email) {
        return ResponseEntity.ok(alimentacionRepository.findByEmail(email));
    }

    @Transactional
    public ResponseEntity<?> finalizarDia(String email) {
        alimentacionRepository.deleteByEmail(email);
        return ResponseEntity.ok().body("Día finalizado");
    }

    public ResponseEntity<?> actualizarAlimento(Integer id, Alimentacion datos) {
        return alimentacionRepository.findById(id)
                .<ResponseEntity<?>>map(alimento -> {
                    if (datos.getCantidadGramos() <= 0) {
                        return ResponseEntity.badRequest().body("La cantidad debe ser mayor a 0");
                    }

                    alimento.setCantidadGramos(datos.getCantidadGramos());
                    alimento.setCalorias(datos.getCalorias());
                    alimento.setProteinas(datos.getProteinas());
                    alimento.setCarbohidratos(datos.getCarbohidratos());
                    alimento.setGrasas(datos.getGrasas());
                    alimento.setTipoComida(datos.getTipoComida());

                    alimentacionRepository.save(alimento);
                    return ResponseEntity.ok().body("Alimento actualizado");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> eliminarAlimento(Integer id) {
        if (!alimentacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        alimentacionRepository.deleteById(id);
        return ResponseEntity.ok().body("Alimento eliminado");
    }

}
