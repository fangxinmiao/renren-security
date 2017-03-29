package io.renren.service.impl;

import io.renren.dao.ScheduleJobLogDao;
import io.renren.entity.ScheduleJobLogEntity;
import io.renren.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
    private final ScheduleJobLogDao scheduleJobLogDao;

    @Autowired
    public ScheduleJobLogServiceImpl(ScheduleJobLogDao scheduleJobLogDao) {
        this.scheduleJobLogDao = scheduleJobLogDao;
    }

    @Override
    public ScheduleJobLogEntity queryObject(Long jobId) {
        return scheduleJobLogDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
        return scheduleJobLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobLogDao.queryTotal(map);
    }

    @Override
    public void save(ScheduleJobLogEntity log) {
        scheduleJobLogDao.save(log);
    }
}
