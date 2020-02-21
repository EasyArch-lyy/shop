package controller;

import com.alibaba.fastjson.JSON;
import json.Tojson;
import oss.UpOSS;

import java.util.HashMap;
import java.util.Map;

/*
* 查询历史成绩
* */
public class SelectPaper {

    private Map map=null;

    public String Return(String s) {

        return Tojson.getSingleton().to("用户答题记录提交成功");
    }
}
