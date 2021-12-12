import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Product {
	private Scanner sc = new Scanner(System.in);
	private String productID, productID1, name;
	private float price, price1;
	private String trade, description;
	private int quantity;
	private int n1;
	private Product dsWH[] = new Product[n1];
	
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProductID1() {
		return productID1;
	}
	public void setProductID1(String productID1) {
		this.productID1 = productID1;
	}
	public float getPrice1() {
		return price1;
	}
	public void setPrice1(float price1) {
		this.price1 = price1;
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
	public Product(String productID, String name, float price, String trade, String description, int quantity) {
		super();
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.trade = trade;
		this.description = description;
		this.quantity = quantity;
	}
	public Product() {
		super();
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		docWareHouse();
		while(true) {
			int flag = 0;
			for (int i = 0; i < dsWH.length; i++) {
				if (productID.compareTo(dsWH[i].getProductID1())==0) {
					flag = 1;
					setName(dsWH[i].getName());
					setDescription(dsWH[i].getDescription());
					setPrice1(dsWH[i].getPrice());
					setTrade(dsWH[i].getTrade());
				}
			}
			if (flag == 1) {
				break;
			} else {
				System.out.print("ID sản phẩm không tồn tại, nhập lại: ");
				productID = sc.nextLine();
				
			}
		}
		this.productID = productID;
	}
	public void outLn() {
		for (int i = 0; i < dsWH.length; i++) {
			dsWH[i].outProduct();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		if(getQuantity() > 50 ) {
			price = price*getQuantity()-price*getQuantity()*6/100;
		} else if (getQuantity() > 30) {
			price = price*getQuantity()-price*getQuantity()*2/100;
		} else {
			price = price*getQuantity();
		}
		this.price = price;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void inProduct() {
		System.out.print("Enter product ID: ");
		setProductID(sc.nextLine());
		System.out.print("Enter quantity: ");
		setQuantity(Integer.parseInt(sc.nextLine()));
	}
	public void inWareHouse() {
		System.out.print("Enter product ID: ");
		setProductID1(sc.nextLine());
		setProductID(getProductID1());
		System.out.print("Enter name: ");
		setName(sc.nextLine());
		System.out.print("Enter price: ");
		setPrice1(Float.parseFloat(sc.nextLine()));
		setPrice(getPrice1());
		System.out.print("Enter trade: ");
		setTrade(sc.nextLine());
		System.out.print("Enter description: ");
		setDescription(sc.nextLine());
		System.out.print("Enter quantity: ");
		setQuantity(Integer.parseInt(sc.nextLine()));
	}
	public String toString() {
		return productID + ";" + name + ";" + price1 + ";" + trade + ";" + description + ";" + quantity +";";
	}
	public void outProduct() {
		setPrice(getPrice1());
		System.out.printf("%-10s %-10s %-15.2f %-10s %20s %15d %15.2f\n", productID, name, price1, trade, description, quantity, price);
	}
	public void outProduct2() {
		System.out.printf("%-10s %-10s %-15.2f %-10s %20s %20d\n", productID, name, price, trade, description, quantity);
	}
	public String toStringManager() {
		return productID1 + ";" + name + ";" + price1 + ";" + trade + ";" + description + ";" + quantity + ";";
	}
	public void outManager() {
		System.out.printf("%-10s %-10s %-15.2f %-10s %20s %20d\n", productID1, name, price1, trade, description, quantity);
	}
	public void change() {
		docWareHouse();
	//	System.out.println(getQuantity());
		for (int i = 0; i < dsWH.length; i++) {
			if (dsWH[i].getProductID().compareTo(getProductID())==0) {
				dsWH[i].setQuantity(dsWH[i].getQuantity()-getQuantity());
			}
		}
	}
	public void docWareHouse(){
		int v = 0;
		setDsWH(dsWH=Arrays.copyOf(dsWH, 0));
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("product.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String st;
	        while(true) {
	        	st = br.readLine();
	        	if (st == null) {
	        		break;
	        	} else {
	        		v = 1;
	        		String s[] = st.split(";");
	        		setDsWH(dsWH=Arrays.copyOf(dsWH, dsWH.length+1));
	        		float temp0 = Float.parseFloat(s[2]);
	        		int temp1 = Integer.parseInt(s[5]);
	        		dsWH[dsWH.length-1] = new Product(s[0], s[1], temp0, s[3], s[4], temp1);
	        		dsWH[dsWH.length-1].setProductID1(dsWH[dsWH.length-1].getProductID());
	        		dsWH[dsWH.length-1].setPrice1(dsWH[dsWH.length-1].getPrice());
	        		setN1(dsWH.length);
	        	}
	        }
	        if (v == 1) {
	        //	System.err.println("Đọc thành công!");
	        //	System.out.println(dsWH.length);
	        } else {
	        //	System.err.println("Đọc thất bại!");
	        }
	        br.close();
	        fr.close();
		} catch (Exception e) {
			System.err.println("Error!");
		}
	}
	public void buyProduct() {
		try {
			change();
			FileWriter fw = new FileWriter("product.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < dsWH.length;i++) {
				bw.write(dsWH[i].toStringManager());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.err.println("Error!");
		}
	}
	public void outListWH() {
		docWareHouse();
		if (dsWH.length == 0) {
			System.out.println("Danh sách rỗng");
		} else {
			for (int i = 0; i < 6*15+1; i++) {
				System.out.print("=");
			}
			System.out.println("");
			System.out.printf("%-10s %-10s %-15s %-10s %20s %20s\n", "ID", "Tên", "Giá/SP (USD)", "Thương hiệu", "Mô tả", "Số lượng trong kho");
			for (int i = 0; i < 6*15+1; i++) {
				System.out.print("=");
			}
			System.out.println("");
			for (int i = 0; i < dsWH.length; i++) {
				dsWH[i].outManager();
			}
			for (int i = 0; i < 6*15+1; i++) {
				System.out.print("=");
			}
			System.out.println("");
		}
	}
}
