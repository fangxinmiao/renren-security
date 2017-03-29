package io.renren.controller;

import com.alibaba.fastjson.JSON;
import io.renren.entity.SysOssEntity;
import io.renren.oss.CloudStorageConfig;
import io.renren.oss.OSSFactory;
import io.renren.service.SysConfigService;
import io.renren.service.SysOssService;
import io.renren.utils.*;
import io.renren.validator.ValidatorUtils;
import io.renren.validator.group.AliyunGroup;
import io.renren.validator.group.QcloudGroup;
import io.renren.validator.group.QiniuGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author chenshun
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    private final SysOssService sysOssService;
    private final SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    public SysOssController(SysOssService sysOssService,
                            SysConfigService sysConfigService) {
        this.sysOssService = sysOssService;
        this.sysConfigService = sysConfigService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<SysOssEntity> sysOssList = sysOssService.queryList(query);
        int total = sysOssService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
        return R.ok().put("config", config);
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config) {
        ValidatorUtils.validateEntity(config);
        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return R.ok();
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        String url = OSSFactory.build().upload(file.getBytes());
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);
        return R.ok().put("url", url);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids) {
        sysOssService.deleteBatch(ids);
        return R.ok();
    }
}
