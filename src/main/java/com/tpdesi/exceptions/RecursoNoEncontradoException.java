package com.tpdesi.exceptions;

public class RecursoNoEncontradoException extends RuntimeException {


	
	private static final long serialVersionUID = 1L;

	public RecursoNoEncontradoException(String entidad, Object id) {
        super(entidad + " no encontrada con id: " + id);
    }
}