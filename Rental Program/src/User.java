import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String userName; //사용자 이름
	private String userPhone; //사용자 전화번호
	private String itemCodeArray[] = new String[3]; //사용자의 렌탈상품코드를 저장하는 배열
	private int itemCodeCount; //사용자의 렌탈상품 품목 개수
	private String rentalDate; //대여일
	private String returnDate; //반납예정일
	
	public String getUserName() { //사용자이름을 get하는 메소드
		return userName;
	}
	
	public void setUserName(String userName) { //사용자이름을 set하는 메소드
		this.userName = userName;
	}
	
	public String getUserPhone() { //사용자 전화번호를 get하는 메소드
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) { //사용자 전화번호를 set하는 메소드
		this.userPhone = userPhone;
	}
	
	public String getRentalDate() { //대여일을 get하는 메소드
		return rentalDate;
	}
	
	public void setRentalDate(String rentalDate) { //대여일을 set하는 메소드
		this.rentalDate = rentalDate;
	}
	
	public String getReturnDate() { //반납예정일을 get하는 메소드
		return returnDate;
	}
	
	public void setReturnDate(String returnDate) { //반납예정일을 set하는 메소드
		this.returnDate = returnDate;
	}
	
	public int getItemCodeCount() { //렌탈상품 품목수를 get하는 메소드
		return itemCodeCount;
	}
	
	public void setItemCodeCount(int itemCodeCount) { //렌탈상품 품목수를 set하는 메소드
		this.itemCodeCount = itemCodeCount;
	}
	
	public String ItemCodeArrayAt(int i) { //사용자의 렌탈상품코드배열에서 특정 상품코드를 반환하는 메소드
		return itemCodeArray[i];
	}
	
	
	public String[] getItemCodeArray() { //사용자의 렌탈상품코드배열을 반환하는 메소드
		return itemCodeArray;
	}
	
	public void addItemCode(int i, String itemCode) { //사용자의 상품코드 배열에 상품코드를 추가하는 메소드
		itemCodeArray[i] = itemCode;
	}
	
	//렌탈 금액은 실제 반납일과 렌트일의 차이로 계산
	//연체가 되어도 과금없이 연체된 기간만큼 일 렌트 비용으로 동일하게 계산
	public long pay(String rentalDay, String returnDay, int itemCost) throws ParseException{ //상품 반납시 전체 금액을 계산하는 메소드
		SimpleDateFormat input_format = new SimpleDateFormat("yyyy/MM/dd");
		Date format1 = input_format.parse(rentalDay); //렌트일
		Date format2 = input_format.parse(returnDay); //반납일(체크아웃시 시스템 시간)
		
		long diffSec = (format2.getTime() - format1.getTime()) / 1000; //반납일과 렌트일 사이의 초 차이
		long diffDays = diffSec / (24*60*60) + 1; //반납일과 렌트일의 차이를 구하고 +1 해줌(6/3일에 빌리고 6/5일에 반납하면 총3일로 계산해야하는데 시스템상 2일로 계산되기 때문)
		
		return diffDays*itemCost; //상품별 1일 대여 가격 * 실제 대여일 = 전체 금액
	}
}