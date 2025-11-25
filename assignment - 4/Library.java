import java.io.*;

public class Book implements Serializable {
    int bookId;
    String title, author, category;
    boolean isIssued;

    Book(int id, String t, String a, String c) {
        bookId = id;
        title = t;
        author = a;
        category = c;
        isIssued = false;
    }

    void markIssued() { isIssued = true; }
    void markReturned() { isIssued = false; }

    void show() {
        System.out.println(bookId+" | "+title+" | "+author+" | "+category+" | Issued:"+isIssued);
    }
}
import java.io.*;
import java.util.*;

public class Member implements Serializable {
    int memberId;
    String name, email;
    List<Integer> issuedBooks = new ArrayList<>();

    Member(int id, String n, String e) {
        memberId = id;
        name = n;
        email = e;
    }

    void addBook(int b) { issuedBooks.add(b); }
    void returnBook(int b) { issuedBooks.remove(Integer.valueOf(b)); }

    void show() {
        System.out.println(memberId+" | "+name+" | "+email+" | Books:"+issuedBooks);
    }
}
import java.io.*;
import java.util.*;

public class LibraryManager {

    Map<Integer,Book> books = new HashMap<>();
    Map<Integer,Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // load data from files
    void load() {
        try {
            books = (Map<Integer,Book>) new ObjectInputStream(new FileInputStream("books.dat")).readObject();
            members = (Map<Integer,Member>) new ObjectInputStream(new FileInputStream("members.dat")).readObject();
        } catch (Exception e) {
            System.out.println("Starting fresh...");
        }
    }

    // save all data in files
    void save() {
        try {
            new ObjectOutputStream(new FileOutputStream("books.dat")).writeObject(books);
            new ObjectOutputStream(new FileOutputStream("members.dat")).writeObject(members);
        } catch (Exception e) {}
    }

    void addBook() {
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Author: ");
        String a = sc.nextLine();
        System.out.print("Category: ");
        String c = sc.nextLine();

        int id = books.size() + 101;
        books.put(id, new Book(id, t, a, c));
        System.out.println("Book added: " + id);
        save();
    }

    void addMember() {
        System.out.print("Name: ");
        String n = sc.nextLine();
        System.out.print("Email: ");
        String e = sc.nextLine();

        int id = members.size() + 201;
        members.put(id, new Member(id, n, e));
        System.out.println("Member added: " + id);
        save();
    }

    void issueBook() {
        System.out.print("Book ID: ");
        int b = sc.nextInt();
        System.out.print("Member ID: ");
        int m = sc.nextInt();
        sc.nextLine();

        if (!books.containsKey(b) || !members.containsKey(m)) {
            System.out.println("Invalid IDs!");
            return;
        }

        Book bk = books.get(b);
        if (bk.isIssued) {
            System.out.println("Already issued!");
            return;
        }

        bk.markIssued();
        members.get(m).addBook(b);
        System.out.println("Book Issued!");
        save();
    }

    void returnBook() {
        System.out.print("Book ID: ");
        int b = sc.nextInt();
        sc.nextLine();

        if (!books.containsKey(b)) {
            System.out.println("Invalid Book ID");
            return;
        }

        books.get(b).markReturned();
        for (Member m : members.values()) {
            m.returnBook(b);
        }
        System.out.println("Book Returned!");
        save();
    }

    void searchBooks() {
        System.out.print("Search: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key) ||
                b.author.toLowerCase().contains(key) ||
                b.category.toLowerCase().contains(key)) {
                b.show();
            }
        }
    }

    void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());

        System.out.println("1. Sort by Title\n2. Sort by Author");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1)
            Collections.sort(list, (x, y) -> x.title.compareToIgnoreCase(y.title));
        else
            Collections.sort(list, (x, y) -> x.author.compareToIgnoreCase(y.author));

        for (Book b : list)
            b.show();
    }

    void menu() {
        load();
        while (true) {
            System.out.println("\n1.Add Book  2.Add Member  3.Issue  4.Return  5.Search  6.Sort  7.Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1: addBook(); break;
                case 2: addMember(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBooks(); break;
                case 6: sortBooks(); break;
                case 7: save(); System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManager().menu();
    }
}
