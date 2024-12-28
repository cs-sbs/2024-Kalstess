package user;

import java.io.*;
import java.util.*;

public class UserManager {

    private static final String FILE_NAME = "users.txt";

    // 登录方法
    public static User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请选择你的身份：1->管理员 0->普通用户");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();
        String role = (roleChoice == 1) ? "admin" : "normal";

        System.out.println("请输入用户名：");
        String name = scanner.nextLine();

        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Map<String, String[]> users = loadUsers();

        if (role.equals("admin")) {
            if (users.containsKey(name)) {
                String[] userInfo = users.get(name);
                String storedPassword = userInfo[0];
                String storedRole = userInfo[1];

                if (storedPassword.equals(password) && storedRole.equals("admin")) {
                    return new AdminUser(name);
                } else {
                    System.out.println("管理员身份或密码错误！");
                }
            } else {
                System.out.println("管理员用户名不存在！");
            }
        } else {
            // 普通用户可以选择注册
            if (users.containsKey(name)) {
                String[] userInfo = users.get(name);
                String storedPassword = userInfo[0];
                String storedRole = userInfo[1];

                if (storedPassword.equals(password) && storedRole.equals("normal")) {
                    return new NormalUser(name);
                } else {
                    System.out.println("密码或身份错误！");
                }
            } else {
                System.out.println("用户不存在，是否注册新用户？(y/n)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    registerNewUser(name, password, role);
                    return login();  // 注册后重新登录
                }
            }
        }

        return null; // 登录失败，返回 null 让主程序重新选择身份
    }

    // 从文件加载所有用户信息
    private static Map<String, String[]> loadUsers() {
        Map<String, String[]> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.put(parts[0], new String[]{parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // 注册新用户
    private static void registerNewUser(String name, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "," + password + "," + role + "\n");
            System.out.println("用户注册成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
