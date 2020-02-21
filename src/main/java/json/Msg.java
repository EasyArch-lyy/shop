package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bson.Document;

import java.util.HashMap;

public class Msg {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //msg处理
    public String msgTostring(String msg){
        HashMap hashMap = new HashMap();
        hashMap = JSON.parseObject(msg, HashMap.class);
        String ans= (String) hashMap.get("answer");
        JSONObject json = new JSONObject();
        json.put("msg", "添加成功");
        return json.toString();
    }
}
