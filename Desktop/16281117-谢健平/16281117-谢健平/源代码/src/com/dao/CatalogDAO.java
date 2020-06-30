package com.dao;

import java.util.List;
import java.util.Map;


import com.entity.Catalog;

public interface CatalogDAO {
// 插入数据 调用entity包catalog.xml里的insertCatalog配置
    void insert(Catalog catalog) ;

    // 更新数据 调用entity包catalog.xml里的updateCatalog配置
    void update(Catalog catalog) ;

    // 删除数据 调用entity包catalog.xml里的deleteCatalog配置
    void delete(String catalogid) ;

    // 查询全部数据 调用entity包catalog.xml里的getAllCatalog配置
    List<Catalog> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Catalog.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    // 按照Admin类里面的值精确查询 调用entity包catalog.xml里的getCatalogByCond配置
    List<Catalog> getByCond(Catalog catalog) ;

    // 按照Catalog类里面的值模糊查询 调用entity包catalog.xml里的getCatalogByLike配置
    List<Catalog> getByLike(Catalog catalog) ;

    // 按主键查询表返回单一的Catalog实例 调用entity包catalog.xml里的getCatalogById配置
    Catalog getById(String catalogid) ;

}
