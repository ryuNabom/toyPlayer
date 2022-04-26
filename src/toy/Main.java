//created by seyoung//

package toy;

public class Main {
	public static void main(String[] args) {
		SetData sd = SetData.getInstance(); // 데이터 세팅
		sd.setDoc();
		sd.setText();
		sd.setImg(sd.getDoc().get(sd.getDoc().size() - 1));

		int choice1_2Num = 0; // 선택지 저장공간
		String choice3 = null;	
		int choice4Num = 0;
		boolean flag = true; // 플래그

/*********************************************************************************/

		choice1_2Num = Screen.show1(sd.getMainImg(), sd.getMain()); // 메인 화면

		if (choice1_2Num == 0) { // 입장하기 눌렀을 때
			while (true) {
				if (flag) {
					choice1_2Num = Screen.show2(sd.getAlbumChoiceImg(), sd.getWhichAlbum()); // 앨범 선택 화면
					if (choice1_2Num == -1) break;
				}

				choice3 = Screen.show3(sd.getWhichAlbum()[choice1_2Num], sd.getWhichTrack()[choice1_2Num]); // 트랙 선택 화면
				if (choice3 == null) {
					flag = true;
					continue;
				}
				Music music = Music.getInstance("src/music/" + sd.getWhichAlbum()[choice1_2Num] + "/" + choice3 + ".mp3");

				do {
					music.play();
					flag = false;
					choice4Num = Screen.show4(choice3, sd.getPlayerImg(), sd.getPlayer()); // 재생 화면
					if (choice4Num == 0) flag = true;
					music.close();
				} while (flag);

				if (choice4Num == 1) flag = true;
				else if (choice4Num == -1 || choice4Num == 3) break;
			}
		}
	}
}
