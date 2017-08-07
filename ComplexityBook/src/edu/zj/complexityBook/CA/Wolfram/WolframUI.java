package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.complexityBook.UI.UIMain;

public class WolframUI{

	public static void main(String[] args) {
		new UIMain(WolframParams.class, WolframMain.class, GridViewParams.class, new Class[]{WolframView.class})
				.start();
	}

}
