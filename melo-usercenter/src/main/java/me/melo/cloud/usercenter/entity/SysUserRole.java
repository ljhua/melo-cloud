package me.melo.cloud.usercenter.entity;import com.baomidou.mybatisplus.annotation.IdType;import com.baomidou.mybatisplus.annotation.TableId;import com.baomidou.mybatisplus.annotation.TableName;import java.io.Serializable;import java.time.LocalDateTime;import io.swagger.annotations.ApiModel;import io.swagger.annotations.ApiModelProperty;/** * <p> * 用户和角色关联表 * </p> * * @author ljhua * @since 2022-08-08 */@TableName("sys_user_role")@ApiModel(value = "SysUserRole对象", description = "用户和角色关联表")public class SysUserRole implements Serializable {    private static final long serialVersionUID = 1L;    @ApiModelProperty("主键")    @TableId(value = "id", type = IdType.AUTO)    private Long id;    @ApiModelProperty("租户id")    private Long tenantId;    @ApiModelProperty("用户id")    private Long userId;    @ApiModelProperty("角色id")    private Long roleId;    @ApiModelProperty("创建者")    private Long createdBy;    @ApiModelProperty("创建时间")    private LocalDateTime createdTime;    @ApiModelProperty("更新者")    private Long modifiedBy;    @ApiModelProperty("更新时间")    private LocalDateTime modifiedTime;    @ApiModelProperty("1:删除 0:不删除")    private Integer delFlag;    public Long getId() {        return id;    }    public void setId(Long id) {        this.id = id;    }    public Long getTenantId() {        return tenantId;    }    public void setTenantId(Long tenantId) {        this.tenantId = tenantId;    }    public Long getUserId() {        return userId;    }    public void setUserId(Long userId) {        this.userId = userId;    }    public Long getRoleId() {        return roleId;    }    public void setRoleId(Long roleId) {        this.roleId = roleId;    }    public Long getCreatedBy() {        return createdBy;    }    public void setCreatedBy(Long createdBy) {        this.createdBy = createdBy;    }    public LocalDateTime getCreatedTime() {        return createdTime;    }    public void setCreatedTime(LocalDateTime createdTime) {        this.createdTime = createdTime;    }    public Long getModifiedBy() {        return modifiedBy;    }    public void setModifiedBy(Long modifiedBy) {        this.modifiedBy = modifiedBy;    }    public LocalDateTime getModifiedTime() {        return modifiedTime;    }    public void setModifiedTime(LocalDateTime modifiedTime) {        this.modifiedTime = modifiedTime;    }    public Integer getDelFlag() {        return delFlag;    }    public void setDelFlag(Integer delFlag) {        this.delFlag = delFlag;    }    @Override    public String toString() {        return "SysUserRole{" +            "id=" + id +            ", tenantId=" + tenantId +            ", userId=" + userId +            ", roleId=" + roleId +            ", createdBy=" + createdBy +            ", createdTime=" + createdTime +            ", modifiedBy=" + modifiedBy +            ", modifiedTime=" + modifiedTime +            ", delFlag=" + delFlag +        "}";    }}