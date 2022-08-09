package me.melo.cloud.usercenter.service.impl;import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;import lombok.RequiredArgsConstructor;import me.melo.cloud.common.enums.ResultCodeEnum;import me.melo.cloud.common.exception.BusinessException;import me.melo.cloud.usercenter.dto.CreateUserDTO;import me.melo.cloud.usercenter.dto.QueryUserResourceDTO;import me.melo.cloud.usercenter.entity.SysResource;import me.melo.cloud.usercenter.entity.SysUser;import me.melo.cloud.usercenter.entity.UserInfo;import me.melo.cloud.usercenter.enums.ResourceEnum;import me.melo.cloud.usercenter.mapper.SysUserMapper;import me.melo.cloud.usercenter.service.ISysResourceService;import me.melo.cloud.usercenter.service.ISysUserService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.util.StringUtils;import java.util.Arrays;import java.util.List;import java.util.stream.Collectors;/** * <p> * 用户表 服务实现类 * </p> * * @author ljhua * @since 2022-08-08 */@Service@RequiredArgsConstructor(onConstructor_ = @Autowired)public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {    private final SysUserMapper sysUserMapper;    private final ISysResourceService sysResourceService;    @Override    public UserInfo queryUserInfo(SysUser sysUser) {        UserInfo userInfo = sysUserMapper.queryUserInfo(sysUser);        if (null == userInfo) {            throw new BusinessException(ResultCodeEnum.INVALID_USERNAME.getMsg());        }        String roleIds = userInfo.getRoleIds();        // 组装角色ID列表，用于oauth2和gateway鉴权        if (!StringUtils.isEmpty(roleIds)) {            String[] roleIdArray = roleIds.split(",");            userInfo.setRoleIdList(Arrays.asList(roleIdArray));        }        String roleKeys = userInfo.getRoleKeys();        // 组装角色key列表，用于前端页面鉴权        if (!StringUtils.isEmpty(roleKeys)) {            String[] roleKeyArray = roleKeys.split(",");            userInfo.setRoleKeyList(Arrays.asList(roleKeyArray));        }        String dataPermissionTypes = userInfo.getDataPermissionTypes();        // 获取用户的角色数据权限级别        if (!StringUtils.isEmpty(dataPermissionTypes)) {            String[] dataPermissionTypeArray = dataPermissionTypes.split(",");            userInfo.setDataPermissionTypeList(Arrays.asList(dataPermissionTypeArray));        }        String orgIds = userInfo.getOrgIds();        // 获取用户机构数据权限列表        if (!StringUtils.isEmpty(orgIds)) {            String[] organizationIdArray = orgIds.split(",");            userInfo.setOrgIdList(Arrays.asList(organizationIdArray));        }        QueryUserResourceDTO queryUserResourceDTO = new QueryUserResourceDTO();        queryUserResourceDTO.setUserId(userInfo.getId());        List<SysResource> resourceList = sysResourceService.queryResourceListByUserId(queryUserResourceDTO);        // 查询用户资源列表，用于前端页面鉴权        List<String> menuList = resourceList.stream().map(SysResource::getResourceKey).collect(Collectors.toList());        userInfo.setResourceKeyList(menuList);        // 查询用户资源列表，用于SpringSecurity鉴权        List<String> resourceUrlList = resourceList.stream().filter(s -> !ResourceEnum.MODULE.getCode().equals(s.getResourceType()) && !ResourceEnum.MENU.getCode().equals(s.getResourceType())).map(SysResource::getResourceUrl).collect(Collectors.toList());        userInfo.setResourceUrlList(resourceUrlList);        // 查询用户菜单树，用于页面展示        List<SysResource> menuTree = sysResourceService.queryMenuTreeByUserId(userInfo.getId());        userInfo.setMenuTree(menuTree);        return userInfo;    }    @Override    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {        return null;    }}