package com.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
	//本地测试
	public static void main(String[] args) {
//		FileWriter(new Score("aa",4));
//		FileWriter(new Score("ab",5));
//		FileWriter(new Score("ac",2));
		FileReader();
	}

	//从文件中读取玩家历史数据并打印最高分和最高纪录者
	public static void FileReader(){
		FileReader fr = null;
		BufferedReader br = null;
		String tempString = "";
		try {
			fr = new FileReader("d:/score.txt");
			br = new BufferedReader(fr);
			String highestPlayer="";
			int highestScore=0;
			while((tempString=br.readLine())!=null){
//				System.out.println(tempString);
				String[] str=tempString.split(" ");
				/*for (String string : str) {
					System.out.println(string);
				}*/
				int sc=Integer.parseInt(str[1]);
				if(sc>highestScore){
					highestPlayer=str[0];
					highestScore=sc;
				}
			}
			System.out.println("游戏最高分是"+highestScore+" ，玩家名"+highestPlayer);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//将玩家信息写入文件
	public static void FileWriter(Score score){
		FileWriter fw = null;
		BufferedWriter bw = null;
		String tempString = score.getPalyer().trim()+" "+score.getScore();
		try {
			fw = new FileWriter("d:/score.txt",true);
			bw = new BufferedWriter(fw);
			bw.write(tempString);
			bw.newLine();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
