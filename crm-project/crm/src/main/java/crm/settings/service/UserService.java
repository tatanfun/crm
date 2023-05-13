package crm.settings.service;

import crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryUserLoginActAanPwd(Map<String,Object> map);

    List<User> queryAllUsers();
}
