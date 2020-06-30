package com.dao;

import java.util.List;


import com.entity.Admin;
import java.util.Map;

public interface AdminDAO {

    // 插入数据 调用entity包admin.xml里的insertAdmin配置
      void insert(Admin admin) ;

    // 更新数据 调用entity包admin.xml里的updateAdmin配置
      void update(Admin admin);

    // 删除数据 调用entity包admin.xml里的deleteAdmin配置
     void delete(String adminid) ;

    // 查询全部数据 调用entity包admin.xml里的getAllAdmin配置
      List<Admin> getAll(Map<String, Object> params)  ;

    // 查询总行数 调用entity包admin.xml里的配置
      int getRowSize(Map<String, Object> params) ;






    // 按照Admin类里面的值精确查询 调用entity包admin.xml里的getAdminByCond配置
     List<Admin> getByCond(Admin admin) ;

    // 按照Admin类里面的值模糊查询 调用entity包admin.xml里的getAdminByLike配置
     List<Admin> getByLike(Admin admin) ;

    // 按主键查询表返回单一的Admin实例 调用entity包admin.xml里的getAdminById配置
     Admin getById(String adminid);



}
