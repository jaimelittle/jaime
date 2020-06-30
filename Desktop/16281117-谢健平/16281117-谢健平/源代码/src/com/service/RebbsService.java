package com.service;

import java.util.List;
import java.util.Map;

import com.dao.*;

import com.entity.Rebbs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rebbs")

public class RebbsService {
    @Resource
    private RebbsDAO rebbsDao;

    // 插入数据 调用entity包rebbs.xml里的insertRebbs配置
    public void insert(Rebbs rebbs) {
        rebbsDao.insert(  rebbs);
    }

    // 更新数据 调用entity包rebbs.xml里的updateRebbs配置
    public void update(Rebbs rebbs) {
        rebbsDao.update(  rebbs);
    }

    // 删除数据 调用entity包rebbs.xml里的deleteRebbs配置
    public void delete(String rebbsid) {
        rebbsDao.delete(  rebbsid);
    }

    // 查询全部数据 调用entity包rebbs.xml里的getAllRebbs配置
    public List<Rebbs> getAll(Map<String, Object> params) {
        return rebbsDao.getAll( params);
    }
    // 查询总行数 调用entity包admin.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return rebbsDao.getRowSize( params);
    }







    // 按照Admin类里面的值精确查询 调用entity包rebbs.xml里的getRebbsByCond配置
    public List<Rebbs> getByCond(Rebbs rebbs) {
        return rebbsDao.getByCond(  rebbs);
    }

    // 按照Rebbs类里面的值模糊查询 调用entity包rebbs.xml里的getRebbsByLike配置
    public List<Rebbs> getByLike(Rebbs rebbs) {
        return rebbsDao.getByLike(  rebbs);
    }

    // 按主键查询表返回单一的Rebbs实例 调用entity包rebbs.xml里的getRebbsById配置
    public Rebbs getById(String rebbsid) {
        return rebbsDao.getById(  rebbsid);
    }

}
