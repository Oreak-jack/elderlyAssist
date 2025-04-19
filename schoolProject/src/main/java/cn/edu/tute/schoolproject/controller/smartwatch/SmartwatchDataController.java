package cn.edu.tute.schoolproject.controller.smartwatch;

import cn.edu.tute.schoolproject.entity.SmartwatchData;
import cn.edu.tute.schoolproject.service.smartwatch.SmartwatchDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/smartwatch")
public class SmartwatchDataController {
    private static final Logger log = LoggerFactory.getLogger(SmartwatchDataController.class);

    @Autowired
    private SmartwatchDataService smartwatchDataService;

    @PostMapping("/data")
    public Map<String, Object> saveSmartwatchData(@RequestBody SmartwatchData data) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = smartwatchDataService.saveSmartwatchData(data);
            response.put("success", result > 0);
            response.put("message", result > 0 ? "数据保存成功" : "数据保存失败");
        } catch (Exception e) {
            log.error("保存智能手表数据失败", e);
            response.put("success", false);
            response.put("message", "系统错误");
        }
        return response;
    }

    @GetMapping("/data/latest/{userId}")
    public Map<String, Object> getLatestData(@PathVariable Integer userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            SmartwatchData data = smartwatchDataService.getLatestDataByUserId(userId);
            response.put("success", true);
            response.put("data", data);
        } catch (Exception e) {
            log.error("获取最新智能手表数据失败", e);
            response.put("success", false);
            response.put("message", "系统错误");
        }
        return response;
    }

    @GetMapping("/data/{userId}")
    public Map<String, Object> getDataByTimeRange(
            @PathVariable Integer userId,
            @RequestParam String startTime,
            @RequestParam String endTime) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<SmartwatchData> dataList = smartwatchDataService.getDataByUserIdAndTimeRange(userId, startTime, endTime);
            response.put("success", true);
            response.put("data", dataList);
        } catch (Exception e) {
            log.error("获取智能手表数据失败", e);
            response.put("success", false);
            response.put("message", "系统错误");
        }
        return response;
    }

    @PutMapping("/data/{id}")
    public Map<String, Object> updateSmartwatchData(@PathVariable Integer id, @RequestBody SmartwatchData data) {
        Map<String, Object> response = new HashMap<>();
        try {
            data.setId(id);
            int result = smartwatchDataService.updateSmartwatchData(data);
            response.put("success", result > 0);
            response.put("message", result > 0 ? "数据更新成功" : "数据更新失败");
        } catch (Exception e) {
            log.error("更新智能手表数据失败", e);
            response.put("success", false);
            response.put("message", "系统错误");
        }
        return response;
    }

    @DeleteMapping("/data/{id}")
    public Map<String, Object> deleteSmartwatchData(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = smartwatchDataService.deleteSmartwatchData(id);
            response.put("success", result > 0);
            response.put("message", result > 0 ? "数据删除成功" : "数据删除失败");
        } catch (Exception e) {
            log.error("删除智能手表数据失败", e);
            response.put("success", false);
            response.put("message", "系统错误");
        }
        return response;
    }
}