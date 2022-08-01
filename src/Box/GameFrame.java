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
                                                                
public class GameFrame extends JFrame {								// 用于建立游戏窗口
	private static final long serialVersionUID = 1L;				//系统自动操作
	public  int[][] temp;										//接收地图的临时数组
	public  int[][] UNmove;									//记录不动物体的位置
	public  JLabel timelabel,MapNolabel,proplabel;
	public  Timer t;
	public  int time=180,min,sec,BackNo=5,propNo=2;										//计时参数
	private ImageIcon[] pic;										//图标数组
	public  int Mapno=0;										//当前地图号
	public  int i,j,bi,bj,choice=2,px=0,py=0;                 //行列移动数,退步行列移动数，通过标志，人物位置
	public  int Desti=0,total=0;								//当前在目的地的箱子数，目的地数
	public  JLabel[][] JLabelGameMap;							//JLabel网格地图
	public GameFrame()  {                                           // 游戏窗口构造方法
		setFrame();                                                 //设置游戏界面UI
		new NextMap();                                              //关卡控制器
		this.addKeyListener(new MyKeyListener());					//添加事件监听器
	}
	private void setFrame(){										//设置界面UI的方法
		JLabelGameMap = new JLabel[20][20];							//初始化JLabel数组
		this.setLayout(null);										//布局管理器置空
		this.setTitle("按esc重新开始，按回车退步,shfit返回主页");				//设置界面标题
		this.setSize(616,677);										//设置界面大小
		this.setLocationRelativeTo(null);							//设置界面居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//设置界面关闭方式
		timelabel=new JLabel("03:00");
		MapNolabel=new JLabel("自定义关卡");
		proplabel=new JLabel("剩余悔步数：无限      加时道具：无限");
		proplabel.setBounds(350, 0, 240, 30);
		MapNolabel.setBounds(0, 0, 100, 30);
		timelabel.setBounds(200, 0, 100, 30);
		t=new Timer();
		this.add(proplabel);
		this.add(MapNolabel);
		this.add(timelabel);
		this.setVisible(true);										//设置界面可见
		
		for (int i = 0; i < 20; i++)								
			for (int j = 0; j < 20; j++) {													
				JLabelGameMap[i][j] = new JLabel();					//为JLabel赋值
				JLabelGameMap[i][j].setBounds(i* 30, 30+j* 30, 30, 30);//将每个JLabel设置到对应位置
				this.add(JLabelGameMap[i][j]); 		                //将每个JLabel加入窗口
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
			int t3 = JOptionPane.YES_NO_OPTION; //对话框
			int anwser= JOptionPane.showConfirmDialog(null,"游戏结束\n是否重新开始？\n点是重新开始       点否退出游戏","GameOver",t3);//弹框	
			if(anwser==1){
				System.exit(0);     //为1退出
			}
			if(anwser==0){
				Desti=0;
				total=0;
				new NextMap();              //重新开始
			}
		}
		}
		
	}
	public class NextMap extends MapArray{							//关卡控制类
		public NextMap()   {										//关卡控制构造方法
			new MoveStack();
			time =180;
			min=0;
			sec=0;
			t.schedule(new GameTimer(), 0);
			temp = new int[20][20];									//临时数组初始化
			UNmove = new int[20][20];								//不动物数组初始化
			pic=new ImageIcon[7];                                   //图标数组初始化
			for(int i=0;i<7;i++){									//图标数组赋值
				pic[i]=new ImageIcon("src/MapArray/"+i+".png");
			}
			if(Mapno>3)											
				Mapno=0;
			if(choice==0)											//如果过关
			   {Mapno++;   											//地图号自增
			    choice=2;}											//标志复位
			try {
				getMap(Mapno,temp);                                 //取相应地图
				getMap(Mapno,UNmove);								//记录不动物体
				draw();												//更新JLabel网格
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 20; i++)		
				for (int j = 0; j < 20; j++) {
					temp[i][j]=0;									//临时数组清空
				}
			try {													//再次取地图
				getMap(Mapno,temp);
				getMap(Mapno,UNmove);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Mapno!=0){
			MapNolabel.setText("第"+Mapno+"关");
			proplabel.setText("剩余悔步数："+BackNo+"      加时道具："+propNo);
			}
			draw();
		}
	}
	public JFrame getJFrame(){
		return this;
	}
	public void getPlayerPositoin() {       //获取人物位置，将其坐标赋值给px，py
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (UNmove[i][j] == 4) {
					px = j;py = i;
				}
			}
	}
	public void draw(){                     //绘图方法
		getPlayerPositoin();                //记录人物初始位置
	for (int i = 0; i < 20; i++)		
		for (int j = 0; j < 20; j++) {
				JLabelGameMap[i][j].setIcon(pic[temp[i][j]]); //设置图标
	        if(UNmove[i][j]==5)
	       	total++;                         //记录目的地的总数
	   }
	     getJFrame().repaint();              //刷新页面
	}
	public class MyKeyListener extends KeyAdapter {  //键盘监听器
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_SHIFT){
				getJFrame().setVisible(false);
				Desti=0;
				total=0;
				new MainFrame();
			}
			if(key==KeyEvent.VK_CONTROL)//按1使用道具
				{
				if(Mapno==0)
					time=time+40;
				if(propNo>=0&&Mapno!=0&&time>0){
				time=time+40;
				propNo--;
				proplabel.setText("剩余悔步数："+BackNo+"      加时道具："+propNo);
				}
			}
			
			
			if (key == KeyEvent.VK_ESCAPE) {
				Desti=0;
				total=0;
				new NextMap();              //重新开始
				
			}
			if(key==KeyEvent.VK_RIGHT||key == KeyEvent.VK_LEFT||key == KeyEvent.VK_UP||key == KeyEvent.VK_DOWN){
				 MoveStack.push(key);
			if (key == KeyEvent.VK_RIGHT) {
				i = 1;j = 0;				//右列加一
			} else if (key == KeyEvent.VK_LEFT) {
				i = -1;j = 0;				//左列减一
			} else if (key == KeyEvent.VK_UP) {
				i = 0;j = -1;				//上行加一
			} else if (key == KeyEvent.VK_DOWN) {
				i = 0;j = 1;				//下列加一
			} 
			move();							//移动
			}
			if(key== KeyEvent.VK_ENTER&&!MoveStack.isEmpty()) //悔步
			{
				if(BackNo>0){
				int back = MoveStack.pop();
				if (back == KeyEvent.VK_LEFT) {
					bi = 1;bj = 0;				//右列加一
				} else if (back == KeyEvent.VK_RIGHT) {
					bi = -1;bj = 0;				//左列减一
				} else if (back == KeyEvent.VK_DOWN) {
					bi = 0;bj = -1;				//上行加一
				} else if (back == KeyEvent.VK_UP) {
					bi = 0;bj = 1;				//下列加一
				}
				backmove();
			}
			
			i=0;							//数据复位
			j=0;
			bi=0;
			bj=0;
		}}
	}
	public void backmove()                      //悔步
	//BUG1：箱子复位后就不会回退BUG2：在BUG1前提下，回到BUG1触发状态之后会出现混乱 BUG3不能长按回车键，会出错
	{
		if(Mapno!=0){
			BackNo--;
			proplabel.setText("剩余悔步数："+BackNo+"      加时道具："+propNo);}//自定义关卡不设限制 
		if(((temp[py+bi][px+bj]==0||UNmove[py+bi][px+bj]==5)&&temp[py-bi][px-bj]!=2)||(UNmove[py-bi][px-bj]==2&&temp[py-bi][px-bj]==2)) {  //如果人前面是空地或者目的地或者后面虽然是箱子但箱子已经回到初始位置继续移动
			JLabelGameMap[py+bi][px+bj].setIcon(pic[4]);  //人物移动
			if(UNmove[py][px]!=5)          //人不在目的地上
				JLabelGameMap[py][px].setIcon(pic[0]);  //原位置空
			else  //人在目的地上
				JLabelGameMap[py][px].setIcon(pic[5]);  //原位置目的地
			
			temp[py][px]=0;                 //改变相应数组的值
			temp[py+bi][px+bj]=4;
			
	}
		if(temp[py-bi][px-bj]==2&&UNmove[py-bi][px-bj]!=2) {  			 //如果后面是箱子箱子不在原位
			JLabelGameMap[py+bi][px+bj].setIcon(pic[4]);//人物移动
			if(UNmove[py][px]!=5)                //箱子前面不是目的地
				JLabelGameMap[py][px].setIcon(pic[2]); //箱子前移动
			else          //箱子前面是目的地
				JLabelGameMap[py][px].setIcon(pic[3]); //箱子前移变色
			
			if(UNmove[py-bi][px-bj]==5)//如果箱子在目的地
					JLabelGameMap[py-bi][px-bj].setIcon(pic[5]);
			else
				JLabelGameMap[py-bi][px-bj].setIcon(pic[0]);
			temp[py][px]=2;
			temp[py+bi][px+bj]=4;
			temp[py-bi][px-bj]=0;
			
		}	
		py=py+bi;             //人物位置刷新
		px=px+bj;
		//判断死局
}
	public void move(){                         //控制移動
		if(UNmove[py+i][px+j]==1||(temp[py+i][px+j]==2&&temp[py+2*i][px+2*j]==2)||(temp[py+i][px+j]==2&&UNmove[py+2*i][px+2*j]==1))
			return;                         //不动                                                                 1. 422，421，41
		
		if((temp[py+i][px+j]==0||UNmove[py+i][px+j]==5)&&temp[py+i][px+j]!=2) {  //如果前面是空地或者目的地继续移动40||45
			temp[py][px]=0;                 //改变相应数组的值
			temp[py+i][px+j]=4;
			JLabelGameMap[py+i][px+j].setIcon(pic[4]);  //人物移动
			if(UNmove[py][px]!=5) {         //人不在目的地上                                                   2. 4（0）0||4(0)5
				JLabelGameMap[py][px].setIcon(pic[0]);  //原位置空
			}else if(UNmove[py][px]==5) {   //人在目的地上                                                        3. 4（5)0||4(5)5
				JLabelGameMap[py][px].setIcon(pic[5]);  //原位置目的地
			}
		}
		if(temp[py+i][px+j]==2) {  			//如果前面是箱子42
			if(UNmove[py][px]==5) {         //人在目的地上4(5)2
				JLabelGameMap[py][px].setIcon(pic[5]); //原位置目的地
				JLabelGameMap[py+i][px+j].setIcon(pic[4]);//人物移动
				if(UNmove[py+i][px+j]==5) { //如果箱子在目的地上4(5)2(5)
					if(UNmove[py+2*i][px+2*j]==5) { //箱子前面是目的地                         4. 4(5)2(5)5
						temp[py+2*i][px+2*j]=2;    //修改数组值
						temp[py+i][px+j]=4;
						temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]); //箱子移动变色
					}
					if(temp[py+2*i][px+2*j]==0) { //如果箱子前面是空地                         5.  4(5)2(5)0
						temp[py+2*i][px+2*j]=2;   //修改数组值
						temp[py+i][px+j]=4;  
						temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]); //箱子移动
					}
				}
				if(UNmove[py+i][px+j]!=5) {        //如果箱子不在目的地4(5)2(0)
					if(temp[py+2*i][px+2*j]==0)   //箱子前面是空地                                6. 4(5)2(0))0
						JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]);//箱子移动
					if(UNmove[py+2*i][px+2*j]==5)                      //9.4(5)2(0)5
						JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]);//箱子移动变色
						temp[py+2*i][px+2*j]=2;    //修改数组值
						temp[py+i][px+j]=4;
						temp[py][px]=0;
				}
			}
			if(UNmove[py][px]!=5) {                //人不在目的地4(0)2
				JLabelGameMap[py][px].setIcon(pic[0]); //原位置空
				JLabelGameMap[py+i][px+j].setIcon(pic[4]);//人物移动
				
				if(UNmove[py+2*i][px+2*j]==5) {//如果箱子前面是目的地                                   7. 4(0)25
					temp[py+2*i][px+2*j]=2;//修改数组
					temp[py+i][px+j]=4;  
					temp[py][px]=0;
				    JLabelGameMap[py+2*i][px+2*j].setIcon(pic[3]);//箱子移动变色
				}
				if(temp[py+2*i][px+2*j]==0) {//箱子前面是空地                                                    8. 4(0)20
					temp[py+2*i][px+2*j]=2;//修改数组
					temp[py+i][px+j]=4;
					temp[py][px]=0;
					JLabelGameMap[py+2*i][px+2*j].setIcon(pic[2]);//箱子移动
				}
			}
		}
		py=py+i;             //人物位置刷新
		px=px+j;
		Victory();          //判断胜利
		Defeat();
}
	public void Defeat(){  						//判断死局
		int t2 = JOptionPane.YES_NO_OPTION; //对话框
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
						if(south==1||north==1)			//箱子相邻两面靠墙，且箱子不在目的地
						anwser_= JOptionPane.showConfirmDialog(null,"该箱子已经被堵死\n按回车悔步\n点是回车       点否退出","defeat",t2);//弹框	
				}
			}
		if(anwser_==1)
			System.exit(0);  //点否就关闭游戏
		if(anwser_==0)
			backmove();  //点是悔步
	}
	public void  Victory(){  				    //判断是否通关
		int t1 = JOptionPane.YES_NO_OPTION; //对话框
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
				if(UNmove[i][j]==5&&JLabelGameMap[i][j].getIcon()==pic[3]){
					Desti++;}       //计现在在目的地上的箱子数
		if(Desti==total/2&&total!=0){         //如果其数量和总数相等
			choice=0;               //过关
			Desti=0;                //参数复位
			total=0;
			propNo++;
			BackNo+=5;
			int anwser= JOptionPane.showConfirmDialog(null,"恭喜通过本关!\n是否进入下一关？","过关",t1);//弹框
			if(anwser==1){
				System.exit(0);     //为1退出
			}else if(anwser==0)     //为0
				new NextMap();      //下一关
				}
		else
			Desti=0;                 //没过关参数复位
	}
}
