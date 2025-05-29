package com.tpdesi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dni;

    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private Ocupacion ocupacion;

    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;

    public enum Ocupacion {
        DESEMPLEADO, EMPLEADO, ESTUDIANTE, AMA_DE_CASA, OTRO
    }

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

 
    
    
}