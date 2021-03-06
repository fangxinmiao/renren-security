package io.renren.service.impl;

import io.renren.dao.SysOssDao;
import io.renren.entity.SysOssEntity;
import io.renren.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {
    private final SysOssDao sysOssDao;

    @Autowired
    public SysOssServiceImpl(SysOssDao sysOssDao) {
        this.sysOssDao = sysOssDao;
    }

    @Override
    public SysOssEntity queryObject(Long id) {
        return sysOssDao.queryObject(id);
    }

    @Override
    public List<SysOssEntity> queryList(Map<String, Object> map) {
        return sysOssDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysOssDao.queryTotal(map);
    }

    @Override
    public void save(SysOssEntity sysOss) {
        sysOssDao.save(sysOss);
    }

    @Override
    public void update(SysOssEntity sysOss) {
        sysOssDao.update(sysOss);
    }

    @Override
    public void delete(Long id) {
        sysOssDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysOssDao.deleteBatch(ids);
    }
}
