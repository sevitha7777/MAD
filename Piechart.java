import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class PieChart extends MIDlet implements CommandListener{
	public Display display;
	public Form form;
	public TextField t1,t2,t3,t4;
	public Command submit,exit,back;
	public Displayable dis;

	public PieChart(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);

		t1=new TextField("Enter Value 1 :","",30,TextField.ANY);
		t2=new TextField("Enter Value 2 :","",30,TextField.ANY);
		t3=new TextField("Enter Value 3 :","",30,TextField.ANY);
		t4=new TextField("Enter Value 4 :","",30,TextField.ANY);

		form=new Form("BAR GRAPH");
		form.append(t1);
		form.append(t2);
		form.append(t3);
		form.append(t4);
		
		submit=new Command("SUBMIT",Command.OK,1);
		back=new Command("BACK",Command.OK,1);
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
	public void commandAction(Command c, Displayable d){
		if(c==submit){
			int[] v=new int[4];
			v[0]=Integer.parseInt(t1.getString());
			v[1]=Integer.parseInt(t2.getString());
			v[2]=Integer.parseInt(t3.getString());
			v[3]=Integer.parseInt(t4.getString());

			dis=new PieChartCanvas(v);
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

class PieChartCanvas extends Canvas{
	public int[] v;
	public int x,h;

	public PieChartCanvas(int[] v){
		this.v=v;
	}

	public void paint(Graphics g){
		g.setColor(255,255,255);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		int colors[]={0x008080,0xff0000,0x00ff00,0x0000ff};

		//calculating angles
		int sum=0;
		int[] angles=new int[4];
		for(int i=0;i<v.length;i++){
			sum+=v[i];
		}
		for(int i=0;i<v.length;i++){
			angles[i]=(v[i]*360)/sum; 
		}

		//drawing pie chart
		int startAngle=0;
		for(int i=0;i<v.length;i++){
			g.setColor(colors[i]);
			g.fillArc(20,20,200,200,startAngle,angles[i]);
			startAngle+=angles[i];
		}
	}
}
