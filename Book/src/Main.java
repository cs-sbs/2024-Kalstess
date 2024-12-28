import book.BookList;
import user.User;
import user.UserManager;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();

        while (true) {
            User user = UserManager.login();

            if (user == null) {
                System.out.println("登录失败，请重新选择身份登录！");
                continue;
            }

            while (true) {
                int choice = user.menu();

                if (choice == -1) {
                    System.out.println("退出程序！");
                    return;
                }
                if (choice == 0) {
                    System.out.println("退出登录...");
                    break;
                }

                user.doWork(choice, bookList);
            }
        }
    }
}
