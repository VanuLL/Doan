import java.util.Random;

public class AsianSup extends Supplier {
	public AsianSup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AsianSup(String id, String nameCity, String country, String value) {
		super(id, nameCity, country, value);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tax() {
		// TODO Auto-generated method stub
		System.out.println("Thuế nhập khẩu từ châu Á tại công ty này là 5%!");
	}
	@Override
	public void taxShow() {
		// TODO Auto-generated method stub
		float t = Float.parseFloat(getValue());
		System.out.println("Nếu tổng hóa đơn trị giá " + t + " USD sẽ phải trả thuế: " + t*5/100 + " USD");
	}
	public void assistant() {
		Random generator = new Random();
		int value = generator.nextInt((4 - 1) + 1) + 1;
		if (value == 1) {
			System.out.println("Văn Công sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 0921003203");
		} else if (value == 2) {
			System.out.println("Hữu Đạt sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 0392138203");
		} else if (value == 3) {
			System.out.println("Kiều Lam sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 0620203203");
		} else {
			System.out.println("Văn Trí sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 0230020203");
		}
	}
}