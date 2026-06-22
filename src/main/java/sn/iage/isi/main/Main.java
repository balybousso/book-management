package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.BookRepository;
import sn.iage.isi.repositories.CategoryRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BookRepository bookRepository = new BookRepository();

        // 1. Créer une catégorie de test (ou récupère-en une existante si tu en as déjà)
        Category category = Category.builder()
                .name("Roman")
                .build();
        Category savedCategory = categoryRepository.create(category);
        System.out.println("Catégorie créée : " + savedCategory);

        // 2. Créer un livre lié à cette catégorie
        Book book = Book.builder()
                .title("Une si longue lettre")
                .author("Mariama Ba")
                .publicationYear(1979)
                .countPages(160)
                .category(savedCategory)
                .build();
        Book savedBook = bookRepository.createBook(book);
        System.out.println("Livre créé : " + savedBook);

        // 3. Lister tous les livres
        System.out.println("\n--- Tous les livres ---");
        List<Book> allBooks = bookRepository.listAllBooks();
        for (Book b : allBooks) {
            System.out.println(b);
        }

        // 4. Rechercher par titre
        System.out.println("\n--- Recherche par titre 'lettre' ---");
        for (Book b : bookRepository.searchBooksByTitle("lettre")) {
            System.out.println(b);
        }

        // 5. Rechercher par auteur
        System.out.println("\n--- Recherche par auteur 'Mariama' ---");
        for (Book b : bookRepository.searchBooksByAuthor("Mariama")) {
            System.out.println(b);
        }

        // 6. Compter tous les livres
        System.out.println("\nNombre total de livres : " + bookRepository.countAllBooks());

        // 7. Trouver un livre par son ISBN généré
        System.out.println("\n--- Recherche par ISBN ---");
        Book foundByIsbn = bookRepository.findBookByIsbn(savedBook.getIsbn());
        System.out.println(foundByIsbn);

        // 8. Lister les livres par catégorie
        System.out.println("\n--- Livres de la catégorie 'Roman' ---");
        for (Book b : bookRepository.listBooksByCategory("Roman")) {
            System.out.println(b);
        }
    }
}