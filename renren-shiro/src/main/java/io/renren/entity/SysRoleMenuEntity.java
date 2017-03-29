package io.renren.entity;

import lombok.Data;

/**
 * 角色与菜单对应关系
 *
 * @author chenshun
 */
@Data
public class SysRoleMenuEntity {

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
