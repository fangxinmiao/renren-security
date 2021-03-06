package io.renren.dao;

import io.renren.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author chenshun
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
