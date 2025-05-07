package cn.edu.tute.schoolproject.controller.family;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.entity.Users;
import cn.edu.tute.schoolproject.service.family.FamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    private static final Logger logger = LoggerFactory.getLogger(FamilyController.class);

    @Autowired
    private FamilyService familyService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("测试接口正常");
    }

    @GetMapping("/elderly-list")
    public ResponseEntity<?> getFamilyInfo(@RequestParam Integer familyId) {
        try {
            List<Relationship> elderlyList = familyService.getFamilyInfo(familyId);
            return ResponseEntity.ok(elderlyList);
        } catch (Exception e) {
            logger.error("获取家属信息失败", e);
            return ResponseEntity.internalServerError().body("获取家属信息失败：" + e.getMessage());
        }
    }

    @GetMapping("/available-elderly")
    public ResponseEntity<?> getAvailableElderly() {
        try {
            logger.info("开始处理获取可绑定老人列表请求");
            List<Users> availableElderly = familyService.getAvailableElderly();
            logger.info("获取可绑定老人列表成功，数量：{}", availableElderly.size());
            return ResponseEntity.ok(availableElderly);
        } catch (Exception e) {
            logger.error("获取可绑定老人列表失败", e);
            return ResponseEntity.internalServerError().body("获取可绑定老人列表失败：" + e.getMessage());
        }
    }


    @PostMapping("/bind")
    public ResponseEntity<?> bindElderly(@RequestParam Integer elderlyId, @RequestParam Integer familyId) {
        try {
            boolean result = familyService.bindElderly(elderlyId, familyId);
            if (result) {
                return ResponseEntity.ok("绑定成功");
            } else {
                return ResponseEntity.badRequest().body("绑定失败");
            }
        } catch (Exception e) {
            logger.error("绑定老人失败", e);
            return ResponseEntity.internalServerError().body("绑定老人失败：" + e.getMessage());
        }
    }
} 