package control;

import view.ScanView;

public class Laucher {

	public static void main(String[] args) {
		try {
			ScanView scanView = new ScanView();
			scanView.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
