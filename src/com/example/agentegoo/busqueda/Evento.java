package com.example.agentegoo.busqueda;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Evento {

	private String nombre;
	private Calendar fecha;
	private int prioridad;
	private boolean estado;
	private String aM_PM;

	public Evento() {
		nombre = null;
		fecha = null;
		prioridad = 0;
		estado = true;
	}

	public Evento(String _nombre, Calendar _fecha, int _prioridad,
			boolean _estado) {
		nombre = _nombre;
		fecha = _fecha;
		prioridad = _prioridad;
		estado = _estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public String getPrioridadString() {
		return Integer.toString(prioridad);
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public String getDiaSemana() {
		String dia = "";
		int diaInt = fecha.get(Calendar.DAY_OF_WEEK);
		switch (diaInt) {
		case 1:
			dia = "Do";
			break;
		case 2:
			dia = "Lu";
			break;
		case 3:
			dia = "Ma";
			break;
		case 4:
			dia = "Mi";
			break;
		case 5:
			dia = "Ju";
			break;
		case 6:
			dia = "Vi";
			break;
		case 7:
			dia = "Sa";
			break;

		}
		return dia;
	}

	public String getMes() {
		String mes = "";
		int mesInt = fecha.get(Calendar.MONTH);
		switch (mesInt) {
		case 0:
			mes = "Enero";
			break;
		case 1:
			mes = "Febrero";
			break;
		case 2:
			mes = "Marzo";
			break;
		case 3:
			mes = "Abril";
			break;
		case 4:
			mes = "Mayo";
			break;
		case 5:
			mes = "Junio";
			break;
		case 6:
			mes = "Julio";
			break;
		case 7:
			mes = "Agosto";
			break;
		case 8:
			mes = "Septiembre";
			break;
		case 9:
			mes = "Octubre";
			break;
		case 10:
			mes = "Nobiembre";
			break;
		case 11:
			mes = "Diciembre";
			break;
		}
		return mes;
	}

	public String getFechaString() {
		return getDiaSemana() + " "
				+ Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) + " de "+getMes()+" del "+ Integer.toString(fecha.get(Calendar.YEAR));
	}

	public String getHoraString() {
		if (fecha.get(Calendar.AM_PM) == 0) 
			aM_PM = "AM";
		else
			aM_PM = "PM";
		return Integer.toString(fecha.get(Calendar.HOUR))+":"+Integer.toString(fecha.get(Calendar.MINUTE))+" "+aM_PM;
	}
	
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

}
