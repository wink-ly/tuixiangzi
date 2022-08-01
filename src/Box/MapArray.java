package Box;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapArray {												//地图工具类
																	//依据地图号和临时数组添加地图到指定文件夹
	public static  void addMap(int[][] MapArray,int Mapno) throws IOException{		                                         
		 File file = new File("src/MapArray/Array"+Mapno+".txt");   //创建存放数组数据的文件
		 FileWriter out = new FileWriter(file);                     //文件写入流		 
		 for(int i=0;i<20;i++){                                     //将数组中的数据写入到文件中。每行各数据之间TAB间隔		
		   for(int j=0;j<20;j++){
		    out.write(MapArray[j][i]+"\t");
		   }
		   out.write("\r\n");
		  }
		  out.close();
		}
	
	public static  void getMap(int Mapno,int[][] MapArrayTem) throws IOException{                   //依据地图号和临时地图数组获取地图
		BufferedReader in = new BufferedReader(new FileReader("src/MapArray/Array"+Mapno+".txt"));  //按路径读文件
		  String line;                                             //一行数据
		  int row=0;                                               //逐行读取，并将每个数组放入到数组中
		  while((line = in.readLine()) != null){                   //为空不存
		   String[] temp = line.split("\t");                       //根据getMap处的TAB切割
		   for(int j=0;j<temp.length;j++){                         //取数据
		    MapArrayTem[j][row] = Integer.parseInt(temp[j]);
		    }
		   row++;
		   }
		  in.close();
	}
}
