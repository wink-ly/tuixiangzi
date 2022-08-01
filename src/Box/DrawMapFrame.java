package Box;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class DrawMapFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	//ϵͳ�Զ����
	public  int mx = 0, my = 0;					//�����֮��ת���������У���Ӧ���������
	public  JLabel[][] JLabelMap = null;			//JLabel���飬����JLabel�����ͼ
	public  int[][] DIYMap;						//��ʱ�������Խ���ͼ������
	public  int flag = 0;						    //��ҵ������ͼ�����ı�־
	public  String[] jbName={"ǽ��","����","Ŀ�ĵ�","���","����","���","����"};//��ť������
	public DrawMapFrame() {								//��ͼ�༭���Ĺ��췽��
		setBackGround();								//���õ�ͼ�༭�����ڵĻ�������
	}

	public void setBackGround() {						//���õ�ͼ�༭�����ڻ�������ķ���
		this.setLayout(null);							//����ͼ�༭�����ڵĲ��ֹ������ÿ�
		this.setTitle("��ͼ�༭������������ͼ������Ҽ�������");		//���õ�ͼ�༭�����ڵı���
		this.setBounds(675, 212, 750, 718);				//���õ�ͼ�༭�����ڵĴ�С��λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���õ�ͼ�༭�����ڵĹر���
		this.setVisible(true);             				//���õ�ͼ�༭�����ڿ���
		DrawMapPanel();									//��Ӱ�ť�������
		drawdefaultMap();								//���û���JLabel�����ͼ
		this.repaint();									//ˢ�´���
		this.addMouseListener(new Mylistener());		//Ϊ�����������¼�������
	}

	public void drawdefaultMap() {						//���û�����ͼ�ķ���
		JLabelMap = new JLabel[20][20];					//��ʼ��JLabel����
		DIYMap = new int[20][20];						//��ʼ����ʱ����
		for (int i = 0; i < 20; i++)					//����JLabel����ȷ�����ǵ�λ��
			for (int j = 0; j < 20; j++) {
				if(i==0||j==0||i==19||j==19){			//����ԵJLabel��Ϊǽ
					JLabelMap[i][j] = new JLabel(new ImageIcon("src/MapArray/1.png"));
					DIYMap[i][j]=1;}					//����ʱ�����Ӧֵ��Ϊǽ���ֵ
				else									//������Ϊ��ɫ����
				{JLabelMap[i][j] = new JLabel(new ImageIcon("src/MapArray/0.png"));
				DIYMap[i][j]=0;}
				JLabelMap[i][j].setBounds((i + 1) * 30+30, (j + 1) * 30 - 10, 30, 30);
				getJFrame().add(JLabelMap[i][j]); 		//��ÿ��JLabel���봰��
			}
	}

	public void DrawMapPanel() {						//���ư�ť���ķ���
		JPanel DMP = new JPanel();						//������ťPanel
		JButton[] jbutton=new JButton[7];				//�������o�M
		for(int i=0;i<7;i++){							//������ť��
			jbutton[i]=new JButton(jbName[i]);			//Ϊ��Ӧ��ť��ֵ
			jbutton[i].addActionListener(new Mylistener());//Ϊÿ����ť����¼�
			jbutton[i].setActionCommand(jbName[i]);		//Ϊÿ����ť������Ӧ��ʶ
			DMP.add(jbutton[i]);						//��ÿ����ť���밴ť���
		}	
		DMP.setLayout(new GridLayout(1, 7));			//����岼���ÿ�
		DMP.setBounds(20, 620, 700, 50);				//���ð�ť���λ�úʹ�С
		this.add(DMP);									//���������ͼ�༭��
		this.repaint();									//ˢ�½���
	}

	public JFrame getJFrame() {							//��õ�ǰ���ڵķ���,����ʹ�ò���
		return this;
	}

	public class Mylistener extends MouseAdapter implements ActionListener {   //��ǰ�������м���������
		public void mouseClicked(MouseEvent e) {                               //������¼�
			mx = (e.getX() / 30) - 2;                                          //���������λ��,��һ,������ʵ�ʵ������ֶ�
			my = (e.getY() / 30) - 2;                                          
			if (mx <= 20 && my <= 20 && mx >= 1 && my >= 1) {				   //��������Խ��
				if (e.getButton() == MouseEvent.BUTTON1) {					   //������
					JLabelMap[mx][my].setIcon( new ImageIcon("src/MapArray/" + flag + ".png"));    //��������Ϊ��λ����JLabel��ͼ��
					DIYMap[mx][my] = flag;                                     //�޸ĸ�λ���µ���ʱ�����ֵ
				}
				if (e.getButton() == MouseEvent.BUTTON3) {                     //����һ�
					JLabelMap[mx][my].setIcon(new ImageIcon("src/MapArray/0.png"));                //�޸ĵ�ǰλ���µ�JLabelͼ��,������Ϊ����ͼ��
					DIYMap[mx][my] = 0;                                        //�޸ĸ�λ���µ���ʱ�����ֵ,������Ϊ0
				}
			}
			else;                                                              //��������̨��㵽�༭���������һƬ��
		}

		@Override
		public void actionPerformed(ActionEvent jb)  {                        //��д��ť�¼�

			if (jb.getActionCommand().equals(jbName[1])) {					  //��Ϊ���Ӱ�ť
				flag = 2;                                                     //��ͼ���־��Ϊ���������ӵ�ֵ2
			}
			if (jb.getActionCommand().equals(jbName[0])) {                    //��Ϊǽ�尴ť
				flag = 1;													  //��Ϊ1
			}
			if (jb.getActionCommand().equals(jbName[3])) {					  //��Ϊ��Ұ�ť
				flag = 4;                                                     //��Ϊ4
			}
			if (jb.getActionCommand().equals(jbName[2])) {                    //��ΪĿ�ĵذ�ť
				flag = 5;                                                     //��Ϊ5
			}
			if (jb.getActionCommand().equals(jbName[5])) {					  //��Ϊ�����ť
				for (int i = 0; i < 20; i++)					              //�������ͼ���
					for (int j = 0; j < 20; j++){
						JLabelMap[i][j].setIcon(new ImageIcon("src/MapArray/0.png"));
						DIYMap[i][j] = 0;  
					}
			}
			if (jb.getActionCommand().equals(jbName[6])) {                    //��Ϊ���ذ�ť
				getJFrame().setVisible(false);                                //���ص�ǰ����
				new MainFrame();                                              //����ҳ
			}
			if (jb.getActionCommand().equals(jbName[4])) {                    //��Ϊ���水ť
				try {                                                         //����洢
					new NewDIYMap();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getJFrame().setVisible(false);								  //����ǰҳ��ر�
				new MainFrame();											  //����ҳ��
			}

		}
	public  class NewDIYMap extends MapArray{                                 //ͨ���½��༯��MapArray����addMap;
			public NewDIYMap() throws IOException {				    
						addMap(DIYMap,0);									  //Ĭ����ӵ�0�ŵ�ͼ�����Ը�д
				 
			}
		}
	}

}
