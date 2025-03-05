package cn.edu.tute.schoolproject.controller.home;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Books;
import cn.edu.tute.schoolproject.entity.BorrowRecords;
import cn.edu.tute.schoolproject.service.home.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private DetailService detailService;

    /**
     * 获取所有图书
     */
    @GetMapping("/books")
    public ResponseResult<List<Books>> getAllBooks() {
        return detailService.getAllBooks();
    }

    /**
     * 根据分类获取图书
     */
    @GetMapping("/books/category")
    public ResponseResult<List<Books>> getBooksByCategory(@RequestParam("category") String category) {
        return detailService.getBooksByCategory(category);
    }

    /**
     * 搜索图书（支持按书名或分类搜索）
     */
    @GetMapping("/books/search")
    public ResponseResult<List<Books>> searchBooks(@RequestParam String keyword) {
        // 去除可能的单引号
        keyword = keyword.replace("'", "");
        return detailService.searchBooks(keyword);
    }

    /**
     * 获取所有图书分类
     */
    @GetMapping("/books/categories")
    public ResponseResult<List<String>> getAllCategories() {
        return detailService.getAllCategories();
    }

    /**
     * 查询借阅历史
     */
    @GetMapping("/history/{borrowerId}")
    public ResponseResult<List<BorrowRecords>> getBorrowHistory(@PathVariable Integer borrowerId) {
        return detailService.getBorrowHistory(borrowerId);
    }
}
