package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Libreria;
import modelo.Libro;
import utiles.Validaciones;
import vista.UI;

public class ParaUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Libreria libreria = new Libreria();

	public ParaUI() {
		super();
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validaciones.validateISBN(txtIsbn.getText()) && Validaciones.validateLetters(txtAutor.getText())
						&& Validaciones.validateLetters(txtEditorial.getText())
						&& Validaciones.isNumberFloat(txtPrecio.getText())
						&& Validaciones.isNtWhite(comboBoxFormato.getSelectedItem().toString())
						&& Validaciones.isNtWhite(comboBoxEstado.getSelectedItem().toString())) {

					Libro libro = new Libro(txtIsbn.getText(), txtTitulo.getText(), txtAutor.getText(),
							txtEditorial.getText(), Float.parseFloat(txtPrecio.getText()),
							comboBoxFormato.getSelectedItem().toString(), comboBoxEstado.getSelectedItem().toString());
					if (!libreria.comprobarISBNExiste(txtIsbn.getText())) {
						libreria.añadirLibro(libro);
						libreria.rellenarTabla(tablaLibros);
						vaciarCampos();
						JOptionPane.showMessageDialog(null, "Libro guardado correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "ISBN existente");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Campos erroneos");
				}
			}
		});
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libreria.eliminarLibro(txtIsbn.getText());
				libreria.rellenarTabla(tablaLibros);
				vaciarCampos();
				JOptionPane.showMessageDialog(null, "Libro borrado");
			}
		});
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vaciarCampos();
				String ISBNSeleccionado = JOptionPane.showInputDialog("Introduce ISBN");
				if (libreria.comprobarISBNExiste(ISBNSeleccionado)) {
					JOptionPane.showMessageDialog(null, libreria.obtenerLibroDos(ISBNSeleccionado));
				} else {
					JOptionPane.showMessageDialog(null, "No existe libro con tal ISBN");
				}
			}
		});
	}

	public void vaciarCampos() {
		txtAutor.setText("");
		txtEditorial.setText("");
		txtPrecio.setText("");
		txtTitulo.setText("");
		txtIsbn.setText("");
		comboBoxEstado.setSelectedIndex(0);
		comboBoxFormato.setSelectedIndex(0);
	}
}
