package cn.edu.tute.schoolproject.service.family.impl;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.mapper.family.RelationshipMapper;
import cn.edu.tute.schoolproject.service.family.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipMapper relationshipMapper;

    @Override
    public int addRelationship(Relationship relationship) {
        return relationshipMapper.insert(relationship);
    }

    @Override
    public int deleteRelationship(Integer id) {
        return relationshipMapper.deleteById(id);
    }

    @Override
    public int updateRelationship(Relationship relationship) {
        return relationshipMapper.update(relationship);
    }

    @Override
    public Relationship getRelationshipById(Integer id) {
        return relationshipMapper.selectById(id);
    }

    @Override
    public List<Relationship> getRelationshipsByUserId(Integer userId) {
        return relationshipMapper.selectByUserId(userId);
    }

    @Override
    public List<Relationship> getRelationshipsByReminderId(Integer reminderId) {
        return relationshipMapper.selectByReminderId(reminderId);
    }

    @Override
    public List<Relationship> getAllRelationships() {
        return relationshipMapper.selectAll();
    }
}