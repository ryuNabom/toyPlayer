package toy;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Screen {
	public static int show1(ImageIcon img, String[] arr) { // 메인 화면
		int result = JOptionPane.showOptionDialog(null, "", "🎵 toy player 🎵", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, img, arr, null);
		return result;
	}

	public static int show2(ImageIcon img, String[] arr) { // 앨범 선택 화면
		int result = JOptionPane.showOptionDialog(null, "", "🎧 앨범 선택 🎧🔊", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, img, arr, null);
		return result;
	}

	public static String show3(String album, String[] arr) { // 트랙 선택 화면
		String result = (String) JOptionPane.showInputDialog(null, "", "🔊 " + album + " 🔊", JOptionPane.PLAIN_MESSAGE,
				new ImageIcon("src/img/album_" + album + ".jpg"), arr, null);
		return result;
	}

	public static int show4(String track, ImageIcon img, String[] arr) { // 재생 화면
		int result = JOptionPane.showOptionDialog(null, "", "🎶 " + track + " 🎶", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, img, arr, null);
		return result;
	}
}



