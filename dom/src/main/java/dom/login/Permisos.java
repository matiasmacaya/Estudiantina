package dom.login;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("Permisos")
public class Permisos {
private  String permiso;
@javax.jdo.annotations.Column(allowsNull="False")
public String getPermiso() {
	return permiso;
}

public void setPermiso(String permiso) {
	this.permiso = permiso;
}
}