package opera;

import book.Book;
import book.BookList;

public class ShowOperation implements IOPeration {
    @Override
    public void work(BookList bookList) {
        System.out.println("显示图书！");
        int currentSize = bookList.getUsedSize();
        if (currentSize == 0) {
            System.out.println("没有图书可以显示！");
        }
        for (int i = 0; i < currentSize; i++) {
            Book book = bookList.getBook(i);
            System.out.println(book);
        }
    }
}
