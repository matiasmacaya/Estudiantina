package dom.Establecimiento;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import javax.jdo.annotations.Column;
import dom.Localidad.Localidad;
import repo.Establecimiento.RepositorioEstablecimiento;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.Establecimiento.Establecimiento WHERE nombre== :nombre"),
	@Query(name="traerTodos", language="JDOQL", value = "SELECT FROM dom.Establecimiento.Establecimiento ") ,
			@Query(name="traerlikePorNombre", language="JDOQL", value = "SELECT FROM dom.Establecimiento.Establecimiento WHERE nombre.indexOf(:nombre) >= 0 range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Establecimiento.Establecimiento ")})
@AutoComplete(repository = RepositorioEstablecimiento.class, action = "autoComplete")
@Audited

@ObjectType("Establecimiento")
public class Establecimiento {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String cue;
	private Localidad localidad;
	private String distritoEscolar;
	@Column(allowsNull="false")
	public String getDistritoEscolar() {
		return distritoEscolar;
	}
	public void setDistritoEscolar(String distritoEscolar) {
		this.distritoEscolar = distritoEscolar;
	}
	@Column(allowsNull="false")
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String title()
	{
		return this.nombre;
		
	}
	public String iconName() {
        return "edificio";
    }
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	
	public String toString()
	{
		return nombre;		
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
		/**
	 * la CUE es la identificacion la escuela solo en la provincia de neuquen
	 * **/
	@Unique
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCue() {
		return cue;
	}
	public void setCue(String cue) {
		this.cue = cue;
	}




	@javax.inject.Inject 
	  private DomainObjectContainer container;


}
