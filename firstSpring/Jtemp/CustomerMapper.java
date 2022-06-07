package Jtemp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<CustomerInfo>{

	@Override
	public CustomerInfo mapRow(ResultSet result, int arg1) throws SQLException {
		CustomerInfo obj = new CustomerInfo();
		obj.setCid(result.getInt(1));
		obj.setCname(result.getString(2));
		obj.setCcity(result.getString(3));
		obj.setCaccbalance(result.getInt(4));
		return obj;
	}
}
