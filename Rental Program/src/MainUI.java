import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainUI {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Manager Array = new Manager(); //매니저 객체 선언 및 생성
		Loop1:
		while(true) { //메뉴
			System.out.println("---------------상품 렌탈 프로그램---------------");
			System.out.println("1. 고객용");
			System.out.println("2. 관리자용");
			System.out.println("3. 종료");
			System.out.println("-------------------------------------------");
			System.out.println("원하시는 프로그램 번호를 입력해주세요: ");
			int menu = scan.nextInt();
			switch(menu) {
			case 1: //1. 고객용 선택시
				Loop2 : 
				while(true) { //고객용 메뉴
					System.out.println("---------------상품 렌탈 프로그램(고객용)---------------");
					System.out.println("1. 상품 검색");
					System.out.println("2. 체크인");
					System.out.println("3. 체크아웃");
					System.out.println("4. 종료");
					System.out.println("-------------------------------------------------");
					System.out.println("원하시는 프로그램 번호를 입력하세요: ");
					int num = scan.nextInt();
					if(num==1) { //1. 상품 검색 선택시
						System.out.println("원하시는 상품의 코드를 입력하세요: "); 
						String ic = scan.next();
						try { //해당 상품의 재고가 남아있는지 확인
							Item item1 = Array.searchAvailableItem(ic);
							System.out.println("해당 상품은 현재 렌트 가능합니다.");
						}
						catch(Exception e) { //재고가 없다면 해당 예외 메세지 출력
							System.out.println(e.getMessage());
						}
					}
					else if(num==2) { //2. 체크인 선택시
						User user1 = new User();
						System.out.println("이름을 입력하세요: "); //이름을 입력받아 set함
						String n = scan.next();
						user1.setUserName(n);
						System.out.println("전화번호를 입력하세요: "); //전화번호를 입력받아 set함
						String p = scan.next();
						user1.setUserPhone(p);
						System.out.println("렌트할 상품 품목수를 입력하세요(최대 3개까지 가능): "); //품목수를 입력받아 set함
						int c = scan.nextInt();
						user1.setItemCodeCount(c);
						for(int i = 0; i < c; i++) { //품목수 만큼 상품코드를 입력받음
							System.out.println("렌트할 상품의 코드를 하나씩 입력하세요: ");
							String ic = scan.next();
							user1.addItemCode(i, ic);
							try { //렌트할 상품의 재고가 존재한다면 렌트 실행
								Array.rent(user1.getItemCodeArray()[i]);
							}
							catch(Exception e){ //렌트할 상품의 재고가 존재하지 않다면 다음 문구 출력
								System.out.println("해당 상품은 렌트할 수 없습니다.");
								continue Loop2; //프로그램 종료
							}
						}
						System.out.println("렌트일(YYYY/MM/dd)을 입력하세요: "); //렌트일을 입력받아 set함
						String ren = scan.next();
						user1.setRentalDate(ren);
						System.out.println("반납예정일(YYYY/MM/dd)을 입력하세요: "); //반납예정일을 입력받아 set함
						String re = scan.next();
						user1.setReturnDate(re);
						Array.addUser(user1); //사용자 정보 객체를 렌탈배열에 추가
					}
					else if(num==3) { //3. 체크아웃 선택시
						System.out.println("전화번호를 입력하세요: ");
						String p = scan.next();
						try {
							int index = Array.searchUser(p); //입력받은 전화번호로 고객 검색 후 렌탈배열 내의 고객 인덱스 반환
							User user1 = Array.rentalArrayAt(index);  //고객 인덱스에 따른 고객 반환
							String rentalDay = user1.getRentalDate(); //해당 고객의 렌트일 반환
							
							SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd"); 
							Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장
							String time = format1.format(date_now); //현재시간을 문자열로 저장

							long returnCost = Array.returnItem(index, rentalDay, time); //최종 결제 금액 계산
							Array.deleteUser(index); //렌탈 배열에서 해당 고객 정보 삭제
							Array.addSales(returnCost, time); //매출에 추가
							System.out.println("결제하실 금액은 "+returnCost +"원입니다.");
							System.out.println("반납이 완료되었습니다.");
							
						}catch(Exception e){ //해당 전화번호 고객이 없을 시
							System.out.println(e.getMessage()); //메세지 발생
						}
					}

					else {//4. 종료 선택시
						System.out.println("프로그램을 종료합니다.");
						continue Loop1;
					}
				}
			case 2: //2. 관리자용 선택시
				while(true) { //관리자용 메뉴
					System.out.println("---------------상품 렌탈 프로그램(관리자용)---------------");
					System.out.println("1. 상품등록");
					System.out.println("2. 상품삭제");
					System.out.println("3. 전체 상품 보여주기");
					System.out.println("4. 체크인 정보 검색하기");
					System.out.println("5. 일매출 조회하기");
					System.out.println("6. 종료");
					System.out.println("--------------------------------------------------");
					System.out.println("원하시는 프로그램 번호를 입력해주세요: ");
					int num = scan.nextInt(); //원하는 메뉴 입력받음
					if(num==1) { //1. 상품등록 선택시
						Item item1 = new Item(); //상품 객체 생성
						System.out.println("상품이름을 입력하세요: "); //상품이름을 입력받아 set함
						String iN = scan.next();
						item1.setItemName(iN);
						System.out.println("상품코드를 입력하세요: "); //상품코드를 입력받아 set함
						String iC = scan.next();
						item1.setItemCode(iC);
						System.out.println("상품 수를 입력하세요: "); //상품 수를 입력받아 set함
						int iCnt = scan.nextInt();
						item1.setItemCount(iCnt);
						System.out.println("상품가격을 입력하세요: "); //상품가격을 입력받아 set함
						int iCost = scan.nextInt();
						item1.setItemCost(iCost);
						try {
							Array.addItem(item1); //상품객체를 상품배열에 추가
						}
						catch(Exception e) {
							System.out.println(e.getMessage()); //추가할 상품코드가 기존의 상품코드와 겹친다면 예외발생
							continue;
						}
					}
					else if(num==2) { //2. 상품삭제 선택시
						System.out.println("삭제하고싶은 상품의 코드를 입력하세요: ");
						String answer = scan.next();
						try {
							Array.deleteItem(Array.searchItem(answer)); //상품 삭제
						}
						catch(Exception e) {
							System.out.println(e.getMessage()); //삭제할 상품이 존재하지 않는다면 예외 발생
						}
					}
					else if(num==3) { //3. 전체 상품 보여주기 선택시
						for(int i = 0; i < Array.getIcount(); i++) {
							System.out.println("상품이름 : " + Array.itemsAt(i).getItemName() + " 상품코드 : " + Array.itemsAt(i).getItemCode() + " 상품 수 : " + Array.itemsAt(i).getItemCount() + " 상품 가격 : " + Array.itemsAt(i).getItemCost());
						}
					}
					else if(num==4) { //4. 체크인 정보 검색하기 선택시
						System.out.println("(1)사용자로 검색 (2)전체 렌탈현황 조회"); //서브 메뉴
						System.out.println("원하시는 번호를 입력하세요: "); 
						int num2 = scan.nextInt();
						if(num2 == 1) { //(1) 사용자로 검색 선택시
							System.out.println("검색을 원하는 사용자의 전화번호를 입력하세요: ");
							String pn = scan.next();
							try {
								int index = Array.searchUser(pn); // 입력받은 전화번호에 해당하는 사용자의 렌탈배열 인덱스 값을 index에 저장
								System.out.println("해당 사용자의 렌탈 현황입니다.");
								System.out.println("이름 : " + Array.rentalArrayAt(index).getUserName()); //해당 사용자의 이름을 출력함
								System.out.println("<렌탈 상품 코드>"); //해당 사용자의 렌탈 상품 코드를 출력함
								for(int i = 0; i < 3; i++) { //사용자의 상품코드배열의 크기가 3이므로 반복하면서
									if(Array.rentalArrayAt(index).ItemCodeArrayAt(i)!= null) { //상품코드의 값이 널이 아니라면 출력
										System.out.println(Array.rentalArrayAt(index).ItemCodeArrayAt(i)+" ");
									}
								}
							}
							catch(Exception e) { //전화번호로 검색시 해당 사용자가 존재하지 않는다면 예외발생
								System.out.println(e.getMessage());
							}
						}
						else { //(2) 전체 렌탈현황 조회 선택시
							for(int i = 0; i < Array.getRcount(); i++) { //렌탈배열을 돌면서 렌탈현황 출력
								System.out.println("이름 : " + Array.rentalArrayAt(i).getUserName() + " 전화번호 : " + Array.rentalArrayAt(i).getUserPhone());
								System.out.println(" <렌탈 상품 코드> ");
								for(int j = 0; j < 3; j++) {
									if(Array.rentalArrayAt(i).ItemCodeArrayAt(j)!= null) {
										System.out.println(Array.rentalArrayAt(i).ItemCodeArrayAt(j)+" ");
									}
								}
							}
						}
					}
					else if(num==5) { //5. 일매출 조회하기 선택시
						System.out.println("매출 조회를 원하는 일을 입력하세요: "); 
						int day = scan.nextInt();
						System.out.println(day+"일의 매출: "+Array.getSales(day-1));
					}
					else {//6. 종료 선택시
						break; //프로그램 종료
					}
				}
			case 3://3. 종료 선택시
				System.out.println("프로그램을 종료합니다."); 
			}
			if(menu==3) break; //전체 프로그램 종료
		}
	}
}
