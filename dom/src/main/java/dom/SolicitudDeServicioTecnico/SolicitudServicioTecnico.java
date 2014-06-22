package dom.SolicitudDeServicioTecnico;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.IOException;

import java.util.Date;



import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
	public Prioridad prioridad;
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




    @javax.jdo.annotations.Column(allowsNull="true")
    @Optional
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Prioridad getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Prioridad prioridad) {
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
     * @throws FileNotFoundException 
     */
	public Blob imprimir() throws JRException, FileNotFoundException
	{
		
		
		/*JasperReport jr = JasperCompileManager.compileReport("reportes/solicitudAsistenciaTecnica.jrxml");
		//para no compilarlo
		//JasperReport jr = (JasperReport) JRLoader.loadObject("solicitudAsistenciaTecnica.jasper");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nombre",this.getNetbook().getModelo().toString());
		JRExporter exporter = new JRPdfExporter();
		JasperPrint jp = JasperFillManager.fillReport(jr, map);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jp); 
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("/tmp/solicitudAsistenciaTecnica.pdf"));
		exporter.exportReport();*/
		Object[] obj= new Object[1];
		obj[0]="";
		JRBeanArrayDataSource jrDataSource= new JRBeanArrayDataSource(obj);
		File file = new File("reportes/solicitudAsistenciaTecnica.jrxml");
		InputStream input = new FileInputStream(file);
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint print = JasperFillManager.fillReport(reporte, null,jrDataSource);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,print); 
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("/tmp/solicitudAsistenciaTecnica.pdf"));
		exporter.exportReport();
		
		
        
		File resume = new File("/tmp/solicitudAsistenciaTecnica.pdf");
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