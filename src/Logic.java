import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Logic {


    public void choose_process(int process_number) throws SQLException {
        try {
            Connection conn = ConnectManager.getConnection();
            EmployeeDao empDao = new EmployeeDao(conn);
            if (process_number == 1) {
                String name;
                int age;
                int department_id;
                System.out.println("従業員の名前と年齢と部署番号を入力してください");
                System.out.print("名前");
                Scanner scanner = new Scanner(System.in);
                name = scanner.nextLine();
                System.out.print("年齢");
                age = scanner.nextInt();
                System.out.print("部署番号");
                department_id = scanner.nextInt();
                empDao.addEmployee(name,age,department_id);


            }

            if (process_number == 2) {

            }

            if (process_number == 3) {

            }

            if (process_number == 4) {

            }
        }
        catch(SQLException e) {
            System.out.println("例外が発生しました");
            e.printStackTrace();
        }

    }
}
