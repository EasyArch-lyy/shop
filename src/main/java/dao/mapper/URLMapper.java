package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.URLObject;

/*
* URL映射查询
* */
public interface URLMapper {

    //添加URL记录
    @Insert("insert into urlmapping (objectName,url)values(#{objectName},#{url})")
    public void insertURL(URLObject urlObject);

    @Update("update urlmapping set url=#{url} where url_name=#{objectName}")
    public String updateURL(URLObject urlObject);

    //删除URL记录
    @Delete("delete from urlmapping where objectName=#{objectName}")
    public String deleteURL(URLObject urlObject);

    @Select("select url from urlmapping where objectName=#{objectName}")
    public String selectByURL(URLObject urlObject);
}
