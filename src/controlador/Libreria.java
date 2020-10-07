package controlador;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Libro;

public class Libreria {
	private ArrayList<Libro> arrayLibro= new ArrayList<Libro>();
	
	public void añadirLibro(Libro libro) {
		arrayLibro.add(libro);
	}
	
	public void rellenarTabla(JTable tablaLibros) {
		String nombresColumnas[] = {"ISBN", "TITULO", "EDITORIAL", "AUTOR", "PRECIO", "FORMATO", "ESTADO"};
		String[][] filasTabla=new String[this.arrayLibro.size()][nombresColumnas.length];
		for (int i = 0; i < this.arrayLibro.size(); i++) {
			filasTabla[i][0]=this.arrayLibro.get(i).getISBN();
			filasTabla[i][1]=this.arrayLibro.get(i).getTitulo();
			filasTabla[i][2]=this.arrayLibro.get(i).getEditorial();
			filasTabla[i][3]=this.arrayLibro.get(i).getAutor();
			filasTabla[i][4]=String.valueOf(this.arrayLibro.get(i).getPrecio());
			filasTabla[i][5]=this.arrayLibro.get(i).getFormato();
			filasTabla[i][6]=this.arrayLibro.get(i).getEstado();
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas);
		tablaLibros.setModel(tablaCompleta);
	}
	
	public int obtenerIdSeleccionando(JTable tablaLibros) {
		for (int i = 0; i < arrayLibro.size(); i++) {
			if(tablaLibros.getSelectedRow()==i) {
				return i;
			}
		}
		return -1;
	}
	
	public void eliminarLibro(int posicion) {
		arrayLibro.remove(posicion);
}

	public Libro obtenerLibroDos(String ISBN) {
		for (int i = 0; i < arrayLibro.size(); i++) {
			if(this.arrayLibro.get(i).getISBN().equals(ISBN)) {
				return arrayLibro.get(i);
			}
		}
		return null;
	}

	public boolean comprobarISBNExiste(String iSBNSeleccionado) {
		for (int i = 0; i < arrayLibro.size(); i++) {
			if(arrayLibro.get(i).getISBN().equals(iSBNSeleccionado)) {
				return true;
			}
		}
		return false;
	}
}
