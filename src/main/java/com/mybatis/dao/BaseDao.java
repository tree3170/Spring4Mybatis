package com.mybatis.dao;


import com.mybatis.model.User;

import java.util.List;


/**
 * Description. Dao基类，主要是负责操纵数据库增删改查
 * Created on  2015-06-13 下午1:15
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0              下午1:15              Darlen                        create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public interface BaseDao<T> {
    /**
     * 根据主键id获取对象
     *
     * @param id
     * @return
     */
    T getObjByID(Integer id);

    /**
     * 根据名获取对象
     *
     * @param name
     * @return 对象
     */
    T getObjByName(String name);

    /**
     * 获取所有对象
     *
     * @return 对象
     */
    List<T> getAllObjs();

    /**
     * 根据对象插入*所有字段*到db
     *
     * @param record
     * @return 插入结果（数量）
     * @deprecated 已经被删除的方法
     */
    int insertObj(User record);

    /**
     * 根据对象有选择性的只插入*字段不为空*到db
     *
     * @param t  对象
     * @return 插入结果（数量）
     */
    int insertObjSelective(T t);

    /**
     * 根据主键ID查询并有选择性的只更新*字段不为空*到db
     *
     * @param t
     * @return 更新结果（数量）
     */
    int updObjByIDSelective(T t);

    /**
     * 根据主键ID查询并*更新所有字段*到db
     *
     * @param t
     * @return 更新结果（数量）
     * @deprecated  已经被抛弃了的方法
     */
    int updObjByID(T t);

    /**
     * 根据主键ID查询并删除对象
     *
     * @param id
     * @return 删除结果（数量）
     */
    int delObjByID(Integer id);

    /**
     * 根据主键ID查询并删除对象
     * 批量删除ID
     *
     * @param ids
     * @return 删除结果（数量）
     */
    int batchDelObjByIDs(List<String> ids);

}