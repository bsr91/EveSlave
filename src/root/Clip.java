package root;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Clip {
	public static void put(String s){
		Toolkit tk=Toolkit.getDefaultToolkit();
		Clipboard c=tk.getSystemClipboard();
		c.setContents(new StringSelection(s), null);
		tk.beep();
	}
}
