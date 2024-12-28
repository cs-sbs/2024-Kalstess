package user;

import opera.*;

import java.util.Scanner;

public class NormalUser extends User {
    public NormalUser(String name) {
        super(name);
        this.ioPerations = new IOPeration[]{
                new ExitOperation(),
                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation()
        };
    }

    @Override
    public int menu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("_________________");
            System.out.println("hello, " + name + "~");
            System.out.println("1. 查找图书！");
            System.out.println("2. 借阅图书！");
            System.out.println("3. 归还图书！");
            System.out.println("0. 退出登录！");
            System.out.println("请输入操作选项或输入 'exit' 退出程序：");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                return -1;
            }

            try {
                choice = Integer.parseInt(input);
                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("无效输入，请输入有效的选项（0-3），或输入 'exit' 退出程序。");
                }
            } catch (NumberFormatException e) {
                System.out.println("无效输入，请输入数字或 'exit'。");
            }
        }
    }
}
