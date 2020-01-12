package operation;

import oss.OssCRUD;

import java.io.File;

public class PaperAnswer {

    //将用户答题的答案以json形式写入文件上传到oss
    public void test1(){
        OssCRUD ossCRUD=new OssCRUD();
        File file=new File("/home/lyy/桌面/ue");
        ossCRUD.upload("","");
    }
}
