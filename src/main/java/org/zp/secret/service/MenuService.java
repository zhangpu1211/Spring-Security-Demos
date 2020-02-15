package org.zp.secret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zp.secret.mapper.MenuMapper;
import org.zp.secret.pojo.Menu;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}
