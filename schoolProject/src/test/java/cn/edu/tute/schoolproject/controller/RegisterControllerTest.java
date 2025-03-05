package cn.edu.tute.schoolproject.controller;

import cn.edu.tute.schoolproject.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testBasicRegister() throws Exception {
        // 创建测试数据，模拟curl命令中的JSON数据
        String jsonContent = "{"
            + "\"userName\": \"testuser\","
            + "\"password\": \"password123\","
            + "\"email\": \"testuser@example.com\""
            + "}";

        // 执行POST请求并验证结果
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())  // 打印请求和响应详情
                .andExpect(status().isOk())  // 验证HTTP状态码为200
                .andExpect(jsonPath("$.success").exists())  // 验证返回的JSON中包含success字段
                .andExpect(jsonPath("$.message").exists());  // 验证返回的JSON中包含message字段
    }

    @Test
    public void testCompleteRegister() throws Exception {
        // 创建完整的用户注册数据
        Users user = new Users();
        user.setUserName("testuser2");
        user.setPassword("password123");
        user.setEmail("testuser2@example.com");
        user.setUserType("老人");
        user.setAge(65);
        user.setGender("男");
        user.setPhoneNumber("13800138000");
        user.setAddress("北京市朝阳区");

        // 使用ObjectMapper将对象转换为JSON字符串
        String jsonContent = objectMapper.writeValueAsString(user);

        // 执行POST请求并验证结果
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("注册成功"));
    }

    @Test
    public void testInvalidRegister() throws Exception {
        // 测试无效数据（缺少必要字段）
        String jsonContent = "{"
            + "\"userName\": \"\","  // 空用户名
            + "\"password\": \"\""    // 空密码
            + "}";

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("必填信息不能为空"));
    }

    @Test
    public void testDuplicateUsername() throws Exception {
        // 先注册一个用户
        String firstUser = "{"
            + "\"userName\": \"duplicate\","
            + "\"password\": \"password123\","
            + "\"phoneNumber\": \"13800138000\""
            + "}";

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(firstUser));

        // 尝试注册相同用户名
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(firstUser))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("用户名已被注册"));
    }
}

     