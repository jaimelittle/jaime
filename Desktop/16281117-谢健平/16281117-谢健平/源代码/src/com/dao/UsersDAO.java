package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Users;

public interface  UsersDAO {
// 插入数据 调用entity包users.xml里的insertUsers配置
    void insert(Users users) ;

    // 更新数据 调用entity包users.xml里的updateUsers配置
    void update(Users users) ;

    // 删除数据 调用entity包users.xml里的deleteUsers配置
    void delete(String usersid) ;

    // 查询全部数据 调用entity包users.xml里的getAllUsers配置
    List<Users> getAll(Map<String, Object> params) ;



    // 查询总行数 调用entity包Users.xml里的配置
    int getRowSize(Map<String, Object> params);





    // 按照Admin类里面的值精确查询 调用entity包users.xml里的getUsersByCond配置
    List<Users> getByCond(Users users) ;



    // 按照Users类里面的值模糊查询 调用entity包users.xml里的getUsersByLike配置
    List<Users> getByLike(Users users);

    // 按主键查询表返回单一的Users实例 调用entity包users.xml里的getUsersById配置
    Users getById(String usersid);

}
