package crm.settings.service.impl;

import crm.settings.domain.User;
import crm.settings.mapper.UserMapper;
import crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserLoginActAanPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAanPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
