package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Libro libro = new Libro(txtIsbn.getText(), txtTitulo.getText(), txtAutor.getText(),
						txtEditorial.getText(), Float.parseFloat(txtPrecio.getText()),
						comboBoxFormato.getSelectedItem().toString(), comboBoxEstado.getSelectedItem().toString(),
						(Integer) spinnerCantidad.getValue());
				if (Validaciones.validateISBN(txtIsbn.getText()) && Validaciones.validateLetters(txtAutor.getText())
						&& Validaciones.validateLetters(txtEditorial.getText())
						&& Validaciones.isNumberFloat(txtPrecio.getText())
						&& Validaciones.isNtWhite(comboBoxFormato.getSelectedItem().toString())
						&& Validaciones.isNtWhite(comboBoxEstado.getSelectedItem().toString())) {
					if (!libreria.comprobarISBNExiste(txtIsbn.getText())) {
						libreria.aņadirLibro(libro);
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
				libreria.eliminarLibro(tablaLibros.getSelectedRow());
				libreria.rellenarTabla(tablaLibros);
				JOptionPane.showMessageDialog(null, "Libro borrado");
			}
		});

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vaciarCampos();
				JTextField txtISBNAComprobar = new JTextField();
				JOptionPane.showOptionDialog(null, txtISBNAComprobar, "Introduce ISBN", JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (!txtISBNAComprobar.getText().isEmpty()) {
					if (libreria.comprobarISBNExiste(txtISBNAComprobar.getText())) {
						JOptionPane.showMessageDialog(null, libreria.obtenerLibroDos(txtISBNAComprobar.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "No existe libro con tal ISBN");
					}
				}
			}
		});

		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ISBNSeleccionado = libreria.obtenerIdSeleccionando(tablaLibros);
				JTextField txtModificar = new JTextField();
				JOptionPane.showOptionDialog(null, txtModificar, "Introduce cantidad", JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (!txtModificar.getText().isEmpty()) {
					libreria.anadirCantidad(Integer.parseInt(txtModificar.getText()), ISBNSeleccionado);
					libreria.rellenarTabla(tablaLibros);
					JOptionPane.showMessageDialog(null, "Cantidad agregada");
				}
				vaciarCampos();
			}
		});

		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ISBNSeleccionado = libreria.obtenerIdSeleccionando(tablaLibros);
				JTextField txtVender = new JTextField();
				JOptionPane.showOptionDialog(null, txtVender, "Introduce cantidad", JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (!txtVender.getText().isEmpty()) {
					libreria.venderCantidad(Integer.parseInt(txtVender.getText()), ISBNSeleccionado);
					libreria.rellenarTabla(tablaLibros);
					JOptionPane.showMessageDialog(null, "Cantidad vendida");
				}
				vaciarCampos();
			}
		});

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ISBN = txtIsbn.getText();
				if (ISBN.isEmpty()) {
					int i = tablaLibros.getSelectedRow();
					if (i >= 0) {
						rellenarCamposDatosLibro(libreria.obtenerISBNconcreto(i));
						 tabbedPane.setSelectedIndex(0);
					} else {
						String isbnSeleccionado = JOptionPane.showInputDialog("Introduce el ISBN");
						if (isbnSeleccionado != null && !isbnSeleccionado.isEmpty()) {
							if (libreria.comprobarISBNExiste(isbnSeleccionado)) {
								rellenarCamposDatosLibro(isbnSeleccionado);
								 tabbedPane.setSelectedIndex(0);
							} else {
								JOptionPane.showInputDialog(null, "ISBN no existe");
							}
						}
					}
				} else {
					libreria.eliminarLibro(ISBN);
					registrarLibro(ISBN);
					txtIsbn.setEnabled(true);
				}

			}
		});

		txtIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				txtIsbn.setBackground(Color.red);
				txtIsbn.setForeground(Color.white);
				if (Validaciones.validateISBN(txtIsbn.getText())) {
					txtIsbn.setForeground(Color.GREEN);
					txtIsbn.setBackground(Color.WHITE);
				}
			}
		});
		;
	}

	private void registrarLibro(String ISBN) {
		String titulo = txtTitulo.getText();
		String autor = txtAutor.getText();
		String editorial = txtEditorial.getText();
		float precio = Float.parseFloat(txtPrecio.getText());
		Libro libro = new Libro(ISBN, titulo, autor, editorial, precio, (String) comboBoxFormato.getSelectedItem(),
				(String) comboBoxEstado.getSelectedItem(), (int) spinnerCantidad.getValue());
		libreria.aņadirLibro(libro);
		libreria.rellenarTabla(tablaLibros);
		vaciarCampos();
	}

	public void rellenarCamposDatosLibro(String ISBN) {
		Libro libro = libreria.getLibro(ISBN);
		txtIsbn.setText(libro.getISBN());
		txtIsbn.setEnabled(false);
		txtAutor.setText(libro.getAutor());
		txtTitulo.setText(libro.getTitulo());
		txtEditorial.setText(libro.getEditorial());
		txtPrecio.setText(String.valueOf(libro.getPrecio()));
		spinnerCantidad.setValue(libro.getCantidad());
		comboBoxEstado.setSelectedItem(libro.getFormato());
		comboBoxFormato.setSelectedItem(libro.getEstado());

	}

	public void vaciarCampos() {
		txtAutor.setText("");
		txtEditorial.setText("");
		txtPrecio.setText("");
		txtTitulo.setText("");
		txtIsbn.setText("");
		comboBoxEstado.setSelectedIndex(0);
		comboBoxFormato.setSelectedIndex(0);
		spinnerCantidad.setValue(0);
	}
}
