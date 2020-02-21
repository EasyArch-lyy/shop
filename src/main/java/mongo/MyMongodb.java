package mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import util.MongoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Demo class
 *
 * @author ls
 * @date 20-1-29
 */

public class MyMongodb {
    private static MongoCollection<Document> collection;

    static {
        collection = MongoUtil.getConnect().getCollection("shop");
    }

    public String insert(String s) {
        HashMap hashMap = new HashMap();
        hashMap = JSON.parseObject(s, HashMap.class);
        Document document = new Document("_id", hashMap.get("_id"))
                .append("goodsname", hashMap.get("goodsname"))
                .append("goodsprice", hashMap.get("goodsprice"))
                .append("goodsnumber", hashMap.get("goodsnumber"))
                .append("goodsid", hashMap.get("goodsid"))
                .append("goodsimgurl", hashMap.get("goodsimgurl"))
                .append("userid", hashMap.get("userid"));
        collection.insertOne(document);
        JSONObject json = new JSONObject();
        json.put("msg", "添加成功");
        return json.toString();
    }

    public String deleteone(String s) {
        HashMap hashMap = new HashMap();
        hashMap = JSON.parseObject(s, HashMap.class);
        collection.deleteOne(eq("_id", hashMap.get("_id")));
        JSONObject json = new JSONObject();
        json.put("msg", "删除成功");
        return json.toString();
    }

    public String deletemany(String s) {
        List<String> list = new ArrayList();
        list = JSON.parseObject(s, List.class);
        for (int i = 0; i < list.size(); i++) {
            collection.deleteOne(eq("_id", list.get(i)));
        }
        JSONObject json = new JSONObject();
        json.put("msg", "结算删除");
        return json.toString();
    }


    public String update(String s) {
        HashMap hashMap = new HashMap();
        hashMap = JSON.parseObject(s, HashMap.class);
        collection.updateOne(
                eq("_id", hashMap.get("_id")),
                combine(set("goodsnumber", hashMap.get("goodsnumber")), set("goodsprice", hashMap.get("goodsprice"))));
        JSONObject json = new JSONObject();
        json.put("msg", "更新成功");
        return json.toString();
    }

    public String select(String s) {
        HashMap hashMap = new HashMap();
        hashMap = JSON.parseObject(s, HashMap.class);
        StringBuilder ss = new StringBuilder();
        ss.append("{");
        FindIterable<Document> documents = collection.find(all("userid", hashMap.get("userid")))
                .sort(Sorts.descending("_id"))
                .projection(fields(include("goodsname", "goodsprice", "goodsimgurl", "goodsnumber", "goodsid"), excludeId()))
                .skip((Integer) hashMap.get("page"))
                .limit(8);
        for (Document document : documents) {
            ss.append(document.toJson() + ",\n");
        }
        ss.append("}");
        return ss.toString();
    }


}


