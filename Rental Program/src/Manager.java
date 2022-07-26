import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Manager {
	
	final int max = 100; //배열의 최대 크기 지정
	private Item[] itemArray = new Item[max]; //상품배열 선언 및 생성
	private User[] rentalArray = new User[max]; //렌탈배열 선언 및 생성
	private static long[] sales = new long[31]; //일매출배열 선언 및 생성
	int iCount = 0; //상품배열의 인덱스 값 변수 선언
	int rCount = 0; //렌탈배열의 인덱스 값 변수 선언

	
	public void addItem(Item item) throws Exception { //상품을 상품배열에 추가하기 위한 메소드
		for(int i = 0; i<iCount; i++) { //상품배열을 돌면서 중복되는 상품코드가 있는지 확인
			if(itemArray[i].getItemCode().equals(item.getItemCode())) {
				throw new Exception("중복된 코드값입니다. 다시 등록해주세요"); //중복된 상품코드가 있다면 예외발생
			}
		}
		itemArray[iCount] = item; //중복된 상품코드가 없다면 매개변수로 들어온 상품을 상품배열에 저장
		iCount++; //인덱스를 1 증가시킴
	}
	
	public void addUser(User user) { //렌탈배열에 사용자 정보를 추가하기 위한 메소드
		rentalArray[rCount] = user; //매개변수로 들어온 사용자 정보를 렌탈배열에 저장
		rCount++; //인덱스를 1 증가시킴
	}
	
	public int searchItem(String itemCode) throws Exception { //매개변수로 전달된 상품코드에 해당하는 상품의 인덱스를 반환해주는 메소드
		for(int i = 0; i<iCount; i++) { //상품배열을 순환
			String str = itemArray[i].getItemCode(); //상품의 상품코드를 get해서 str에 저장
			if(itemCode.equals(str)) //str과 매개변수로 전달된 상품코드가 같다면
				return i; //해당 인덱스 반환
		}
		throw new Exception("해당 상품을 찾을 수 없습니다.");
	}
	
	public void deleteItem(int i) { //상품배열에서 상품을 삭제하기 위한 메소드 
		for(int k = i; k < iCount-1; k++) { //해당 인덱스 요소의 뒷부분 요소부터 하나씩 당겨옴
			itemArray[k] = itemArray[k+1];
		}
		iCount--; //인덱스를 1 감소시킴
	}
	
	public void deleteUser(int i) { //렌탈배열에서 사용자 정보를 삭제하는 메소드
		for(int k = i; k < rCount-1; k++) { //해당 인덱스 요소의 뒷부분 요소부터 하나씩 당겨옴
			rentalArray[k] = rentalArray[k+1];
		}
		rCount--; //인덱스를 1 감소시킴
	}
	
	public Item itemsAt(int i) { //상품배열에서 해당 인덱스의 상품을 반환하기 위한 메소드
		return itemArray[i];
	}
	
	public User rentalArrayAt(int i) { //렌탈배열에서 해당 인덱스의 사용자 정보를 반환하기 위한 메소드
		return rentalArray[i];
	}
	
	public int getIcount() { //상품배열에 저장된 상품 개수를  반환하는 메소드
		return iCount;
	}
	
	public int getRcount() { //렌탈배열에 저장된 사용자 수를 반환하는 메소드
		return rCount;
	}
	
	public Item searchAvailableItem(String itemCode) throws Exception { //특정 상품이 렌탈가능한 재고가 있는지 확인하는 메소드
		for(int i = 0; i < iCount; i++) { //상품배열을 돌면서
			if(itemCode.equals(itemArray[i].getItemCode()) && itemArray[i].getItemCount() > 0) { //매개변수로 전달된 상품코드가 상품배열 내의 상품코드와 같고 재고가 1개 이상이라면
				return itemArray[i]; //해당 상품 반환
			}
		}
		throw new Exception("현재 렌탈 가능한 재고가 없습니다."); 
	}
	
	public int searchUser(String userPhoneNum) throws Exception { //전화번호를 이용해 사용자를 검색하는 메소드
		for(int i = 0; i < rCount; i++) { //렌탈배열을 돌면서
			if(userPhoneNum.equals(rentalArray[i].getUserPhone())) { //매개변수로 전달된 전화번호가 렌탈배열 내의 전화번호와 같다면
				return i; //해당 사용자의 인덱스 반환
			}
		}
		throw new Exception("해당 사용자는 존재하지 않습니다.");
	}
	
	public void rent(String itemCode) throws Exception{ //렌트 진행 시 해당 상품의 재고 수를 하나 감소시키기 위한 메소드
		int index = searchItem(itemCode); //매개변수로 전달된 상품코드를 검색해서 index에 저장
			itemArray[index].subtract(); //상품 배열 속 해당 상품의 재고를 하나 감소
	}
	
	public long returnItem(int userIndex, String rentalDay, String returnDay) throws Exception { //상품 반납 메소드
		long returnCost = 0; //사용자의 결제금액 초기화
		for(int i = 0; i < 3; i++) { //사용자의 상품코드 배열을 돌면서
			String itemCode = rentalArray[userIndex].ItemCodeArrayAt(i); //사용자의 상품코드배열 속 각각의 상품코드 반환
			if(itemCode != null) { //상품코드가 존재한다면
				int itemIndex = searchItem(itemCode); //상품코드로 해당 물품 검색
				int itemCost = itemsAt(itemIndex).getItemCost(); //검색된 상품의 가격 반환
				returnCost += rentalArray[userIndex].pay(rentalDay, returnDay, itemCost); //렌탈일에 따른 전체 금액 계산
				itemArray[itemIndex].plus(); //상품 재고 +1
			}
		}
		return returnCost; //전체 금액 반환
	}
	
	public static void addSales(long returnCost, String returnDay) throws ParseException{ //매출배열에 일매출을 추가하는 메소드
		SimpleDateFormat format1 = new SimpleDateFormat("dd"); 
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
		
		Date returnDate = format2.parse(returnDay); //매개변수 전달된 문자열 형태의 반납일을 Date로 파싱
		String date = format1.format(returnDate); //Date 형태의 반납일에서 일만 반환해서 문자열로 반환

		int num = Integer.parseInt(date)-1; //문자열 형태의 '일'을 정수형으로 바꾸고 -1함(배열의 인덱스값이 0부터 시작하기 때문)
		sales[num] += returnCost; //일매출배열에 누적하면서 저장
	}
	
	public long getSales(int i) { //매출배열에서 특정 일의 매출을 반환하는 메소드
		return sales[i];
	}
}
