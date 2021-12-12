import java.util.Scanner;

public abstract class People {
		private Scanner sc = new Scanner(System.in);
		private String name, sdt;
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
		public abstract float salary();
		public void inStaff() {
			System.out.println("Nhập tên: ");
			setName(sc.nextLine());
			System.out.println("Nhập số điện thoại: ");
			setSdt(sc.nextLine());
		}
		public void outStaff() {
			System.out.printf("%15s %15s %15s %15.2f %15s", name, sdt);
		}
}
