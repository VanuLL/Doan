import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Customer implements Doc{
	private Scanner sc = new Scanner(System.in);
	private String replace;
	private String customerID, name, adr, phoneNumber;
	public Customer() {
		super();
	}
	public Customer(String customerID, String name, String adr, String phoneNumber) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.adr = adr;
		this.phoneNumber = phoneNumber;
	}
	public String getCustomerID() {
		return customerID;
	}
	public String getReplace() {
		return replace;
	}
	public void setReplace(String replace) {

		this.replace = replace;
	}
	public void setCustomerID(String customerID) {
		while (true) {
			if (customerID.length() <= 15 && customerID.length() > 0) { 
				break;
			} else {
				System.out.printf("Độ dài quá lớn hoặc quá nhỏ, nhập lại: ");
				customerID = sc.nextLine();
			}
		}
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		while (true) {
			if (name.length() <= 15 && name.length() > 0) { 
				break;
			} else {
				System.out.printf("Độ dài quá lớn hoặc quá nhỏ, nhập lại: ");
				name = sc.nextLine();
			}
		}
		this.name = name;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		while (true) {
			if (customerID.length() <= 15 && customerID.length() > 0) { 
				break;
			} else {
				System.out.printf("Độ dài quá lớn hoặc quá nhỏ, nhập lại: ");
				customerID = sc.nextLine();
			}
		}
		this.adr = adr;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		while (true) {
			if (phoneNumber.length() <= 15 && phoneNumber.length() > 0) { 
				break;
			} else {
				System.out.printf("Độ dài quá lớn hoặc quá nhỏ, nhập lại: ");
				phoneNumber = sc.nextLine();
			}
		}
		this.phoneNumber = phoneNumber;
	}

	public void inCustomer() {
		System.out.print("Nhập ID khách hàng: ");
		setCustomerID(sc.nextLine());
		System.out.print("Nhập tên: ");
		setName(sc.nextLine());
		System.out.print("Nhập địa chỉ: ");
		setAdr(sc.nextLine());
		System.out.print("Nhập số điện thoại: ");
		setPhoneNumber(sc.nextLine());
		//System.out.print("Nhập mật khẩu: ");
		//setPassword(sc.nextLine());
	}
	@Override
	public void doc() {
		// TODO Auto-generated method stub
		try {
			setReplace("");
			FileReader fr=new FileReader("customer.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st;
	        while (true) {
	        	st = br.readLine();
	        	if(st==null) break;
	        	else {
	        		String[]s = st.split(";");
	        		setReplace(replace += s[0] + ";");
	        	}
	        }
	        br.close();
	        fr.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	public void outCustomer() {
		System.out.printf("%-10s %-15s %-25s %20s\n", customerID, name, adr, phoneNumber);
	}
	public void docCheckID() {
		// TODO Auto-generated method stub
		try {
			setReplace("");
			FileReader fr=new FileReader("customer.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st;
	        while (true) {
	        	st = br.readLine();
	        	if(st==null) break;
	        	else {
	        		String[]s = st.split(";");
	        		setReplace(replace += s[0] + ";");
	        	}
	        }
	        fr.close();
	        br.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	public String toString() {
		return customerID + ";" + name + ";" + adr + ";"+ phoneNumber + ";";
	}
}
