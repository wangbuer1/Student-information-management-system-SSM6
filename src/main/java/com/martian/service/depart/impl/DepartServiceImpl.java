package com.martian.service.depart.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.util.CommonUtils;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.depart.DepartMapper;
import com.martian.dto.depart.DepartDto;
import com.martian.entity.depart.Depart;
import com.martian.enums.YNEnum;
import com.martian.service.depart.IDepartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 院系表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-03-13
 */
@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements IDepartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartServiceImpl.class);

    /**
     * 添加
     *
     * @param departDto
     */
    @Override
    public void add(DepartDto departDto) {
        Depart depart = new Depart();
        BeanUtils.copyProperties(departDto, depart);
        EntityUtils.init(depart);
        boolean flag = super.insert(depart);
        if (!flag) {
            LOGGER.error("院系添加失败，depart:{}", JsonUtils.toJSONString(depart));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);
    }

    /**
     * 修改
     *
     * @param departDto
     */
    @Override
    public void update(DepartDto departDto) {
        Depart depart = new Depart();
        BeanUtils.copyProperties(departDto, depart);
        EntityUtils.init(depart);
        boolean flag = super.updateById(depart);
        if (!flag) {
            LOGGER.error("院系修改失败，depart:{}", JsonUtils.toJSONString(depart));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
    }

    /**
     * 删除
     *
     * @param departId
     */
    @Override
    public void del(String departId) {
        if (CommonUtils.isNotEmpty(departId)) {
            Depart param = new Depart();
            param.setId(departId);
            param.setIsValid(YNEnum.NO);
            EntityUtils.init(param);
            boolean flag = super.updateById(param);
            if (!flag) {
                LOGGER.error("院系删除失败，param:{}", JsonUtils.toJSONString(param));
            }
            BusinessAsserts.isTrue(flag, Exceptions.System.DEL_FAIL);
        }
    }

    /**
     * 获取列表
     *
     * @return
     */
    @Override
    public List<Depart> getDepartList() {
        Depart param = new Depart();
        param.setIsValid(YNEnum.YES);
        return super.selectList(new EntityWrapper<Depart>(param));
    }
}
