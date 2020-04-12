package com.nines.novel.service.impl;

import com.nines.novel.dao.FictionMapper;
import com.nines.novel.entity.Fiction;
import com.nines.novel.service.FictionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: FictionServiceImpl
 * @Description: 小说信息实现
 * @author: Nines
 * @date: 2020年04月12日 20:50
 */
@Service
public class FictionServiceImpl implements FictionService {

    @Resource
    FictionMapper fictionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Fiction fiction) {
        return fictionMapper.insert(fiction);
    }

    @Override
    public int insertSelective(Fiction fiction) {
        return 0;
    }

    @Override
    public Fiction selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Fiction fiction) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Fiction fiction) {
        return 0;
    }
}
