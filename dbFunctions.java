package jdbc;
import java.sql.*;

public class dbFunctions {
	// ResultSet result = getSQLConnection().createStatement().executeQuery("Select
	// * from StudentData");
	private Connection getSQLConnection() { // getSQL Connection
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "atos123$");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	private PreparedStatement getPStatement(String query) { // prepared statement
		PreparedStatement pstat = null;
		try {
			pstat = getSQLConnection().prepareStatement(query);
			return pstat;
		} catch (Exception e) {

		}
		return pstat;
	}
	public void searchCustomer(int sid) { // Search Function
		try {
			ResultSet result = getSQLConnection().createStatement().executeQuery("select * from customer c join account a on a.accid=c.accid where a.accid =" + sid);
			if (result.next())
				System.out.println("CID: "+result.getString(1) + " Cname: " + result.getString(2) + " Accid: " + result.getString(3) + " Accid: "
						+ result.getString(4) + " Accpin: " + result.getString(5) + " Accbalance " + result.getString(6)+ "\n");
			else
				System.out.println("not available");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectAccBal(int sid) { // Search Function
		try {
			ResultSet result = getSQLConnection().createStatement().executeQuery("select accbalance from customer c join account a on a.accid=c.accid where a.accid =" + sid);
			if (result.next())
				System.out.println("AccountBal: "+result.getInt(1) + "\n");
			else
				System.out.println("not available");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Deposit(int accid, int accpin, int dpamount) { // Deposit Function
		try {
			Connection con = getSQLConnection();
			PreparedStatement pstat;
			pstat = con
					.prepareStatement("update account set accbalance = accbalance + ? where accid = ? and accpin = ?");
			pstat.setInt(1, dpamount);
			pstat.setInt(2, accid);
			pstat.setInt(3, accpin);
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		System.out.println("Depo Done\n");
	}
	public void Withdraw(int accid, int accpin, int wdamount) { // Withdraw Function
		try {
			PreparedStatement pstat;
			String query = "update account set accbalance = accbalance - ? where accid = ? and accpin = ?";
			pstat = getSQLConnection().prepareStatement(query);
			pstat.setInt(1, wdamount);
			pstat.setInt(2, accid);
			pstat.setInt(3, accpin);
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Withdraw Done\n");
	}
}