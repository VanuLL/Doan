import java.util.Scanner;

public class Staff extends People {
	private Scanner sc = new Scanner(System.in);
	private String staffID, name, sdt;
	private float hourWork;
	public Staff() {
		// TODO Auto-generated constructor stub
	}
	public Staff(String staffID, String name, String sdt, float hourWork) {
		super();
		this.staffID = staffID;
		this.name = name;
		this.sdt = sdt;
		this.hourWork = hourWork;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		while (true) {
			if (staffID.length() < 12 && staffID.length() > 0) {
				break;
			} else {
				System.out.print("Bị trùng dữ liệu hoặc nhập sai giá trị mời nhập lại: ");
				staffID = sc.nextLine();
			}
		}
		this.staffID = staffID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public float gethourWork() {
		return hourWork;
	}
	public void sethourWork(float hourWork) {
		this.hourWork = hourWork;
	}
	public void inStaff() {
		System.out.print("Nhập ID nhân viên: ");
		setStaffID(sc.nextLine());
		System.out.print("Nhập tên: ");
		setName(sc.nextLine());
		System.out.print("Nhập số điện thoại: ");
		setSdt(sc.nextLine());
		System.out.print("Nhập giờ làm việc: ");
		sethourWork(Float.parseFloat(sc.nextLine()));
	}
	@Override
	public String toString() {
		String a = ";";
		return staffID + a + name + a + sdt + a + hourWork + a;
	}
	public void outStaff() {
		System.out.printf("%-15s %-15s %-15s %15.2f\n", staffID, name, sdt, hourWork);
	}
	@Override
	public float salary() {
		// TODO Auto-generated method stub
		return hourWork*3;
	}
}
