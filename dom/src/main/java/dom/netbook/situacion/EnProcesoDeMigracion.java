package dom.netbook.situacion;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
import dom.netbook.Netbook;
import dom.persona.personagestionable.PersonaGestionable;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnMigracion" )
@Uniques({ @Unique(name = "enMigracionUnique" , members = { "idEnMigracion" } ) })

@ObjectType("ENPROCESODEMIGRACION")
public class EnProcesoDeMigracion implements ISituacionDeNetbook{

	private Netbook netbook;
	private Establecimiento establecimientoAmigrar;
	@javax.jdo.annotations.Column(allowsNull="true")
	public Establecimiento getEstablecimientoAmigrar() {
		return establecimientoAmigrar;
	}
	public void setEstablecimientoAmigrar(Establecimiento establecimientoAmigrar) {
		this.establecimientoAmigrar = establecimientoAmigrar;
	}
	public EnProcesoDeMigracion(Netbook netbook)
	{
		this.netbook= netbook;
	}
	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return null;
	}

	@Override
	public boolean ocultarImprimirActaMigracion() {
		return false;
	}

	@Override
	public boolean ocultarImprimirActaPrestamo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarNumeroActaDeRobo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarPersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarAsignarPersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarDesasignarNetbookDePersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarImprimirActaRecepcionDeNetbook() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarReportarComoRobada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reportarComoRobada(String numeroDeActa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blob imprimirActaMigracion() {
		HashMap<String,Object> parametros = new HashMap<String, Object>();
 		PersonaGestionable persona = container.firstMatch(QueryDefault.create(PersonaGestionable.class, "traerPorcuil","cuil",netbook.getPersona().getCuil()));
 		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
 		parametros.put("distrito", establecimiento.getDistritoEscolar());
 		parametros.put("cue", establecimiento.getCue());
 		parametros.put("emailEstablecimiento", establecimiento.getEmail());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
 		parametros.put("establecimiento", establecimiento.getNombre());
 		parametros.put("localidad", establecimiento.getLocalidad());
 		parametros.put("domicilio", establecimiento.getDireccion());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
      	parametros.put("alumno", persona.getNombre()+", "+persona.getApellido());
        parametros.put("cuilAlumno", persona.getCuil());
        parametros.put("nombreDirectorCedente", establecimientoAmigrar.getDirectivo().getApellido()+" "+establecimientoAmigrar.getDirectivo().getNombre());
 		parametros.put("netbookModelo", netbook.getMarca().toString()+" "+netbook.getModelo());
 		parametros.put("numeroSerieNetbook", netbook.getNumeroDeSerie());
 		parametros.put("directorCedente", establecimientoAmigrar.getDirectivo().getApellido()+ ",  "+establecimiento.getDirectivo().getNombre());
 		parametros.put("nroDniDirector", establecimiento.getDirectivo().getCuil());
 		parametros.put("directorCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("dniDirectorCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("nombreEstablecimientoCedente", establecimientoAmigrar.getNombre());
 		parametros.put("cueEstablecimientoCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("distritoEscolarCedente", establecimientoAmigrar.getDistritoEscolar());
 		parametros.put("ciudadEstablecimientoCedente", establecimientoAmigrar.getLocalidad().getLocalidad());
 		parametros.put("provinciaEstablecimientoCedente", establecimientoAmigrar.getLocalidad().getLocalidad());
 		parametros.put("domicilioEstablecimientoCedente", establecimientoAmigrar.getDireccion());
 		parametros.put("nombreAlumno", netbook.getPersona().getNombre());
 		parametros.put("modeloNetbook", netbook.getMarca()+" "+netbook.getModelo());
 		parametros.put("nroSerieNetbook", netbook.getNumeroDeSerie());
 		parametros.put("ciudadAlumno", netbook.getPersona().getLocalidad().getLocalidad());
 		parametros.put("ciudadAlumno", "");
 		try {
			return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaMigracion.jrxml", parametros, "Solicitud");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Blob imprimirActaPrestamo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desasignarNetbookDePersona() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void migrarNetbook(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean ocultarMigrarNetbook() {
		// TODO Auto-generated method stub
		return false;
	}

	@javax.inject.Inject
    private DomainObjectContainer container;

	@Override
	public void aceptarMigracion() {
		
		
	}
	@Override
	public boolean ocultarAceptarMigracion() {
		// TODO Auto-generated method stub
		return false;
	}
}