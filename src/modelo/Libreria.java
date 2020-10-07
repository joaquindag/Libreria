package modelo;

import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Libreria {
	private HashMap<String, Libro> mapaLibros=new HashMap<String, Libro>();
	
	public void añadirLibro(Libro libro) {
		mapaLibros.put(libro.getISBN(), libro);
	}
	
	public void rellenarTabla(JTable tablaLibros) {
		String nombresColumnas[] = {"ISBN", "TITULO", "EDITORIAL", "AUTOR", "PRECIO"};
		String[][] filasTabla=new String[this.mapaLibros.size()][nombresColumnas.length];
		int i = 0;
		for (HashMap.Entry<String, Libro> entry : mapaLibros.entrySet()) {
			filasTabla[i][0] = entry.getKey();
			filasTabla[i][1] = entry.getValue().getTitulo();
			filasTabla[i][2] = entry.getValue().getEditorial();
			filasTabla[i][3] = entry.getValue().getAutor();
			filasTabla[i][4] = String.valueOf(entry.getValue().getPrecio());
			i++;
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas);
		tablaLibros.setModel(tablaCompleta);
	}
	
	public String obtenerIdSeleccionando(JTable tablaLibros) {
		int i =0;
		for (HashMap.Entry<String, Libro> entry : mapaLibros.entrySet()) {
			if (tablaLibros.getSelectedRow()==i) {
				return entry.getKey();
			}
			i++;
		}
		return null;
	}
	
	public void eliminarLibro(String ISBN) {
		mapaLibros.remove(ISBN);
}

	public Libro obtenerLibroDos(String ISBN) {
		return mapaLibros.get(ISBN);
	}

	public boolean comprobarISBNExiste(String iSBNSeleccionado) {
		return mapaLibros.containsKey(iSBNSeleccionado);
	}
}
