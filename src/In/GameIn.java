package In;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Box.MainFrame;
import Plane.GameFrame;

@SuppressWarnings("serial")
public class GameIn extends JFrame {
	public GameIn() {
		
		
		JButton[] Game=new JButton[2];
		Game[0]= new JButton("推箱子");
		Game[1]=new JButton("飞机大战");
		Game[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new MainFrame();
			}
		});
		Game[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new GameFrame();
			}
		} );
		JLabel info =new JLabel("欢迎进入游戏");
		JPanel[] GameP=new JPanel[3];
		 GameP[1] =new JPanel();
		 GameP[0] =new JPanel();
		 GameP[2] =new JPanel();
		 this.setLayout(new GridLayout(3,1));
		GameP[2].add(info);
		GameP[0].add(Game[0]);
		GameP[1].add(Game[1]);
		this.add(GameP[2]);
		this.add(GameP[1]);
		this.add(GameP[0]);
		this.setTitle("Double");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameIn();
	}

}
