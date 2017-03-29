package io.renren.oss;

import io.renren.service.SysConfigService;
import io.renren.utils.ConfigConstant;
import io.renren.utils.Constant;
import io.renren.utils.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author chenshun
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService)
                SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        CloudStorageConfig config = sysConfigService.getConfigObject(
                ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }
        return null;
    }
}
