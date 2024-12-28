package opera;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class FindOperation implements IOPeration {
    @Override
    public void work(BookList bookList) {
        System.out.println("查找图书！");
        System.out.println("请输入要查找的图书名字（支持模糊查询）");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        int currentSize = bookList.getUsedSize();
        boolean found = false;

        for (int i = 0; i < currentSize; i++) {
            Book book = bookList.getBook(i);
            if (book.getName().contains(name)) {
                System.out.println("查到了：");
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("没有找到匹配的书籍！");
        }
    }
}
