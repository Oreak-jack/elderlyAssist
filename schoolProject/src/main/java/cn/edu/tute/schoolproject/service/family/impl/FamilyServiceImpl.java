package cn.edu.tute.schoolproject.service.family.impl;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.mapper.family.FamilyMapper;
import cn.edu.tute.schoolproject.service.family.FamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    private static final Logger logger = LoggerFactory.getLogger(FamilyServiceImpl.class);

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public List<Relationship> getFamilyInfo(Integer familyId) {
        try {
            logger.info("开始获取家属信息，familyId: {}", familyId);
            List<Relationship> relationships = familyMapper.getFamilyInfo(familyId);
            logger.info("获取家属信息成功，数量：{}", relationships.size());
            return relationships;
        } catch (DataAccessException e) {
            logger.error("数据库访问错误，获取家属信息失败，familyId: {}", familyId, e);
            throw new RuntimeException("数据库访问错误：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("获取家属信息失败，familyId: {}", familyId, e);
            throw new RuntimeException("获取家属信息失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<Users> getAvailableElderly() {
        try {
            logger.info("开始获取可绑定老人列表");
            List<Users> elderlyList = familyMapper.getAvailableElderly();
            logger.info("获取可绑定老人列表成功，数量：{}", elderlyList.size());
            if (elderlyList != null && !elderlyList.isEmpty()) {
                logger.debug("老人列表详情：{}", elderlyList);
            }
            return elderlyList;
        } catch (DataAccessException e) {
            logger.error("数据库访问错误，获取可绑定老人列表失败", e);
            throw new RuntimeException("数据库访问错误：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("获取可绑定老人列表失败", e);
            throw new RuntimeException("获取可绑定老人列表失败：" + e.getMessage(), e);
        }
    }

    @Override
    public boolean bindElderly(Integer elderlyId, Integer familyId) {
        try {
            logger.info("开始绑定老人，elderlyId: {}, familyId: {}", elderlyId, familyId);
            familyMapper.bindElderly(elderlyId, familyId);
            logger.info("绑定老人成功");
            return true;
        } catch (DataAccessException e) {
            logger.error("数据库访问错误，绑定老人失败，elderlyId: {}, familyId: {}", elderlyId, familyId, e);
            return false;
        } catch (Exception e) {
            logger.error("绑定老人失败，elderlyId: {}, familyId: {}", elderlyId, familyId, e);
            return false;
        }
    }
} 