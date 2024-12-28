package opera;
import book.BookList;
import java.util.Scanner;

public class DelOperation implements IOPeration {
    @Override
    public void work(BookList bookList) {
        System.out.println("删除图书！");
        System.out.println("请输入要删除图书的名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        if (bookList.deleteBook(name)) {
            System.out.println("删除成功！");
        } else {
            System.out.println("没有找到这本书！");
        }
    }
}
