import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Manager {
	
	final int max = 100; //�迭�� �ִ� ũ�� ����
	private Item[] itemArray = new Item[max]; //��ǰ�迭 ���� �� ����
	private User[] rentalArray = new User[max]; //��Ż�迭 ���� �� ����
	private static long[] sales = new long[31]; //�ϸ���迭 ���� �� ����
	int iCount = 0; //��ǰ�迭�� �ε��� �� ���� ����
	int rCount = 0; //��Ż�迭�� �ε��� �� ���� ����

	
	public void addItem(Item item) throws Exception { //��ǰ�� ��ǰ�迭�� �߰��ϱ� ���� �޼ҵ�
		for(int i = 0; i<iCount; i++) { //��ǰ�迭�� ���鼭 �ߺ��Ǵ� ��ǰ�ڵ尡 �ִ��� Ȯ��
			if(itemArray[i].getItemCode().equals(item.getItemCode())) {
				throw new Exception("�ߺ��� �ڵ尪�Դϴ�. �ٽ� ������ּ���"); //�ߺ��� ��ǰ�ڵ尡 �ִٸ� ���ܹ߻�
			}
		}
		itemArray[iCount] = item; //�ߺ��� ��ǰ�ڵ尡 ���ٸ� �Ű������� ���� ��ǰ�� ��ǰ�迭�� ����
		iCount++; //�ε����� 1 ������Ŵ
	}
	
	public void addUser(User user) { //��Ż�迭�� ����� ������ �߰��ϱ� ���� �޼ҵ�
		rentalArray[rCount] = user; //�Ű������� ���� ����� ������ ��Ż�迭�� ����
		rCount++; //�ε����� 1 ������Ŵ
	}
	
	public int searchItem(String itemCode) throws Exception { //�Ű������� ���޵� ��ǰ�ڵ忡 �ش��ϴ� ��ǰ�� �ε����� ��ȯ���ִ� �޼ҵ�
		for(int i = 0; i<iCount; i++) { //��ǰ�迭�� ��ȯ
			String str = itemArray[i].getItemCode(); //��ǰ�� ��ǰ�ڵ带 get�ؼ� str�� ����
			if(itemCode.equals(str)) //str�� �Ű������� ���޵� ��ǰ�ڵ尡 ���ٸ�
				return i; //�ش� �ε��� ��ȯ
		}
		throw new Exception("�ش� ��ǰ�� ã�� �� �����ϴ�.");
	}
	
	public void deleteItem(int i) { //��ǰ�迭���� ��ǰ�� �����ϱ� ���� �޼ҵ� 
		for(int k = i; k < iCount-1; k++) { //�ش� �ε��� ����� �޺κ� ��Һ��� �ϳ��� ��ܿ�
			itemArray[k] = itemArray[k+1];
		}
		iCount--; //�ε����� 1 ���ҽ�Ŵ
	}
	
	public void deleteUser(int i) { //��Ż�迭���� ����� ������ �����ϴ� �޼ҵ�
		for(int k = i; k < rCount-1; k++) { //�ش� �ε��� ����� �޺κ� ��Һ��� �ϳ��� ��ܿ�
			rentalArray[k] = rentalArray[k+1];
		}
		rCount--; //�ε����� 1 ���ҽ�Ŵ
	}
	
	public Item itemsAt(int i) { //��ǰ�迭���� �ش� �ε����� ��ǰ�� ��ȯ�ϱ� ���� �޼ҵ�
		return itemArray[i];
	}
	
	public User rentalArrayAt(int i) { //��Ż�迭���� �ش� �ε����� ����� ������ ��ȯ�ϱ� ���� �޼ҵ�
		return rentalArray[i];
	}
	
	public int getIcount() { //��ǰ�迭�� ����� ��ǰ ������  ��ȯ�ϴ� �޼ҵ�
		return iCount;
	}
	
	public int getRcount() { //��Ż�迭�� ����� ����� ���� ��ȯ�ϴ� �޼ҵ�
		return rCount;
	}
	
	public Item searchAvailableItem(String itemCode) throws Exception { //Ư�� ��ǰ�� ��Ż������ ��� �ִ��� Ȯ���ϴ� �޼ҵ�
		for(int i = 0; i < iCount; i++) { //��ǰ�迭�� ���鼭
			if(itemCode.equals(itemArray[i].getItemCode()) && itemArray[i].getItemCount() > 0) { //�Ű������� ���޵� ��ǰ�ڵ尡 ��ǰ�迭 ���� ��ǰ�ڵ�� ���� ��� 1�� �̻��̶��
				return itemArray[i]; //�ش� ��ǰ ��ȯ
			}
		}
		throw new Exception("���� ��Ż ������ ��� �����ϴ�."); 
	}
	
	public int searchUser(String userPhoneNum) throws Exception { //��ȭ��ȣ�� �̿��� ����ڸ� �˻��ϴ� �޼ҵ�
		for(int i = 0; i < rCount; i++) { //��Ż�迭�� ���鼭
			if(userPhoneNum.equals(rentalArray[i].getUserPhone())) { //�Ű������� ���޵� ��ȭ��ȣ�� ��Ż�迭 ���� ��ȭ��ȣ�� ���ٸ�
				return i; //�ش� ������� �ε��� ��ȯ
			}
		}
		throw new Exception("�ش� ����ڴ� �������� �ʽ��ϴ�.");
	}
	
	public void rent(String itemCode) throws Exception{ //��Ʈ ���� �� �ش� ��ǰ�� ��� ���� �ϳ� ���ҽ�Ű�� ���� �޼ҵ�
		int index = searchItem(itemCode); //�Ű������� ���޵� ��ǰ�ڵ带 �˻��ؼ� index�� ����
			itemArray[index].subtract(); //��ǰ �迭 �� �ش� ��ǰ�� ��� �ϳ� ����
	}
	
	public long returnItem(int userIndex, String rentalDay, String returnDay) throws Exception { //��ǰ �ݳ� �޼ҵ�
		long returnCost = 0; //������� �����ݾ� �ʱ�ȭ
		for(int i = 0; i < 3; i++) { //������� ��ǰ�ڵ� �迭�� ���鼭
			String itemCode = rentalArray[userIndex].ItemCodeArrayAt(i); //������� ��ǰ�ڵ�迭 �� ������ ��ǰ�ڵ� ��ȯ
			if(itemCode != null) { //��ǰ�ڵ尡 �����Ѵٸ�
				int itemIndex = searchItem(itemCode); //��ǰ�ڵ�� �ش� ��ǰ �˻�
				int itemCost = itemsAt(itemIndex).getItemCost(); //�˻��� ��ǰ�� ���� ��ȯ
				returnCost += rentalArray[userIndex].pay(rentalDay, returnDay, itemCost); //��Ż�Ͽ� ���� ��ü �ݾ� ���
				itemArray[itemIndex].plus(); //��ǰ ��� +1
			}
		}
		return returnCost; //��ü �ݾ� ��ȯ
	}
	
	public static void addSales(long returnCost, String returnDay) throws ParseException{ //����迭�� �ϸ����� �߰��ϴ� �޼ҵ�
		SimpleDateFormat format1 = new SimpleDateFormat("dd"); 
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
		
		Date returnDate = format2.parse(returnDay); //�Ű����� ���޵� ���ڿ� ������ �ݳ����� Date�� �Ľ�
		String date = format1.format(returnDate); //Date ������ �ݳ��Ͽ��� �ϸ� ��ȯ�ؼ� ���ڿ��� ��ȯ

		int num = Integer.parseInt(date)-1; //���ڿ� ������ '��'�� ���������� �ٲٰ� -1��(�迭�� �ε������� 0���� �����ϱ� ����)
		sales[num] += returnCost; //�ϸ���迭�� �����ϸ鼭 ����
	}
	
	public long getSales(int i) { //����迭���� Ư�� ���� ������ ��ȯ�ϴ� �޼ҵ�
		return sales[i];
	}
}
