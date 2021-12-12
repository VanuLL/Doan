import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class EuropeSupList {
	private Scanner sc = new Scanner(System.in);
	private int n, x;
	private String f;
	private EuropeSup[] ds = new EuropeSup[n];
	public EuropeSupList() {
		super();
	}
	public EuropeSupList(int n) {
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
	
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public EuropeSup[] getDs() {
		return ds;
	}
	public void setDs(EuropeSup[] ds) {
		this.ds = ds;
	}
	public void add() {
		System.out.println("Nhập vị trí x: ");
		setX(Integer.parseInt(sc.nextLine()));
		System.out.println("Mời nhập thông tin cần thêm");
		EuropeSup temp = new EuropeSup();
		temp.inSupplier();
		while (true) {
			int flag = 0;
			for (int i = 0; i < ds.length; i++) {
				if (temp.getId().compareTo(ds[i].getId())==0) {
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			} else {
				System.out.println("Dữ liệu thêm trùng. Mời nhập lại!");
				temp.inSupplier();
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
		String s; int flagf = 0;
		System.out.print("Nhập mã khách hàng: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) { 
			if (ds[i].getId().compareTo(s)==0) {
				ds[i].inSupplier(); flagf = 1;
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
			if (ds[i].getId().compareTo(s)==0) {
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
			if (ds[i].getId().compareTo(s)==0) {
				System.out.println("Thông tin...........");
				System.out.printf("%-15s %-15s %-15s %-15s\n", "ID","Tên công ty","Nước","Giá trị nhập USD");
				ds[i].outSupplier();
			}
		}
	}
	public void inList() {
		System.out.print("Nhập số lượng: ");
		setN(Integer.parseInt(sc.nextLine()));
		setDs(ds = Arrays.copyOf(ds, n));
		for (int i = 0; i < n; i++) {
			ds[i] = new EuropeSup();
			ds[i].inSupplier();
			for (int j = 0; j < i; j++) {
				if (ds[i].getId().compareTo(ds[j].getId()) == 0) {
					System.out.print("Bị trùng, nhập lại ID: ");
					ds[i].setId(sc.nextLine());
					j = -1;
				}
			}
		}
	}
	public void outList() {
		if (ds.length == 0) {
			System.out.println("Danh sách rỗng");
		} else {
			System.out.printf("%-15s %-15s %-15s %15s\n", "ID","Tên công ty","Nước","Giá trị nhập USD");
			for (int i = 0; i < 5*15; i++) {
				System.out.print("=");
			}
			System.out.println();
			for (int i = 0; i < n; i++) {
				ds[i].outSupplier();
			}
		}
	}
	public void taxDisplay() {
		System.out.print("Mời nhập ID công ty cần xem thuế và chi phí dự kiến: ");
		setF(sc.nextLine());
		for (int i = 0; i < n; i++) {
			if (ds[i].getId().compareTo(getF())==0) {
				ds[i].tax();
				ds[i].taxShow();
			}
		}
	}
	public void doc(){
		int v = 0;
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("EuropeSup.txt");
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
	        		ds[count-1] = new EuropeSup(s[0], s[1], s[2], s[3]);
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
				FileWriter fw = new FileWriter("EuropeSup.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				if(ds.length > 0) {
					for (int i = 0; i < ds.length; i++) {
						bw.write(ds[i].toString());
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
			FileReader fr=new FileReader("EuropeSup.txt");
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
		return sum;
	}
	public static void main(String[] args) {
		EuropeSupList o = new EuropeSupList();
		o.tkTongChiPhi();
	}
}
