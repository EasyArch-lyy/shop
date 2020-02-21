package controller;

import com.alibaba.fastjson.JSON;
import dao.mapper.MybatisDao;
import json.Tojson;
import oss.UpOSS;
import pojo.Grade;

import java.util.HashMap;
import java.util.Map;
/*
* 同步用户答题记录
* */
public class UpdatePaper {
    /*{
      "user":"xxx",
      "daan": ["A","C","C","C","C","C","C","C","C","C"],
      "paperName": "中小学python",
      "grade": 20
    }*/
    /*"{+
      +'user':'xxx',+
      +'daan': ['A','C','C','C','C','C','C','C','C','C'],+
      +'paperName': '中小学python',+
      +'grade': 20+
    +}"*/
    public static void main(String[] args) {
        String s = "{ 'user':'xxx', 'daan': ['A','C','C','C','C','C','C','C','C','C'], 'paperName': '中小学python', 'grade': 20}";
        UpdatePaper updatePaper = new UpdatePaper();
        updatePaper.Return(s);
    }

    private Map map = null;

    public String Return(String s) {
        UpOSS upOSS = new UpOSS();
        map = new HashMap<String, String>();
        map = JSON.parseObject(s, map.getClass());
        String uid = (String) map.get("user");
        String answer = (String) map.get("daan");
        String pid = (String) map.get("paperName");
        int num = (int) map.get("grade");
        Grade grade = new Grade(uid, pid, num, answer);
        MybatisDao mybatisDao = new MybatisDao();
        mybatisDao.insertGrade(grade);
        return Tojson.getSingleton().to("用户答题记录提交成功");
    }
}
