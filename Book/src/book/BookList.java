package book;
import java.io.*;

public class BookList {
    private static final int DEFAULT_SIZE = 10;
    private final Book[] books = new Book[DEFAULT_SIZE];
    private int usedSize;
    private static final String FILE_NAME = "books.txt";

    public BookList() {
        loadBooksFromFile();
    }

    // 从文件加载图书数据
    public void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            usedSize = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 6) {
                    String name = parts[0];
                    String author = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    String type = parts[3];
                    boolean isBorrowed = Boolean.parseBoolean(parts[4]);

                    String borrowedBy = isBorrowed ? parts[5] : "无";

                    Book book = new Book(name, author, price, type);
                    book.setBorrowed(isBorrowed);
                    book.setBorrowedBy(borrowedBy);
                    books[usedSize++] = book;
                } else {
                    System.out.println("警告：文件中的一行数据格式不正确，忽略该行。");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 保存图书数据到文件
    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < usedSize; i++) {
                Book book = books[i];
                writer.write(book.getName() + "," +
                        book.getAuthor() + "," +
                        book.getPrice() + "," +
                        book.getType() + "," +
                        book.isBorrowed() + "," +
                        (book.isBorrowed() ? book.getBorrowedBy() : "无") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 删除指定书籍
    public boolean deleteBook(String name) {
        int index = -1;
        for (int i = 0; i < usedSize; i++) {
            if (books[i].getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        for (int i = index; i < usedSize - 1; i++) {
            books[i] = books[i + 1];
        }

        books[usedSize - 1] = null;
        usedSize--;

        saveBooksToFile();
        return true;
    }

    public Book getBook(int pos) {
        return this.books[pos];
    }

    public void setBooks(Book book) {
        this.books[usedSize] = book;
    }

    public int getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(int usedSize) {
        this.usedSize = usedSize;
    }
}
