import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbFuncs {

	public static Connection getSQLConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "atos123$");
		return con;
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

	public static float getCoupDP(String cd) throws SQLException {// Search Function
		float answer = 0;
		ResultSet result = getSQLConnection().createStatement()
				.executeQuery("Select dispercent from coupon where coupdesc=" + "'" + cd + "'");
		if (result.next())
			;
		return answer = result.getFloat(1);
	}

	public static int buyProduct(String name) throws SQLException { //returns the coupon product discount percent
		int price = 0;
		ResultSet result = getSQLConnection().createStatement()
				.executeQuery("Select pprice from product where productname = " + "'" + name + "'");
		if (result.next()) {
			return price = result.getInt(1);
		} else {
			return price;
		}
	}

	public static void main(String[] args) throws SQLException {
		if (validate(1, 1) == false) {
			System.out.println(false);
		}
	}
}
