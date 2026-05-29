import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("1~4の数字を入力してください");
        System.out.println("1:従業員の追加");
        System.out.println("2:従業員の削除");
        System.out.println("3:従業員情報の変更");
        System.out.println("4:従業員テーブルの表示");
        Scanner scanner = new Scanner(System.in);
        int process_number = scanner.nextInt();

        Logic logic = new Logic();
        try {
            logic.choose_process(process_number);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
