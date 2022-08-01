package Box;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapArray {												//��ͼ������
																	//���ݵ�ͼ�ź���ʱ������ӵ�ͼ��ָ���ļ���
	public static  void addMap(int[][] MapArray,int Mapno) throws IOException{		                                         
		 File file = new File("src/MapArray/Array"+Mapno+".txt");   //��������������ݵ��ļ�
		 FileWriter out = new FileWriter(file);                     //�ļ�д����		 
		 for(int i=0;i<20;i++){                                     //�������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���		
		   for(int j=0;j<20;j++){
		    out.write(MapArray[j][i]+"\t");
		   }
		   out.write("\r\n");
		  }
		  out.close();
		}
	
	public static  void getMap(int Mapno,int[][] MapArrayTem) throws IOException{                   //���ݵ�ͼ�ź���ʱ��ͼ�����ȡ��ͼ
		BufferedReader in = new BufferedReader(new FileReader("src/MapArray/Array"+Mapno+".txt"));  //��·�����ļ�
		  String line;                                             //һ������
		  int row=0;                                               //���ж�ȡ������ÿ��������뵽������
		  while((line = in.readLine()) != null){                   //Ϊ�ղ���
		   String[] temp = line.split("\t");                       //����getMap����TAB�и�
		   for(int j=0;j<temp.length;j++){                         //ȡ����
		    MapArrayTem[j][row] = Integer.parseInt(temp[j]);
		    }
		   row++;
		   }
		  in.close();
	}
}
