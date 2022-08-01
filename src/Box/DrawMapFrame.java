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
	private static final long serialVersionUID = 1L;	//系统自动编号
	public  int mx = 0, my = 0;					//鼠标点击之后转化的行与列，对应数组的行列
	public  JLabel[][] JLabelMap = null;			//JLabel数组，可视JLabel网格地图
	public  int[][] DIYMap;						//临时存放玩家自建地图的数组
	public  int flag = 0;						    //玩家点击放置图标类别的标志
	public  String[] jbName={"墙体","箱子","目的地","玩家","保存","清除","返回"};//按钮名数组
	public DrawMapFrame() {								//地图编辑器的构造方法
		setBackGround();								//设置地图编辑器窗口的基本界面
	}

	public void setBackGround() {						//设置地图编辑器窗口基本界面的方法
		this.setLayout(null);							//将地图编辑器窗口的布局管理器置空
		this.setTitle("地图编辑器（鼠标左键绘图，鼠标右键擦除）");		//设置地图编辑器窗口的标题
		this.setBounds(675, 212, 750, 718);				//设置地图编辑器窗口的大小和位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置地图编辑器窗口的关闭项
		this.setVisible(true);             				//设置地图编辑器窗口可视
		DrawMapPanel();									//添加按钮控制面板
		drawdefaultMap();								//布置基本JLabel网格地图
		this.repaint();									//刷新窗口
		this.addMouseListener(new Mylistener());		//为窗口添加鼠标事件监听器
	}

	public void drawdefaultMap() {						//布置基本地图的方法
		JLabelMap = new JLabel[20][20];					//初始化JLabel数组
		DIYMap = new int[20][20];						//初始化临时数组
		for (int i = 0; i < 20; i++)					//遍历JLabel数组确定它们的位置
			for (int j = 0; j < 20; j++) {
				if(i==0||j==0||i==19||j==19){			//将边缘JLabel置为墙
					JLabelMap[i][j] = new JLabel(new ImageIcon("src/MapArray/1.png"));
					DIYMap[i][j]=1;}					//将临时数组对应值置为墙体的值
				else									//其他置为灰色背景
				{JLabelMap[i][j] = new JLabel(new ImageIcon("src/MapArray/0.png"));
				DIYMap[i][j]=0;}
				JLabelMap[i][j].setBounds((i + 1) * 30+30, (j + 1) * 30 - 10, 30, 30);
				getJFrame().add(JLabelMap[i][j]); 		//将每个JLabel加入窗口
			}
	}

	public void DrawMapPanel() {						//绘制按钮面板的方法
		JPanel DMP = new JPanel();						//建立按钮Panel
		JButton[] jbutton=new JButton[7];				//建立按鈕組
		for(int i=0;i<7;i++){							//遍历按钮组
			jbutton[i]=new JButton(jbName[i]);			//为相应按钮赋值
			jbutton[i].addActionListener(new Mylistener());//为每个按钮添加事件
			jbutton[i].setActionCommand(jbName[i]);		//为每个按钮设置相应标识
			DMP.add(jbutton[i]);						//将每个按钮加入按钮面板
		}	
		DMP.setLayout(new GridLayout(1, 7));			//将面板布局置空
		DMP.setBounds(20, 620, 700, 50);				//设置按钮面板位置和大小
		this.add(DMP);									//将面板加入地图编辑器
		this.repaint();									//刷新界面
	}

	public JFrame getJFrame() {							//获得当前窗口的方法,避免使用参数
		return this;
	}

	public class Mylistener extends MouseAdapter implements ActionListener {   //当前界面所有监听器的类
		public void mouseClicked(MouseEvent e) {                               //鼠标点击事件
			mx = (e.getX() / 30) - 2;                                          //获得鼠标相对位置,减一,减二是实际的修正手段
			my = (e.getY() / 30) - 2;                                          
			if (mx <= 20 && my <= 20 && mx >= 1 && my >= 1) {				   //避免数组越界
				if (e.getButton() == MouseEvent.BUTTON1) {					   //鼠标左击
					JLabelMap[mx][my].setIcon( new ImageIcon("src/MapArray/" + flag + ".png"));    //将其设置为该位置下JLabel的图标
					DIYMap[mx][my] = flag;                                     //修改该位置下的临时数组的值
				}
				if (e.getButton() == MouseEvent.BUTTON3) {                     //鼠标右击
					JLabelMap[mx][my].setIcon(new ImageIcon("src/MapArray/0.png"));                //修改当前位置下的JLabel图标,将其置为背景图标
					DIYMap[mx][my] = 0;                                        //修改该位置下的临时数组的值,将其置为0
				}
			}
			else;                                                              //消除控制台因点到编辑区域以外而一片红
		}

		@Override
		public void actionPerformed(ActionEvent jb)  {                        //重写按钮事件

			if (jb.getActionCommand().equals(jbName[1])) {					  //若为箱子按钮
				flag = 2;                                                     //将图标标志置为数组中箱子的值2
			}
			if (jb.getActionCommand().equals(jbName[0])) {                    //若为墙体按钮
				flag = 1;													  //置为1
			}
			if (jb.getActionCommand().equals(jbName[3])) {					  //若为玩家按钮
				flag = 4;                                                     //置为4
			}
			if (jb.getActionCommand().equals(jbName[2])) {                    //若为目的地按钮
				flag = 5;                                                     //置为5
			}
			if (jb.getActionCommand().equals(jbName[5])) {					  //若为清除按钮
				for (int i = 0; i < 20; i++)					              //将界面地图清空
					for (int j = 0; j < 20; j++){
						JLabelMap[i][j].setIcon(new ImageIcon("src/MapArray/0.png"));
						DIYMap[i][j] = 0;  
					}
			}
			if (jb.getActionCommand().equals(jbName[6])) {                    //若为返回按钮
				getJFrame().setVisible(false);                                //隐藏当前界面
				new MainFrame();                                              //打开首页
			}
			if (jb.getActionCommand().equals(jbName[4])) {                    //若为保存按钮
				try {                                                         //将其存储
					new NewDIYMap();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getJFrame().setVisible(false);								  //将当前页面关闭
				new MainFrame();											  //打开首页面
			}

		}
	public  class NewDIYMap extends MapArray{                                 //通过新建类集成MapArray来用addMap;
			public NewDIYMap() throws IOException {				    
						addMap(DIYMap,0);									  //默认添加到0号地图，可以覆写
				 
			}
		}
	}

}
