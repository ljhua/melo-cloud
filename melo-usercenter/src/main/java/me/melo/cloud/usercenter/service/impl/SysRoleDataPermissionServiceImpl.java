package me.melo.cloud.usercenter.service.impl;import me.melo.cloud.usercenter.entity.SysRoleDataPermission;import me.melo.cloud.usercenter.mapper.SysRoleDataPermissionMapper;import me.melo.cloud.usercenter.service.ISysRoleDataPermissionService;import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;import org.springframework.stereotype.Service;/** * <p> * 角色和数据权限关联表 服务实现类 * </p> * * @author ljhua * @since 2022-08-08 */@Servicepublic class SysRoleDataPermissionServiceImpl extends ServiceImpl<SysRoleDataPermissionMapper, SysRoleDataPermission> implements ISysRoleDataPermissionService {}