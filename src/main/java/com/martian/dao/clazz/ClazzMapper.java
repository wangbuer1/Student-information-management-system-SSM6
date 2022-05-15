package com.martian.dao.clazz;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.martian.dto.clazz.ClazzSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.vo.clazz.ClazzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表 Mapper 接口
 */
public interface ClazzMapper extends BaseMapper<Clazz> {
    /**
     * 搜索
     *
     * @param page
     * @param clazzSearchDto
     * @return
     */
    List<ClazzVo> searchClazzVoPage(@Param("page") Page<ClazzVo> page, @Param("clazzSearchDto") ClazzSearchDto clazzSearchDto);

    /**
     * 获取教师授课班级
     *
     * @param teacherId
     * @return
     */
    List<Clazz> getTeacherClazzList(String teacherId);
}
