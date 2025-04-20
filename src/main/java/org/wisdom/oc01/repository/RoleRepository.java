package org.wisdom.oc01.repository;


import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.Role;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface RoleRepository extends IRepository<Role, Integer> {

    Role findByRoleName(String roleName);
}
