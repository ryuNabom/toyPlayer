package toy;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {
	private static Crawler cr;
	private ArrayList<String> albumUrl = new ArrayList<String>(); // 크롤링 대상 url 리스트
	private String albumListUrl = "https://music.bugs.co.kr/artist/22751/albums?type=RELEASE&sort=R&row=70&page=1&roleCode=0&group=REGULAR&highQualityOnly=";
	private String album = ".info .albumTitle";
	private String track = ".list .info .title.ellipsis";

	private Crawler() { // 기본 생성자, 크롤링 대상 url지정
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=15034231");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=15035038");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=15035538");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=15036667");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=15025539");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=54420313");
		albumUrl.add("https://www.genie.co.kr/detail/albumInfo?axnm=80479080");
	}

	public static Crawler getInstance() { // 싱글톤 객체 만들기
		if (cr == null) {
			cr = new Crawler();
		}
		return cr;
	}

/*********************************************************************************/

	public ArrayList<String> getAlbumUrl() { //getter
		return albumUrl;
	}
	
	public String getAlbumListUrl() {
		return albumListUrl;
	}
	
/*********************************************************************************/
	
	public Document connect(String url) { // 파싱
		try {
			Document document = Jsoup.connect(url).timeout(5000).maxBodySize(0).get();
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] getAlbum(Document doc) { // 앨범 이름 데이터 가져오기
		Elements elements = doc.select(album);
		String[] result = new String[elements.size()];
		for (int i = 0; i < result.length; i++) {
			result[result.length - 1 - i] = elements.get(i).text();
		}
		return result;
	}

	public String[] getTrack(Document doc) { // 트랙 이름 데이터 가져오기
		Elements elements = doc.select(track);
		String[] result = new String[elements.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = elements.get(i).text().replace("?", ""); // 파일명에 "?"가 올 수 없으므로 "?" 제거
		}
		return result;
	}
}
