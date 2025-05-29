package com.tpdesi.service.familiaService;

import java.util.List;

import com.tpdesi.entity.Familia;

public interface FamiliaServiceInterface {

    Familia crearFamilia(Familia familia);

    List<Familia> listarActivas();

    Familia modificarFamilia(Familia familiaActualizada);

    void eliminarLogicamente(Long id);

    Familia obtenerPorId(Long id);
	

}
