package extraccion;

public class Atributo {
	private String nombre;
	private String valores;
	public Atributo() {
		super();
	}
	
	public Atributo(String nombre, String valores) {
		super();
		this.nombre = nombre;
		this.valores = valores;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValores() {
		return valores;
	}
	public void setValores(String valores) {
		this.valores = valores;
	}
	

}
