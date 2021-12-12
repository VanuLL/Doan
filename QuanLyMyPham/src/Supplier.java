import java.util.Scanner;

public abstract class Supplier {
	private Scanner sc = new Scanner(System.in);
	private String id, nameCity, country, value;
	public Supplier() {
		// TODO Auto-generated constructor stub
	}


	public Supplier(String id, String nameCity, String country, String value) {
		super();
		this.id = id;
		this.nameCity = nameCity;
		this.country = country;
		this.value = value;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void inSupplier() {
		System.out.print("Enter ID: ");
		setId(sc.nextLine());
		System.out.print("Enter city name: ");
		setNameCity(sc.nextLine());
		System.out.print("Enter country: ");
		setCountry(sc.nextLine());
		System.out.print("Giá trị đơn hàng muốn nhập khẩu (Dự kiến): ");
		setValue(sc.nextLine());
	}
	@Override
	public String toString() {
		return id + ";" + nameCity + ";" + country + ";" + value + ";";
	}
	
	public void outSupplier() {
		System.out.printf("%-15s %-15s %-15s %15s\n", id, nameCity, country, value);
	}
	public abstract void tax();
	public abstract void taxShow();
	public void assistant() {
		System.out.println("Không ai cả!");
	}
}
