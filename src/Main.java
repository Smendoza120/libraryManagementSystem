import controllers.LibraryController;
import models.Book;
import models.User;

import java.util.Scanner;

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
                    addUser();
                    break;
                case 4:
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
        System.out.println("2. Buscar libro por ID");
        System.out.println("3. Agregar usuario");
        System.out.println("4. Buscar usuario por ID");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void addBook() {
        System.out.println("Ingresa el ID del libro: ");
        String id = scanner.nextLine();

        System.out.println("Ingresa el titulo del libro: ");
        String title = scanner.nextLine();

        System.out.println("Ingresa el autor del libro: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, title, author);
        libraryController.addBook(newBook);
        System.out.println("Libro agregado con exito");
    }

    /*Ese mejorara esta funcionalidad para que no solo busque por ID si no tambien por nombre*/
    private static void findBook() {
        System.out.println("Ingrese el ID del libro a buscar: ");
        String id = scanner.nextLine();

        Book book = libraryController.findBookById(id);
        if (book != null) {
            System.out.println("Libro encontrado: " + book.getTitle() + " de " + book.getAuthor());
        } else {
            System.out.println("Libro no encontrado");
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