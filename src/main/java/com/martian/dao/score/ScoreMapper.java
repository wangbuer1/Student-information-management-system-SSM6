package com.martian.dao.score;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.martian.dto.score.ScoreSearchDto;
import com.martian.entity.score.Score;
import com.martian.vo.score.ScoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成绩表 Mapper 接口
 */
public interface ScoreMapper extends BaseMapper<Score> {
    /**
     * 查询成绩
     * @param page
     * @param scoreSearchDto
     * @return
     */
    List<ScoreVo> searchScoreVoPage(@Param("page") Page<ScoreVo> page, @Param("scoreSearchDto") ScoreSearchDto scoreSearchDto);
}
