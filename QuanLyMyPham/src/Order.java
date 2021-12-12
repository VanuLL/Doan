import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Order implements Ghi {
	private String customerID;
	private String adr, sdt;
	private Scanner sc = new Scanner(System.in);
	private int n, x, n1;
	private Product ds[] = new Product[n];
	private Product dsWH[] = new Product[n1];
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public int getN1() {
		return n1;
	}
	public void setN1(int n1) {
		this.n1 = n1;
	}
	public Product[] getDsWH() {
		return dsWH;
	}
	public void setDsWH(Product[] dsWH) {
		this.dsWH = dsWH;
	}

	public Order(int n) {
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
	public Product[] getDs() {
		return ds;
	}
	public void setDs(Product[] ds) {
		this.ds = ds;
	}
	public void add() {
		System.out.print("Nhập vị trí x: ");
		setX(Integer.parseInt(sc.nextLine()));
		System.out.println("Mời nhập thông tin cần thêm");
		Product temp = new Product();
		int flag;
		temp.inProduct();
		while (true) {
			flag=0;
			for (int i = 0; i < n; i++) {
				if(temp.getProductID().compareTo(ds[i].getProductID()) == 0) {
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			} else {
				System.out.println("Mời nhập lại: ");
				temp.inProduct();
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
		System.out.print("Nhập mã sản phẩm: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) { 
			if (ds[i].getProductID().compareTo(s)==0) {
				ds[i].inProduct(); flagf = 1;
				break;
			}
		}
		if (flagf == 0) {
			System.out.println("Mã không tồn tại hoặc danh sách rỗng mời chọn chức năng khác!");
		}
	}
	public void delete() {
		String s; int count = 0; int flagd = 0;
		System.out.print("Nhập mã sản phẩm: ");
		s = sc.nextLine();
		for (int i = 0; i < n; i++) { 
			if (ds[i].getProductID().compareTo(s)==0) {
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
	System.out.print("Nhập mã sản phẩm: ");
	s = sc.nextLine();
	System.out.println("Thông tin...........");

	for (int i = 0; i < n; i++) {
		if (ds[i].getProductID().compareTo(s)==0) {
			for (int j = 0; j < 6*15+12; j++) {
				System.out.print("=");
			}
			System.out.println("");
			System.out.printf("%-10s %-10s %-15s %-10s %20s %15s %15s\n", "ID", "Tên", "Giá/SP (USD)", "Thương hiệu", "Mô tả", "Số lượng mua", "Thành tiền");
			for (int j = 0; j < 6*15+12; j++) {
				System.out.print("=");
			}
			System.out.println("");
			ds[i].outProduct();
		}
	}
	for (int j = 0; j < 6*15+12; j++) {
		System.out.print("=");
	}
	System.out.println("");
}
	public void inList() {
		System.out.print("Nhập số lượng: ");
		setN(Integer.parseInt(sc.nextLine()));
		setDs(ds = Arrays.copyOf(ds, n));
		for (int i = 0; i < n; i++) {
			ds[i] = new Product();
			ds[i].inProduct();
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					if (ds[i].getProductID().compareTo(ds[j].getProductID()) == 0) {
						System.out.print("Bị trùng, nhập lại ID: ");
						ds[i].setProductID(sc.nextLine());
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
			for (int i = 0; i < 6*15+12; i++) {
				System.out.print("=");
			}
			System.out.println("");
			System.out.printf("%-10s %-10s %-15s %-10s %20s %15s %15s\n", "ID", "Tên", "Giá/SP (USD)", "Thương hiệu", "Mô tả", "Số lượng mua", "Thành tiền");
			for (int i = 0; i < 6*15+12; i++) {
				System.out.print("=");
			}
			System.out.println("");
			for (int i = 0; i < n; i++) {
				ds[i].outProduct();
			}
			for (int i = 0; i < 6*15+12; i++) {
				System.out.print("=");
			}
			System.out.println("");
			System.out.println("Tổng tiền: " + tongTien());
			for (int i = 0; i < 6*15+12; i++) {
				System.out.print("=");
			}
			System.out.println("");
			System.out.printf("%-30s%s%-15s\n", "ID khách hàng",":", getCustomerID());
			System.out.printf("%-30s%s%-15s\n", "Địa chỉ khách hàng",":", getAdr());
			System.out.printf("%-30s%s%-15s\n", "Số điện thoại liên lạc",":", getSdt());
			for (int i = 0; i < 6*15+12; i++) {
				System.out.print("=");
			}
			System.out.println("");
		}
	}
	public Float tongTien() {
		float mo = 0;
		for (int i = 0; i < n; i++) {
			mo += ds[i].getPrice();
		}
		return mo;
	}
	@Override
	public void ghi() {
		String s = getCustomerID() + ";" + "customer";
		try {
			FileWriter fw = new FileWriter("ghiHD.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < ds.length; i++) {
				bw.write(ds[i].toString());
				bw.write(s);
				bw.newLine();
			}
            bw.close();
            fw.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error!");
		}
	}
	public void docWareHouse(){
		int v = 0;
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("product.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st; int count = 0;
	        while(true) {
	        	st = br.readLine();
	        	if (st == null) {
	        		break;
	        	} else {
	        		v = 1; count++;
	        		String s[] = st.split(";");
	        		setDs(dsWH=Arrays.copyOf(dsWH, count));
	        		float temp0 = Float.parseFloat(s[2]);
	        		int temp1 = Integer.parseInt(s[5]);
	        		dsWH[count-1] = new Product(s[0], s[1], temp0, s[3], s[4], temp1);
	        		setN1(count);
	        	}
	        }
	        if (v == 1) {
	        	System.err.println("Đọc thành công!");
	        } else {
	        	System.err.println("Đọc thất bại!");
	        }
	        br.close();
	        fr.close();
		} catch (Exception e) {
			System.err.println("Error!");
		}
	}
	public void ghiWareHouseList() {
		for (int i = 0; i < n; i++) {
			ds[i].buyProduct();
		}
	}
	
	//Doc thong tin tu ghi hoa don de tinh tong tien, tu tong tien tinh tong thu nhap
	public void tkTongThuNhap() {
		float sum = 0;
		try {
			FileReader fr=new FileReader("C:\\Users\\84982\\Documents\\w_files\\ghiHD.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st = "";
	        while (true) {
	        	st = br.readLine();
	        	if (st == null) break;
	        	else {
	        		String s[] = st.split(";");
	        		sum += Float.parseFloat(s[2])*Float.parseFloat(s[5]);
	        	}
	        }
	        br.close();
	        fr.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error!");
		}
		System.out.println("Tổng thu nhập của cửa hàng là: " + sum);
	}
	public float tongThuNhap() {
		float sum = 0;
		try {
			FileReader fr=new FileReader("C:\\Users\\84982\\Documents\\w_files\\ghiHD.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st = "";
	        while (true) {
	        	st = br.readLine();
	        	if (st == null) break;
	        	else {
	        		String s[] = st.split(";");
	        		sum += Float.parseFloat(s[2])*Float.parseFloat(s[5]);
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

}
