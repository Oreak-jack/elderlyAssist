package cn.edu.tute.schoolproject.mapper.index;

import cn.edu.tute.schoolproject.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UsersMapper {
    // 插入新用户
    int insertUser(Users user);
    
    // 根据用户ID查询用户
    Users getUserById(Integer userId);
    
    // 根据用户名查询用户
    Users getUserByUsername(String userName);
    
    // 更新用户信息
    int updateUser(Users user);
    
    // 删除用户
    int deleteUser(Integer userId);
    
    // 查询所有用户
    List<Users> getAllUsers();
    
    // 根据用户类型查询用户
    List<Users> getUsersByType(String userType);

    // 根据手机号查询用户
    Users getUserByPhoneNumber(String phoneNumber);
}
