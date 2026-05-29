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
                EmployeeEntity emp = new EmployeeEntity(name,age,department_id);
                empDao.addEmployee(emp);


            }

            if (process_number == 2) {
                System.out.println("削除したい従業員番号を入力してください");
                Scanner scanner = new Scanner(System.in);
                int employeeId = scanner.nextInt();
                empDao.deleteEmployee(employeeId);
            }

            if (process_number == 3) {
                System.out.println("変更したい従業員番号を入力してください");
                Scanner scanner = new Scanner(System.in);
                int employeeId = scanner.nextInt();
                System.out.println("変更したい内容を選択してください");
                System.out.println("1:名前、2:年齢、3:部署番号");
                int alterElement = scanner.nextInt();
                empDao.alterEmployeeElement(employeeId, alterElement);

            }

            if (process_number == 4) {
                empDao.showEmployeeTable();
            }

            if (process_number == 5) {
                System.out.println("従業員番号を入力してください");
                Scanner scanner = new Scanner(System.in);
                int showDepartmentEmployeeId = scanner.nextInt();
                String departmentName = empDao.showDepartmentName(showDepartmentEmployeeId);
                System.out.println(departmentName + "です。");

            }
        }
        catch(SQLException e) {
            System.out.println("例外が発生しました");
            e.printStackTrace();
        }

    }
}
