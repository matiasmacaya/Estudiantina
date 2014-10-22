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
package repo.notificaciones;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.annotation.Named;
import org.joda.time.LocalDate;
import dom.login.Login;
import dom.notificaciones.CertificadoAlumnoRegular;
import dom.notificaciones.Notificaciones;

@Named("Solicitudes")
public class RepoNotificaciones extends AbstractFactoryAndRepository {

	
	public String getId() {
        return "Notificaciones";
    }

	public String pedirCertificado(@Named("detalles") @Optional String detalles)
	{
		final CertificadoAlumnoRegular certificadoAlumnoRegular = container.newTransientInstance(CertificadoAlumnoRegular.class);
		certificadoAlumnoRegular.setDetallesYobservaciones(detalles);	
		String usuarioActual = container.getUser().getName();
		Login login = firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuarioActual));
		LocalDate fecha = new LocalDate();
		certificadoAlumnoRegular.setFechaNotificacion(fecha);
		certificadoAlumnoRegular.setPersona(login.getPersona());
		certificadoAlumnoRegular.setVista(false);//la notificacion todavia no esta vista
		container.persistIfNotAlready(certificadoAlumnoRegular);
		container.informUser("Se Ha solicitado un nuevo certificado De Alumno Regular");
		return "Se Ha solicitado un nuevo certificado";
	}

  	
	@Named("Notificaciones No Leidas")
	public List<Notificaciones> verNotificacionesNoLeidas()
	{		
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificacionesNoLeidas"));
		
	}
	
	public List<Notificaciones> verTodasLasNotificaciones()
	{

		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificaciones"));
		
	}

	@Named("Notificaciones de Hoy")
	public List<Notificaciones> verNotificaciones()
	{	
		LocalDate fechaAnterior = LocalDate.now().minusDays(2); 
 
		LocalDate fechaPosterior = LocalDate.now().minusDays(-1);
		
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificacionesEntreFechas","fechaAnterior",fechaAnterior,"fechaPosterior",fechaPosterior));
		
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}
