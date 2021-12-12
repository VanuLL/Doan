import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class StaffList implements Doc, Ghi {
	private Scanner sc = new Scanner(System.in);
	private int n, x;
	private Staff ds[] = new Staff[n];
	public StaffList(int n, Staff[] ds) {
		super();
		this.n = n;
		this.ds = ds;
	}
	public StaffList() {
		// TODO Auto-generated constructor stub
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
	public Staff[] getDs() {
		return ds;
	}
	public void setDs(Staff[] ds) {
		this.ds = ds;
	}
	public void add() {
		System.out.println("Nhập vị trí x: ");
		setX(Integer.parseInt(sc.nextLine()));
		System.out.println("Mời nhập thông tin cần thêm");
		Staff temp = new Staff();
		temp.inStaff();
		while (true) {
			int flag = 0;
			for (int i = 0; i < ds.length; i++) {
				if (temp.getStaffID().compareTo(ds[i].getStaffID())==0) {
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			} else {
				System.out.println("Dữ liệu thêm trùng. Mời nhập lại!");
				temp.inStaff();
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
		System.out.print("Nhập mã nhân viên: ");
		s = sc.nextLine();
		System.out.println("Mời nhập dữ liệu cần sửa!");
		Staff temp = new Staff();
		temp.inStaff();
		while (true) {
			flag2 = 0;
			for (int i = 0; i < n; i++) {
				if (temp.getStaffID().compareTo(ds[i].getStaffID())==0) {
					flag2 = 1;
					break;
				}
			}
			if(flag2 == 0)
				break;
			else {
				System.out.print("ID sửa trùng với ID khác trong danh sách, nhập lại ID: ");
				temp.setStaffID(sc.nextLine());
			}
		}
		for (int i = 0; i < n; i++) { 
			if (ds[i].getStaffID().compareTo(s)==0) {
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
		System.out.print("Nhập mã nhân viên: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) { 
			if (ds[i].getStaffID().compareTo(s)==0) {
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
		System.out.print("Nhập mã nhân viên: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) {
			if (ds[i].getStaffID().compareTo(s)==0) {
				System.out.println("Thông tin...........");
				System.out.printf("%15s %15s %15s %15s\n", "ID","Tên","Số điện thoại","Giờ làm");
				ds[i].outStaff();
			}
		}
	}
	public void inList() {
		System.out.print("Nhập số lượng: ");
		setN(Integer.parseInt(sc.nextLine()));
		setDs(ds = Arrays.copyOf(ds, n));
		for (int i = 0; i < n; i++) {
			ds[i] = new Staff();
			ds[i].inStaff();
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					if (ds[i].getStaffID().compareTo(ds[j].getStaffID()) == 0) {
						System.out.print("Bị trùng, nhập lại ID: ");
						ds[i].setStaffID(sc.nextLine());;
						j = -1;
					}
				}
			}
		}
	}
	public void outList() {
		if (ds.length == 0) {
			System.out.println("Danh sách rỗng");
		} else {
			for (int i = 0; i < 5*15; i++) {
				System.out.print("=");
			}
			System.out.println();
			System.out.printf("%-15s %-15s %-15s %15s\n", "ID","Tên","Số điện thoại","Giờ làm (hour)");
			for (int i = 0; i < 5*15; i++) {
				System.out.print("=");
			}
			System.out.println();
			for (int i = 0; i < n; i++) {
				ds[i].outStaff();
			}
			for (int i = 0; i < 5*15; i++) {
				System.out.print("=");
			}
			System.out.println();
		}
	}
	@Override
	public void doc(){
		int v = 0;
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("staff.txt");
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
					Float temp = Float.parseFloat(s[3]);
					ds[count-1] = new Staff(s[0], s[1], s[2], temp);
					setN(count);
				}
			}
			if (v == 1) {
				System.err.println("Đọc thành công!");
			} else {
				System.err.println("Đọc thất bại!");
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.err.println("Error!");
		}
	}
	@Override
	public void ghi() {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter("staff.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			if(ds.length > 0) {
				for (Staff p: ds) {
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
	public float tkTongChiPhi() {
		float sum = 0;
		try {
			FileReader fr=new FileReader("staff.txt");
			BufferedReader br = new BufferedReader(fr);
			String st = "";
			while (true) {
				st = br.readLine();
				if (st == null) break;
				else {
					String s[] = st.split(";");
					sum += Float.parseFloat(s[3]);
				}
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error!");
		}
		return sum*30;
	}
}
