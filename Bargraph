import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class BarGraph extends MIDlet implements CommandListener{
	public Display display;
	public Form form;
	public Command submit,exit,back;
	public TextField v1,v2,v3,v4;
	public Displayable dis;

	public BarGraph(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);

		form=new Form("BAR GRAPH");
		v1= new TextField("Enter Value 1","",30,TextField.ANY);
		v2= new TextField("Enter Value 2","",30,TextField.ANY);
		v3= new TextField("Enter Value 3","",30,TextField.ANY);
		v4= new TextField("Enter Value 4","",30,TextField.ANY);
		form.append(v1);
		form.append(v2);
		form.append(v3);
		form.append(v4);

		submit=new Command("SUBMIT",Command.OK,1);
		exit=new Command("EXIT",Command.EXIT,1);

		form.addCommand(submit);
		form.addCommand(exit);
		form.setCommandListener(this);

		display.setCurrent(form);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){
		if(c==submit){
			int[] values= new int[4];
			values[0]=Integer.parseInt(v1.getString());
			values[1]=Integer.parseInt(v2.getString());
			values[2]=Integer.parseInt(v3.getString());
			values[3]=Integer.parseInt(v4.getString());
			
			dis= new BarCanvas(values);
			back = new Command("BACK",Command.OK,1);
			dis.addCommand(back);
			dis.setCommandListener(this);
			display.setCurrent(dis);
		}
		else if(c==back){
			display.setCurrent(form);
		}
		else if(c==exit){
			destroyApp(false);
		}
	}
}

class BarCanvas extends Canvas{
	public int[] values;
	public int x,y,p,q,h,y1;
	public BarCanvas(int[] values){
		this.values=values;
	}
	public void paint(Graphics g){
		g.setColor(255,255,255);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(126,87,194);

		x=10;
		for(int i=0;i<values.length;i++){
			y=values[i];
			h=15*y;
			g.fillRect(x,this.getHeight()-h,30,h);
			x+=35;
		}
	}
}
