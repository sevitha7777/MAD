import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class RMSdemo extends MIDlet implements CommandListener{
	private Display display;
	private TextField name,score;
	private Form home,result;
	private Command save,view,exit,back,del_rec;
	
	private RecordStore recordStore=null;
	static final String REC_STORE = "RMSdemo";

	public RMSdemo(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);

		home=new Form("RMS to store player score");
		
		
		name=new TextField("PlayerName : ","",30,TextField.ANY);
		score=new TextField("Score : ","",30,TextField.ANY);
		home.append(name);
		home.append(score);

		save=new Command("SAVE",Command.OK,1);
		view=new Command("VIEW",Command.OK,2);
		del_rec=new Command("DELETE",Command.OK,3);
		exit=new Command("EXIT",Command.EXIT,1);
		back=new Command("BACK",Command.EXIT,1);


		home.addCommand(save);
		home.addCommand(view);
		home.addCommand(del_rec);
		home.addCommand(exit);
		home.setCommandListener(this);

		createRecordStore();

		display.setCurrent(home);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){
		if(c==save){
			String temp=name.getString()+" ; "+score.getString();
			saveRecord(temp);
		}
		else if(c==view){
			displayRecords();
		}
		else if(c==exit){
			destroyApp(false);
		}
		else if(c==back){
			display.setCurrent(home);
		}
		else if(c==del_rec){
			deleteRecordStore();
		}
	}


	public void createRecordStore(){
		try{
			recordStore = RecordStore.openRecordStore(REC_STORE, true );
		}catch (Exception e){}
	}


	public void saveRecord(String r){
		byte[] record=r.getBytes();
		try{
			recordStore.addRecord(record,0,record.length);
		}catch(Exception e){}
	}


	public void displayRecords(){
		try{
			byte[] recData = new byte[5]; 
			int len;
			result=new Form("View Records");
			for(int i = 1; i <= recordStore.getNumRecords(); i++){
				if(recordStore.getRecordSize(i) > recData.length){
					recData = new byte[recordStore.getRecordSize(i)];
				}
				len = recordStore.getRecord(i, recData, 0);
				String temp = new String(recData, 0, len);
				temp=temp.concat("\n");
				result.append(temp);  
			}
			result.addCommand(back);
			result.setCommandListener(this);
			display.setCurrent(result);
		}catch (Exception e){}
	}
	public void deleteRecordStore(){
		try{
			RecordStore.deleteRecordStore(REC_STORE);
		}catch (Exception e){}	
	}
}
