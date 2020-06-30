package com.service;

import java.util.List;
import java.util.Map;

import com.dao.*;

import com.entity.Catalog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("catalog")

public class CatalogService {
    @Resource
    private CatalogDAO catalogDao;

    // 插入数据 调用entity包catalog.xml里的insertCatalog配置
    public void insert(Catalog catalog) {
        catalogDao.insert(  catalog);
    }

    // 更新数据 调用entity包catalog.xml里的updateCatalog配置
    public void update(Catalog catalog) {
        catalogDao.update(  catalog);
    }

    // 删除数据 调用entity包catalog.xml里的deleteCatalog配置
    public void delete(String catalogid) {
        catalogDao.delete(  catalogid);
    }

    // 查询全部数据 调用entity包catalog.xml里的getAllCatalog配置
    public List<Catalog> getAll(Map<String, Object> params) {
        return catalogDao.getAll( params);
    }
    // 查询总行数 调用entity包Catalog.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return catalogDao.getRowSize( params);
    }




    // 按照Admin类里面的值精确查询 调用entity包catalog.xml里的getCatalogByCond配置
    public List<Catalog> getByCond(Catalog catalog) {
        return catalogDao.getByCond(  catalog);
    }

    // 按照Catalog类里面的值模糊查询 调用entity包catalog.xml里的getCatalogByLike配置
    public List<Catalog> getByLike(Catalog catalog) {
        return catalogDao.getByLike(  catalog);
    }

    // 按主键查询表返回单一的Catalog实例 调用entity包catalog.xml里的getCatalogById配置
    public Catalog getById(String catalogid) {
        return catalogDao.getById(  catalogid);
    }

}
