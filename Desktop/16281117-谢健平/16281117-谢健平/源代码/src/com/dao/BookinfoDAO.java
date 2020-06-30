package com.dao;

import com.entity.Bookinfo;

import java.util.List;
import java.util.Map;

public interface BookinfoDAO {
// 插入数据 调用entity包bookinfo.xml里的insertBookinfo配置
    void insert(Bookinfo bookinfo) ;

    // 更新数据 调用entity包bookinfo.xml里的updateBookinfo配置
    void update(Bookinfo bookinfo) ;

    // 删除数据 调用entity包bookinfo.xml里的deleteBookinfo配置
    void delete(String bookinfoid) ;

    // 查询全部数据 调用entity包bookinfo.xml里的getAllBookinfo配置
    List<Bookinfo> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Bookinfo.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    // 按照Admin类里面的值精确查询 调用entity包bookinfo.xml里的getBookinfoByCond配置
    List<Bookinfo> getByCond(Bookinfo bookinfo) ;

    // 按照Bookinfo类里面的值模糊查询 调用entity包bookinfo.xml里的getBookinfoByLike配置
    List<Bookinfo> getByLike(Bookinfo bookinfo) ;

    // 按主键查询表返回单一的Bookinfo实例 调用entity包bookinfo.xml里的getBookinfoById配置
    Bookinfo getById(String bookinfoid) ;

}
