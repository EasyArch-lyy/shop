package io;

import java.io.*;

public class IOTest {

    public static void main(String[] args) {

    }
   /*
    * 字节流
    */

    //用字节流写文件
    public void writeByteToFile(String s,String path){
        byte[] byteArray= s.getBytes();
        File file= new File(path);
        //因为是用字节流来写媒介，所以对应的是OutputStream
        //又因为媒介对象是文件，所以用到子类是FileOutputStream
        OutputStream os= null;
        try {
            os = new FileOutputStream( file);
            os.write( byteArray);
            System.out.println("写入成功");
            System.out.println("写入地址: "+path);
            System.out.println("写入内容: "+s);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用字节流读文件
    public String readByteFromFile(String path) throws IOException{
        File file= new File( path);
        byte[] byteArray= new byte[( int) file.length()];
        //因为是用字节流来读媒介，所以对应的是InputStream
        //又因为媒介对象是文件，所以用到子类是FileInputStream
        InputStream is= new FileInputStream( file);
        int size= is.read( byteArray);
        String s=new String(byteArray);
        System. out.println( "大小: "+size +"\n"+";内容: "+s);
        is.close();
        //返回读取的文件
        return s;
    }

    /*
     * 字符流,符流对应的类应该是Reader和Writer
     */

    //用字符流读文件
    public String readCharFromFile(String path) throws IOException{
        File file = new File(path);
        //因为是用字符流来读媒介，所以对应的是Reader
        //又因为媒介对象是文件，所以用到子类是FileReader
        Reader reader = new FileReader(file);
        char[] byteArray = new char[(int) file.length()];
        int size= reader.read( byteArray);
        String s=new String(byteArray);
        System.out.println("大小: "+size +";内容:" +s);
        reader.close();
        return s;
    }

    //用字符流写文件
    public void writeCharToFile(String path,String s) throws IOException{
        String hello = s;
        File file = new File(path);
        //因为是用字符流来读媒介，所以对应的是Writer，又因为媒介对象是文件，所以用到子类是FileWriter
        Writer os = new FileWriter(file);
        os.write( hello);
        System.out.println("写入成功");
        System.out.println("写入路径: "+path);
        System.out.println("写入内容: "+s);
        os.close();
    }

    /*
     * 字节流转换为字符流
     */

    //文件中字节流转换为缓存中字符流
    public void convertByteToChar(String path) throws IOException{
        File file = new File(path);
        //获得一个字节流
        InputStream is = new FileInputStream(file);
        //把字节流转换为字符流，其实就是把字符流和字节流组合的结果。
        Reader reader = new InputStreamReader(is);
        char[] byteArray = new char[(int) file.length()];
        int size = reader.read(byteArray);
        System. out.println( "大小: "+size +";内容: " +new String(byteArray));
        is.close();
        reader.close();
    }
}
