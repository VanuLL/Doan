import java.util.Random;

public class EuropeSup extends Supplier {
	public EuropeSup() {
		// TODO Auto-generated constructor stub
	}
	public EuropeSup(String id, String nameCity, String country, String value) {
		super(id, nameCity, country, value);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tax() {
		// TODO Auto-generated method stub
		System.out.println("Thuế nhập khẩu từ châu Âu tại công ty này là 10%!");
	}
	@Override
	public void taxShow() {
		// TODO Auto-generated method stub
		float t = Float.parseFloat(getValue());
		System.out.println("Nếu tổng hóa đơn trị giá " + t + " USD sẽ phải trả thuế: " + t*10/100 + " USD");
	}
	public void assistant() {
		Random generator = new Random();
		int value = generator.nextInt((4 - 1) + 1) + 1;
		if (value == 1) {
			System.out.println("Davinci sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 01020203203");
		} else if (value == 2) {
			System.out.println("Robert sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 01020203232");
		} else if (value == 3) {
			System.out.println("Johnny sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 01020292803");
		} else {
			System.out.println("Linda sẽ hỗ trợ sếp");
			System.out.println("Số điện thoại: 01020203203");
		}
	}
}
