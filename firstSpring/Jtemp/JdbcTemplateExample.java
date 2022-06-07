package Jtemp;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateExample {
	
	public JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml"); //loads thespring container
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp"); //type cast to JdbcTemplate class
		return temp;
	}
	
	void fun() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml"); //loads thespring container
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp"); //type cast to JdbcTemplate class
		
		List<CustomerInfo> list = temp.query("Select * from CustomerInfo",new CustomerMapper());
		System.out.println("-----------ok");
		for(CustomerInfo customer: list) {
			customer.displayCustomerInfo();
		}
	}
	public void select() {
		JdbcTemplate temp = getTemplate();
		List<CustomerInfo> list = temp.query("Select * from CustomerInfo",new CustomerMapper());
		System.out.println("-----------ok");
		for(CustomerInfo customer: list) {
			customer.displayCustomerInfo();
	}
	}
	
	public void insert(int cid, String cname, String ccity, int cbalance) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml"); //loads thespring container
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp"); //type cast to JdbcTemplate class
		temp.update("insert into customerinfo values(?,?,?,?)", new Object[] {cid,cname,ccity,cbalance} );
		System.out.println("Row Inserted");
	}
	
	public void update (int cid, int cbalance) {
		JdbcTemplate temp = getTemplate();
		temp.update("update customerinfo set caccbalance = ? where cid = ? ", new Object[] {cid,cbalance});
	}
	public void searchCustomer(int cid) {
		try {
		JdbcTemplate temp = getTemplate();
		CustomerInfo customer = temp.queryForObject("Select * from customerInfo where cid = ?", new Object[] {cid},new CustomerMapper());
		customer.displayCustomerInfo(); }
		catch(EmptyResultDataAccessException e) {
		}
	}
	
	public void countTotalCusomters() {
		JdbcTemplate temp = getTemplate();
		Integer totalcustomers = temp.queryForObject("Select count(*) from CustomerInfo",Integer.class);
		System.out.println(totalcustomers);
	}
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		JdbcTemplateExample obj = new JdbcTemplateExample();
		//obj.fun();
		//obj.insert(sc.nextInt(),sc.next(),sc.next(),sc.nextInt());
		//obj.update(sc.nextInt(), sc.nextInt());
		obj.countTotalCusomters();
	}
}