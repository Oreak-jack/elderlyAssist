package cn.edu.tute.schoolproject.service.family;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.entity.Users;
import java.util.List;

public interface FamilyService {
    /**
     * 获取家属信息和关联老人列表
     * @param familyId 家属ID
     * @return 老人列表
     */
    List<Relationship> getFamilyInfo(Integer familyId);

    /**
     * 获取可绑定的老人列表
     * @return 可绑定的老人列表
     */
    List<Users> getAvailableElderly();

    /**
     * 绑定老人
     * @param elderlyId 老人ID
     * @param familyId 家属ID
     * @return 是否绑定成功
     */
    boolean bindElderly(Integer elderlyId, Integer familyId);
} 