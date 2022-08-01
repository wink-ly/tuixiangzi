package Box;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import In.GameIn;

public class MainFrame extends JFrame implements KeyListener{ //��ҳ����
	private static final long serialVersionUID = 1L;         
	public  JLabel choiceIcon;	//���ѡ��ʾ����ɫͼ���JLabel
	public MainFrame(){   				//��ҳ��Ĺ��췽��
		choiceIcon();					//����ʾ��ͼ��
		setMainFrameUI();				//������ҳ
		this.addKeyListener(this);		//Ϊ��ҳ��Ӽ��̼�����
	}
	private void choiceIcon() {			//����ʾ��ͼ��ķ���
	    ImageIcon i=new ImageIcon("src/MapArray/4.png");  //ͼ����ɫ
	    choiceIcon=new JLabel(i);	    //��ͼ��Ž�JLabel
	    choiceIcon.setBounds(180, 300, 30, 30);			  //����ͼ�����ʼλ��
	    this.add(choiceIcon);			//��ͼ�������ҳ
	}
	private void setMainFrameUI() {		//������ҳ�ķ���
		    setLayout(null);		
			setSize(585,605);		
			setTitle("������ ��esc���¿�ʼ");	
			setLocationRelativeTo(null);
	        JLabel txt1=new JLabel("��ʼ��Ϸ");
	        JLabel txt2=new JLabel("��ͼ�༭��");
	        txt1.setFont(new Font("΢���ź�",Font.PLAIN,24));
	        txt2.setFont(new Font("΢���ź�",Font.PLAIN,24));
	        txt1.setBounds(260, 262, 200, 100);			 
	        txt2.setBounds(260, 315, 200, 100);			 
	        ImageIcon background = new ImageIcon("src/MapArray/6.png");  
	        JLabel BGJLabel = new JLabel(background);  					 
	        BGJLabel.setBounds(0, 0, this.getWidth(), this.getHeight()); 
	        this.add(txt1);												 
	        this.add(txt2);												 
	        this.add(BGJLabel);											
	        setVisible(true);											
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );			
		}
	@Override
	public void keyPressed(KeyEvent e) {				//��д���̼��������¼����¼��ķ���
		int key=e.getKeyCode();						    //��ȡ���¼��̵ļ���ֵ
		int x=(int)choiceIcon.getLocation().getX();		//��ȡѡ��ʾ��ͼ���ˮƽ����
		int y=(int)choiceIcon.getLocation().getY();		//��ȡѡ��ʾ��ͼ��Ĵ�ֱ����
		if(key == KeyEvent.VK_DOWN) {					//����ѡ����Ϊֻ������ѡ�����Լ�Ϊѡ�����·���ѡ��
			choiceIcon.setBounds(180, 350, 30, 30);}	//��ͼ�������·���ѡ�����							
		if(key == KeyEvent.VK_UP) {						//����ѡ����Ϊֻ������ѡ�����Լ�Ϊѡ�����Ϸ���ѡ��	
		    choiceIcon.setBounds(180, 300, 30, 30);}	//��ͼ�������Ϸ���ѡ�����
		if(key==KeyEvent.VK_ENTER){						//�������ֵΪ�س����ļ���ֵ
			if(x==180&&y==350) {						//���ѡ��ͼ�����·���ѡ��
			  this.setVisible(false);                   //������ҳ����
			  new DrawMapFrame(); }                     //�򿪵�ͼ�༭��
		if(x==180&&y==300) {							//���ѡ��ͼ�����Ϸ���ѡ��
			  this.setVisible(false);                   //���ص�ǰ����
			  new GameFrame();}                         //��ת��Ϸ����
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}
}
