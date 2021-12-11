package anexo;
import java.io.Serializable;

public class FileType implements Serializable {
	private static final long serialVersionUID = 1L;
	private String extension;
	private String descripcion;
	private int[] magico;
	
	public FileType() {
		super();
		this.magico = new int[4];
	}

	public FileType(String extension, String descripcion, int[] magico) {
		super();
		this.extension = extension;
		this.descripcion = descripcion;
		this.magico = magico;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int[] getMagico() {
		return magico;
	}

	public void setMagico(int[] magico) {
		this.magico = magico;
	}

	@Override
	public String toString() {
		String salida;
		
		salida = this.getExtension() + ": ";
		for (int i=0; i<this.magico.length; i++) {
			salida += String.format("%02X ",this.magico[i]);
		}
		salida += " (" + this.getDescripcion() + ")";
		return salida;
	}
}
