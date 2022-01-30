import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class P2_1 extends MIDlet{
	private Display display;
	private List list;

	public P2_1(){
		super();
	}
	public void startApp(){

		//2_1 is simply displaying the menu without commands

		display=Display.getDisplay(this);
		list=new List("MENULIST",List.IMPLICIT);
		list.append("cut",null);
		list.append("copy",null);
		list.append("paste",null);
		list.append("delete",null);
		list.append("select_all",null);
		list.append("unselect_all",null);

		display.setCurrent(list);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}
