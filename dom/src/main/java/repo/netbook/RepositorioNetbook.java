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
package repo.netbook;

import java.util.Date;

import java.util.List;
import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;
import dom.login.Login;
import dom.netbook.ModeloNetbook;
import dom.netbook.Netbook;
import dom.netbook.Estado.SituacionDeNetbook;
import dom.notificaciones.SolicitudNetbookPrestada;



@Named("Netbook")
public class RepositorioNetbook extends AbstractFactoryAndRepository {


	public String getId() {
        return "Netbook";
    }

    public String iconName() {
        return "netbook";
    }
    
    

    
	/**
	 * muestra una lista de todas las Netbooks que existen
	 * @return lista de Netbooks
	 */
    public List<Netbook> listaNetbooks() {
        return allMatches(QueryDefault.create(Netbook.class, "traerTodo"));
    }
    
    /**
     * 
     * @param searchPhrase parametro de busqueda en el combo
     * del viewer para autocompletado
     * @return
     */
    @Hidden
    public List<Netbook> autoComplete(String searchPhrase) { 
    	if (searchPhrase==null)
    	{
    	return null;//para optimizar el rendimiento y que no busque lista completa	
    	}
    	else
    	{
    	return allMatches(QueryDefault.create(Netbook.class, "traerlikePorId","idNetbook",searchPhrase));
    	}
    }
    /**
     * Crea y guarda una netbook en el sistema
     * las propiedades de la netbook
     * son ingresadas por los parametros
     * del metodo mediante el viewer y
     * asignados al un objeto de tipo netbook
     * el cual se va a persistir
     * @param idNetbook 
     * @param modelo
     * @param numeroDeSerie
     * @param numeroLicenciaWindows
     * @param fechaDeExpiracion
     * @param direccionMac
     * @return 
     */
	public Netbook ingresarNetbook(
	@Named("id de Netbook")@MaxLength(10)final String idNetbook,
	@Named("Modelo")final ModeloNetbook modelo,
	@Named("Numero De Serie")@MaxLength(30)final String numeroDeSerie,
	@Named("Numero De Licencia de Windows")@MaxLength(30)final String numeroLicenciaWindows,
	@Named("Fecha de Expiracion") @Optional final Date fechaDeExpiracion,
	@MaxLength(17)@RegEx(validation = "[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]") @Named("Direccion Mac")final String direccionMac)

	{
		final Netbook netbook = container.newTransientInstance(Netbook.class);
	    netbook.setFechaDeExpiracion(fechaDeExpiracion);
	    netbook.setIdNetbook(idNetbook);
	    netbook.setDireccionMac(direccionMac);
	    netbook.setModelo(modelo);
	    netbook.setNumeroDeSerie(numeroDeSerie);
	    netbook.setNumeroLicenciaWindows(numeroLicenciaWindows);
	    netbook.setSituacionDeNetbook("Entregada");
	    netbook.setEstadoNetbook(new dom.netbook.Estado.Deposito());
	    //netbook.setPersona(null);	    
	    container.persistIfNotAlready(netbook);
	    
		return netbook;
	}
	/**
	 * metodo que valida los parametros cuando una nueva netbook es creada
	 * @param idNetbook
	 * @param modelo
	 * @param numeroDeSerie
	 * @param numeroLicenciaWindows
	 * @param fechaDeExpiracion
	 * @param direccionMac
	 * @param estadoNetbook
	 * @return
	 */
	
	public String validateIngresarNetbook(final String idNetbook,
			final ModeloNetbook modelo,
			final String numeroDeSerie,
			final String numeroLicenciaWindows,
			final Date fechaDeExpiracion,
			final String direccionMac
			) {
        return validarDatosDeNetbook(numeroDeSerie,numeroLicenciaWindows,fechaDeExpiracion,direccionMac);
    }
	/**
	 * 
	 * @param numeroDeSerie
	 * @param numeroLicenciaWindows
	 * @param fechaDeExpiracion
	 * @param direccionMac
	 * @return mensajes de validacion al usuario en caso de ser nulo es porque la validacion es correcta 
	 */
	public static String validarDatosDeNetbook(String numeroDeSerie,String numeroLicenciaWindows,Date fechaDeExpiracion,String direccionMac)
	{
		//validar fecha de expiracion
		Date fechahoy = new Date();
		if(fechahoy.equals(fechaDeExpiracion)||fechahoy.after(fechaDeExpiracion))
		{
			return "debe ingresar una fecha de expiracion correcta no puede expirar el dia de hoy";
		}
		else
		{
			if (direccionMac.length()!=17)
			{
				return "la direccion mac debe tener 17 caracteres";
			}
			else
			{
		         return null;
			}
		}
	}
	
	

	
	
	
	/**
	 * Busqueda de Netbook por Id 
	 * 
	 * se retorna la net completa
	 * 
	 * @param netbook
	 * 
	 * @return List<Netbook>
	 * 
	 */
	@Named("Buscar Netbook")
    public List<Netbook> listaNetbookPorId(@Named("Id de Netbook")String idNet) {        
    	return allMatches(QueryDefault.create(Netbook.class, "traerPorId","idNetbook",idNet));
    }
	
    /**
     * Metodo que se encarga encarga de Notificar
     * Que se esta Solicitando una Netbook Prestada
     * Por algun Motivo
     * @param motivo motivo justificatorio para prestar netbook
     * @return Solicitud realizada
     */
	public String solicitarNetbookPrestada(@Named("motivo")String motivo)
	{
		final SolicitudNetbookPrestada solicitud = container.newTransientInstance(SolicitudNetbookPrestada.class);
		solicitud.setDetallesYobservaciones(motivo);
		solicitud.setFechaNotificacion(LocalDate.now());
		Login login = container.firstMatch(QueryDefault.create(Login.class,"buscarPorUsuario","usuario",container.getUser().getName())) ;
		solicitud.setPersona(login.getPersona());
		container.persistIfNotAlready(solicitud);
		container.informUser("La Solicitud ha sido realizada");
		return "Solicitud realizada";
	}
	
	
	
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
		
	
}