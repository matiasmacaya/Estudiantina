package repo.Persona;


import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Hidden;

import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.Alumno.Alumno;
import dom.Alumno.EstadoDeAlumno;
import dom.Alumno.Nacionalidad;


@Named("Administrar Personas")
public class RepositorioPersona extends AbstractFactoryAndRepository {

	/*
	public String getId() {
        return "persona";
    }
    */
    public String iconName() {
        return "alumno";
    }
   
    
    /**
	 * muestra una lista de todas los Alumnos que existen
	 * @return lista de Alumnos
	 */
    public List<Alumno> listaAlumnos() {
        return allMatches(QueryDefault.create(Alumno.class, "traerTodoAlumno"));
    }
    
	
	/**
	 * Se realiza la carga de los alumno, con todos sus atributos.
	 * 
	 * @param cuil
	 * @param nombre
	 * @param apellido
	 * @param telefonoCelular
	 * @param telefonoFijo
	 * @param email
	 * @param domicilio
	 * @param fechaNacimiento
	 * @param fechaIngreso
	 * @param nacionalidad
	 * 
	 * @return Alumno
	 */
	
	
	
	public Alumno ingresarAlumno (
			@Named("CUIL")Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")String email,
			@Named("DOMICILIO")String domicilio,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@Named("FECHA INGRESO")Date fechaIngreso,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NACIONALIDAD")Nacionalidad nacionalidad,
			@Named("ESTADO DEL ALUMNO") EstadoDeAlumno estadoDeAlumno 			
			)
	{
	
		final Alumno alumno = container.newTransientInstance(Alumno.class);
	alumno.setCuil(cuil);
	alumno.setNombre(nombre);
	alumno.setApellido(apellido);
	alumno.setTelefonoCelular(telefonoCelular);
	alumno.setTelefinoFijo(telefinoFijo);
	alumno.setEmail(email);
	alumno.setDomicilio(domicilio);
	alumno.setFechaNacimiento(fechaNacimiento);
	alumno.setFechaIngreso(fechaIngreso);
	alumno.setNacionalidad(nacionalidad);    
    alumno.setEstadoDeAlumno(estadoDeAlumno);

	container.persistIfNotAlready(alumno);
	
	return alumno;
	
	}
	
	@Hidden
    public List<Alumno> autoComplete(Long searchPhrase) {        
    	return allMatches(QueryDefault.create(Alumno.class, "traerTodoAlumno","cuil",searchPhrase));
    }
	
	
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
    
    
    
}