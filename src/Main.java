import controllers.LibraryController;
import models.Book;
import models.User;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static LibraryController libraryController = new LibraryController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    listAllBooks();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    findUser();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo");

            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Sistema de Gestión de Biblioteca ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Buscar libro");
        System.out.println("3. Listar todos los libros");
        System.out.println("4. Agregar usuario");
        System.out.println("5. Buscar usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void addBook() {
        System.out.println("Ingresa el titulo del libro: ");
        String title = scanner.nextLine();

        System.out.println("Ingresa el autor del libro: ");
        String author = scanner.nextLine();

        System.out.println("Ingresa el genero del libro: ");
        String genre = scanner.nextLine();

        Book newBook = new Book(title, author, genre);
        libraryController.addBook(newBook);
        System.out.println("Libro agregado con exito");
    }

    /*Ese mejorara esta funcionalidad para que no solo busque por ID si no tambien por nombre*/
    private static void findBook() {
        System.out.println("Ingrese el título del libro (dejar vacío si no desea buscar por título): ");
        String title = scanner.nextLine();

        System.out.println("Ingrese el autor del libro (dejar vacío si no desea buscar por autor): ");
        String author = scanner.nextLine();

        System.out.println("Ingrese el género del libro (dejar vacío si no desea buscar por género): ");
        String genre = scanner.nextLine();

        List<Book> foundBooks = libraryController.searchBooks(title, author, genre);
        if (!foundBooks.isEmpty()) {
            System.out.println("Libros encontrados:");
            for (Book book : foundBooks) {
                System.out.println("- " + book.getTitle() + " de " + book.getAuthor());
            }
        } else {
            System.out.println("No se encontraron libros que coincidan con los criterios de búsqueda.");
        }
    }

    private static void listAllBooks() {
        List<Book> books = libraryController.getAllBooks();

        if (!books.isEmpty()) {
            System.out.println("Listado de libros en la biblioteca");

            for (Book book : books) {
                System.out.println("- " + book.getTitle() + " de " + book.getAuthor() + " (Género: " + book.getGenre() + ")");
            }
        } else {
            System.out.println("No hay libros en la biblioteca.");
        }
    }

    private static void addUser() {
        System.out.println("Ingrese el ID del usuario: ");
        String id = scanner.nextLine();

        System.out.println("Ingrese el nombre del usuario: ");
        String name = scanner.nextLine();

        User newUser = new User(id, name);
        libraryController.addUser(newUser);
        System.out.println("Usuario agregado con exito");
    }

    private static void findUser() {
        System.out.println("Ingrese el ID del usuario a buscar: ");
        String id = scanner.nextLine();

        User user = libraryController.findUserById(id);
        if (user != null) {
            System.out.println("Usuario encontrado: " + user.getName());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}