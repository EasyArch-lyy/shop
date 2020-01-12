package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.LinkedList;
import java.util.List;

//开启课程页面json
public class PaperJSON {


    private int size;//pageurl数量
    JSONArray jsonArray;
    int d=jsonArray.size();
    //分片(6个一片)
    public List<String[]> test1(){
        List<String[]>list=new LinkedList<String[]>();
        String[] strings=new String[6];
        int j = 0;
        for(int i=0;i<d;i++){
            j=i;
            if(j==6){
                j=0;
                list.add(strings);
                strings=null;
            }
            strings[j]= String.valueOf(jsonArray.get(j));
        }
        return list;
    }
    //增
    public void addSub(String kind, String time, String age, String purl, String vurl, String turl){
        int k=d+1;
        SubjectBean subjectBean=new SubjectBean(k,kind,time,age,purl,vurl,turl);
        String json = JSON.toJSONString(subjectBean);
        jsonArray.add(json);
    }
    //删
    public JSONArray sumSub(int id){
        JSONArray jsonArray1=new JSONArray();
        for(int i=0;i<jsonArray.size();i++){
            if(i==id){
                i+=1;
            }else if(i==d){
                return jsonArray1;
            }
            jsonArray1.add(jsonArray.get(i));
        }
        return jsonArray1;
    }

    //开启页面调用
    public String returnURL(){
        String url=null;

        return url;
    }
}
