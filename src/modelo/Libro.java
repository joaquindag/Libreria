package modelo;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private float precio;
	private String formato;
	private String estado;

	public Libro(String iSBN, String titulo, String autor, String editorial, float precio,String formato,String estado) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato=formato;
		this.estado=estado;
	}

	
	public String getFormato() {
		return formato;
	}


	public String getEstado() {
		return estado;
	}


	public String getISBN() {
		return ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public float getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "ISBN: " + ISBN + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial
				+ "\nFormato: " + formato + "\nEstado: " + estado + "\nPrecio: " + precio;
	}

}