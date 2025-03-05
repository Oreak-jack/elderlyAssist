package cn.edu.tute.schoolproject.service.home;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Books;
import cn.edu.tute.schoolproject.entity.BorrowRecords;
import cn.edu.tute.schoolproject.mapper.home.BooksMapper;
import cn.edu.tute.schoolproject.mapper.home.BorrowRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReturnBookService {
    
    @Autowired
    private BooksMapper booksMapper;
    
    @Autowired
    private BorrowRecordsMapper borrowRecordsMapper;

    /**
     * 还书
     */
    @Transactional
    public ResponseResult<BorrowRecords> returnBook(Integer bookId, Integer borrowerId) {
        try {
            // 查询借阅记录
            List<BorrowRecords> records = borrowRecordsMapper.getUnreturnedRecords(borrowerId);
            BorrowRecords record = records.stream()
                    .filter(r -> r.getBookId().equals(bookId))
                    .findFirst()
                    .orElse(null);
            
            if (record == null) {
                return ResponseResult.error("未找到借阅记录");
            }
            
            // 更新归还日期
            record.setReturnDate(new Date());
            borrowRecordsMapper.updateReturnDate(record);
            
            // 更新库存
            Books book = booksMapper.getBookById(bookId);
            book.setNums(book.getNums() + 1);
            booksMapper.updateBook(book);
            
            return ResponseResult.success("归还成功", record);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("归还失败，系统错误");
        }
    }
}
