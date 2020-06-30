package com.service;

import java.util.List;
import java.util.Map;

import com.dao.*;
import com.entity.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("admin")
public class AdminService {

    @Resource
    private AdminDAO adminDao;

    // 插入数据 调用dAO里的insertAdmin配置
    public void insert(Admin admin) {
          adminDao.insert(  admin);
    }

    // 更新数据 调用dAO里的updateAdmin配置
    public void update(Admin admin) {
        adminDao.update(  admin);
    }

    // 删除数据 调用dAO里的deleteAdmin配置
    public void delete(String adminid) {
        adminDao.delete(  adminid);
    }

    // 查询全部数据 调用dAO里的getAllAdmin配置
    public List<Admin> getAll(Map<String, Object> params) {
        return adminDao.getAll( params);
    }

    // 查询总行数 调用dAO里的配置
    public int getRowSize(Map<String, Object> params) {
        return adminDao.getRowSize( params);
    }






    // 按照Admin类里面的值精确查询 调用dAO里的getAdminByCond配置
    public List<Admin> getByCond(Admin admin) {
        return adminDao.getByCond(  admin);
    }

    // 按照Admin类里面的值模糊查询 调用dAO里的getAdminByLike配置
    public List<Admin> getByLike(Admin admin) {
        return adminDao.getByLike( admin);
    }

    // 按主键查询表返回单一的Admin实例 调用dAO里的getAdminById配置
    public Admin getById(String adminid) {
        return adminDao.getById( adminid);
    }




}
