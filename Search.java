package Main;
import java.util.Scanner;
import jdbc.*;

public class Search {
	
	public static void main (String[] args) {
		
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
		int accid = in.nextInt();
		int accpin = in.nextInt();
		int amnt = in.nextInt();
		db.Deposit(accid, accpin, amnt);
		break;
		
		case "w":
		//perform withdraw transaction
		System.out.println("Enter Accountid , accpin, Withdraw Amount");
		int accid2 = in.nextInt();
		int accpin2 = in.nextInt();
		int wdamnt = in.nextInt();
		db.Withdraw(accid2, accpin2, wdamnt);
		break;
		}
	  }
	}
}