package com.nines.novel.dao;

import com.nines.novel.entity.Chapter;

/**
 * @ClassName: FictionMapper
 * @Description: 章节信息DAO
 * @author: Nines
 * @date: 2020年04月12日 20:00
 */
public interface ChapterMapper {

    /**
     * 通过ID删除数据
     * @param id ID
     * @return 成功条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过实体类添加
     * @param chapter 实体类
     * @return 操作成功条数
     */
    int insert(Chapter chapter);

    /**
     * 通过实体类添加
     * @param chapter 实体类
     * @return 操作成功条数
     */
    int insertSelective(Chapter chapter);

    /**
     * 通过ID查找
     * @param id ID
     * @return 实体对象
     */
    Chapter selectByPrimaryKey(Long id);

    /**
     * 通过主键更新
     * @param chapter fiction 实体类
     * @return 操作成功条数
     */
    int updateByPrimaryKeySelective(Chapter chapter);

    /**
     * 通过主键更新带正文
     * @param chapter fiction 实体类
     * @return 操作成功条数
     */
    int updateByPrimaryKeyWithBLOBs(Chapter chapter);

    /**
     * 通过主键更新
     * @param record fiction 实体类
     * @return 操作成功条数
     */
    int updateByPrimaryKey(Chapter record);
}