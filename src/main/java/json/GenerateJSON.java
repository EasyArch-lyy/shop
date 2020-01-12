package json;

import io.IOTest;
import oss.OssCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
* 生成json类(扫描指定文件夹后生成对应文件)
* */
public class GenerateJSON {

    private static ArrayList<Object> scanFiles = new ArrayList<Object>();
    private static int count=0;

    public static void main(String[] args) {
        GenerateJSON test=new GenerateJSON();
//        String s=test.test1("/home/lyy/jsonFactory/json生成");
        IOTest ioTest=new IOTest();
        OssCRUD ossCRUD=new OssCRUD();
//        ioTest.writeByteToFile(s,"/home/lyy/jsonFactory/json1");

        try {
            List<File> list = test.scanFilesWithRecursion("/home/lyy/jsonFactory/source");
            System.out.println("文件数量:" + scanFiles.size());
            for (int i = 0; i < list.size(); i++) {
                String localfile="/home/lyy/jsonFactory/project/json" + (i+1);
                String nameInbucket="json"+i+1;
                //生成json
                String s = test.test1(String.valueOf(list.get(i)));
                File file = new File(localfile);
                file.createNewFile();
                System.out.println("已创建文件:"+file);
                ioTest.writeByteToFile(s, localfile);
                ossCRUD.upload(localfile,nameInbucket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test3(){
//        long currentTimeMillis = System.currentTimeMillis();
//        scanFilesWithRecursion("D:\\Workspace");
//        System.out.println(scanFiles.size());
//        System.out.println(count);
//        long currentTimeMillis2 = System.currentTimeMillis();
//        System.out.println(currentTimeMillis2-currentTimeMillis);


    }

    //扫描将文件夹中文件加入链表(注释:递归扫描)
    public List<File> scanFilesWithRecursion(String folderPath) throws FileNotFoundException {
        File directory = new File(folderPath);
        List<File>list=new LinkedList<>();
        if (!directory.isDirectory()) {
            throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                /**如果当前是文件夹，进入递归扫描文件夹**/
//                if (filelist[i].isDirectory()) {
//                    /**递归扫描下面的文件夹**/
//                    count++;
//                    scanFilesWithRecursion(filelist[i].getAbsolutePath());
//                }
                /**非文件夹**/
//                else {
                scanFiles.add(filelist[i].getAbsolutePath());
                System.out.println(filelist[i].getAbsolutePath());
//                }
                list.add(filelist[i]);
            }
        }
        return list;
    }


    //输出格式化json
    public String test1(String path/*源文件地址*/){
        String s = null;//全文
        String s1=null;//返回值
        int k=0;// 文章行数
        try {
            //根据%标识符分开文章和全文行数
            String[] s7 = getMessage(path).split("%");
            s = s7[0];//提取全文
            k = Integer.parseInt(s7[1]);//提取行数
            String[] s8 = new String[k];//6个小json的数组
            String[] q = s.split("\n");//根据换行分词
            //获取每行json
            for (int j = 0; j < k; j++) {
                String[] s4 = q[j].split(" ");//中间变量(每行元素)
                s8[j] =
                        "{" +
                                "course_name :" + "\"" + s4[0] + "\"" + "," +
                                "time :"  + s4[1] +"," +
                                "age :" +  s4[2]  +"," +
                                "rurl:" + "\"" + s4[3] + "\"" +"," +
                                "furl:" + "\"" + s4[4] + "\"" +
                                "}";

                if (s1 == null || s1.equals("")) {
                    s1 = s8[j];
                } else {
                    s1 = s1 + ",\n" + s8[j];
                }
            }
            s1="["+s1+"]";
            System.out.println(s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s1;
    }

    String encoding = "UTF-8";
    //传入路径将文章转为bufferRead
    public BufferedReader getModel(String file) {
        File file1 = new File(file);
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        if (file1.isFile() && file1.exists()) {
            try {
                read = new InputStreamReader(new FileInputStream(file), encoding);
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bufferedReader;
    }

    //将整篇信息读出来
    public String getMessage(String filePath) throws IOException {
        String AllMessage = null;
        String firstLine=null;
        int num=1;
        BufferedReader bufferedReader=getModel(filePath);
        try {
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                if (AllMessage == null) {
                    AllMessage = lineTxt;
                    firstLine=AllMessage;
                } else {
                    AllMessage += "\n"+lineTxt;//将换行存入String
                    num++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return AllMessage+"%"+num;//将全文和标识符全部返回
    }

}


