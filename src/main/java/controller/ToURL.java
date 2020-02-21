package controller;

import dao.mapper.MybatisDao;
import json.Tojson;
import pojo.URLObject;

import java.util.Map;
/*
* 返回URL
* */
public class ToURL {
    private Map map = null;

    public String Return(String s) {
        MybatisDao mybatisDao=new MybatisDao();
        return Tojson.getSingleton().to(mybatisDao.getURL(s));
    }
}
