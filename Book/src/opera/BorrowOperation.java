package opera;
import book.Book;
import book.BookList;
import java.util.Scanner;

public class BorrowOperation implements IOPeration {
    @Override
    public void work(BookList bookList) {
        System.out.println("借阅图书！");
        System.out.println("请输入要借阅的图书名字:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int currentSize = bookList.getUsedSize();
        for (int i = 0; i < currentSize; i++) {
            Book temp = bookList.getBook(i);
            if (temp.getName().equals(name) && !temp.isBorrowed()) {
                System.out.println("请输入借阅者的姓名:");
                String borrowedBy = scanner.nextLine();
                temp.setBorrowed(true);
                temp.setBorrowedBy(borrowedBy);
                bookList.saveBooksToFile();
                System.out.println("借阅成功！");
                return;
            }
        }
        System.out.println("没有该图书或该图书已借出！");
    }
}
