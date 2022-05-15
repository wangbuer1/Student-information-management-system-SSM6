package com.martian.service.score;

import com.baomidou.mybatisplus.service.IService;
import com.martian.common.page.PageVo;
import com.martian.dto.score.ScoreDto;
import com.martian.dto.score.ScoreSearchDto;
import com.martian.entity.score.Score;

/**
 * 成绩表 服务类
 */
public interface IScoreService extends IService<Score> {
    /**
     * 搜索
     *
     * @param scoreSearchDto
     * @return
     */
    PageVo searchScoreVoPage(ScoreSearchDto scoreSearchDto);

    /**
     * 添加
     *
     * @param scoreDto
     */
    void add(ScoreDto scoreDto);

    /**
     * 修改
     *
     * @param scoreDto
     */
    void update(ScoreDto scoreDto);
}
