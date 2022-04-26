package toy;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.jsoup.nodes.Document;

public class SetData {
	Crawler cr = Crawler.getInstance(); // 싱글톤 객체 불러오기
	
	private static SetData sd;
	
	private ArrayList<Document> doc;

	private String[] main; // 첫 번째 화면 선택지 (입장 여부)
	private String[] whichAlbum; // 두 번째 화면 선택지 (앨범 선택)
	private String[][] whichTrack; // 세 번째 화면 선택지 (트랙 선택)
	private String[] player;// 네 번째 화면 선택지 (트랙 선택)

	private ImageIcon mainImg; // 첫 번째 화면 이미지
	private ImageIcon albumChoiceImg; // 두 번째 화면 이미지
	private ImageIcon playerImg; // 네 번째 화면 이미지

/*********************************************************************************/

	public ArrayList<Document> getDoc() { //getter
		return doc;
	}

	public String[] getMain() {
		return main;
	}

	public String[] getWhichAlbum() {
		return whichAlbum;
	}

	public String[][] getWhichTrack() {
		return whichTrack;
	}

	public String[] getPlayer() {
		return player;
	}

	public ImageIcon getMainImg() {
		return mainImg;
	}

	public ImageIcon getAlbumChoiceImg() {
		return albumChoiceImg;
	}

	public ImageIcon getPlayerImg() {
		return playerImg;
	}

/*********************************************************************************/
	
	private SetData() {}

	public static SetData getInstance() { // 싱글톤 객체 만들기
		if (sd == null) {
			sd = new SetData();
		}
		return sd;
	}
	
/*********************************************************************************/

	public void setDoc() {
		doc = new ArrayList<Document>(); // html 리스트
		for (String v : cr.getAlbumUrl()) { // html 파싱하기
			doc.add(cr.connect(v));
		}
		doc.add(cr.connect(cr.getAlbumListUrl()));
		System.out.println("파싱한 페이지 개수 : " + doc.size() + "\n");
	}

	public void setText() {
		main = new String[] { "입장하기", "나가기" };

		whichAlbum = new String[cr.getAlbumUrl().size()];
		whichAlbum = cr.getAlbum(doc.get(doc.size() - 1)); // 앨범 이름 데이터 넣기

		whichTrack = new String[whichAlbum.length][];
		for (int i = 0; i < whichTrack.length; i++) { // 앨범별로 트랙 데이터 넣기, 앨범이름& 트랙 데이터 확인
			whichTrack[i] = cr.getTrack(doc.get(i));
			System.out.println(whichAlbum[i] + " 트랙 : ");
			for (String v : whichTrack[i]) {
				System.out.println(v);
			}
			System.out.println();
		}

		player = new String[] {"다시 재생", "앨범 선택", "곡 선택", "나가기"};
	}

	public void setImg(Document doc) {
		mainImg = new ImageIcon("src/img/main.gif");
		albumChoiceImg = new ImageIcon("src/img/albumChoice.jpg");
		playerImg = new ImageIcon("src/img/player.gif");
	}
}
