package com.xtlh.sbdemo.repository;

import com.xtlh.sbdemo.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/22
 * @功能描述 权限数据层
 */
@Repository("permissionRepository")
public interface PermissionRepository extends JpaRepository<SysPermission, Long>, JpaSpecificationExecutor {
    @Override
    List<SysPermission> findAll();

    @Query(value = "select p.* from user " +
            "left join  sys_role_user sru on u.id=sru.sys_user_id " +
            "left join sys_role r on sru.sys_role_id=r.id " +
            "left join sys_permission_role spr on spr.role_id=r.id " +
            "left join sys_permission p on p.id=spr.permission_id " +
            "where u.id=?1 ", nativeQuery = true)
    List<SysPermission> findByAdminUserId(Long userId);
}
