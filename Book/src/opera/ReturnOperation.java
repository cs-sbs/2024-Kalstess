package opera;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class ReturnOperation implements IOPeration {
    @Override
    public void work(BookList bookList) {
        System.out.println("归还图书！");
        System.out.println("请输入要归还的图书名字:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int currentSize = bookList.getUsedSize();
        for (int i = 0; i < currentSize; i++) {
            Book temp = bookList.getBook(i);
            if (temp.getName().equals(name) && temp.isBorrowed()) {
                temp.setBorrowed(false);
                temp.setBorrowedBy("");
                bookList.saveBooksToFile();
                System.out.println("归还成功！");
                return;
            }
        }
        System.out.println("没有需要归还的书！");
    }
}
