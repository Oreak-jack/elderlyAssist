package cn.edu.tute.schoolproject.controller.index;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.service.index.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录接口
     * @param loginParams 登录参数（手机号和密码）
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseResult<Users> login(@RequestBody Map<String, String> loginParams) {
        String phoneNumber = loginParams.get("phoneNumber");
        String password = loginParams.get("password");
        
        // 参数校验
        if (phoneNumber == null || password == null) {
            return ResponseResult.error("手机号和密码不能为空");
        }
        
        return loginService.login(phoneNumber, password);
    }
}
