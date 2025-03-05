package cn.edu.tute.schoolproject.service.index;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.mapper.index.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户登录
     * @param phoneNumber 手机号
     * @param password 密码
     * @return 登录结果
     */
    public ResponseResult<Users> login(String phoneNumber, String password) {
        try {
            // 参数校验
            if (!StringUtils.hasText(phoneNumber) || !StringUtils.hasText(password)) {
                return ResponseResult.error("手机号和密码不能为空");
            }

            // 验证手机号格式
            if (!isValidPhoneNumber(phoneNumber)) {
                return ResponseResult.error("手机号格式不正确");
            }

            // 查询用户
            Users user = usersMapper.getUserByPhoneNumber(phoneNumber);
            if (user == null) {
                return ResponseResult.error("该手机号未注册");
            }

            // 验证密码
            if (!password.equals(user.getPassword())) {
                return ResponseResult.error("密码错误");
            }

            // 登录成功，清除密码等敏感信息
            user.setPassword(null);
            
            return ResponseResult.success("登录成功", user);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("系统错误，请联系管理员");
        }
    }

    /**
     * 验证手机号格式
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^1[3-9]\\d{9}$");
    }
}
