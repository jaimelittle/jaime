package com.dao;

import java.util.List;
import java.util.Map;


import com.entity.Bbs;

public interface  BbsDAO {
// 插入数据 调用entity包bbs.xml里的insertBbs配置
    void insert(Bbs bbs) ;

    // 更新数据 调用entity包bbs.xml里的updateBbs配置
    void update(Bbs bbs);

    // 删除数据 调用entity包bbs.xml里的deleteBbs配置
    void delete(String bbsid);

    // 查询全部数据 调用entity包bbs.xml里的getAllBbs配置
    List<Bbs> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Bbs.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    // 按照Admin类里面的值精确查询 调用entity包bbs.xml里的getBbsByCond配置
    List<Bbs> getByCond(Bbs bbs) ;

    // 按照Bbs类里面的值模糊查询 调用entity包bbs.xml里的getBbsByLike配置
    List<Bbs> getByLike(Bbs bbs) ;
    // 按主键查询表返回单一的Bbs实例 调用entity包bbs.xml里的getBbsById配置
    Bbs getById(String bbsid);

}
