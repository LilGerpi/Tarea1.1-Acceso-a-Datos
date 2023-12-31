package Proyecto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GestorBiblioteca extends JFrame {

	// Listas para almacenar libros, préstamos actuales e históricos.
	private List<Libro> libros;
	private List<Prestamo> prestamos;
	private List<Prestamo> historialPrestamos;

	// Campos de texto para ingresar detalles del libro.
	private JTextField tituloField;
	private JTextField autorField;
	private JTextField isbnField;
	private JTextField buscarField; // Campo de texto para la búsqueda.

	// Método principal para iniciar la aplicación.
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			GestorBiblioteca gestor = new GestorBiblioteca(); // Crear una instancia de la clase.
			gestor.iniciar(); // Iniciar la interfaz gráfica.
		});
	}

	// Constructor de la clase.
	public GestorBiblioteca() {
		// Configuración básica de la ventana.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestor de Biblioteca");
		setSize(700, 450); // Aumentar un poco el tamaño para mejor visualización.
		setLayout(new GridLayout(0, 2, 20, 20)); // Aumentar el espaciado entre componentes.
		getContentPane().setBackground(Color.decode("#F5F5F5")); // Color de fondo suave para la ventana.

		// Inicialización de las listas.
		libros = new ArrayList<>();
		prestamos = new ArrayList<>();
		historialPrestamos = new ArrayList<>();

		// Creación de etiquetas y campos de texto.
		JLabel tituloLabel = new JLabel("Título:");
		tituloField = new JTextField();
		JLabel autorLabel = new JLabel("Autor:");
		autorField = new JTextField();
		JLabel isbnLabel = new JLabel("ISBN:");
		isbnField = new JTextField();
		JLabel buscarLabel = new JLabel("Buscar:"); // Etiqueta para el campo de búsqueda.
		buscarField = new JTextField(); // Campo de texto para ingresar el término de búsqueda.

		Font fuenteGrande = new Font("SansSerif", Font.PLAIN, 18); // Cambiar la fuente a SansSerif para un look más moderno.

		// Aplica la nueva fuente a tus etiquetas.
		tituloLabel.setFont(fuenteGrande);
		autorLabel.setFont(fuenteGrande);
		isbnLabel.setFont(fuenteGrande);
		buscarLabel.setFont(fuenteGrande);

		// Estilo para los campos de texto.
		Color colorBorde = Color.decode("#1e88e5"); // Color primario para bordes.
		tituloField.setBorder(BorderFactory.createLineBorder(colorBorde));
		autorField.setBorder(BorderFactory.createLineBorder(colorBorde));
		isbnField.setBorder(BorderFactory.createLineBorder(colorBorde));
		buscarField.setBorder(BorderFactory.createLineBorder(colorBorde));

		// Botón para agregar un libro.
		JButton agregarButton = new JButton("Agregar libro");
		agregarButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		agregarButton.setForeground(Color.WHITE); // Color de texto blanco.
		agregarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		agregarButton.addActionListener(e -> {
			String titulo = tituloField.getText();
			String autor = autorField.getText();
			String isbn = isbnField.getText();

			// Verificar que todos los campos estén llenos antes de proceder.
			if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Libro libro = new Libro(titulo, autor, isbn);
				libros.add(libro);
				JOptionPane.showMessageDialog(null, "Libro agregado correctamente.");
				limpiarCampos();
				guardarDatos();
			}
		});

		// Botón para mostrar todos los libros.
		JButton mostrarButton = new JButton("Mostrar libros");
		mostrarButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		mostrarButton.setForeground(Color.WHITE); // Color de texto blanco.
		mostrarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		mostrarButton.addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			for (Libro libro : libros) {
				sb.append(libro).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb.toString(), "Listado de libros", JOptionPane.INFORMATION_MESSAGE);
		});

		// Botón para registrar un préstamo.
		JButton registrarButton = new JButton("Registrar préstamo");
		registrarButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		registrarButton.setForeground(Color.WHITE); // Color de texto blanco.
		registrarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		registrarButton.addActionListener(e -> {
			String titulo = tituloField.getText();
			// Verificación de que el campo del título no esté vacío antes de proceder.
			if (titulo.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, introduzca el título del libro.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String nombrePrestatario = JOptionPane.showInputDialog(null, "Nombre del prestatario:");
				String fechaPrestamo = JOptionPane.showInputDialog(null, "Fecha del préstamo:");

				// Verificar que se hayan introducido el nombre y la fecha antes de proceder.
				if (nombrePrestatario == null || nombrePrestatario.trim().isEmpty() || fechaPrestamo == null
						|| fechaPrestamo.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Prestamo prestamo = new Prestamo(titulo, nombrePrestatario, fechaPrestamo);
					prestamos.add(prestamo);
					JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente.");
					limpiarCampos();
					guardarDatos();
				}
			}
		});

		// Botón para mostrar todos los préstamos.
		JButton mostrarPrestamosButton = new JButton("Mostrar préstamos");
		mostrarPrestamosButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		mostrarPrestamosButton.setForeground(Color.WHITE); // Color de texto blanco.
		mostrarPrestamosButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		mostrarPrestamosButton.addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			for (Prestamo prestamo : prestamos) {
				sb.append(prestamo).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb.toString(), "Listado de préstamos", JOptionPane.INFORMATION_MESSAGE);
		});

		// Botón para devolver un préstamo.
		JButton devolverPrestamoButton = new JButton("Devolver préstamo");
		devolverPrestamoButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		devolverPrestamoButton.setForeground(Color.WHITE); // Color de texto blanco.
		devolverPrestamoButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		devolverPrestamoButton.addActionListener(e -> {
			String titulo = tituloField.getText();
			Prestamo prestamo = buscarPrestamo(titulo);

			if (prestamo != null) {
				List<String> titulosPrestamos = new ArrayList<>();
				for (Prestamo p : prestamos) {
					titulosPrestamos.add(p.getTituloLibro());
				}
				Object[] prestamosArray = titulosPrestamos.toArray();
				String tituloSeleccionado = (String) JOptionPane.showInputDialog(null,
						"Selecciona el préstamo a devolver:", "Seleccionar Préstamo", JOptionPane.PLAIN_MESSAGE, null,
						prestamosArray, prestamosArray[0]);

				if (tituloSeleccionado != null) {
					Prestamo prestamoSeleccionado = buscarPrestamo(tituloSeleccionado);
					if (prestamoSeleccionado != null) {
						prestamos.remove(prestamoSeleccionado);
						historialPrestamos.add(prestamoSeleccionado);
						JOptionPane.showMessageDialog(null, "Préstamo devuelto y registrado en el historial.");
						limpiarCampos();
						guardarDatos();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "El libro no se encuentra prestado.");
			}
		});

		// Botón para mostrar el historial de préstamos.
		JButton mostrarHistorialButton = new JButton("Mostrar historial de préstamos");
		mostrarHistorialButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		mostrarHistorialButton.setForeground(Color.WHITE); // Color de texto blanco.
		mostrarHistorialButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		mostrarHistorialButton.addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			for (Prestamo prestamo : historialPrestamos) {
				sb.append(prestamo).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb.toString(), "Historial de préstamos",
					JOptionPane.INFORMATION_MESSAGE);
		});
		// Botón y acción para buscar libros.
		JButton buscarButton = new JButton("Buscar libro");
		buscarButton.setBackground(new Color(59, 89, 182)); // Color de fondo azul.
		buscarButton.setForeground(Color.WHITE); // Color de texto blanco.
		buscarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente Arial en negrita.
		buscarButton.addActionListener(e -> {
			String textoBusqueda = buscarField.getText().trim(); // Texto ingresado para buscar.

			if (textoBusqueda.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, introduzca algún criterio de búsqueda.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				List<Libro> resultados = new ArrayList<>(); // Resultados de la búsqueda.

				// Búsqueda por título, autor o ISBN.
				for (Libro libro : libros) {
					if (libro.getTitulo().equalsIgnoreCase(textoBusqueda)
							|| libro.getAutor().equalsIgnoreCase(textoBusqueda)
							|| libro.getIsbn().equalsIgnoreCase(textoBusqueda)) {
						resultados.add(libro);
					}
				}

				// Mostrar resultados.
				if (resultados.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se encontraron resultados.", "Búsqueda",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					StringBuilder sb = new StringBuilder();
					for (Libro libro : resultados) {
						sb.append(libro).append("\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString(), "Resultados de la búsqueda",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton[] botones = { agregarButton, mostrarButton, registrarButton, mostrarPrestamosButton,
				devolverPrestamoButton, mostrarHistorialButton, buscarButton };
		for (JButton boton : botones) {
			boton.setBorderPainted(false);
			boton.setFocusPainted(false);
			boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar sobre el botón.
		}
		add(tituloLabel);
		add(tituloField);
		add(autorLabel);
		add(autorField);
		add(isbnLabel);
		add(isbnField);
		add(buscarLabel);
		add(buscarField);
		add(agregarButton);
		add(buscarButton);
		add(mostrarButton);
		add(registrarButton);
		add(mostrarPrestamosButton);
		add(devolverPrestamoButton);
		add(mostrarHistorialButton);
		add(new JLabel()); // Espaciador para alinear correctamente los componentes.

		cargarDatos();
	}

// Clase interna para representar un libro.
	class Libro {
		private String titulo;
		private String autor;
		private String isbn;

		public Libro(String titulo, String autor, String isbn) {
			this.titulo = titulo;
			this.autor = autor;
			this.isbn = isbn;
		}

		public String getTitulo() {
			return titulo;
		}

		public String getAutor() {
			return autor;
		}

		public String getIsbn() {
			return isbn;
		}

		@Override
		public String toString() {
			return "Libro{" + "titulo='" + titulo + '\'' + ", autor='" + autor + '\'' + ", isbn='" + isbn + '\'' + '}';
		}
	}

// Clase interna para representar un préstamo.
	class Prestamo {
		private String tituloLibro;
		private String nombrePrestatario;
		private String fechaPrestamo;

		public Prestamo(String tituloLibro, String nombrePrestatario, String fechaPrestamo) {
			this.tituloLibro = tituloLibro;
			this.nombrePrestatario = nombrePrestatario;
			this.fechaPrestamo = fechaPrestamo;
		}

		public String getTituloLibro() {
			return tituloLibro;
		}

		public String getNombrePrestatario() {
			return nombrePrestatario;
		}

		public String getFechaPrestamo() {
			return fechaPrestamo;
		}

		@Override
		public String toString() {
			return "Prestamo{" + "tituloLibro='" + tituloLibro + '\'' + ", nombrePrestatario='" + nombrePrestatario
					+ '\'' + ", fechaPrestamo='" + fechaPrestamo + '\'' + '}';
		}

	}

	// Método para hacer visible la interfaz gráfica.
	public void iniciar() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Establecer el aspecto visual del
																					// sistema para la aplicación.
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setLocationRelativeTo(null); // Centra la ventana en la pantalla.
		setVisible(true);
	}

	// Método para limpiar los campos de texto.
	private void limpiarCampos() {
		tituloField.setText("");
		autorField.setText("");
		isbnField.setText("");
	}

	// Método para buscar un préstamo por título de libro.
	private Prestamo buscarPrestamo(String titulo) {
		for (Prestamo prestamo : prestamos) {
			if (prestamo.getTituloLibro().equals(titulo)) {
				return prestamo;
			}
		}
		return null;
	}

	// Método para guardar datos en archivos.
	private void guardarDatos() {
		// Guardar los libros en libros.txt
		File librosArchivo = new File("src/Proyecto/libros.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(librosArchivo))) {
			for (Libro libro : libros) {
				writer.write(libro.getTitulo() + "|" + libro.getAutor() + "|" + libro.getIsbn());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Guardar los préstamos en prestamos.txt
		File prestamosArchivo = new File("src/Proyecto/prestamos.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(prestamosArchivo))) {
			for (Prestamo prestamo : prestamos) {
				writer.write(prestamo.getTituloLibro() + "|" + prestamo.getNombrePrestatario() + "|"
						+ prestamo.getFechaPrestamo());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Guardar el historial de préstamos en historialPrestamos.txt
		File historialPrestamosArchivo = new File("src/Proyecto/historialPrestamos.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(historialPrestamosArchivo))) {
			for (Prestamo prestamo : historialPrestamos) {
				writer.write(prestamo.getTituloLibro() + "|" + prestamo.getNombrePrestatario() + "|"
						+ prestamo.getFechaPrestamo());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para cargar datos desde archivos.
	private void cargarDatos() {
		// Cargar los libros desde libros.txt
		File librosArchivo = new File("src/Proyecto/libros.txt");

		if (librosArchivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(librosArchivo))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("\\|");
					String titulo = parts[0];
					String autor = parts[1];
					String isbn = parts[2];

					Libro libro = new Libro(titulo, autor, isbn);
					libros.add(libro);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Cargar los préstamos desde prestamos.txt
		File prestamosArchivo = new File("src/Proyecto/prestamos.txt");

		if (prestamosArchivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(prestamosArchivo))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("\\|");
					String tituloLibro = parts[0];
					String nombrePrestatario = parts[1];
					String fechaPrestamo = parts[2];

					Prestamo prestamo = new Prestamo(tituloLibro, nombrePrestatario, fechaPrestamo);
					prestamos.add(prestamo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Cargar el historial de préstamos desde historialPrestamos.txt
		File historialPrestamosArchivo = new File("src/Proyecto/historialPrestamos.txt");

		if (historialPrestamosArchivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(historialPrestamosArchivo))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("\\|");
					String tituloLibro = parts[0];
					String nombrePrestatario = parts[1];
					String fechaPrestamo = parts[2];

					Prestamo prestamo = new Prestamo(tituloLibro, nombrePrestatario, fechaPrestamo);
					historialPrestamos.add(prestamo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
