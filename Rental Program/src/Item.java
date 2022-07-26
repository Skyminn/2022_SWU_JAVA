
public class Item {
	private String itemName; //상품이름
	private String itemCode; //상품코드
	private int itemCount; //상품수
	private int itemCost; //상품가격
	
	public String getItemName() { //상품이름을 get하는 메소드
		return itemName;
	}
	
	public void setItemName(String itemName) { //매개변수로 전달된 값을 상품이름으로 set하는 메소드
		this.itemName = itemName;
	}
	
	public String getItemCode() { //상품코드를 get하는 메소드
		return itemCode;
	}
	
	public void setItemCode(String itemCode) { //매개변수로 전달된 값을 상품코드를 set하는 메소드
		this.itemCode = itemCode;
	}
	
	public int getItemCount() { //상품수를 get하는 메소드
		return itemCount;
	}
	
	public void setItemCount(int itemCount) { //매개변수로 전달된 값을 상품수로 set하는 메소드
		this.itemCount = itemCount;
	}
	
	public int getItemCost() { //상품가격을 get하는 메소드
		return itemCost;
	}
	
	public void setItemCost(int itemCost) { //매개변수로 전달된 값을 상품가격으로 set하는 메소드
		this.itemCost = itemCost;
	}
	
	public void plus() { //재고를 +1 하는 메소드
		itemCount += 1;
	}
	
	public void subtract() { //재고를 -1 하는 메소드
		itemCount -= 1;
	}
}
