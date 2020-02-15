package org.zp.secret.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zp.secret.pojo.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
