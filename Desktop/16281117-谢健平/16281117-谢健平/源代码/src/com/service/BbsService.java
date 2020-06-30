package com.service;

import java.util.List;
import java.util.Map;

import com.dao.*;

import com.entity.Bbs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("bbs")

public class BbsService {
    @Resource
    private BbsDAO bbsDao;
    // 插入数据 调用entity包bbs.xml里的insertBbs配置
    public void insert(Bbs bbs) {
        bbsDao.insert(  bbs);
    }

    // 更新数据 调用entity包bbs.xml里的updateBbs配置
    public void update(Bbs bbs) {
        bbsDao.update(  bbs);
    }

    // 删除数据 调用entity包bbs.xml里的deleteBbs配置
    public void delete(String bbsid) {
        bbsDao.delete(  bbsid);
    }

    // 查询全部数据 调用entity包bbs.xml里的getAllBbs配置
    public List<Bbs> getAll(Map<String, Object> params) {
        return bbsDao.getAll( params);
    }
    // 查询总行数 调用entity包Bbs.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return bbsDao.getRowSize( params);
    }




    // 按照Admin类里面的值精确查询 调用entity包bbs.xml里的getBbsByCond配置
    public List<Bbs> getByCond(Bbs bbs) {
        return bbsDao.getByCond(  bbs);
    }

    // 按照Bbs类里面的值模糊查询 调用entity包bbs.xml里的getBbsByLike配置
    public List<Bbs> getByLike(Bbs bbs) {
        return bbsDao.getByLike( bbs);
    }

    // 按主键查询表返回单一的Bbs实例 调用entity包bbs.xml里的getBbsById配置
    public Bbs getById(String bbsid) {
        return bbsDao.getById( bbsid);
    }

}
