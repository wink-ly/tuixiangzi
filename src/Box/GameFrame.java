package Box;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
                                                                
public class GameFrame extends JFrame {								// ���ڽ�����Ϸ����
	private static final long serialVersionUID = 1L;				//ϵͳ�Զ�����
	public  int[][] temp;										//���յ�ͼ����ʱ����
	public  int[][] UNmove;									//��¼���������λ��
	public  JLabel timelabel,MapNolabel,proplabel;
	public  Timer t;
	public  int time=180,min,sec,BackNo=5,propNo=2;										//��ʱ����
	private ImageIcon[] pic;										//ͼ������
	public  int Mapno=0;										//��ǰ��ͼ��
	public  int i,j,bi,bj,choice=2,px=0,py=0;                 //�����ƶ���,�˲������ƶ�����ͨ����־������λ��
	public  int Desti=0,total=0;								//��ǰ��Ŀ�ĵص���������Ŀ�ĵ���
	public  JLabel[][] JLabelGameMap;							//JLabel�����ͼ
	public GameFrame()  {                                           // ��Ϸ���ڹ��췽��
		setFrame();                                                 //������Ϸ����UI
		new NextMap();                                              //�ؿ�������
		this.addKeyListener(new MyKeyListener());					//����¼�������
	}
	private void setFrame(){										//���ý���UI�ķ���
		JLabelGameMap = new JLabel[20][20];							//��ʼ��JLabel����
		this.setLayout(null);										//���ֹ������ÿ�
		this.setTitle("��esc���¿�ʼ�����س��˲�,shfit������ҳ");				//���ý������
		this.setSize(616,677);										//���ý����С
		this.setLocationRelativeTo(null);							//���ý������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//���ý���رշ�ʽ
		timelabel=new JLabel("03:00");
		MapNolabel=new JLabel("�Զ���ؿ�");
		proplabel=new JLabel("ʣ��ڲ���������      ��ʱ���ߣ�����");
		proplabel.setBounds(350, 0, 240, 30);
		MapNolabel.setBounds(0, 0, 100, 30);
		timelabel.setBounds(200, 0, 100, 30);
		t=new Timer();
		this.add(proplabel);
		this.add(MapNolabel);
		this.add(timelabel);
		this.setVisible(true);										//���ý���ɼ�
		
		for (int i = 0; i < 20; i++)								
			for (int j = 0; j < 20; j++) {													
				JLabelGameMap[i][j] = new JLabel();					//ΪJLabel��ֵ
				JLabelGameMap[i][j].setBounds(i* 30, 30+j* 30, 30, 30);//��ÿ��JLabel���õ���Ӧλ��
				this.add(JLabelGameMap[i][j]); 		                //��ÿ��JLabel���봰��
			}
	}
	public class GameTimer extends TimerTask{

		@Override
		public void run() {
			while(time>0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time--;
				min=time/60;
				sec=time%60;
				timelabel.setText(String.format("%02d",min)+":"+String.format("%02d",sec));
				getJFrame().repaint();
			}
		if(time==0){
			timelabel.setText("GameOver");
			int t3 = JOptionPane.YES_NO_OPTION; //�Ի���
			int anwser= JOptionPane.showConfirmDialog(null,"��Ϸ����\n�Ƿ����¿�ʼ��\n�������¿�ʼ       ����˳���Ϸ","GameOver",t3);//����	
			if(anwser==1){
				System.exit(0);     //Ϊ1�˳�
			}
			if(anwser==0){
				Desti=0;
				total=0;
				new NextMap();              //���¿�ʼ
			}
		}
		}
		
	}
	public class NextMap extends MapArray{							//�ؿ�������
		public NextMap()   {										//�ؿ����ƹ��췽��
			new MoveStack();
			time =180;
			min=0;
			sec=0;
			t.schedule(new GameTimer(), 0);
			temp = new int[20][20];									//��ʱ�����ʼ��
			UNmove = new int[20][20];								//�����������ʼ��
			pic=new ImageIcon[7];                                   //ͼ�������ʼ��
			for(int i=0;i<7;i++){									//ͼ�����鸳ֵ
				pic[i]=new ImageIcon("src/MapArray/"+i+".png");
			}
			if(Mapno>3)											
				Mapno=0;
			if(choice==0)											//�������
			   {Mapno++;   											//��ͼ������
			    choice=2;}											//��־��λ
			try {
				getMap(Mapno,temp);                                 //ȡ��Ӧ��ͼ
				getMap(Mapno,UNmove);								//��¼��������
				draw();												//����JLabel����
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 20; i++)		
				for (int j = 0; j < 20; j++) {
					temp[i][j]=0;									//��ʱ�������
				}
			try {													//�ٴ�ȡ��ͼ
				getMap(Mapno,temp);
				getMap(Mapno,UNmove);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Mapno!=0){
			MapNolabel.setText("��"+Mapno+"��");
			proplabel.setText("ʣ��ڲ�����"+BackNo+"      ��ʱ���ߣ�"+propNo);
			}
			draw();
		}
	}
	public JFrame getJFrame(){
		return this;
	}
	public void getPlayerPositoin() {       //��ȡ����λ�ã��������긳ֵ��px��py
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (UNmove[i][j] == 4) {
					px = j;py = i;
				}
			}
	}
	public void draw(){                     //��ͼ����
		getPlayerPositoin();                //��¼�����ʼλ��
	for (int i = 0; i < 20; i++)		
		for (int j = 0; j < 20; j++) {
				JLabelGameMap[i][j].setIcon(pic[temp[i][j]]); //����ͼ��
	        if(UNmove[i][j]==5)
	       	total++;                         //��¼Ŀ�ĵص�����
	   }
	     getJFrame().repaint();              //ˢ��ҳ��
	}
	public class MyKeyListener extends KeyAdapter {  //���̼�����
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_SHIFT){
				getJFrame().setVisible(false);
				Desti=0;
				total=0;
				new MainFrame();
			}
			if(key==KeyEvent.VK_CONTROL)//��1ʹ�õ���
				{
				if(Mapno==0)
					time=time+40;
				if(propNo>=0&&Mapno!=0&&time>0){
				time=time+40;
				propNo--;
				proplabel.setText("ʣ��ڲ�����"+BackNo+"      ��ʱ���ߣ�"+propNo);
				}
			}
			
			
			if (key == KeyEvent.VK_ESCAPE) {
				Desti=0;
				total=0;
				new NextMap();              //���¿�ʼ
				
			}
			if(key==KeyEvent.VK_RIGHT||key == KeyEvent.VK_LEFT||key == KeyEvent.VK_UP||key == KeyEvent.VK_DOWN){
				 MoveStack.push(key);
			if (key == KeyEvent.VK_RIGHT) {
				i = 1;j = 0;				//���м�һ
			} else if (key == KeyEvent.VK_LEFT) {
				i = -1;j = 0;				//���м�һ
			} else if (key == KeyEvent.VK_UP) {
				i = 0;j = -1;				//���м�һ
			} else if (key == KeyEvent.VK_DOWN) {
				i = 0;j = 1;				//���м�һ
			} 
			move();							//�ƶ�
			}
			if(key== KeyEvent.VK_ENTER&&!MoveStack.isEmpty()) //�ڲ�
			{
				if(BackNo>0){
				int back = MoveStack.pop();
				if (back == KeyEvent.VK_LEFT) {
					bi = 1;bj = 0;				//���м�һ
				} else if (back == KeyEvent.VK_RIGHT) {
					bi = -1;bj = 0;				//���м�һ
				} else if (back == KeyEvent.VK_DOWN) {
					bi = 0;bj = -1;				//���м�һ
				} else if (back == KeyEvent.VK_UP) {
					bi = 0;bj = 1;				//���м�һ
				}
				backmove();
			}
			
			i=0;							//���ݸ�λ
			j=0;
			bi=0;
			bj=0;
		}}
	}
	public void backmove()                      //�ڲ�
	//BUG1�����Ӹ�λ��Ͳ������BUG2����BUG1ǰ���£��ص�BUG1����״̬֮�����ֻ��� BUG3���ܳ����س����������
	{
		if(Mapno!=0){
			BackNo--;
			proplabel.setText("ʣ��ڲ�����"+BackNo+"      ��ʱ���ߣ�"+propNo);}//�Զ���ؿ��������� 
		if(((temp[py+bi][px+bj]==0||UNmove[py+bi][px+bj]==5)&&temp[py-bi][px-bj]!=2)||(UNmove[py-bi][px-bj]==2&&temp[py-bi][px-bj]==2)) {  //�����ǰ���ǿյػ���Ŀ�ĵػ��ߺ�����Ȼ�����ӵ������Ѿ��ص���ʼλ�ü����ƶ�
			JLabelGameMap[py+bi][px+bj].setIcon(pic[4]);  //�����ƶ�
			if(UNmove[py][px]!=5)          //�˲���Ŀ�ĵ���
				JLabelGameMap[py][px].setIcon(pic[0]);  //ԭλ�ÿ�
			else  //����Ŀ�ĵ���
				JLabelGameMap[py][px].setIcon(pic[5]);  //ԭλ��Ŀ�ĵ�
			
			temp[py][px]=0;                 //�ı���Ӧ�����ֵ
			temp[py+bi][px+bj]=4;
			
	}
		if(temp[py-bi][px-bj]==2&&UNmove[py-bi][px-bj]!=2) {  			 //����������������Ӳ���ԭλ
			JLabelGameMap[py+bi][px+bj].setIcon(pic[4]);//�����ƶ�
			if(UNmove[py][px]!=5)                //����ǰ�治��Ŀ�ĵ�
				JLabelGameMap[py][px].setIcon(pic[2]); //����ǰ�ƶ�
			else          //����ǰ����Ŀ�ĵ�
				JLabelGameMap[py][px].setIcon(pic[3]); //����ǰ�Ʊ�ɫ
			
			if(UNmove[py-bi][px-bj]==5)//���������Ŀ�ĵ�
					JLabelGameMap[py-bi][px-bj].setIcon(pic[5]);
			else
				JLabelGameMap[py-bi][px-bj].setIcon(pic[0]);
			temp[py][px]=2;
			temp[py+bi][px+bj]=4;
			temp[py-bi][px-bj]=0;
			
		}	
		py=py+bi;             //����λ��ˢ��
		px=px+bj;
		//�ж�����
}
	public void move(){                         //�����Ƅ�
		if(UNmove[py+i][px+j]==1||(temp[py+i][px+j]==2&&temp[py+2*i][px+2*j]==2)||(temp[py+i][px+j]==2&&UNmove[py+2*i][px+2*j]==1))
			return;                         //����                                                                 1. 422��421��41
		
		if((temp[py+i][px+j]==0||UNmove[py+i][px+j]==5)&&temp[py+i][px+j]!=2) {  //���ǰ���ǿյػ���Ŀ�ĵؼ����ƶ�40||45
			temp[py][px]=0;                 //�ı���Ӧ�����ֵ
			temp[py+i][px+j]=4;
			JLabelGameMap[py+i][px+j].setIcon(pic[4]);  //�����ƶ�
			if(UNmove[py][px]!=5) {         //�˲���Ŀ�ĵ���                                                   2. 4��0��0||4(0)5
				JLabelGameMap[py][px].setIcon(pic[0]);  //ԭλ�ÿ�
			}else if(UNmove[py][px]==5) {   //����Ŀ�ĵ���                                                        3. 4��5)0||4(5)5
				JLabelGameMap[py][px].setIcon(pic[5]);  //ԭλ��Ŀ�ĵ�
			}
		}
		if(temp[py+i][px+j]==2) {  			//���ǰ��������42
			if(UNmove[py][px]==5) {         //����Ŀ�ĵ���4(5)2
				JLabelGameMap[py][px].setIcon(pic[5]); //ԭλ��Ŀ�ĵ�
				JLabelGameMap[py+i][px+j].setIcon(pic[4]);//�����ƶ�
				if(UNmove[py+i][px+j]==5) { //���������Ŀ�ĵ���4(5)2(5)
					if(UNmove[py+2*i][px+2*j]==5) { //����ǰ����Ŀ�ĵ�                         4. 4(5)2(5)5
						temp[py+2*i][px+2*j]=2;    //�޸�����ֵ
						temp[py+i][px+j]=4;
						temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]); //�����ƶ���ɫ
					}
					if(temp[py+2*i][px+2*j]==0) { //�������ǰ���ǿյ�                         5.  4(5)2(5)0
						temp[py+2*i][px+2*j]=2;   //�޸�����ֵ
						temp[py+i][px+j]=4;  
						temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]); //�����ƶ�
					}
				}
				if(UNmove[py+i][px+j]!=5) {        //������Ӳ���Ŀ�ĵ�4(5)2(0)
					if(temp[py+2*i][px+2*j]==0)   //����ǰ���ǿյ�                                6. 4(5)2(0))0
						JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]);//�����ƶ�
					if(UNmove[py+2*i][px+2*j]==5)                      //9.4(5)2(0)5
						JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]);//�����ƶ���ɫ
						temp[py+2*i][px+2*j]=2;    //�޸�����ֵ
						temp[py+i][px+j]=4;
						temp[py][px]=0;
				}
			}
			if(UNmove[py][px]!=5) {                //�˲���Ŀ�ĵ�4(0)2
				JLabelGameMap[py][px].setIcon(pic[0]); //ԭλ�ÿ�
				JLabelGameMap[py+i][px+j].setIcon(pic[4]);//�����ƶ�
				
				if(UNmove[py+2*i][px+2*j]==5) {//�������ǰ����Ŀ�ĵ�                                   7. 4(0)25
					temp[py+2*i][px+2*j]=2;//�޸�����
					temp[py+i][px+j]=4;  
					temp[py][px]=0;
				    JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]);//�����ƶ���ɫ
				}
				if(temp[py+2*i][px+2*j]==0) {//����ǰ���ǿյ�                                                    8. 4(0)20
					temp[py+2*i][px+2*j]=2;//�޸�����
					temp[py+i][px+j]=4;
					temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]);//�����ƶ�
				}
			}
		}
		py=py+i;             //����λ��ˢ��
		px=px+j;
		Victory();          //�ж�ʤ��
		Defeat();
}
	public void Defeat(){  						//�ж�����
		int t2 = JOptionPane.YES_NO_OPTION; //�Ի���
		int anwser_ = 2,east,north,west,south;
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++){
				if(temp[i][j]==2&&UNmove[i][j]!=5)
				{
					east=temp[i+1][j];
					west=temp[i-1][j];
					south=temp[i][j+1];
					north=temp[i][j-1];
					if(east==1||west==1)
						if(south==1||north==1)			//�����������濿ǽ�������Ӳ���Ŀ�ĵ�
						anwser_= JOptionPane.showConfirmDialog(null,"�������Ѿ�������\n���س��ڲ�\n���ǻس�       ����˳�","defeat",t2);//����	
				}
			}
		if(anwser_==1)
			System.exit(0);  //���͹ر���Ϸ
		if(anwser_==0)
			backmove();  //���ǻڲ�
	}
	public void  Victory(){  				    //�ж��Ƿ�ͨ��
		int t1 = JOptionPane.YES_NO_OPTION; //�Ի���
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
				if(UNmove[i][j]==5&&JLabelGameMap[i][j].getIcon()==pic[3]){
					Desti++;}       //��������Ŀ�ĵ��ϵ�������
		if(Desti==total/2&&total!=0){         //������������������
			choice=0;               //����
			Desti=0;                //������λ
			total=0;
			propNo++;
			BackNo+=5;
			int anwser= JOptionPane.showConfirmDialog(null,"��ϲͨ������!\n�Ƿ������һ�أ�","����",t1);//����
			if(anwser==1){
				System.exit(0);     //Ϊ1�˳�
			}else if(anwser==0)     //Ϊ0
				new NextMap();      //��һ��
				}
		else
			Desti=0;                 //û���ز�����λ
	}
}
