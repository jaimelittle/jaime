package com.dao;

import java.util.List;
import java.util.Map;


import com.entity.Rebbs;

public interface  RebbsDAO {
// 插入数据 调用entity包rebbs.xml里的insertRebbs配置
    void insert(Rebbs rebbs) ;

    // 更新数据 调用entity包rebbs.xml里的updateRebbs配置
    void update(Rebbs rebbs) ;

    // 删除数据 调用entity包rebbs.xml里的deleteRebbs配置
    void delete(String rebbsid) ;

    // 查询全部数据 调用entity包rebbs.xml里的getAllRebbs配置
    List<Rebbs> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包admin.xml里的配置
    int getRowSize(Map<String, Object> params) ;







    // 按照Admin类里面的值精确查询 调用entity包rebbs.xml里的getRebbsByCond配置
    List<Rebbs> getByCond(Rebbs rebbs) ;

    // 按照Rebbs类里面的值模糊查询 调用entity包rebbs.xml里的getRebbsByLike配置
    List<Rebbs> getByLike(Rebbs rebbs) ;

    // 按主键查询表返回单一的Rebbs实例 调用entity包rebbs.xml里的getRebbsById配置
    Rebbs getById(String rebbsid);

}
