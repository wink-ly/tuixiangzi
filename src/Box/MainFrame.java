package Box;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import In.GameIn;

public class MainFrame extends JFrame implements KeyListener{ //首页界面
	private static final long serialVersionUID = 1L;         
	public  JLabel choiceIcon;	//存放选择示意蓝色图标的JLabel
	public MainFrame(){   				//首页面的构造方法
		choiceIcon();					//建造示意图标
		setMainFrameUI();				//设置首页
		this.addKeyListener(this);		//为首页添加键盘监听器
	}
	private void choiceIcon() {			//建造示意图标的方法
	    ImageIcon i=new ImageIcon("src/MapArray/4.png");  //图标蓝色
	    choiceIcon=new JLabel(i);	    //将图标放进JLabel
	    choiceIcon.setBounds(180, 300, 30, 30);			  //设置图标的起始位置
	    this.add(choiceIcon);			//将图标加入首页
	}
	private void setMainFrameUI() {		//设置首页的方法
		    setLayout(null);		
			setSize(585,605);		
			setTitle("推箱子 按esc重新开始");	
			setLocationRelativeTo(null);
	        JLabel txt1=new JLabel("开始游戏");
	        JLabel txt2=new JLabel("地图编辑器");
	        txt1.setFont(new Font("微软雅黑",Font.PLAIN,24));
	        txt2.setFont(new Font("微软雅黑",Font.PLAIN,24));
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
	public void keyPressed(KeyEvent e) {				//重写键盘监听器按下键盘事件的方法
		int key=e.getKeyCode();						    //获取按下键盘的键码值
		int x=(int)choiceIcon.getLocation().getX();		//获取选项示意图标的水平坐标
		int y=(int)choiceIcon.getLocation().getY();		//获取选项示意图标的垂直坐标
		if(key == KeyEvent.VK_DOWN) {					//向下选择，因为只有两个选项所以即为选择了下方的选项
			choiceIcon.setBounds(180, 350, 30, 30);}	//将图标置于下方的选项左侧							
		if(key == KeyEvent.VK_UP) {						//向上选择，因为只有两个选项所以即为选择了上方的选项	
		    choiceIcon.setBounds(180, 300, 30, 30);}	//将图标置于上方的选项左侧
		if(key==KeyEvent.VK_ENTER){						//如果键码值为回车键的键码值
			if(x==180&&y==350) {						//如果选项图标在下方的选项
			  this.setVisible(false);                   //隐藏首页窗口
			  new DrawMapFrame(); }                     //打开地图编辑器
		if(x==180&&y==300) {							//如果选项图标在上方的选项
			  this.setVisible(false);                   //隐藏当前窗口
			  new GameFrame();}                         //跳转游戏界面
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
