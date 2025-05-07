package cn.edu.tute.schoolproject.mapper.family;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyMapper {
    /**
     * 获取家属信息和关联老人列表
     * @param familyId 家属ID
     * @return 老人列表
     */
    List<Relationship> getFamilyInfo(@Param("familyId") Integer familyId);

    /**
     * 获取可绑定的老人列表
     * @return 可绑定的老人列表
     */
    List<Users> getAvailableElderly();

    /**
     * 绑定老人
     * @param elderlyId 老人ID
     * @param familyId 家属ID
     */
    void bindElderly(@Param("elderlyId") Integer elderlyId, @Param("familyId") Integer familyId);
} 