import java.sql.*;

public class dbFunctions {
	// ResultSet result = getSQLConnection().createStatement().executeQuery("Select
	// * from StudentData");
	private static Connection getSQLConnection() { // getSQL Connection
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
	
	public static boolean validate(int accid, int accpin) throws SQLException {
		boolean answer = false;
		String query = "Select accid from account where accid = " + accid + " and accpin = " + accpin;
		ResultSet result = getSQLConnection().createStatement().executeQuery(query);
		if (result.next()) {
			return answer = true;
		} else {
			return answer = false;
		}
	}
	
	public static void recordTransaction(int accid, int amnt,String tranctype ) { //Record Transaction function
		//insert into Transaction values(trancid_seq.nextval,67700,'Loan',2);
	try {       PreparedStatement pstat;
				String query = "insert into Transaction values(trancid_seq.nextval,?,?,?)";
				pstat = getSQLConnection().prepareStatement(query);
				//pstat.setString(1, "trancid_seq.nextval"); 
				pstat.setInt(1, amnt); pstat.setString(2, tranctype); pstat.setInt(3,accid);
				pstat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error"); }
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
			recordTransaction(accid, dpamount,"deposit");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		System.out.println("Depo Done\n");
	}
	public void Withdraw(int accid, int accpin, int wdamount) { // Withdraw Function
		try {
			String query = "Select accbalance from account where accid = "+accid+ "and accpin = "+accpin;
			ResultSet result = getSQLConnection().createStatement().executeQuery(query);
			if(result.next());
			if( wdamount <= result.getInt(1)) {
			PreparedStatement pstat;
			String query2 = "update account set accbalance = accbalance - ? where accid = ? and accpin = ?";
			pstat = getSQLConnection().prepareStatement(query2);
			pstat.setInt(1, wdamount);
			pstat.setInt(2, accid);
			pstat.setInt(3, accpin);
			pstat.executeUpdate(); 
			recordTransaction(accid, wdamount,"withdraw");
			}
			 else {
				System.out.println("Insufficient Funds!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Withdraw Done\n");
	}
}