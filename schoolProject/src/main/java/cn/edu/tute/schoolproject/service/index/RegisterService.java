package cn.edu.tute.schoolproject.service.index;

import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.mapper.index.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    
    @Autowired
    private UsersMapper UsersMapper;

    /**
     * 用户注册
     * @param user 用户信息
     * @return 包含注册结果的Map
     */
    @Transactional
    public Map<String, Object> register(Users user) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 基础信息验证
            String validationError = validateRegistration(user);
            if (validationError != null) {
                result.put("success", false);
                result.put("message", validationError);
                return result;
            }

            // 检查手机号是否已存在
            Users existingUser = UsersMapper.getUserByPhoneNumber(user.getPhoneNumber());
            if (existingUser != null) {
                result.put("success", false);
                result.put("message", "该手机号已被注册");
                return result;
            }

            // 设置默认值
            setDefaultValues(user);

            // 执行注册
            int insertResult = UsersMapper.insertUser(user);
            if (insertResult > 0) {
                result.put("success", true);
                result.put("message", "注册成功");
                result.put("userId", user.getUserId());
            } else {
                result.put("success", false);
                result.put("message", "注册失败，请稍后重试");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "系统错误，请联系管理员");
            e.printStackTrace();
        }
        
        return result;
    }

    /**
     * 验证注册信息
     */
    private String validateRegistration(Users user) {
        if (user == null) {
            return "注册信息不能为空";
        }
        if (!StringUtils.hasText(user.getUserName())) {
            return "用户名不能为空";
        }
        if (!StringUtils.hasText(user.getPassword())) {
            return "密码不能为空";
        }
        if (user.getPassword().length() < 6) {
            return "密码长度不能小于6位";
        }
        if (StringUtils.hasText(user.getPhoneNumber()) && !isValidPhoneNumber(user.getPhoneNumber())) {
            return "手机号格式不正确";
        }
        if (StringUtils.hasText(user.getEmail()) && !isValidEmail(user.getEmail())) {
            return "邮箱格式不正确";
        }
        if (user.getAge() != null && (user.getAge() < 0 || user.getAge() > 150)) {
            return "年龄不合法";
        }
        return null;
    }

    /**
     * 设置默认值
     */
    private void setDefaultValues(Users user) {
        if (user.getUserType() == null) {
            user.setUserType("老人");
        }
        if (!StringUtils.hasText(user.getGender())) {
            user.setGender("男");
        }
    }

    /**
     * 验证手机号格式
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^1[3-9]\\d{9}$");
    }

    /**
     * 验证邮箱格式
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
