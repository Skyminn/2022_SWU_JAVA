
public class Item {
	private String itemName; //��ǰ�̸�
	private String itemCode; //��ǰ�ڵ�
	private int itemCount; //��ǰ��
	private int itemCost; //��ǰ����
	
	public String getItemName() { //��ǰ�̸��� get�ϴ� �޼ҵ�
		return itemName;
	}
	
	public void setItemName(String itemName) { //�Ű������� ���޵� ���� ��ǰ�̸����� set�ϴ� �޼ҵ�
		this.itemName = itemName;
	}
	
	public String getItemCode() { //��ǰ�ڵ带 get�ϴ� �޼ҵ�
		return itemCode;
	}
	
	public void setItemCode(String itemCode) { //�Ű������� ���޵� ���� ��ǰ�ڵ带 set�ϴ� �޼ҵ�
		this.itemCode = itemCode;
	}
	
	public int getItemCount() { //��ǰ���� get�ϴ� �޼ҵ�
		return itemCount;
	}
	
	public void setItemCount(int itemCount) { //�Ű������� ���޵� ���� ��ǰ���� set�ϴ� �޼ҵ�
		this.itemCount = itemCount;
	}
	
	public int getItemCost() { //��ǰ������ get�ϴ� �޼ҵ�
		return itemCost;
	}
	
	public void setItemCost(int itemCost) { //�Ű������� ���޵� ���� ��ǰ�������� set�ϴ� �޼ҵ�
		this.itemCost = itemCost;
	}
	
	public void plus() { //��� +1 �ϴ� �޼ҵ�
		itemCount += 1;
	}
	
	public void subtract() { //��� -1 �ϴ� �޼ҵ�
		itemCount -= 1;
	}
}
