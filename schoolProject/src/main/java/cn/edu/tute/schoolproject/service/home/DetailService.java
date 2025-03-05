package cn.edu.tute.schoolproject.service.home;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Books;
import cn.edu.tute.schoolproject.entity.BorrowRecords;
import cn.edu.tute.schoolproject.mapper.home.BooksMapper;
import cn.edu.tute.schoolproject.mapper.home.BorrowRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    
    @Autowired
    private BooksMapper booksMapper;
    
    @Autowired
    private BorrowRecordsMapper borrowRecordsMapper;

    /**
     * 获取所有图书
     */
    public ResponseResult<List<Books>> getAllBooks() {
        try {
            List<Books> books = booksMapper.getAllBooks();
            if (books != null && !books.isEmpty()) {
                return ResponseResult.success("查询成功", books);
            } else {
                return ResponseResult.success("暂无图书数据", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("查询失败，系统错误");
        }
    }

    /**
     * 根据分类获取图书
     */
    public ResponseResult<List<Books>> getBooksByCategory(String category) {
        try {
            List<Books> books = booksMapper.getBooksByCategory(category);
            return ResponseResult.success("查询成功", books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("查询失败，系统错误");
        }
    }

    /**
     * 搜索图书（支持按书名或分类搜索）
     */
    public ResponseResult<List<Books>> searchBooks(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                return ResponseResult.error("搜索关键词不能为空");
            }
            
            List<Books> books = booksMapper.searchBooks(keyword.trim());
            if (books != null && !books.isEmpty()) {
                return ResponseResult.success("查询成功", books);
            } else {
                return ResponseResult.success("未找到相关图书", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("查询失败，系统错误");
        }
    }

    /**
     * 获取所有图书分类
     */
    public ResponseResult<List<String>> getAllCategories() {
        try {
            List<String> categories = booksMapper.getAllCategories();
            return ResponseResult.success("查询成功", categories);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("查询失败，系统错误");
        }
    }

    /**
     * 查询用户的借阅记录
     */
    public ResponseResult<List<BorrowRecords>> getBorrowHistory(Integer borrowerId) {
        try {
            List<BorrowRecords> records = borrowRecordsMapper.getBorrowRecordsByUser(borrowerId);
            return ResponseResult.success("查询成功", records);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("查询失败，系统错误");
        }
    }
}
