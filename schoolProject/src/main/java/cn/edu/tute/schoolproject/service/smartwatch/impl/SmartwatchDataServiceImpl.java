package cn.edu.tute.schoolproject.service.smartwatch.impl;

import cn.edu.tute.schoolproject.entity.SmartwatchData;
import cn.edu.tute.schoolproject.mapper.smartwatch.SmartwatchDataMapper;
import cn.edu.tute.schoolproject.service.smartwatch.SmartwatchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartwatchDataServiceImpl implements SmartwatchDataService {

    @Autowired
    private SmartwatchDataMapper smartwatchDataMapper;

    @Override
    public int saveSmartwatchData(SmartwatchData data) {
        return smartwatchDataMapper.insertSmartwatchData(data);
    }

    @Override
    public SmartwatchData getLatestDataByUserId(Integer userId) {
        return smartwatchDataMapper.getLatestDataByUserId(userId);
    }

    @Override
    public List<SmartwatchData> getDataByUserIdAndTimeRange(Integer userId, String startTime, String endTime) {
        return smartwatchDataMapper.getDataByUserIdAndTimeRange(userId, startTime, endTime);
    }

    @Override
    public SmartwatchData getDataById(Integer id) {
        return smartwatchDataMapper.getDataById(id);
    }

    @Override
    public int updateSmartwatchData(SmartwatchData data) {
        return smartwatchDataMapper.updateSmartwatchData(data);
    }

    @Override
    public int deleteSmartwatchData(Integer id) {
        return smartwatchDataMapper.deleteSmartwatchData(id);
    }
}