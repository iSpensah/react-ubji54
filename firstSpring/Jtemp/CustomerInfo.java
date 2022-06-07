package Jtemp;

public class CustomerInfo {

	private int cid;
	private String cname;
	private String ccity;
	private int caccbalance;
	
	public void displayCustomerInfo() {
		System.out.println(cid +" "+ cname +" "+ ccity +" "+ caccbalance);
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCcity() {
		return ccity;
	}
	public void setCcity(String ccity) {
		this.ccity = ccity;
	}
	public int getCaccbalance() {
		return caccbalance;
	}
	public void setCaccbalance(int caccbalance) {
		this.caccbalance = caccbalance;
	}
		
}
