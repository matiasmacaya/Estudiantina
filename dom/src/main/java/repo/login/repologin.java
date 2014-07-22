package repo.login;



import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.value.Password;


import dom.Persona.Persona;
import dom.login.Login;
@Named("Administracion De Cuentas")
public class repologin extends AbstractFactoryAndRepository {	
	public String iconName()
	{
		return "seguridad";	
	}
	@Named("dar de alta a un usuario")
	public Login altaUsuario (@Named("usuario")String usuario,@Named("contraseña")Password password,@Named("Persona")Persona persona)
	{
		final Login login = container.newTransientInstance(Login.class);
		login.setPersona(persona);
		login.setUsuario(usuario);
		login.setPassword(password);
		container.persistIfNotAlready(login);
		return login;
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;

	
}
