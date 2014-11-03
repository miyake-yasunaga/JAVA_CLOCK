// Anclock.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Anclock extends Frame {

  //Disp�N���X�̃C���X�^���X��
  Disp disp_0 = new Disp();

 public static void main(String args[]) {
  //AncloApp�N���X�̃C���X�^���X��
  new Anclock();
 }

//�R���X�g���N�^
 public Anclock() {
   super("Anclock");
// ��ʏ�̃E�C���h�E�\���ʒu
   setLocation(100,100);
// �E�B���h�E�̃T�C�Y���w��
   setSize(100,150);

//�L�����o�X�̃T�C�Y
   disp_0.setSize(100,150);
//�L�����o�X���t���[���ɒǉ�
   add(disp_0);

// �E�B���h�E��������ɏI������悤�ɐݒ�
  addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {System.exit(0);}
  });
// �E�B���h�E��\��
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

//�R���X�g���N�^
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

   //�͂�
   for (int dsp = 0; dsp < 20; dsp++){
     offscr_g.setColor(new Color(0,255,127));
     //�Z�j�̕`��(����6,�c��6�̗ΐF�œh��Ԃ��ꂽ�~�`�̐}�`��`��)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(hangle*rd)),cy-(int)(dsp*Math.sin(hangle*rd)),6,6);
   }
   for (int dsp = 0; dsp < 25; dsp++){
     offscr_g.setColor(new Color(255,127,255));
     //���j�̕`��(����4,�c��4�̓��F�œh��Ԃ��ꂽ�~�`�̐}�`��`��)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(mangle*rd)),cy-(int)(dsp*Math.sin(mangle*rd)),4,4);
   }
   for (int dsp = 0; dsp < 30; dsp++){
     offscr_g.setColor(new Color(255,0,0));
     //�b�j�̕`��(����4,�c��4�̐ԐF�œh��Ԃ��ꂽ�~�`�̐}�`��`��)
     offscr_g.fillOval(cx+(int)(dsp*Math.cos(sangle*rd)),cy-(int)(dsp*Math.sin(sangle*rd)),2,2);
   }
   //�͂�
   offscr_g.fillOval(40,32,14,10);
   //�Ă���
   offscr_g.setColor(new Color(255,255,255));
   offscr_g.fillOval(43,34,3,2);
   g.drawImage(offscr_img, 0, 0, this);
  }
 }
}
