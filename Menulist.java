import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MenuList extends MIDlet implements CommandListener{
	public Display display;
	public List list;
	public Command select,exit;
	public MenuList(){
		display=Display.getDisplay(this);

		list=new List("MENULIST",List.IMPLICIT);
		list.append("CUT",null);
		list.append("COPY",null);
		list.append("PASTE",null);
		list.append("DELETE",null);
		list.append("SELECT ALL",null);
		list.append("UNSELECT ALL",null);

		select = new Command("SELECT",Command.SCREEN,2);
		exit = new Command("EXIT",Command.EXIT,1);

		list.addCommand(exit);
		list.addCommand(select);
		list.setCommandListener(this);

	}
	public void startApp(){
		display.setCurrent(list);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void showMsg(){
		Alert alert = new Alert("Selected",list.getString(list.getSelectedIndex()),null,null);
		display.setCurrent(alert);
	}

	public void commandAction(Command c,Displayable d){
		String label=c.getLabel();
		if(label.equals("SELECT")){
			showMsg();
		}
		else if(label.equals("EXIT")){
			destroyApp(false);
		}
	}
}
