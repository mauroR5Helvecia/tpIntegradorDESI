package com.tpdesi.service.familiaService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tpdesi.entity.Familia;
import com.tpdesi.repository.FamiliaRepository;

import com.tpdesi.exceptions.NegocioException;
import com.tpdesi.exceptions.RecursoNoEncontradoException;



@Service
public class FamiliaServiceImplement implements FamiliaServiceInterface {

    @Autowired
    private FamiliaRepository familiaRepository;

    @Override
    public Familia crearFamilia(Familia familia) {
        try {
            familia.setFechaAlta(LocalDate.now());
            return familiaRepository.save(familia);
        } catch (DataIntegrityViolationException e) {
        	
        	
           
            throw new NegocioException("No se pudo crear la familia: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public List<Familia> listarActivas() {
       
        return familiaRepository.findByActivaTrue();
    }

    @Override
    public Familia modificarFamilia(Familia familiaActualizada) {
        
    	
        if (!familiaRepository.existsById(familiaActualizada.getId())) {
            throw new RecursoNoEncontradoException("Familia", familiaActualizada.getId());
        }
        try {
            return familiaRepository.save(familiaActualizada);
        } catch (DataIntegrityViolationException e) {
            throw new NegocioException("No se pudo modificar la familia: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public void eliminarLogicamente(Long id) {
        Familia familia = familiaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Familia", id));

        try {
            familia.setActiva(false);
            familiaRepository.save(familia);
        } catch (DataIntegrityViolationException e) {
            throw new NegocioException("No se pudo eliminar lógicamente la familia: " 
                                        + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public Familia obtenerPorId(Long id) {
        return familiaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("familia con id: ", id + " no encontrada"));
    }
}
