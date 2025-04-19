package cn.edu.tute.schoolproject.mapper.family;

import cn.edu.tute.schoolproject.entity.Relationship;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelationshipMapper {
    // 插入新的关系记录
    int insert(Relationship relationship);
    
    // 根据ID删除关系记录
    int deleteById(Integer id);
    
    // 更新关系记录
    int update(Relationship relationship);
    
    // 根据ID查询关系记录
    Relationship selectById(Integer id);
    
    // 根据用户ID查询所有关系记录
    List<Relationship> selectByUserId(Integer userId);
    
    // 根据提醒ID查询所有关系记录
    List<Relationship> selectByReminderId(Integer reminderId);
    
    // 查询所有关系记录
    List<Relationship> selectAll();
}