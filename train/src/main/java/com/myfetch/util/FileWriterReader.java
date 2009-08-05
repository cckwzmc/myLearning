package com.myfetch.util;
import java.io.*;
public class FileWriterReader {
//功能:读取f:/aillo.txt文件的内容(一行一行读),并将其内容写入f:/jackie.txt中
//知识点:java读文件、写文件---<以字符流方式>
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("D:/aillo.txt");//创建FileReader对象，用来读取字符流
            BufferedReader br = new BufferedReader(fr);    //缓冲指定文件的输入
            FileWriter fw = new FileWriter("D:/jackie.txt");//创建FileWriter对象，用来写入字符流
            BufferedWriter bw = new BufferedWriter(fw);    //将缓冲对文件的输出
            String myreadline;    //定义一个String类型的变量,用来每次读取一行
            while (br.ready()) {
                myreadline = br.readLine();//读取一行
                bw.write(myreadline); //写入文件
                bw.newLine();
                System.out.println(myreadline);//在屏幕上输出
            }
            bw.flush();    //刷新该流的缓冲
            bw.close();
            br.close();
            fw.close();
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}