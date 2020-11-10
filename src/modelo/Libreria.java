package modelo;

import java.util.HashMap;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Libreria {
	private HashMap<String, Libro> mapaLibros = new HashMap<String, Libro>();

	public void añadirLibro(Libro libro) {
		mapaLibros.put(libro.getISBN(), libro);
	}

	public void rellenarTabla(JTable tablaLibros) {
		String nombresColumnas[] = { "ISBN", "TITULO", "EDITORIAL", "AUTOR", "PRECIO", "CANTIDAD" };
		String[][] filasTabla = new String[this.mapaLibros.size()][nombresColumnas.length];
		int i = 0;
		for (HashMap.Entry<String, Libro> entry : mapaLibros.entrySet()) {
			filasTabla[i][0] = entry.getKey();
			filasTabla[i][1] = entry.getValue().getTitulo();
			filasTabla[i][2] = entry.getValue().getEditorial();
			filasTabla[i][3] = entry.getValue().getAutor();
			filasTabla[i][4] = String.valueOf(entry.getValue().getPrecio()) + "€";
			filasTabla[i][5] = String.valueOf(entry.getValue().getCantidad());
			i++;
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas);
		tablaLibros.setModel(tablaCompleta);
	}

	public String obtenerIdSeleccionando(JTable tablaLibros) {
		int i = 0;
		for (HashMap.Entry<String, Libro> entry : mapaLibros.entrySet()) {
			if (tablaLibros.getSelectedRow() == i) {
				return entry.getKey();
			}
			i++;
		}
		return null;
	}

	public void anadirCantidad(int cantidadNueva, String ISBNSelecionado) {
		mapaLibros.get(ISBNSelecionado).setCantidad(mapaLibros.get(ISBNSelecionado).getCantidad() + cantidadNueva);
	}

	public void eliminarLibro(int index) {
		String ISBN = obtenerISBNconcreto(index);
		this.mapaLibros.remove(ISBN);
	}
	
	public void eliminarLibro(String ISBN) {
		this.mapaLibros.remove(ISBN);
	}

	public void venderCantidad(int cantidadNueva, String ISBNSelecionado) {
		if(mapaLibros.get(ISBNSelecionado).getCantidad()>cantidadNueva) {
			mapaLibros.get(ISBNSelecionado).setCantidad(mapaLibros.get(ISBNSelecionado).getCantidad() - cantidadNueva);
		} else {
			mapaLibros.get(ISBNSelecionado).setCantidad(0);
		}
	}

	public String obtenerISBNconcreto(int index) {
		Set<String> conjunto = this.mapaLibros.keySet();
		String ISBN = null;
		if (conjunto.size() != 0) {
			Object[] array = conjunto.toArray();
			ISBN = (String) array[index];
		}
		return ISBN;
	}

	public Libro obtenerLibroDos(String ISBN) {
		return mapaLibros.get(ISBN);
	}

	public boolean comprobarISBNExiste(String iSBNSeleccionado) {
		return mapaLibros.containsKey(iSBNSeleccionado);
	}

	public Libro getLibro(String iSBN) {
		return mapaLibros.get(iSBN);
	}

//	public void modificarLibro(String autor, String editorial, float precio, String titulo, String formato,
//			String estado, String ISBN) {
//		mapaLibros.get(ISBN).setAutor(autor);
//		mapaLibros.get(ISBN).setEditorial(editorial);
//		mapaLibros.get(ISBN).setPrecio(precio);
//		mapaLibros.get(ISBN).setTitulo(titulo);
//		mapaLibros.get(ISBN).setFormato(formato);
//		mapaLibros.get(ISBN).setEstado(estado);
//	}


}
