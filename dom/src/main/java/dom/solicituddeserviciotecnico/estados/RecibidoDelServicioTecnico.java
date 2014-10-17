package dom.solicituddeserviciotecnico.estados;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idRecibido" )
@Uniques({ @Unique(name = "recibidoUnique" , members = { "idRecibido" } ) })
@ObjectType("RECIBIDODELSERVICIOTECNICO")
public class RecibidoDelServicioTecnico implements IEstadoSolicitudDeServicioTecnico {
	
	
	/**
	 * titulo del estado
	 * return titulo RECIBIDO DEL SERVICIO TECNICO
	 */
	public String title()
	{
		return "RECIBIDO DEL SERVICIO TECNICO";
	}
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
	public RecibidoDelServicioTecnico(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}
	
	
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}



	@Hidden
	@Override
	public boolean ocultarImprimir() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	
	

	@Hidden
	@Override
	public boolean ocultarSolucion() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	
	@Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	
	@Hidden
	@Override
	public boolean ocultarFechaDeSolucion() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

}



