package com.martian.service.clazz.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.page.PageVo;
import com.martian.common.util.CommonUtils;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.clazz.ClazzMapper;
import com.martian.dto.clazz.ClazzDto;
import com.martian.dto.clazz.ClazzSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.entity.depart.Depart;
import com.martian.enums.YNEnum;
import com.martian.service.clazz.IClazzService;
import com.martian.service.depart.IDepartService;
import com.martian.vo.clazz.ClazzVo;

/**
 * 班级表 服务实现类
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements IClazzService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClazzServiceImpl.class);
	@Autowired
	private IDepartService departService;

	/**
	 * 添加
	 *
	 * @param clazzDto
	 */
	@Override
	public void add(ClazzDto clazzDto) {
		// 校验
		BusinessAsserts.notBlank(clazzDto.getName(), Exceptions.Clazz.CLAZZ_NAME_CAN_NOT_NULL);
		BusinessAsserts.notBlank(clazzDto.getDepartId(), Exceptions.Depart.DEPART_ID_NULL);
		Depart depart = departService.selectById(clazzDto.getDepartId());
		BusinessAsserts.notNull(depart, Exceptions.Depart.DEPART_NOT_EXIST);
		BusinessAsserts.isTrue(YNEnum.YES.equals(depart.getIsValid()), Exceptions.Depart.DEPART_NOT_EXIST);
		// 添加
		Clazz clazz = new Clazz();
		BeanUtils.copyProperties(clazzDto, clazz);
		EntityUtils.init(clazz);
		boolean flag = super.insert(clazz);
		if (!flag) {
			LOGGER.error("班级添加失败，clazz:{}", JsonUtils.toJSONString(clazz));
		}
		BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);
	}

	/**
	 * 修改
	 *
	 * @param clazzDto
	 */
	@Override
	public void update(ClazzDto clazzDto) {
		// 校验
		BusinessAsserts.notBlank(clazzDto.getId(), Exceptions.Clazz.CLAZZ_ID_NULL);
		BusinessAsserts.notBlank(clazzDto.getName(), Exceptions.Clazz.CLAZZ_NAME_CAN_NOT_NULL);
		// 修改
		Clazz clazz = new Clazz();
		BeanUtils.copyProperties(clazzDto, clazz);
		EntityUtils.init(clazz);
		// 不允许修改院系
		clazz.setDepartId(null);
		boolean flag = super.updateById(clazz);
		if (!flag) {
			LOGGER.error("班級修改失败，clazz:{}", JsonUtils.toJSONString(clazz));
		}
		BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
	}

	/**
	 * 删除
	 *
	 * @param clazzId
	 */
	@Override
	public void del(String clazzId) {
		if (CommonUtils.isNotEmpty(clazzId)) {
			Clazz param = new Clazz();
			param.setId(clazzId);
			param.setIsValid(YNEnum.NO);
			EntityUtils.init(param);
			boolean flag = super.updateById(param);
			if (!flag) {
				LOGGER.error("班級删除失败，param:{}", JsonUtils.toJSONString(param));
			}
			BusinessAsserts.isTrue(flag, Exceptions.System.DEL_FAIL);
		}
	}

	@Override
	public PageVo searchClazzVoPage(ClazzSearchDto clazzSearchDto) {
		Page<ClazzVo> page = new Page<>(clazzSearchDto.getPageNo(), clazzSearchDto.getPageSize());
		page.setRecords(this.baseMapper.searchClazzVoPage(page, clazzSearchDto));
		return new PageVo(page);
	}

	/**
	 * 根据院系Id搜索班级
	 *
	 * @param departId
	 * @return
	 */
	@Override
	public List<Clazz> searchByDepartId(String departId) {
		if (CommonUtils.isNotEmpty(departId)) {
			Clazz param = new Clazz();
			param.setIsValid(YNEnum.YES);
			param.setDepartId(departId);
			return super.selectList(new EntityWrapper<>(param));
		}
		return new ArrayList<>();
	}

	/**
	 * 获取教师授课班级
	 *
	 * @param teacherId
	 * @return
	 */
	@Override
	public List<Clazz> getTeacherClazzList(String teacherId) {
		if (CommonUtils.isNotEmpty(teacherId)) {
			return this.baseMapper.getTeacherClazzList(teacherId);
		}
		return null;
	}
}
