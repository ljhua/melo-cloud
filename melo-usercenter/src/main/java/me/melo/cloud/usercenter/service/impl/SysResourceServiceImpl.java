package me.melo.cloud.usercenter.service.impl;import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;import com.google.common.collect.Lists;import lombok.RequiredArgsConstructor;import me.melo.cloud.usercenter.dto.QueryUserResourceDTO;import me.melo.cloud.usercenter.entity.SysResource;import me.melo.cloud.usercenter.enums.ResourceEnum;import me.melo.cloud.usercenter.mapper.SysResourceMapper;import me.melo.cloud.usercenter.service.ISysResourceService;import org.springframework.stereotype.Service;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;/** * <p> * 权限表 服务实现类 * </p> * * @author ljhua * @since 2022-08-08 */@Service@RequiredArgsConstructorpublic class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {    private final SysResourceMapper sysResourceMapper;    @Override    public List<SysResource> queryResourceListByUserId(QueryUserResourceDTO queryUserResourceDTO) {        List<SysResource> resourceList = sysResourceMapper.queryResourceByUserId(queryUserResourceDTO);        return resourceList;    }    @Override    public List<SysResource> queryMenuTreeByUserId(Long userId) {        QueryUserResourceDTO queryUserResourceDTO = new QueryUserResourceDTO();        queryUserResourceDTO.setUserId(userId);        queryUserResourceDTO.setResourceTypeList(Lists.newArrayList(ResourceEnum.MENU.getCode()));        List<SysResource> resourceList = sysResourceMapper.queryResourceByUserId(queryUserResourceDTO);        Map<Long, SysResource> resourceMap = new HashMap<>();        List<SysResource> menus = this.assembleResourceTree(resourceList, resourceMap);        return menus;    }    @Override    public List<SysResource> queryResourceRoles() {        List<SysResource> resourceList = sysResourceMapper.queryResourceRoles();        return resourceList;    }    private List<SysResource> assembleResourceTree(List<SysResource> resourceList, Map<Long, SysResource> resourceMap) {        List<SysResource> menus = new ArrayList<>();        for (SysResource resource : resourceList) {            resourceMap.put(resource.getId(), resource);        }        for (SysResource resource : resourceList) {            Long treePId = resource.getParentId();            SysResource resourceTree = resourceMap.get(treePId);            if (null != resourceTree && !resource.equals(resourceTree)) {                List<SysResource> nodes = resourceTree.getChildren();                if (null == nodes) {                    nodes = new ArrayList<>();                    resourceTree.setChildren(nodes);                }                nodes.add(resource);            } else {                menus.add(resource);            }        }        return menus;    }}