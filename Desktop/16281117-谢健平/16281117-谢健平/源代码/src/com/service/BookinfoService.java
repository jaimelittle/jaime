package com.service;

import com.dao.BookinfoDAO;
import com.entity.Bookinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("bookinfo")

public class BookinfoService {
    @Resource
    private BookinfoDAO bookinfoDao;

    // 插入数据 调用entity包bookinfo.xml里的insertBookinfo配置
    public void insert(Bookinfo bookinfo) {
        bookinfoDao.insert(  bookinfo);
    }

    // 更新数据 调用entity包bookinfo.xml里的updateBookinfo配置
    public void update(Bookinfo bookinfo) {
        bookinfoDao.update(  bookinfo);
    }

    // 删除数据 调用entity包bookinfo.xml里的deleteBookinfo配置
    public void delete(String bookinfoid) {
        bookinfoDao.delete(  bookinfoid);
    }

    // 查询全部数据 调用entity包bookinfo.xml里的getAllBookinfo配置
    public List<Bookinfo> getAll(Map<String, Object> params) {
        return bookinfoDao.getAll( params);
    }
    // 查询总行数 调用entity包Bookinfo.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return bookinfoDao.getRowSize( params);
    }




    // 按照Admin类里面的值精确查询 调用entity包bookinfo.xml里的getBookinfoByCond配置
    public List<Bookinfo> getByCond(Bookinfo bookinfo) {
        return bookinfoDao.getByCond(  bookinfo);
    }

    // 按照Bookinfo类里面的值模糊查询 调用entity包bookinfo.xml里的getBookinfoByLike配置
    public List<Bookinfo> getByLike(Bookinfo bookinfo) {
        return bookinfoDao.getByLike(  bookinfo);
    }

    // 按主键查询表返回单一的Bookinfo实例 调用entity包bookinfo.xml里的getBookinfoById配置
    public Bookinfo getById(String bookinfoid) {
        return bookinfoDao.getById(  bookinfoid);
    }

}
