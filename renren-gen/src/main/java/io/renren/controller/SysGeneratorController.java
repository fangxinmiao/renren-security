package io.renren.controller;

import com.alibaba.fastjson.JSON;
import io.renren.annotation.SysLog;
import io.renren.service.SysGeneratorService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    private final SysGeneratorService sysGeneratorService;

    @Autowired
    public SysGeneratorController(SysGeneratorService sysGeneratorService) {
        this.sysGeneratorService = sysGeneratorService;
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:generator:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @SysLog("生成代码")
    @RequestMapping("/code")
    @RequiresPermissions("sys:generator:code")
    public void code(String tables, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = sysGeneratorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
