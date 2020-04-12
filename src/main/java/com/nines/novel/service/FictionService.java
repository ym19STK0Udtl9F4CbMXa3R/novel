package com.nines.novel.service;

import com.nines.novel.entity.Fiction;

/**
 * @ClassName: FictionService
 * @Description: 小说信息Service
 * @author: Nines
 * @date: 2020年04月12日 20:46
 */
public interface FictionService {

    /**
     * 通过ID删除数据
     * @param id ID
     * @return 成功条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过实体类添加
     * @param fiction 实体类
     * @return 操作成功条数
     */
    int insert(Fiction fiction);

    /**
     * 通过实体类添加
     * @param fiction 实体类
     * @return 操作成功条数
     */
    int insertSelective(Fiction fiction);

    /**
     * 通过ID查找
     * @param id ID
     * @return 实体对象
     */
    Fiction selectByPrimaryKey(Long id);

    /**
     * 通过主键更新
     * @param fiction fiction 实体类
     * @return 操作成功条数
     */
    int updateByPrimaryKeySelective(Fiction fiction);

    /**
     * 通过主键更新
     * @param fiction fiction 实体类
     * @return 操作成功条数
     */
    int updateByPrimaryKey(Fiction fiction);

}
