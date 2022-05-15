package com.martian.service.depart;

import com.baomidou.mybatisplus.service.IService;
import com.martian.dto.depart.DepartDto;
import com.martian.entity.depart.Depart;

import java.util.List;

/**
 * 院系表 服务类
 */
public interface IDepartService extends IService<Depart> {
    /**
     * 添加
     *
     * @param departDto
     */
    void add(DepartDto departDto);

    /**
     * 修改
     *
     * @param departDto
     */
    void update(DepartDto departDto);

    /**
     * 删除
     *
     * @param departId
     */
    void del(String departId);

    /**
     * 获取列表
     *
     * @return
     */
    List<Depart> getDepartList();
}
