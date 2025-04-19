package cn.edu.tute.schoolproject.service.family;

import cn.edu.tute.schoolproject.entity.Relationship;

import java.util.List;

public interface RelationshipService {
    // 添加关系记录
    int addRelationship(Relationship relationship);
    
    // 删除关系记录
    int deleteRelationship(Integer id);
    
    // 更新关系记录
    int updateRelationship(Relationship relationship);
    
    // 根据ID获取关系记录
    Relationship getRelationshipById(Integer id);
    
    // 根据用户ID获取关系记录
    List<Relationship> getRelationshipsByUserId(Integer userId);
    
    // 根据提醒ID获取关系记录
    List<Relationship> getRelationshipsByReminderId(Integer reminderId);
    
    // 获取所有关系记录
    List<Relationship> getAllRelationships();
}