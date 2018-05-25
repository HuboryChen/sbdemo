package com.xtlh.sbdemo.repository;

import com.xtlh.sbdemo.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor {
}
