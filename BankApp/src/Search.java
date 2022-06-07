
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

class NegativeNotAllowed extends Exception {
	void print() {
		System.out.println("Negative Not Allowed!");
	}
}

public class Search {
	
	static int accpin,accid,amnt;
	
	public static void main (String[] args) throws SQLException, NegativeNotAllowed {
	
		try {
		Scanner in = new Scanner(System.in);
		dbFunctions db = new dbFunctions();
		String choice = null;
		
		while(choice !="e") {
		System.out.println("Enter type of Transaction:/ s for search/ d for DEPO / w for WITHDRAW");
		choice = in.next();
		
		switch(choice) {

		case "s":
		//search
		System.out.println("Enter Id to Search:");
		int id = in.nextInt();
		db.searchCustomer(id);
		break;
		
		case "d":
		//perform deposit transaction
		System.out.println("Enter Accountid , accpin, Deposit Amount");
		 accid = in.nextInt();  //example of static variable
		 accpin = in.nextInt(); //example of static variable
		if(db.validate(accid, accpin)==true) {
			System.out.println("Enter Deposit Amount");
			amnt = in.nextInt();
			if(amnt < 0) { throw new NegativeNotAllowed(); }
			else db.Deposit(accid, accpin, amnt);
		}
		else {
			System.out.println("Account pin Does not Match");
		}
		break;
		
		case "w":
		//perform withdraw transaction
		System.out.println("Enter Accountid , accpin, Withdraw Amount");
		accid = in.nextInt();  //example of static variable
		accpin = in.nextInt();  //example of static variable
		if(db.validate(accid, accpin)==true) {
			System.out.println("Enter Withdraw Amount");
		amnt = in.nextInt();
		if(amnt < 0) { throw new NegativeNotAllowed();}
		else db.Withdraw(accid, accpin, amnt);
		}else {
			System.out.println("Account pin Does not Match");
		}
		break;
		}
	  }
		} catch(InputMismatchException e) {
			main(args);
		} catch(ArithmeticException e) {
			main(args);
		}catch (NegativeNotAllowed e) {
			e.print();
			main(args);
		}
		catch (Exception e) {
			main(args);
		}
	}
}