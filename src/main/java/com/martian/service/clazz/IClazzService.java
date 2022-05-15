package com.martian.service.clazz;

import com.baomidou.mybatisplus.service.IService;
import com.martian.common.page.PageVo;
import com.martian.dto.clazz.ClazzDto;
import com.martian.dto.clazz.ClazzSearchDto;
import com.martian.entity.clazz.Clazz;

import java.util.List;

/**
 * 班级表 服务类
 */
public interface IClazzService extends IService<Clazz> {
    /**
     * 添加
     *
     * @param clazzDto
     */
    void add(ClazzDto clazzDto);

    /**
     * 修改
     *
     * @param clazzDto
     */
    void update(ClazzDto clazzDto);

    /**
     * 删除
     *
     * @param clazzId
     */
    void del(String clazzId);

    /**
     * 搜索
     *
     * @param clazzSearchDto
     * @return
     */
    PageVo searchClazzVoPage(ClazzSearchDto clazzSearchDto);

    /**
     * 根据院系Id搜索班级
     *
     * @param departId
     * @return
     */
    List<Clazz> searchByDepartId(String departId);

    /**
     * 获取教师授课班级
     *
     * @param teacherId
     * @return
     */
    List<Clazz> getTeacherClazzList(String teacherId);
}
