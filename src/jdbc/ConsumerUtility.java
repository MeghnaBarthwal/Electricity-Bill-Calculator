package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javafiles.Consumer;

public class ConsumerUtility {
    static Scanner scanner = new Scanner(System.in);

    public static Consumer validateConsumer(int id, String password) throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("Select * from consumer where id = ? and password = ?");
        pst.setInt(1, id);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == false)
            return null;
        return new Consumer(rs.getInt("id"), rs.getString("name"), rs.getString("area"), rs.getString("city"),
                rs.getString("type"), rs.getString("password"));
    }

    public static Consumer newRegistration() throws ClassNotFoundException, SQLException {
        System.out.println("Registration form: ");
        System.out.print("Enter your name:");
        String name = scanner.nextLine();
        System.out.print("Enter your area: ");
        String area = scanner.nextLine();
        System.out.print("Enter your city: ");
        String city = scanner.nextLine();
        System.out.print("Enter your connection type: ");
        String type = scanner.nextLine();
        System.out.print("Set password: ");
        String password = scanner.next();
        scanner.nextLine();
        int check = ConsumerUtility.registerConsumer(name, city, area, type, password);
        if (check == 1) {
            System.out.println("Registration Successful!");
            System.out.println("\nGenerating your ConsumerId and Password");
            return new Consumer(ConsumerUtility.getConsumerId(name, password), name, area, city, type, password);
        }
        return null;
    }

    private static int getConsumerId(String Name, String pswd) throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.getInstance().getConnection();
        CallableStatement callable = con.prepareCall("{call getNamePassword(?,?,?)}");
        callable.setString(1, Name);
        callable.setString(2, pswd);
        callable.registerOutParameter(3, java.sql.Types.INTEGER);
        callable.executeUpdate();
        int id = callable.getInt(3);
        return id;
    }

    private static int registerConsumer(String consumerName, String consumerCity, String consumerArea,
            String connectionType, String password) throws ClassNotFoundException, SQLException {
        System.out.println("\nRegistring Consumer........");
        Connection con = MyConnection.getInstance().getConnection();
        String sqlInsert = "insert into consumer (consumerName,area,city,connectionType,password) value(?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sqlInsert);
        pst.setString(1, consumerName);
        pst.setString(2, consumerArea);
        pst.setString(3, consumerCity);
        pst.setString(4, connectionType);
        pst.setString(5, password);
        int rowAffected = pst.executeUpdate();
        pst.clearParameters();
        con.close();
        return rowAffected;
    }

    public static Consumer consumerLogin() throws ClassNotFoundException, SQLException {
        System.out.println("Enter Your Consumer Id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your Password");
        String password = scanner.next().toLowerCase();
        scanner.nextLine();
        System.out.println("\nVerifying your details....");
        Consumer cobj = validateConsumer(id, password);
        return cobj;
    }

    public static void getBillByMonth(String month, int year, int id) throws ClassNotFoundException, SQLException {
        Connection con = MyConnection.getInstance().getConnection();
        PreparedStatement pst = con
                .prepareStatement("Select * from Bill where month = ? and year = ? and consumerId = ?");
        pst.setString(1, month);
        pst.setInt(2, year);
        pst.setInt(3, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == false) {
            System.out.println("No record find for this month and year.");
            return;
        }
        System.out.println("BillID  UnitsConsumed  Month   Year");
        while (rs.next()) {
            System.out.println(rs.getInt("billId") + " " + rs.getInt("unitsConsumed") + " " + rs.getInt("year") + " "
                    + rs.getString("month"));
        }
    }

    public static void getBillsByYear(int year, int id) {

    }

    public static void getAllBills(int id) {

    }
}
