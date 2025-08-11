package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.RoleMenu;

public interface RoleMenuService extends IService<RoleMenu> {

    //修改角色-保存修改好的角色信息
    void deleteRoleMenuByRoleId(Long id);

}