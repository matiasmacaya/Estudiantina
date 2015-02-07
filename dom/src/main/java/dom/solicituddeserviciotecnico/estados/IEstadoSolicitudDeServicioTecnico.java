/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Robobich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package dom.solicituddeserviciotecnico.estados;

import org.joda.time.LocalDate;

import dom.tecnico.Tecnico;

public interface IEstadoSolicitudDeServicioTecnico {
	public String getNombre();
	public boolean ocultarImprimir();
	public boolean ocultarSolucion();
	public boolean ocultarAvisarPorMailQueEstaLista();
	public boolean ocultarFechaDeSolucion();
	public boolean ocultarTecnicoAsignado();
	public boolean ocultarAsignarTecnico();
	public boolean ocultarFinalizarSolicitud();
	public boolean ocultarEnviarAServicioTecnico();
	public boolean ocultarRecibirDelServicioTecnico();
	public void recibirDeServicioTecnico();
	public void enviarAServicioTecnico();
	public void avisarNetbookReparada(final String solucion,final LocalDate fechaDeSolucion);
	public void finalizarSolicitud();
	public void asignarTecnico(final Tecnico tecnico,final String codigoSolicitud);
}