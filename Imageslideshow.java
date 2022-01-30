import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SlideShowImages extends MIDlet implements CommandListener{
    private Display display;
    private Form slide1,slide2,slide3;
    private Command exit;
    private Image i1,i2,i3;
    private ImageItem t1,t2,t3;

    public SlideShowImages(){
        super();
    }
    public void startApp(){
        display=Display.getDisplay(this);
        //images and image items
        try{
            i1=Image.createImage("/1.png");  //place the images in res folder
            i2=Image.createImage("/2.png");
            i3=Image.createImage("/3.png");
        }catch(Exception e){}

        t1=new ImageItem("IMAGE1",i1,ImageItem.LAYOUT_CENTER,"image1");
        t2=new ImageItem("IMAGE2",i2,ImageItem.LAYOUT_CENTER,"image2");
        t3=new ImageItem("IMAGE3",i3,ImageItem.LAYOUT_CENTER,"image3");

        //Forms
        slide1=new Form("Slide1");
        slide2=new Form("Slide2");
        slide3=new Form("Slide3");
        slide1.append(t1);
        slide2.append(t2);
        slide3.append(t3);
        
        //adding commands to forms
        exit=new Command("EXIT",Command.EXIT,1);
        slide1.addCommand(exit);
        slide2.addCommand(exit);
        slide3.addCommand(exit);
        slide1.setCommandListener(this);
        slide2.setCommandListener(this);
        slide3.setCommandListener(this);

        Thread runner = new Thread(new ThreadRunner(display,slide1,slide2,slide3));
        runner.start();
    }
    public void pauseApp(){

    }
    public void destroyApp(boolean unconditional){
        notifyDestroyed();
    }

    public void commandAction(Command c,Displayable d){
        if(c==exit){
            destroyApp(false);
        }
    }
}
class ThreadRunner implements Runnable{
    
    public Display display;
    public Form slide1;
    public Form slide2;
    public Form slide3;

    public ThreadRunner(Display display,Form slide1,Form slide2,Form slide3){
        this.display=display;
        this.slide1=slide1;
        this.slide2=slide2;
        this.slide3=slide3;    
    }
    public void run(){
        int c=0;
        while(true){
            c++;
            if(c==1) display.setCurrent(slide1);
            else if(c==2) display.setCurrent(slide2);
            else if(c==3) display.setCurrent(slide3);
            else c=0;
            try{
            Thread.sleep(5000);
            }
            catch(Exception ex){

            }
        }
    }
}
