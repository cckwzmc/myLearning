package com.lottery.ssq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * 逐行读取，以Tab分隔符划分的数据。<br>
 * 使用字节进行处理而不是字符串。<br>
 * 因为发现有些数据，由于特殊字符导致分隔符失效，所以必须在字节一级处理。
 *
 * @author JAVA世纪网(java2000.net, laozizhu.com)
 */
public class SplitFileReader {
  // 文件读取操作
  FileInputStream fis = null;

  // 文件读取的字节缓冲区
  byte[] bs = new byte[1024];

  // 当前数据在缓冲区的位置指针
  int pointer;

  // 保存字节，便于转换为字符串
  // 注意这里最大支持4k的字符串，请根据需要修改
  ByteBuffer bf = ByteBuffer.allocate(4096);

  /**
   * 指定文件名的构造函数
   *
   * @param filename
   * @throws FileNotFoundException
   */
  public SplitFileReader(String filename) throws FileNotFoundException {
    this(new File(filename));
  }

  /**
   * 指定文件的构造函数
   *
   * @param file
   * @throws FileNotFoundException
   */
  public SplitFileReader(File file) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException();
    }
    fis = new FileInputStream(file);
  }

  int len;

  byte b;

  /**
   * 读取一行数据
   *
   * @return 数据分割好的数组
   * @throws IOException
   */
  public List<String> readLine() throws IOException {
    List<String> list = new LinkedList<String>();
    // 读取数据
    while (pointer < len || (readMore() != -1)) {
      b = bs[pointer++];
      if (b == 9 || b == '\n') {
        list.add(new String(bf.array(), 0, bf.position(), "GBK"));
        bf.clear();
        if (b == '\n') {
          return list;
        }
      } else if (b != '\r') {
        bf.put(b);
      }
    }
    return list;
  }

  /**
   * 关闭
   */
  public void close() {
    bs = null;
    bf = null;
    try {
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private int readMore() throws IOException {
    len = fis.read(bs);
    pointer = 0;
    return len;
  }

  // 测试
  public static void main(String[] args) throws Exception {
    SplitFileReader reader = new SplitFileReader("c:/test.txt");
    List<String> list = reader.readLine();
    System.out.println(list);
  }
}