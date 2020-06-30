package com.service;

import com.dao.ClickDAO;
import com.entity.Click;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("click")

public class ClickService {
    @Resource
    private ClickDAO clickDao;

    // 插入数据 调用entity包click.xml里的insertClick配置
    public void insert(Click click) {
        clickDao.insert(click);
    }

    // 更新数据 调用entity包click.xml里的updateClick配置
    public void update(Click click) {
        clickDao.update(click);
    }

    // 删除数据 调用entity包click.xml里的deleteClick配置
    public void delete(String clickid) {
        clickDao.delete(clickid);
    }

    // 查询全部数据 调用entity包click.xml里的getAllClick配置
    public List<Click> getAll(Map<String, Object> params) {
        return clickDao.getAll(params);
    }

    // 查询总行数 调用entity包Click.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return clickDao.getRowSize(params);
    }


    // 按照Admin类里面的值精确查询 调用entity包click.xml里的getClickByCond配置
    public List<Click> getByCond(Click click) {
        return clickDao.getByCond(click);
    }

    // 按照Click类里面的值模糊查询 调用entity包click.xml里的getClickByLike配置
    public List<Click> getByLike(Click click) {
        return clickDao.getByLike(click);
    }

    // 按主键查询表返回单一的Click实例 调用entity包click.xml里的getClickById配置
    public Click getById(String clickid) {
        return clickDao.getById(clickid);
    }

}
