import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String userName; //����� �̸�
	private String userPhone; //����� ��ȭ��ȣ
	private String itemCodeArray[] = new String[3]; //������� ��Ż��ǰ�ڵ带 �����ϴ� �迭
	private int itemCodeCount; //������� ��Ż��ǰ ǰ�� ����
	private String rentalDate; //�뿩��
	private String returnDate; //�ݳ�������
	
	public String getUserName() { //������̸��� get�ϴ� �޼ҵ�
		return userName;
	}
	
	public void setUserName(String userName) { //������̸��� set�ϴ� �޼ҵ�
		this.userName = userName;
	}
	
	public String getUserPhone() { //����� ��ȭ��ȣ�� get�ϴ� �޼ҵ�
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) { //����� ��ȭ��ȣ�� set�ϴ� �޼ҵ�
		this.userPhone = userPhone;
	}
	
	public String getRentalDate() { //�뿩���� get�ϴ� �޼ҵ�
		return rentalDate;
	}
	
	public void setRentalDate(String rentalDate) { //�뿩���� set�ϴ� �޼ҵ�
		this.rentalDate = rentalDate;
	}
	
	public String getReturnDate() { //�ݳ��������� get�ϴ� �޼ҵ�
		return returnDate;
	}
	
	public void setReturnDate(String returnDate) { //�ݳ��������� set�ϴ� �޼ҵ�
		this.returnDate = returnDate;
	}
	
	public int getItemCodeCount() { //��Ż��ǰ ǰ����� get�ϴ� �޼ҵ�
		return itemCodeCount;
	}
	
	public void setItemCodeCount(int itemCodeCount) { //��Ż��ǰ ǰ����� set�ϴ� �޼ҵ�
		this.itemCodeCount = itemCodeCount;
	}
	
	public String ItemCodeArrayAt(int i) { //������� ��Ż��ǰ�ڵ�迭���� Ư�� ��ǰ�ڵ带 ��ȯ�ϴ� �޼ҵ�
		return itemCodeArray[i];
	}
	
	
	public String[] getItemCodeArray() { //������� ��Ż��ǰ�ڵ�迭�� ��ȯ�ϴ� �޼ҵ�
		return itemCodeArray;
	}
	
	public void addItemCode(int i, String itemCode) { //������� ��ǰ�ڵ� �迭�� ��ǰ�ڵ带 �߰��ϴ� �޼ҵ�
		itemCodeArray[i] = itemCode;
	}
	
	//��Ż �ݾ��� ���� �ݳ��ϰ� ��Ʈ���� ���̷� ���
	//��ü�� �Ǿ ���ݾ��� ��ü�� �Ⱓ��ŭ �� ��Ʈ ������� �����ϰ� ���
	public long pay(String rentalDay, String returnDay, int itemCost) throws ParseException{ //��ǰ �ݳ��� ��ü �ݾ��� ����ϴ� �޼ҵ�
		SimpleDateFormat input_format = new SimpleDateFormat("yyyy/MM/dd");
		Date format1 = input_format.parse(rentalDay); //��Ʈ��
		Date format2 = input_format.parse(returnDay); //�ݳ���(üũ�ƿ��� �ý��� �ð�)
		
		long diffSec = (format2.getTime() - format1.getTime()) / 1000; //�ݳ��ϰ� ��Ʈ�� ������ �� ����
		long diffDays = diffSec / (24*60*60) + 1; //�ݳ��ϰ� ��Ʈ���� ���̸� ���ϰ� +1 ����(6/3�Ͽ� ������ 6/5�Ͽ� �ݳ��ϸ� ��3�Ϸ� ����ؾ��ϴµ� �ý��ۻ� 2�Ϸ� ���Ǳ� ����)
		
		return diffDays*itemCost; //��ǰ�� 1�� �뿩 ���� * ���� �뿩�� = ��ü �ݾ�
	}
}