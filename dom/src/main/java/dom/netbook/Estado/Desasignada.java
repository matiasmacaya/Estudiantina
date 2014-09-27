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
package dom.netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbooksEstadoDesasignada")
@ObjectType("NetbookEnEstadoDesasignada")
public class Desasignada implements SituacionDeNetbook {

	@Override
	public String title() {
		return "Desasignada";
	}

}