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
package dom.directivo;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.persona.RepositorioPersona;
import dom.establecimiento.Establecimiento;
import dom.persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.directivo.Directivo WHERE cuil== :cuil && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerTodoDirectivo", language = "JDOQL", value = "SELECT FROM dom.directivo.Directivo WHERE estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerDirectivoPorEstablecimiento", language = "JDOQL", value = "SELECT FROM dom.directivo.Directivo WHERE establecimiento == :institucion && estaBorrado== 'ACTIVO'")
})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@ObjectType("DIRECTIVO")
public class Directivo extends Persona {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4633674601613540127L;

	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * @return devuelve como titulo el cuil del Directivo
	 */
	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
	}
	
	public void modifyEstablecimiento(Establecimiento e) {
		e.setDirectivo(this);
    }
}