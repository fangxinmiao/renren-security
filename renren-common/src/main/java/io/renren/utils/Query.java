package io.renren.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author chenshun
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Query extends LinkedHashMap<String, Object> {

    public Query(Map<String, Object> params) {
        this.putAll(params);
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int limit;
}
