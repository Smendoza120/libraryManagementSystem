import controllers.LibraryController;
import models.Book;
import models.User;

import java.time.LocalDate;
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
                    showGenres();
                    break;
                case 3:
                    findBook();
                    break;
                case 4:
                    listAllBooks();
                    break;
                case 5:
                    addUser();
                    break;
                case 6:
                    listAllUsers();
                    break;
                case 7:
                    findUser();
                    break;
                case 8:
                    loanBook();
                    break;
                case 9:
                    returnBook();
                    break;
                case 10:
                    listUserLoans();
                    break;
                case 11:
                    libraryController.listAllLoans();
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
        System.out.println("2. Lista de generos");
        System.out.println("3. Buscar libro");
        System.out.println("4. Listar todos los libros");
        System.out.println("5. Agregar usuario");
        System.out.println("6. Listar todos los usuarios");
        System.out.println("7. Buscar usuario");
        System.out.println("8. Prestar un libro");
        System.out.println("9. Devolver un libro");
        System.out.println("10. Listar libros prestados por un usuario");
        System.out.println("11. Listar todos los libros prestados");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void showGenres() {
        System.out.println("\n--- Lista de Géneros Disponibles ---");
        libraryController.showAvailableGenres();
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
        System.out.println("Ingrese el título, autor o género del libro a buscar: ");
        String query = scanner.nextLine().trim();

        List<Book> books = libraryController.searchBooks(query);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan con el criterio.");
        } else {
            System.out.println("\n--- Resultados de la búsqueda ---");
            for (Book book : books) {
                System.out.println("ID: " + book.getId() + ", Título: " + book.getTitle() +
                        ", Autor: " + book.getAuthor() + ", Género: " + book.getGenre());
            }
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

    //USer section
    private static void addUser() {
        System.out.println("Ingrese el nombre del usuario: ");
        String name = scanner.nextLine();

        System.out.println("Ingrese el tipo de documento: ");
        String documentType = scanner.nextLine();

        System.out.println("Ingrese el número de documento: ");
        String documentNumber = scanner.nextLine();

        User newUser = new User(null, name, documentType, documentNumber);
        libraryController.addUser(newUser);
        System.out.println("Usuario agregado con exito");
    }

    private static void listAllUsers() {
        List<User> users = libraryController.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("\n--- Listado de Usuarios ---");
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Nombre: " + user.getName() +
                        ", Tipo de Documento: " + user.getDocumentType() +
                        ", Número de Documento: " + user.getDocumentNumber());
            }
        }
    }

    private static void findUser() {
        System.out.println("Ingrese el nombre o número de documento del usuario a buscar: ");
        String searchValue = scanner.nextLine();

        User user = libraryController.searchUser(searchValue);

        if (user != null) {
            System.out.println("Usuario encontrado: " + user.getName() + " (Documento: " + user.getDocumentType() + ": " + user.getDocumentNumber() + ")");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    //Loan section
    private static void loanBook() {
        System.out.println("Ingrese el nombre o número de documento del usuario: ");
        String userQuery = scanner.nextLine();

        System.out.println("Ingrese el título, autor o género del libro a prestar: ");
        String bookQuery = scanner.nextLine();

        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusWeeks(2);

        libraryController.loanBook(userQuery, bookQuery);
    }

    private static void returnBook() {
        System.out.println("Ingrese el nombre o número de documento del usuario que devuelve el libro: ");
        String userQuery = scanner.nextLine();

        System.out.println("Ingrese el título, autor o género del libro a devolver: ");
        String bookQuery = scanner.nextLine();

        libraryController.returnBook(bookQuery, userQuery);
    }

    private static void listUserLoans() {
        System.out.println("Ingrese el nombre o número de documento del usuario: ");
        String userQuery = scanner.nextLine();

        libraryController.listUserLoans(userQuery);
    }
}