# Gestor de Biblioteca

El proyecto "Gestor de Biblioteca" es una aplicación de escritorio desarrollada en Java, que permite administrar una pequeña biblioteca con funcionalidades para agregar libros, gestionar préstamos y devoluciones, y llevar un registro histórico de los préstamos.

## Clases

### `GestorBiblioteca`

Esta es la clase principal que contiene la lógica de la interfaz de usuario y la interacción con el sistema de gestión de la biblioteca.

#### Atributos

- `libros`: Lista que almacena todos los libros disponibles en la biblioteca.
- `prestamos`: Lista que lleva el control de los libros que están actualmente prestados.
- `historialPrestamos`: Lista que mantiene un registro histórico de los préstamos realizados.
- `tituloField`, `autorField`, `isbnField`, `buscarField`: Campos de texto para ingresar la información de los libros y realizar búsquedas.

#### Métodos

- `main`: Método principal que inicia la aplicación.
- `GestorBiblioteca`: Constructor que inicializa la interfaz gráfica y carga los datos existentes.
- `iniciar`: Hace visible la interfaz gráfica para el usuario.
- `limpiarCampos`: Limpia los campos de texto en la interfaz gráfica.
- `buscarPrestamo`: Busca un préstamo en la lista de préstamos actuales basado en el título del libro.
- `guardarDatos`: Guarda los datos actuales de libros, préstamos y el historial en archivos.
- `cargarDatos`: Carga los datos de libros, préstamos y el historial desde archivos.

### `Libro`

Clase que representa la información de un libro en la biblioteca.

#### Atributos

- `titulo`: Título del libro.
- `autor`: Nombre del autor del libro.
- `isbn`: ISBN del libro.

#### Métodos

- `Libro`: Constructor para inicializar un nuevo libro.
- `getTitulo`, `getAutor`, `getIsbn`: Métodos para obtener la información del libro.
- `toString`: Devuelve una representación en cadena del objeto Libro.

### `Prestamo`

Clase que representa la información de un préstamo.

#### Atributos

- `tituloLibro`: Título del libro prestado.
- `nombrePrestatario`: Nombre de la persona a quien se le ha prestado el libro.
- `fechaPrestamo`: Fecha en que se realizó el préstamo.

#### Métodos

- `Prestamo`: Constructor para inicializar un nuevo préstamo.
- `getTituloLibro`, `getNombrePrestatario`, `getFechaPrestamo`: Métodos para obtener la información del préstamo.
- `toString`: Devuelve una representación en cadena del objeto Prestamo.

## Funcionalidades

1. **Agregar Libros**: Permite ingresar nuevos libros a la biblioteca, registrando su título, autor e ISBN.
2. **Gestionar Préstamos**: Permite realizar nuevos préstamos, asegurándose de que el libro esté disponible.
3. **Devolución de Libros**: Facilita la devolución de libros y actualiza el historial de préstamos.
4. **Búsqueda de Libros**: Ofrece la funcionalidad de buscar libros disponibles en la biblioteca.
5. **Visualización de Préstamos Actuales e Históricos**: Permite ver los préstamos actuales y un registro histórico de los préstamos realizados.

## Requisitos del Sistema

- Java Runtime Environment (JRE) 8 o superior.
- Acceso de lectura y escritura al sistema de archivos para guardar y cargar datos.

## Instrucciones de Uso

1. Ejecute la aplicación. Se mostrará la interfaz principal del "Gestor de Biblioteca".
2. Para agregar un nuevo libro, complete los campos "Título", "Autor" e "ISBN" y haga clic en "Agregar libro".
3. Para realizar un préstamo, ingrese el título del libro y haga clic en "Registrar préstamo".
4. Para devolver un libro, seleccione el préstamo correspondiente y haga clic en "Devolver préstamo".
5. Utilice el campo "Buscar" para encontrar libros rápidamente en la biblioteca.
6. Explore las demás funcionalidades disponibles en la interfaz según sus necesidades.

## Contribuciones y Mejoras Futuras

Este proyecto es de código abierto y acepta contribuciones. Algunas mejoras futuras podrían incluir:

- Mejora de la interfaz de usuario para una experiencia más rica.
- Implementación de una base de datos para manejar grandes cantidades de datos.
- Funcionalidades adicionales como reservas de libros, recordatorios de devolución, entre otros.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulte el archivo `LICENSE` para más información.
