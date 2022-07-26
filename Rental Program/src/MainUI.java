import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainUI {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Manager Array = new Manager(); //�Ŵ��� ��ü ���� �� ����
		Loop1:
		while(true) { //�޴�
			System.out.println("---------------��ǰ ��Ż ���α׷�---------------");
			System.out.println("1. ����");
			System.out.println("2. �����ڿ�");
			System.out.println("3. ����");
			System.out.println("-------------------------------------------");
			System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է����ּ���: ");
			int menu = scan.nextInt();
			switch(menu) {
			case 1: //1. ���� ���ý�
				Loop2 : 
				while(true) { //���� �޴�
					System.out.println("---------------��ǰ ��Ż ���α׷�(����)---------------");
					System.out.println("1. ��ǰ �˻�");
					System.out.println("2. üũ��");
					System.out.println("3. üũ�ƿ�");
					System.out.println("4. ����");
					System.out.println("-------------------------------------------------");
					System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է��ϼ���: ");
					int num = scan.nextInt();
					if(num==1) { //1. ��ǰ �˻� ���ý�
						System.out.println("���Ͻô� ��ǰ�� �ڵ带 �Է��ϼ���: "); 
						String ic = scan.next();
						try { //�ش� ��ǰ�� ��� �����ִ��� Ȯ��
							Item item1 = Array.searchAvailableItem(ic);
							System.out.println("�ش� ��ǰ�� ���� ��Ʈ �����մϴ�.");
						}
						catch(Exception e) { //��� ���ٸ� �ش� ���� �޼��� ���
							System.out.println(e.getMessage());
						}
					}
					else if(num==2) { //2. üũ�� ���ý�
						User user1 = new User();
						System.out.println("�̸��� �Է��ϼ���: "); //�̸��� �Է¹޾� set��
						String n = scan.next();
						user1.setUserName(n);
						System.out.println("��ȭ��ȣ�� �Է��ϼ���: "); //��ȭ��ȣ�� �Է¹޾� set��
						String p = scan.next();
						user1.setUserPhone(p);
						System.out.println("��Ʈ�� ��ǰ ǰ����� �Է��ϼ���(�ִ� 3������ ����): "); //ǰ����� �Է¹޾� set��
						int c = scan.nextInt();
						user1.setItemCodeCount(c);
						for(int i = 0; i < c; i++) { //ǰ��� ��ŭ ��ǰ�ڵ带 �Է¹���
							System.out.println("��Ʈ�� ��ǰ�� �ڵ带 �ϳ��� �Է��ϼ���: ");
							String ic = scan.next();
							user1.addItemCode(i, ic);
							try { //��Ʈ�� ��ǰ�� ��� �����Ѵٸ� ��Ʈ ����
								Array.rent(user1.getItemCodeArray()[i]);
							}
							catch(Exception e){ //��Ʈ�� ��ǰ�� ��� �������� �ʴٸ� ���� ���� ���
								System.out.println("�ش� ��ǰ�� ��Ʈ�� �� �����ϴ�.");
								continue Loop2; //���α׷� ����
							}
						}
						System.out.println("��Ʈ��(YYYY/MM/dd)�� �Է��ϼ���: "); //��Ʈ���� �Է¹޾� set��
						String ren = scan.next();
						user1.setRentalDate(ren);
						System.out.println("�ݳ�������(YYYY/MM/dd)�� �Է��ϼ���: "); //�ݳ��������� �Է¹޾� set��
						String re = scan.next();
						user1.setReturnDate(re);
						Array.addUser(user1); //����� ���� ��ü�� ��Ż�迭�� �߰�
					}
					else if(num==3) { //3. üũ�ƿ� ���ý�
						System.out.println("��ȭ��ȣ�� �Է��ϼ���: ");
						String p = scan.next();
						try {
							int index = Array.searchUser(p); //�Է¹��� ��ȭ��ȣ�� �� �˻� �� ��Ż�迭 ���� �� �ε��� ��ȯ
							User user1 = Array.rentalArrayAt(index);  //�� �ε����� ���� �� ��ȯ
							String rentalDay = user1.getRentalDate(); //�ش� ���� ��Ʈ�� ��ȯ
							
							SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd"); 
							Date date_now = new Date(System.currentTimeMillis()); // ����ð��� ������ Date������ ����
							String time = format1.format(date_now); //����ð��� ���ڿ��� ����

							long returnCost = Array.returnItem(index, rentalDay, time); //���� ���� �ݾ� ���
							Array.deleteUser(index); //��Ż �迭���� �ش� �� ���� ����
							Array.addSales(returnCost, time); //���⿡ �߰�
							System.out.println("�����Ͻ� �ݾ��� "+returnCost +"���Դϴ�.");
							System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
							
						}catch(Exception e){ //�ش� ��ȭ��ȣ ���� ���� ��
							System.out.println(e.getMessage()); //�޼��� �߻�
						}
					}

					else {//4. ���� ���ý�
						System.out.println("���α׷��� �����մϴ�.");
						continue Loop1;
					}
				}
			case 2: //2. �����ڿ� ���ý�
				while(true) { //�����ڿ� �޴�
					System.out.println("---------------��ǰ ��Ż ���α׷�(�����ڿ�)---------------");
					System.out.println("1. ��ǰ���");
					System.out.println("2. ��ǰ����");
					System.out.println("3. ��ü ��ǰ �����ֱ�");
					System.out.println("4. üũ�� ���� �˻��ϱ�");
					System.out.println("5. �ϸ��� ��ȸ�ϱ�");
					System.out.println("6. ����");
					System.out.println("--------------------------------------------------");
					System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է����ּ���: ");
					int num = scan.nextInt(); //���ϴ� �޴� �Է¹���
					if(num==1) { //1. ��ǰ��� ���ý�
						Item item1 = new Item(); //��ǰ ��ü ����
						System.out.println("��ǰ�̸��� �Է��ϼ���: "); //��ǰ�̸��� �Է¹޾� set��
						String iN = scan.next();
						item1.setItemName(iN);
						System.out.println("��ǰ�ڵ带 �Է��ϼ���: "); //��ǰ�ڵ带 �Է¹޾� set��
						String iC = scan.next();
						item1.setItemCode(iC);
						System.out.println("��ǰ ���� �Է��ϼ���: "); //��ǰ ���� �Է¹޾� set��
						int iCnt = scan.nextInt();
						item1.setItemCount(iCnt);
						System.out.println("��ǰ������ �Է��ϼ���: "); //��ǰ������ �Է¹޾� set��
						int iCost = scan.nextInt();
						item1.setItemCost(iCost);
						try {
							Array.addItem(item1); //��ǰ��ü�� ��ǰ�迭�� �߰�
						}
						catch(Exception e) {
							System.out.println(e.getMessage()); //�߰��� ��ǰ�ڵ尡 ������ ��ǰ�ڵ�� ��ģ�ٸ� ���ܹ߻�
							continue;
						}
					}
					else if(num==2) { //2. ��ǰ���� ���ý�
						System.out.println("�����ϰ���� ��ǰ�� �ڵ带 �Է��ϼ���: ");
						String answer = scan.next();
						try {
							Array.deleteItem(Array.searchItem(answer)); //��ǰ ����
						}
						catch(Exception e) {
							System.out.println(e.getMessage()); //������ ��ǰ�� �������� �ʴ´ٸ� ���� �߻�
						}
					}
					else if(num==3) { //3. ��ü ��ǰ �����ֱ� ���ý�
						for(int i = 0; i < Array.getIcount(); i++) {
							System.out.println("��ǰ�̸� : " + Array.itemsAt(i).getItemName() + " ��ǰ�ڵ� : " + Array.itemsAt(i).getItemCode() + " ��ǰ �� : " + Array.itemsAt(i).getItemCount() + " ��ǰ ���� : " + Array.itemsAt(i).getItemCost());
						}
					}
					else if(num==4) { //4. üũ�� ���� �˻��ϱ� ���ý�
						System.out.println("(1)����ڷ� �˻� (2)��ü ��Ż��Ȳ ��ȸ"); //���� �޴�
						System.out.println("���Ͻô� ��ȣ�� �Է��ϼ���: "); 
						int num2 = scan.nextInt();
						if(num2 == 1) { //(1) ����ڷ� �˻� ���ý�
							System.out.println("�˻��� ���ϴ� ������� ��ȭ��ȣ�� �Է��ϼ���: ");
							String pn = scan.next();
							try {
								int index = Array.searchUser(pn); // �Է¹��� ��ȭ��ȣ�� �ش��ϴ� ������� ��Ż�迭 �ε��� ���� index�� ����
								System.out.println("�ش� ������� ��Ż ��Ȳ�Դϴ�.");
								System.out.println("�̸� : " + Array.rentalArrayAt(index).getUserName()); //�ش� ������� �̸��� �����
								System.out.println("<��Ż ��ǰ �ڵ�>"); //�ش� ������� ��Ż ��ǰ �ڵ带 �����
								for(int i = 0; i < 3; i++) { //������� ��ǰ�ڵ�迭�� ũ�Ⱑ 3�̹Ƿ� �ݺ��ϸ鼭
									if(Array.rentalArrayAt(index).ItemCodeArrayAt(i)!= null) { //��ǰ�ڵ��� ���� ���� �ƴ϶�� ���
										System.out.println(Array.rentalArrayAt(index).ItemCodeArrayAt(i)+" ");
									}
								}
							}
							catch(Exception e) { //��ȭ��ȣ�� �˻��� �ش� ����ڰ� �������� �ʴ´ٸ� ���ܹ߻�
								System.out.println(e.getMessage());
							}
						}
						else { //(2) ��ü ��Ż��Ȳ ��ȸ ���ý�
							for(int i = 0; i < Array.getRcount(); i++) { //��Ż�迭�� ���鼭 ��Ż��Ȳ ���
								System.out.println("�̸� : " + Array.rentalArrayAt(i).getUserName() + " ��ȭ��ȣ : " + Array.rentalArrayAt(i).getUserPhone());
								System.out.println(" <��Ż ��ǰ �ڵ�> ");
								for(int j = 0; j < 3; j++) {
									if(Array.rentalArrayAt(i).ItemCodeArrayAt(j)!= null) {
										System.out.println(Array.rentalArrayAt(i).ItemCodeArrayAt(j)+" ");
									}
								}
							}
						}
					}
					else if(num==5) { //5. �ϸ��� ��ȸ�ϱ� ���ý�
						System.out.println("���� ��ȸ�� ���ϴ� ���� �Է��ϼ���: "); 
						int day = scan.nextInt();
						System.out.println(day+"���� ����: "+Array.getSales(day-1));
					}
					else {//6. ���� ���ý�
						break; //���α׷� ����
					}
				}
			case 3://3. ���� ���ý�
				System.out.println("���α׷��� �����մϴ�."); 
			}
			if(menu==3) break; //��ü ���α׷� ����
		}
	}
}
