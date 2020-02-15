package org.zp.secret.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zp.secret.pojo.Role;
import org.zp.secret.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);
}
