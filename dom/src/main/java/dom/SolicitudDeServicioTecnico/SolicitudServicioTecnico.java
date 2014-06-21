package dom.SolicitudDeServicioTecnico;




import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.Date;



import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.value.Blob;





import dom.Netbook.Netbook;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("SERVICIOTECNICO")

public class SolicitudServicioTecnico {
    //public solicitante integrante de la institucion  
	public String motivoDeSolicitud;
	public Date fechaDeSolicitud;
	public String solucion;
	public Date fechaDeSolucion;
	public Integer prioridad;
	public Netbook netbook ;
	public String codigoSolicitud;
	public String numeroTiquetRegistro;
	public String comentario;
	
   
	public String iconName() {
        return "asistenciatecnica";
    }
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	
	
	
	@Title
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getMotivoDeSolicitud() {
		return motivoDeSolicitud;
	}





	public void setMotivoDeSolicitud(String motivoDeSolicitud) {
		this.motivoDeSolicitud = motivoDeSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}





	public void setFechaDeSolicitud(Date fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}



    @Optional
	@javax.jdo.annotations.Column(allowsNull="true")
    @MultiLine
	public String getSolucion() {
		return solucion;
	}




    
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}




    @javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Integer getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}





	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroTiquetRegistro() {
		return numeroTiquetRegistro;
	}





	public void setNumeroTiquetRegistro(String numeroTiquetRegistro) {
		this.numeroTiquetRegistro = numeroTiquetRegistro;
	}



    @MultiLine
    @javax.jdo.annotations.Column(allowsNull="true")
	public String getComentario() {
		return comentario;
	}

    



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

    
    /**
     * TODO ImprimirReporte
     * archivo incompleto para imprimir
     * @return Reporte a imprimir
     * @throws JRException 
     */
	public Blob imprimir() throws JRException
	{
		

        
		File resume = new File("solicitudAsistenciaTecnica.pdf");
		if (!(resume.exists()))
		{
		try {
			resume.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
		byte[] fileContent = new byte[(int) resume.length()];
		try {
		    FileInputStream fileInputStream = new FileInputStream(resume);
		         
		    fileInputStream.read(fileContent);
		    fileInputStream.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		Blob blob= new Blob("solicitudAsistenciaTecnica.pdf","application/pdf",fileContent);
			
		return blob;
	}
	



	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
