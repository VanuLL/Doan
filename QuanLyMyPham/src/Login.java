
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Login {
	private String replace;
	private String user, pass, permission, numberID, adr, sdt;
	private Scanner sc = new Scanner(System.in);
	public Login() {
		// TODO Auto-generated constructor stub
	}
	

	public Login(String user, String pass, String permission, String numberID, String adr, String sdt) {
		super();
		this.user = user;
		this.pass = pass;
		this.permission = permission;
		this.numberID = numberID;
		this.adr = adr;
		this.sdt = sdt;
	}


	public String getAdr() {
		return adr;
	}


	public void setAdr(String adr) {
		this.adr = adr;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getNumberID() {
		return numberID;
	}

	public void setNumberID(String numberID) {
		this.numberID = numberID;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getReplace() {
		return replace;
	}
	public void setReplace(String replace) {
		this.replace = replace;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		docCheckID();
		String s[] = replace.split(";");
		while (true) {
			int flagID = 0;
			for (int i = 0; i < s.length; i++) {
				if (user.compareTo(s[i]) == 0) {
					flagID = 1;
				}
			}
			if (user.length() < 12 && user.length() > 0 && flagID == 0) {
				break;
			} else {
				System.out.printf("Mời nhập lại: ");
				user = sc.nextLine();
			}
		}
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPermission() {
		return permission;
	}
	public void setpermission(String permission) {
		while (true) {
			if (permission.compareTo("manager") == 0 || permission.compareTo("staff") == 0 || permission.compareTo("customer") == 0) {
				break;
			} else {
				System.out.println("Chỉ nhập các chữ <manager>, <staff> or <customer>!");
				permission = sc.nextLine();
			}
		}
		this.permission = permission;
	}
	public void inLogin() {
		System.out.print("Tài khoản: ");
		setUser(sc.nextLine());
		System.out.print("Mật khẩu: ");
		setPass(sc.nextLine());
		System.out.print("Quyền truy cập: ");
		setpermission(sc.nextLine());
		System.out.printf("ID nhận dạng: ");
		setNumberID(sc.nextLine());
		System.out.printf("Địa chỉ: ");
		setAdr(sc.nextLine());
		System.out.printf("Số điện thoại: ");
		setSdt(sc.nextLine());
	}
	public String toString() {
		return user + ";" + pass + ";" + permission + ";" + numberID +";" + adr + ";" + sdt + ";";
	}
	public void outLogin() {
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", user, pass, permission, numberID, adr, sdt);
	}
	public void docCheckID() {
		// TODO Auto-generated method stub
		try {
			setReplace("");
			FileReader fr=new FileReader("account.txt");
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
	public void jsonKey() {
		// TODO Auto-generated method stub
		try {
			setReplace("");
			FileReader fr=new FileReader("account.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st;
	        while (true) {
	        	st = br.readLine();
	        	if(st==null) break;
	        	else {
	        		String[]s = st.split(";");
	        		setReplace(replace += s[0] + ";" +s[1] + ";" + s[2] + ";" + s[3] + ";");
	        	}
	        }
	        fr.close();
	        br.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
}
