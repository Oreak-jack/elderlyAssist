package cn.edu.tute.schoolproject.controller.index;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.service.index.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 用户注册接口
     * @param user 用户注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResponseResult<Map<String, Object>> register(@RequestBody Users user) {
        Map<String, Object> result = registerService.register(user);
        
        if ((Boolean) result.get("success")) {
            return ResponseResult.success((String) result.get("message"), result);
        } else {
            return ResponseResult.error((String) result.get("message"));
        }
    }
}
