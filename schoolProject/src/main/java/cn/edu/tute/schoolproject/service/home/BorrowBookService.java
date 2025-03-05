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
public class BorrowBookService {
    
    @Autowired
    private BooksMapper booksMapper;
    
    @Autowired
    private BorrowRecordsMapper borrowRecordsMapper;

    /**
     * 借书
     */
    @Transactional
    public ResponseResult<BorrowRecords> borrowBook(Integer bookId, Integer borrowerId) {
        try {
            // 查询图书
            Books book = booksMapper.getBookById(bookId);
            if (book == null) {
                return ResponseResult.error("图书不存在");
            }
            
            // 检查库存
            if (book.getNums() <= 0) {
                return ResponseResult.error("图书库存不足");
            }
            
            // 检查是否有未归还的同一本书
            List<BorrowRecords> unreturnedRecords = borrowRecordsMapper.getUnreturnedRecords(borrowerId);
            for (BorrowRecords record : unreturnedRecords) {
                if (record.getBookId().equals(bookId)) {
                    return ResponseResult.error("您已借阅此书且未归还");
                }
            }
            
            // 创建借阅记录
            BorrowRecords borrowRecord = new BorrowRecords();
            borrowRecord.setBookId(bookId);
            borrowRecord.setBorrowerId(borrowerId);
            borrowRecord.setBorrowDate(new Date());
            
            // 更新库存
            book.setNums(book.getNums() - 1);
            booksMapper.updateBook(book);
            
            // 保存借阅记录
            borrowRecordsMapper.insertBorrowRecord(borrowRecord);
            
            return ResponseResult.success("借阅成功", borrowRecord);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("借阅失败，系统错误");
        }
    }
}
