package com.service;

import com.dao.*;
import com.entity.Users;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("user")

public class UsersService {
    @Resource
    private UsersDAO usersDao;

    // 插入数据 调用entity包users.xml里的insertUsers配置
    public void insert(Users users) {
        usersDao.insert(  users);
    }

    // 更新数据 调用entity包users.xml里的updateUsers配置
    public void update(Users users) {
        usersDao.update(  users);
    }

    // 删除数据 调用entity包users.xml里的deleteUsers配置
    public void delete(String usersid) {
        usersDao.delete(  usersid);
    }

    // 查询全部数据 调用entity包users.xml里的getAllUsers配置
    public List<Users> getAll(Map<String, Object> params) {
        return usersDao.getAll( params);
    }



    // 查询总行数 调用entity包Users.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return usersDao.getRowSize( params);
    }





    // 按照Admin类里面的值精确查询 调用entity包users.xml里的getUsersByCond配置
    public List<Users> getByCond(Users users) {
        return usersDao.getByCond(  users);
    }



    // 按照Users类里面的值模糊查询 调用entity包users.xml里的getUsersByLike配置
    public List<Users> getByLike(Users users) {
        return usersDao.getByLike(  users);
    }

    // 按主键查询表返回单一的Users实例 调用entity包users.xml里的getUsersById配置
    public Users getById(String usersid) {
        return usersDao.getById(  usersid);
    }

}
