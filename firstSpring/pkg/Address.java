package pkg;

public class Address 
{
	private String street;
	private String state;
	private String pincode;
	
	public void displayAddress()
	{
		System.out.println("Street :" + street);
		System.out.println("State :" + state);
		System.out.println("Pincode :" + pincode);
	}


public Address(String street,String state,String pincode)
{
this.street=street;
this.state=state;
this.pincode=pincode;
}

}