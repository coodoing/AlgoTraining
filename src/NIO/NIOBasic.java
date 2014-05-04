package NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class NIOBasic {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String infile = "source/source.txt";  
        String outfile = "source/des.txt";  
          
        // ��ȡԴ�ļ���Ŀ���ļ������������  
        FileInputStream fin = new FileInputStream(infile);  
        FileOutputStream fout = new FileOutputStream(outfile);  
  
        // ��ȡ�������ͨ��  
        FileChannel fcin = fin.getChannel();  
        FileChannel fcout = fout.getChannel();  
  
        // ����������  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
  
        while (true) {  
            // clear�������軺������ʹ�����Խ��ܶ��������  
            buffer.clear();  
  
            // ������ͨ���н����ݶ���������  
            int r = fcin.read(buffer);  
  
            // read�������ض�ȡ���ֽ���������Ϊ�㣬�����ͨ���ѵ�������ĩβ���򷵻�-1  
            if (r == -1) {  
                break;  
            }  
  
            // flip�����û��������Խ��¶��������д����һ��ͨ��  
            buffer.flip();  
  
            // �����ͨ���н�����д�뻺����  
            fcout.write(buffer);  
        }  
	}

}
