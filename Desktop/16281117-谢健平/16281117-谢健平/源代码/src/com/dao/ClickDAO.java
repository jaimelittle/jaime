package com.dao;

import com.entity.Click;

import java.util.List;
import java.util.Map;

public interface ClickDAO {
// 插入数据 调用entity包click.xml里的insertClick配置
    void insert(Click click) ;
    // 更新数据 调用entity包click.xml里的updateClick配置
    void update(Click click) ;

    // 删除数据 调用entity包click.xml里的deleteClick配置
    void delete(String clickid);

    // 查询全部数据 调用entity包click.xml里的getAllClick配置
    List<Click> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Click.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    // 按照Admin类里面的值精确查询 调用entity包click.xml里的getClickByCond配置
    List<Click> getByCond(Click click) ;

    // 按照Click类里面的值模糊查询 调用entity包click.xml里的getClickByLike配置
    List<Click> getByLike(Click click) ;

    // 按主键查询表返回单一的Click实例 调用entity包click.xml里的getClickById配置
    Click getById(String clickid) ;

}
