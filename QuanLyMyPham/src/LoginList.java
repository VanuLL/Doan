import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LoginList implements Doc, Ghi {
	private String replace;
	private Scanner sc = new Scanner(System.in);
	private int n;
	private Login ds[] = new Login[n];
	public LoginList() {
		// TODO Auto-generated constructor stub
	}
	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public Login[] getDs() {
		return ds;
	}
	public void setDs(Login[] ds) {
		this.ds = ds;
	}
	public void addLast() {
		System.out.println("Mời nhập thông tin cần thêm");
		Login temp = new Login();
		temp.inLogin();
		//kiem tra xem dữ liệu nhập vào có trùng với dữ liệu trong kho không
		while (true) {
			int flag = 0; // cờ hiệu
			for (int i = 0; i < ds.length; i++) {
				if (temp.getUser().compareTo(ds[i].getNumberID())==0) {
					//không được phép trùng tài khoản
					flag = 1; // cứ trùng là bị cắm cờ
				}
			}
			if (flag == 0) {
				break; // không trùng thì thoát không cần nhập nữa
			} else {
				System.out.println("Dữ liệu thêm trùng. Mời nhập lại!");
				temp.inLogin();
			}
		}
		setDs(ds=Arrays.copyOf(ds, ds.length+1)); //tăng độ dài mảng ds
		ds[ds.length-1] = new Login(); // tạo đối tượng mới cho danh sách
		ds[ds.length-1] = temp; // gán nó bằng dữ liệu nhập vào
		setN(ds.length); // tăng biến di động tượng trưng độ dài của ds
	}
	public void inList() {
		System.out.print("Nhập số lượng: ");
		setN(Integer.parseInt(sc.nextLine()));
		setDs(ds = Arrays.copyOf(ds, n));
		for (int i = 0; i < n; i++) {
			ds[i] = new Login();
			ds[i].docCheckID();
			ds[i].inLogin();
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					if (ds[i].getUser().compareTo(ds[j].getUser()) == 0) {
						System.out.print("Bị trùng, nhập lại ID: ");
						ds[i].setUser(sc.nextLine());;
						j = -1;
					}
				}
			}
		}
	}
	public void outList() {
		for (int i = 0; i < n; i++) {
			ds[i].outLogin();
		}
	}
	@Override
	public void doc(){
		setReplace("");
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("login.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st,t = "";
	        while(true) {
	        	st = br.readLine();
	        	if (st == null) {
	        		break;
	        	} else {
	        		String s[] = st.split(";");
	        		setDs(ds=Arrays.copyOf(ds, ds.length+1));
	        		ds[ds.length-1] = new Login(s[0], s[1], s[2], s[3], s[4], s[5]);
	        		setN(ds.length);
	        		t += s[0] + ";" + s[1] + ";" + s[2] + ";" + s[3] + ";" + s[4] + ";" + s[5] + ";";
	        		setReplace(t);
	        	}
	        }
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}

	@Override
	public void ghi() {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter("login.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < ds.length; i++) {
				bw.write(ds[i].toString());
				bw.newLine();
			}
			fw.close();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!");
		}
	}
	
	// Hàm đọc này lấy tất cả thông tin trong file login vào thằng ds
	public void getInfo(){
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("login.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st;
	        while(true) {
	        	st = br.readLine();
	        	if (st == null) {
	        		break;
	        	} else {
	        		String s[] = st.split(";");
	        		setDs(ds=Arrays.copyOf(ds, ds.length+1));
	        		ds[ds.length-1] = new Login(s[0], s[1], s[2], s[3], s[4], s[5]);
	        		setN(ds.length);
	        	}
	        }
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	// Hàm ghi lại thông tin vào file login từ ds, true để ghi thêm dữ liệu vào file
	public void giveInfo() {
		try {
			FileWriter fw = new FileWriter("login.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			if(ds.length > 0) {
				bw.write(ds[ds.length-1].toString());
				bw.newLine();
				System.err.println("Thành công!");
			} else {
				System.err.println("Tạo thất bại....!");
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.err.println("Error!");
		}
	}
	public void createAc() {
		// TODO Auto-generated method stub
		getInfo();
		//hàm thêm phải đảm bảo khác với những phần tử trước đó
		// thêm vào cuối
		addLast();
		// ghi nhận tài khoản cuối cùng vào file login
		giveInfo();
	}
}
