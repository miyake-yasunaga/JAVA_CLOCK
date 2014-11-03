// Anclock.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Anclock extends Frame {

  //Dispクラスのインスタンス化
  Disp disp_0 = new Disp();

 public static void main(String args[]) {
  //AncloAppクラスのインスタンス化
  new Anclock();
 }

//コンストラクタ
 public Anclock() {
   super("Anclock");
// 画面上のウインドウ表示位置
   setLocation(100,100);
// ウィンドウのサイズを指定
   setSize(100,150);

//キャンバスのサイズ
   disp_0.setSize(100,150);
//キャンバスをフレームに追加
   add(disp_0);

// ウィンドウを閉じた時に終了するように設定
  addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {System.exit(0);}
  });
// ウィンドウを表示
  setVisible(true);
 }
}

class Disp extends Canvas implements Runnable {

 static final int cx=46;
 static final int cy=36;
 double rd = 3.14/180;
 Toolkit tk = getToolkit();

 Image clock_img;
 Image offscr_img = null;
 Graphics offscr_g;
 GregorianCalendar gcal;
 Date dat;
 MediaTracker mt;
 Thread th;

//コンストラクタ
  public Disp() {
    gcal = new GregorianCalendar();
    mt = new MediaTracker(this);
    dat=null;
    clock_img = tk.getImage(getClass().getResource("Anp.jpg"));
    mt.addImage(clock_img,0);
    th = new Thread(this);
    th.start();
  }

 public void run(){
  Thread me = Thread.currentThread();
  try{
   mt.waitForID(0);
  }catch(InterruptedException e){
    }
     while (th == me) {
   repaint();
   try {
    me.sleep(1000);
   } catch (InterruptedException e){
      }
  }
 }
 public void update(Graphics g){
  paint(g);
 }
 public void paint(Graphics g){
  if(mt.checkID(0)){
   int hour,min,sec;
   double hangle,mangle,sangle;
   dat = new Date();
   gcal.setTime(dat);
   hour = (int)gcal.get(Calendar.HOUR_OF_DAY);
   min = (int)gcal.get(Calendar.MINUTE);
   sec = (int)gcal.get(Calendar.SECOND);
   hangle = 90 - hour*30 - 0.5*min;
   mangle = 90 - min*6;
   sangle = 90 - sec*6;
   offscr_img = createImage(100,150);
   offscr_g = offscr_img.getGraphics();
   offscr_g.drawImage(clock_img,0,0,this);

   //はり
   for (int dsp = 0; dsp < 20; dsp++){
     offscr_g.setColor(new Color(0,255,127));
     //短針の描画(横幅6,縦幅6の緑色で塗りつぶされた円形の図形を描く)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(hangle*rd)),cy-(int)(dsp*Math.sin(hangle*rd)),6,6);
   }
   for (int dsp = 0; dsp < 25; dsp++){
     offscr_g.setColor(new Color(255,127,255));
     //長針の描画(横幅4,縦幅4の桃色で塗りつぶされた円形の図形を描く)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(mangle*rd)),cy-(int)(dsp*Math.sin(mangle*rd)),4,4);
   }
   for (int dsp = 0; dsp < 30; dsp++){
     offscr_g.setColor(new Color(255,0,0));
     //秒針の描画(横幅4,縦幅4の赤色で塗りつぶされた円形の図形を描く)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(sangle*rd)),cy-(int)(dsp*Math.sin(sangle*rd)),2,2);
   }
   //はな
   offscr_g.fillOval(40,32,14,10);
   //てかり
   offscr_g.setColor(new Color(255,255,255));
   offscr_g.fillOval(43,34,3,2);
   g.drawImage(offscr_img, 0, 0, this);
  }
 }
}
