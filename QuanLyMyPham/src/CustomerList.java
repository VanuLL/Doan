import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerList {
	private Scanner sc = new Scanner(System.in);
	private int n, x;
	private Customer[] ds = new Customer[n];
	public CustomerList() {
		super();
	}
	public CustomerList(int n) {
		super();
		this.n = n;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		while (true) {
			if (x >= 0 && x <= ds.length) break;
			else {
				System.out.printf("Nhập lại, giá trị x không lớn hơn %s và x không âm: ", ds.length);
				x = Integer.parseInt(sc.nextLine());
			}
		}
		this.x = x;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public Customer[] getDs() {
		return ds;
	}
	public void setDs(Customer[] ds) {
		this.ds = ds;
	}
	public void add() {
		System.out.println("Nhập vị trí x: ");
		setX(Integer.parseInt(sc.nextLine()));
		System.out.println("Mời nhập thông tin cần thêm");
		Customer temp = new Customer();
		temp.inCustomer();
		while (true) {
			int flag = 0;
			for (int i = 0; i < ds.length; i++) {
				if (temp.getCustomerID().compareTo(ds[i].getCustomerID())==0) {
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			} else {
				System.out.println("Dữ liệu thêm trùng. Mời nhập lại!");
				temp.inCustomer();
			}
		}
		setDs(ds=Arrays.copyOf(ds, ds.length+1));
		for (int i = ds.length-1; i > x; i--) {
			ds[i] = ds[i-1];
		}
		ds[x] = temp;
		setN(ds.length);
	}
	public void fix() {
		String s; int flagf = 0, flag2 = 0;
		System.out.print("Nhập mã khách hàng: ");
		s = sc.nextLine();
		System.out.println("Mời nhập dữ liệu cần sửa!");
		Customer temp = new Customer();
		temp.inCustomer();
		while (true) {
			flag2 = 0;
			for (int i = 0; i < n; i++) {
				if (temp.getCustomerID().compareTo(ds[i].getCustomerID())==0) {
					flag2 = 1;
					break;
				}
			}
			if(flag2 == 0)
				break;
			else {
				System.out.print("ID sửa trùng với ID khác trong danh sách, nhập lại ID: ");
				temp.setCustomerID(sc.nextLine());
			}
		}
		for (int i = 0; i < n; i++) { 
			if (ds[i].getCustomerID().compareTo(s)==0) {
				ds[i] = temp;
				flagf = 1;
				break;
			}
		}
		if (flagf == 0) {
			System.out.println("Mã không tồn tại hoặc danh sách rỗng mời chọn chức năng khác!");
		}
	}
	public void delete() {
		String s; int count = 0; int flagd = 0;
		System.out.print("Nhập mã khách hàng: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) { 
			if (ds[i].getCustomerID().compareTo(s)==0) {
				count = i; flagd = 1;
				break;
			}
		}
		if (flagd == 1) {
			for (int i = count; i < n - 1; i++) {
				ds[i] = ds[i+1];
			}
			setDs(ds=Arrays.copyOf(ds, ds.length-1));
			setN(ds.length);
		} else
			System.out.println("Không tìm thấy mã! Mời chọn chức năng khác!");
	}
	public void find() {
		String s;
		System.out.print("Nhập mã khách hàng: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) {
			if (ds[i].getCustomerID().compareTo(s)==0) {
				System.out.println("Thông tin...........");
				System.out.printf("%-10s %-15s %-25s %20s\n", "ID","Tên","Địa chỉ","Số điện thoại");
				ds[i].outCustomer();
			}
		}
	}
	public void inList() {
		System.out.print("Nhập số lượng: ");
		setN(Integer.parseInt(sc.nextLine()));
		setDs(ds = Arrays.copyOf(ds, n));
		for (int i = 0; i < n; i++) {
			ds[i] = new Customer();
			ds[i].docCheckID();
			ds[i].inCustomer();
			for (int j = 0; j < i; j++) {
				if (ds[i].getCustomerID().compareTo(ds[j].getCustomerID()) == 0) {
					System.out.print("Bị trùng, nhập lại ID: ");
					ds[i].setCustomerID(sc.nextLine());
					j = -1;
				}
			}
		}
	}
	public void outList() {
		if (ds.length == 0) {
			System.out.println("Danh sách rỗng");
		} else {
			System.out.printf("%-10s %-15s %-25s %20s\n", "ID","Tên","Địa chỉ","Số điện thoại");
			for (int i = 0; i < 5*15+3; i++) {
				System.out.print("=");
			}
			System.out.println();
			for (int i = 0; i < n; i++) {
				ds[i].outCustomer();
			}
		}
	}
	public void doc(){
		int v = 0;
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("customer.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st; int count = 0;
	        while(true) {
	        	st = br.readLine();
	        	if (st == null) {
	        		break;
	        	} else {
	        		v = 1; count++;
	        		String s[] = st.split(";");
	        		setDs(ds=Arrays.copyOf(ds, count));
	        		ds[count-1] = new Customer(s[0], s[1], s[2], s[3]);
	        		setN(count);
	        	}
	        }
	        if (v == 1) {
	        	System.out.println("Đọc thành công!");
	        }
	        fr.close();
	        br.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	public void ghi() {
		// TODO Auto-generated method stub
			try {
				FileWriter fw = new FileWriter("customer.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				if(ds.length > 0) {
					for (Customer p: ds) {
		                bw.write(p.toString());
		                bw.newLine();
					}
					System.err.println("Ghi thành công!");
				} else {
					System.err.println("Ghi thất bại <Danh sách rỗng>....");
				}
	            bw.close();
	            fw.close();
			} catch (Exception e) {
				System.err.println("Error!");
			}
	}
}
