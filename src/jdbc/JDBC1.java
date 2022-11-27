package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC1 {
	public static void addUnitsConsumed() throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Connection con = MyConnection.getConnection();
		String sqlInsert = "insert into bill(consumerId,unitsconsume,year,month) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sqlInsert);
		System.out.print("Enter the consumerID of consumer : ");
		pst.setInt(1, sc.nextInt());
		System.out.print("Enter the year : ");
		pst.setInt(3, sc.nextInt());
		System.out.print("Enter the month : ");
		pst.setString(4, sc.nextLine());
		System.out.print("Enter units consumed by consumer : ");
		pst.setInt(2, sc.nextInt());
		pst.executeUpdate();
		
	}
	
	public static void generateBillsForAllCustomers() throws ClassNotFoundException, SQLException {
		Connection con = MyConnection.getConnection();
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("select c.consumerid as consumerid,b.billid as bid,b.unitsconsume as units,b.year as year,b.month as month,c.connectiontype as connection from bill b join consumer c on b.consumerid=c.consumerid;");
		while(result.next()) {
			System.out.println("Consumer id : "+result.getInt("consumerid"));
			System.out.println("Bill Number : "+result.getInt("bid"));
			System.out.println("Year : "+result.getInt("year"));
			System.out.println("Month : "+result.getString("month"));
			System.out.println("Connection Type : "+result.getString("connection"));
			System.out.println("Units Consumed : "+result.getInt("units"));
			if(result.getString("connection").equalsIgnoreCase("domestic")) {
				int totalAmount = 2*result.getInt("units");
				System.out.println("Total Amount : "+totalAmount);
			}
			if(result.getString("connection").equalsIgnoreCase("commercial")) {
				int totalAmount = 4*result.getInt("units");
				System.out.println("Total Amount : "+totalAmount);
			}
			
		}
	}
	
	public static void generateBillByMonth() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getConnection();
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the month : ");
		String month = sc.nextLine();
		ResultSet result2 = st.executeQuery("select c.consumerid as consumerid,c.connectiontype as type,b.unitsconsume as units,c.consumername as name from bill b join consumer c on b.consumerid = c.consumerid where b.month = "+month+"");
		while(result2.next()) {
			System.out.println("Consumer id : "+result2.getInt("consumerid"));
			System.out.println("Consumer name is : "+result2.getString("name"));
			System.out.println("Connection Type is : "+result2.getString("type"));
			System.out.println("Units Consumed : "+result2.getInt("units"));
			if(result2.getString("connection").equalsIgnoreCase("domestic")) {
				int n = 2*result2.getInt("units");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result2.getInt("units");
			}
			if(result2.getString("connection").equalsIgnoreCase("commercial")) {
				int num = 4*result2.getInt("units");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result2.getInt("units");
			}
		}
		System.out.println("Total Domestic units consumed in "+month+" are : "+totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in "+month+" are : "+totalDomesticAmount);
		System.out.println("Total Commercial units consumed in "+month+" are : "+totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in "+month+" are : "+totalCommercialAmount);
		System.out.println("Total Units consumed in "+month+" are : "+(totalDomesticUnits+totalCommercialUnits));
		System.out.println("Total Amount in "+month+" is : "+(totalDomesticAmount+totalCommercialAmount));
	}
	
	
	public static void generateBillByYear() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getConnection();
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the year : ");
		String year = sc.nextLine();
		ResultSet result2 = st.executeQuery("select c.consumerid as consumerid,c.connectiontype as type,b.unitsconsume as units,c.consumername as name from bill b join consumer c on b.consumerid = c.consumerid where b.month = "+year+"");
		while(result2.next()) {
			System.out.println("Consumer id : "+result2.getInt("consumerid"));
			System.out.println("Consumer name is : "+result2.getString("name"));
			System.out.println("Connection Type is : "+result2.getString("type"));
			System.out.println("Units Consumed : "+result2.getInt("units"));
			if(result2.getString("connection").equalsIgnoreCase("domestic")) {
				int n = 2*result2.getInt("units");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result2.getInt("units");
			}
			if(result2.getString("connection").equalsIgnoreCase("commercial")) {
				int num = 4*result2.getInt("units");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result2.getInt("units");
			}
		}
		System.out.println("Total Domestic units consumed in "+year+" are : "+totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in "+year+" are : "+totalDomesticAmount);
		System.out.println("Total Commercial units consumed in "+year+" are : "+totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in "+year+" are : "+totalCommercialAmount);
		System.out.println("Total Units consumed in "+year+" are : "+(totalDomesticUnits+totalCommercialUnits));
		System.out.println("Total Amount in "+year+" is : "+(totalDomesticAmount+totalCommercialAmount));
	}


}
