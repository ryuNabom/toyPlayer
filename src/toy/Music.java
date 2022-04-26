package toy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private static Music music;
	
	private String fileName;
	private Player p;

	private Music(String fileName) { // 노래 제목 설정
		this.fileName = fileName;
	}
	
	public static Music getInstance(String fileName) { // 싱글톤 객체 만들기
		music = new Music(fileName);
		return music;
	}

	public void play() {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis); // 해당 파일을 버퍼에 담아서 가져올 수 있도록 함
			p = new Player(bis); // 해당 파일을 담을 수 있도록 함
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread() {
			public void run() {
				try {
					p.play(); // 곡 재생
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void close() {
		if (p != null)
			p.close(); // 곡 정지
		this.interrupt(); // 해당 스레드 중지 상태로 만듦
	}
}
