package cn.edu.tute.schoolproject.mapper.home;

import cn.edu.tute.schoolproject.entity.BorrowRecords;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BorrowRecordsMapper {
    // 添加借阅记录
    int insertBorrowRecord(BorrowRecords record);
    
    // 更新归还日期
    int updateReturnDate(BorrowRecords record);
    
    // 查询用户的借阅记录
    List<BorrowRecords> getBorrowRecordsByUser(Integer borrowerId);
    
    // 查询未归还的借阅记录
    List<BorrowRecords> getUnreturnedRecords(Integer borrowerId);
}
