package controller;

import json.Tojson;
import java.util.Map;

/*
* 选课类
* */
public class ChooseCourse {
    private Map map=null;

    public String Return(String s) {

        return Tojson.getSingleton().to("用户答题记录提交成功");
    }
}
