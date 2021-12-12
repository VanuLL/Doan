import java.util.Scanner;

public class QuanLyChucNang {
	private static Scanner sc = new Scanner(System.in);
	private static int solider;
	private static String user, pass;
	private static String keylevel0;
	private static String keylevel1;
	private static String keylevel2;
	private static AsianSupList asian = new AsianSupList(); 
	private static EuropeSupList europe = new EuropeSupList();
	private static WareHouse warehouse = new WareHouse();
	private static Order product1 = new Order();
	private static StaffList staff = new StaffList();
	private static CustomerList customer = new CustomerList();
	private static Sell product2 = new Sell();
	private static LoginList log = new LoginList();

	//Menu level 0: For all (Customer, Staff, Manager)
	public static void menuAll() {
		System.out.printf("+-----------------------------------------------+\n");
		System.out.printf("|\t%-40s|\n","");
		System.out.printf("|\t%-40s|\n","1. Customer");
		System.out.printf("|\t%-40s|\n","2. Staff");
		System.out.printf("|\t%-40s|\n","3. Manager");
		System.out.printf("|\t%-40s|\n","4. Thoát");
		System.out.printf("|\t%-40s|\n","");
		System.out.printf("+-----------------------------------------------+\n");
		System.out.print("Mời nhập chức năng: ");
		keylevel0 = sc.nextLine();
		switch(keylevel0) {
		case "1": 
			checkCustomer();
			if (solider != 1) {
				break;
			}
			do {
				keylevel1 = "1";
				customer();
			} while (keylevel1 != "9");
			break;
		case "2":
			checkStaff();
			if (solider != 2) {
				break;
			}
			do {
				keylevel1 = "1";
				staff();
			} while (keylevel1 != "7");
			break;
		case "3":
			checkManager();
			if (solider != 3) {
				break;
			}
			do {
				keylevel1 = "1";
				manager();
			} while (keylevel1 != "7");
			break;
		case "4":
			System.err.print("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		}
	}

	//Customer
	//Check customer
	public static void checkCustomer() {
		log.doc();
		System.out.print("Nhập tài khoản: ");
		user = sc.nextLine();
		System.out.print("Nhập mật khẩu: ");
		pass = sc.nextLine();
		int i = 0; String temp = "customer";
		String s1[] = log.getReplace().split(";");
		for (i = 0; i < s1.length; i++) {
			if(i%6==0) {
				if (user.compareTo(s1[i])==0 && pass.compareTo(s1[i+1])==0 && temp.compareTo(s1[i+2])==0) {
					solider = 1;
					product1.setCustomerID(s1[i+3]);
					product1.setAdr(s1[i+4]);
					product1.setSdt(s1[i+5]);
					break;
				}
			}
		}
		if (solider == 1) {
			System.out.printf("Đăng nhập thành công với tư cách %s!\n", "Khách hàng");
		} else {
			System.out.println("Đăng nhập thất bại! Trở về menu chính!");
		}
	}
	//Menu level 1: For customer
	public static void customer() {
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("|\t%-50s|\n","1. Thêm n sản phẩm");
		System.out.printf("|\t%-50s|\n","2. Xem danh sách giỏ");
		System.out.printf("|\t%-50s|\n","3. Thêm mới một sản phẩm trong giỏ");
		System.out.printf("|\t%-50s|\n","4. Sửa sản phẩm trong giỏ theo mã");
		System.out.printf("|\t%-50s|\n","5. Xóa sản phẩm trong giỏ theo mã");
		System.out.printf("|\t%-50s|\n","6. Tìm kiếm sản phẩm trong giỏ theo mã");
		System.out.printf("|\t%-50s|\n","7. Mua hàng (Lưu hóa đơn)");
		System.out.printf("|\t%-50s|\n","8. Danh sách mỹ phẩm trong cửa hàng");
		System.out.printf("|\t%-50s|\n","9. Trở về");
		System.out.printf("|\t%-50s|\n","10. Exit");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.print("Nhập chức năng: ");
		keylevel1 = sc.nextLine();
		switch(keylevel1) {
		case "1":
			product1.inList();
			break;
		case "2":
			product1.outList();
			break;
		case "3":
			product1.add();
			break;
		case "4":
			product1.fix();
			break;
		case "5":
			product1.delete();;
			break;
		case "6":
			product1.find();
			break;
		case "7":
			product1.ghi();
			product1.ghiWareHouseList();
			break;
		case "8":
			Product out = new Product();
			out.outListWH();
			break;
		case "9":
			keylevel1 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}

	//Staff
	//Check staff
	public static void checkStaff() {
		log.doc();
		System.out.print("Nhập tài khoản: ");
		user = sc.nextLine();
		System.out.print("Nhập mật khẩu: ");
		pass = sc.nextLine();
		int i = 0; String temp = "staff";
		String s1[] = log.getReplace().split(";");
		for (i = 0; i < s1.length; i++) {
			if(i%6==0) {
				if (user.compareTo(s1[i])==0 && pass.compareTo(s1[i+1])==0 && temp.compareTo(s1[i+2])==0) {
					solider = 2;
					product2.setStaffID(s1[i+3]);
					break;
				}
			}
		}
		if (solider == 2) {
			System.out.printf("Đăng nhập thành công với tư cách %s!\n", "Nhân viên");
		} else {
			System.out.println("Đăng nhập thất bại! Trở về menu chính!");
		}
	}
	//Menu level 1: For staff
	public static void staff() {
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("|\t%-50s|\n","1. Nhập hóa đơn");
		System.out.printf("|\t%-50s|\n","2. Xem hóa đơn hiện tại");
		System.out.printf("|\t%-50s|\n","3. Thêm sản phẩm trong hóa đơn theo mã");
		System.out.printf("|\t%-50s|\n","4. Sửa sản phẩm trong hóa đơn theo mã");
		System.out.printf("|\t%-50s|\n","5. Xóa sản phẩm trong hóa đơn theo mã");
		System.out.printf("|\t%-50s|\n","6. Xuất hóa đơn (Lưu hóa đơn vào file)");
		System.out.printf("|\t%-50s|\n","7. Trở về");
		System.out.printf("|\t%-50s|\n","8. Exit");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.print("Nhập chức năng: ");
		keylevel1 = sc.nextLine();
		switch(keylevel1) {
		case "1":
			product2.inList();
			break;
		case "2":
			product2.outList();
			break;
		case "3":
			product2.add();
			break;
		case "4":
			product2.fix();
			break;
		case "5":
			product2.delete();
			break;
		case "6":
			product2.ghi();
			product2.ghiWareHouseList();
			break;
		case "7":
			keylevel1 = "7";
			break;
		case "8":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.err.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}

	//Manager
	//Check manager
	public static void checkManager() {
		log.doc(); // lấy dữ liệu từ file vào mảng của log
		System.out.print("Nhập tài khoản: ");
		user = sc.nextLine();
		System.out.print("Nhập mật khẩu: ");
		pass = sc.nextLine();
		int i = 0; String temp = "manager";
		String s1[] = log.getReplace().split(";");
		for (i = 0; i < s1.length; i++) {
			if(i%6==0) {
				if (user.compareTo(s1[i])==0 && pass.compareTo(s1[i+1])==0 && temp.compareTo(s1[i+2])==0) {
					solider = 3;
					break;
				}
			}
		}
		if (solider == 3) {
			System.out.printf("Đăng nhập thành công với tư cách %s!\n", "Quản lý");
		} else {
			System.out.println("Đăng nhập thất bại! Trở về menu chính!");
		}
	}
	//Menu level 1: For manager
	public static void manager() {
		System.out.printf("+-----------------------------------------------+\n");
		System.out.printf("|\t%-40s|\n","");
		System.out.printf("|\t%-40s|\n","1. QL kho hàng");
		System.out.printf("|\t%-40s|\n","2. QL nhân viên");
		System.out.printf("|\t%-40s|\n","3. QL khách hàng");
		System.out.printf("|\t%-40s|\n","4. QL tiền đầu tư tại Asian");
		System.out.printf("|\t%-40s|\n","5. QL tiền đầu tư tại Europe");
		System.out.printf("|\t%-40s|\n","6. Thống kê");
		System.out.printf("|\t%-40s|\n","7. Trở về");
		System.out.printf("|\t%-40s|\n","8. Exit");
		System.out.printf("|\t%-40s|\n","");
		System.out.printf("+-----------------------------------------------+\n");
		System.out.print("Nhập chức năng: ");
		keylevel1 = sc.nextLine();
		switch(keylevel1) {
		case "1":
			do {
				keylevel2 = "1";
				menuQLK();
			} while (keylevel2 != "9");
			break;
		case "2":
			do {
				keylevel2 = "1";
				menuQLNV();
			} while (keylevel2 != "9");
			break;
		case "3":
			do {
				keylevel2 = "1";
				menuQLKH();
			} while (keylevel2 != "9");
			break;
		case "4":
			do {
				keylevel2 = "1";
				menuQLAsian();
			} while (keylevel2 != "9");
			break;
		case "5":
			do {
				keylevel2 = "1";
				menuQLEurope();
			} while (keylevel2 != "9");
			break;
		case "6":
			do {
				keylevel2 = "1";
				menuTK();
			} while (keylevel2 != "7");
			break;
		case "7":
			keylevel1 = "7";
			break;
		case "8":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}
	private static void menuTK() {
		// TODO Auto-generated method stub
		float sum = 0;
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("|\t%-50s|\n","1. Xuất tổng thu nhập từ tất cả hóa đơn");
		System.out.printf("|\t%-50s|\n","2. Xuất tổng chi phí mua hàng");
		System.out.printf("|\t%-50s|\n","3. Xuất tổng chi phí thuê nhân viên");
		System.out.printf("|\t%-50s|\n","4. Xuất tổng lợi nhuận gần đúng (Dự kiến)");
		System.out.printf("|\t%-50s|\n","5. Xuất ngẫu nhiên 1 phiên dịch viên tại Asian");
		System.out.printf("|\t%-50s|\n","6. Xuất ngẫu nhiên 1 phiên dịch viên tại Europe");
		System.out.printf("|\t%-50s|\n","7. Trở về");
		System.out.printf("|\t%-50s|\n","8. Exit");
		System.out.printf("|\t%-50s|\n","");
		System.out.printf("+---------------------------------------------------------+\n");
		System.out.print("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "1":
			System.out.printf("Tổng thu thu nhập từ việc bán hàng là: %.2f $\n", product1.tongThuNhap());
			break;
		case "2":
			sum +=europe.tkTongChiPhi();
			sum +=asian.tkTongChiPhi();
			System.out.println("Tổng chi phí mua hàng từ nhà cung cấp là: " + sum + "$");
			break;
		case "3":
			sum +=staff.tkTongChiPhi();
			System.out.println("Tổng chi phí thuê nhân viên là: " + sum + "$");
			break;
		case "4":
			System.out.println("Tổng lợi nhuận tính theo công thức: <Tổng thu nhập> - <Tổng chi phí>");
			sum +=europe.tkTongChiPhi();
			sum +=asian.tkTongChiPhi();
			sum +=staff.tkTongChiPhi();
			System.out.println("Tổng lợi nhuận là: " + (product1.tongThuNhap()-sum) + "$");
			break;
		case "5":
			Supplier a = new AsianSup();
			a.assistant();
			break;
		case "6":
			Supplier e = new EuropeSup();
			e.assistant();
			break;
		case "7":
			keylevel2 = "7";
			break;
		case "8":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}

	// Menu 2: For Manager Job
	public static void menuQLK() {
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("|\t%-65s|\n", "1. Nhập n sản phẩm đầu tiên");
		System.out.printf("|\t%-65s|\n", "2. Xuất danh sách sản phẩm");
		System.out.printf("|\t%-65s|\n", "3. Thêm mới một sản phẩm");
		System.out.printf("|\t%-65s|\n", "4. Sửa sản phẩm theo mã");
		System.out.printf("|\t%-65s|\n", "5. Xóa sản phẩm theo mã");
		System.out.printf("|\t%-65s|\n", "6. Tìm kiếm sản phẩm");
		System.out.printf("|\t%-65s|\n", "7. Đọc file (Chú ý: Ghi đè lên dữ liệu hiện tại)");
		System.out.printf("|\t%-65s|\n", "8. Lưu file (Chú ý: Ghi đè dữ liệu hiện tại vào file)");
		System.out.printf("|\t%-65s|\n", "9. Trở về menu");
		System.out.printf("|\t%-65s|\n", "10. Thoát");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "1":
			warehouse.inList();
			break;
		case "2":
			warehouse.outList();
			break;
		case "3":
			warehouse.add();
			break;
		case "4":
			warehouse.fix();
			break;
		case "5":
			warehouse.delete();
			break;
		case "6":
			warehouse.find();
			break;
		case "7":
			warehouse.doc();
			break;
		case "8":
			warehouse.ghi();
			break;
		case "9":
			keylevel2 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}
	public static void menuQLEurope() {
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("|\t%-65s|\n", "1. Nhập n nhà cung cấp tại Europe");
		System.out.printf("|\t%-65s|\n", "2. Xuất danh sách nhà cung cấp");
		System.out.printf("|\t%-65s|\n", "3. Thêm mới một nhà cung cấp");
		System.out.printf("|\t%-65s|\n", "4. Sửa nhà cung cấp theo mã");
		System.out.printf("|\t%-65s|\n", "5. Xóa nhà cung cấp theo mã");
		System.out.printf("|\t%-65s|\n", "6. Xuất thuế dự kiến");
		System.out.printf("|\t%-65s|\n", "7. Đọc file (Chú ý: Ghi đè lên dữ liệu hiện tại)");
		System.out.printf("|\t%-65s|\n", "8. Lưu file (Chú ý: Ghi đè dữ liệu hiện tại vào file)");
		System.out.printf("|\t%-65s|\n", "9. Trở về menu");
		System.out.printf("|\t%-65s|\n", "10. Thoát");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "1":
			europe.inList();
			break;
		case "2":
			europe.outList();
			break;
		case "3":
			europe.add();
			break;
		case "4":
			europe.fix();
			break;
		case "5":
			europe.delete();
			break;
		case "6":
			europe.taxDisplay();
			break;
		case "7":
			europe.doc();
			break;
		case "8":
			europe.ghi();
			break;
		case "9":
			keylevel2 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}
	public static void menuQLAsian() {
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("|\t%-65s|\n", "1. Nhập n nhà cung cấp tại Asian");
		System.out.printf("|\t%-65s|\n", "2. Xuất danh sách nhà cung cấp");
		System.out.printf("|\t%-65s|\n", "3. Thêm mới một nhà cung cấp");
		System.out.printf("|\t%-65s|\n", "4. Sửa nhà cung cấp theo mã");
		System.out.printf("|\t%-65s|\n", "5. Xóa nhà cung cấp theo mã");
		System.out.printf("|\t%-65s|\n", "6. Xuất thuế dự kiến");
		System.out.printf("|\t%-65s|\n", "7. Đọc file (Chú ý: Ghi đè lên dữ liệu hiện tại)");
		System.out.printf("|\t%-65s|\n", "8. Lưu file (Chú ý: Ghi đè dữ liệu hiện tại vào file)");
		System.out.printf("|\t%-65s|\n", "9. Trở về menu");
		System.out.printf("|\t%-65s|\n", "10. Thoát");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "1":
			asian.inList();
			break;
		case "2":
			asian.outList();
			break;
		case "3":
			asian.add();
			break;
		case "4":
			asian.fix();
			break;
		case "5":
			asian.delete();
			break;
		case "6":
			asian.taxDisplay();
			break;
		case "7":
			asian.doc();
			break;
		case "8":
			asian.ghi();
			break;
		case "9":
			keylevel2 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}
	public static void menuQLNV() {
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("|\t%-65s|\n", "0. Tạo tài khoản cho nhân viên");
		System.out.printf("|\t%-65s|\n", "1. Nhập n nhân viên đầu tiên");
		System.out.printf("|\t%-65s|\n", "2. Xuất danh sách nhân viên");
		System.out.printf("|\t%-65s|\n", "3. Thêm mới một nhân viên");
		System.out.printf("|\t%-65s|\n", "4. Sửa nhân viên theo mã");
		System.out.printf("|\t%-65s|\n", "5. Xóa nhân viên theo mã");
		System.out.printf("|\t%-65s|\n", "6. Tìm kiếm nhân viên theo mã");
		System.out.printf("|\t%-65s|\n", "7. Đọc file (Chú ý: Ghi đè lên dữ liệu hiện tại)");
		System.out.printf("|\t%-65s|\n", "8. Lưu file (Chú ý: Ghi đè dữ liệu hiện tại vào file)");
		System.out.printf("|\t%-65s|\n", "9. Trở về menu");
		System.out.printf("|\t%-65s|\n", "10. Thoát");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "0":
			log.createAc();
			break;
		case "1":
			staff.inList();
			break;
		case "2":
			staff.outList();
			break;
		case "3":
			staff.add();
			break;
		case "4":
			staff.fix();
			break;
		case "5":
			staff.delete();
			break;
		case "6":
			staff.find();
			break;
		case "7":
			staff.doc();
			break;
		case "8":
			staff.ghi();
			break;
		case "9":
			keylevel2 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}

	}
	public static void menuQLKH() {
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("|\t%-65s|\n", "0. Tạo tài khoản cho khách hàng");
		System.out.printf("|\t%-65s|\n", "1. Nhập n khách hàng đầu tiên");
		System.out.printf("|\t%-65s|\n", "2. Xuất danh sách khách hàng");
		System.out.printf("|\t%-65s|\n", "3. Thêm mới một khách hàng");
		System.out.printf("|\t%-65s|\n", "4. Sửa khách hàng theo mã");
		System.out.printf("|\t%-65s|\n", "5. Xóa khách hàng theo mã");
		System.out.printf("|\t%-65s|\n", "6. Tìm kiếm khách hàng");
		System.out.printf("|\t%-65s|\n", "7. Đọc file (Chú ý: Ghi đè lên dữ liệu hiện tại)");
		System.out.printf("|\t%-65s|\n", "8. Lưu file (Chú ý: Ghi đè dữ liệu hiện tại vào file)");
		System.out.printf("|\t%-65s|\n", "9. Trở về menu");
		System.out.printf("|\t%-65s|\n", "10. Thoát");
		System.out.printf("|\t%-65s|\n","");
		System.out.printf("+------------------------------------------------------------------------+\n");
		System.out.printf("Nhập chức năng: ");
		keylevel2 = sc.nextLine();
		switch(keylevel2) {
		case "0":
			log.createAc();
			break;
		case "1":
			customer.inList();
			break;
		case "2":
			customer.outList();
			break;
		case "3":
			customer.add();
			break;
		case "4":
			customer.fix();
			break;
		case "5":
			customer.delete();
			break;
		case "6":
			customer.find();
			break;
		case "7":
			customer.doc();
			break;
		case "8":
			customer.ghi();
			break;
		case "9":
			keylevel2 = "9";
			break;
		case "10":
			System.err.println("Cảm ơn bạn đã dùng phần mềm!");
			System.exit(0);
		default:
			System.out.println("Giá trị bạn nhập không đúng!");
			break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			menuAll();
		}
	}

}
