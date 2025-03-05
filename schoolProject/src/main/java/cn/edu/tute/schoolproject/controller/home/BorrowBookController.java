package cn.edu.tute.schoolproject.controller.home;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Books;
import cn.edu.tute.schoolproject.entity.BorrowRecords;
import cn.edu.tute.schoolproject.service.home.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    /**
     * 借书
     */
    @PostMapping("/borrowBook")
    public ResponseResult<BorrowRecords> borrowBook(@RequestBody Map<String, Object> params) {
        Integer bookId = (Integer) params.get("bookId");
        Integer borrowerId = (Integer) params.get("borrowerId");
        
        if (bookId == null || borrowerId == null) {
            return ResponseResult.error("参数错误");
        }
        
        return borrowBookService.borrowBook(bookId, borrowerId);
    }

}
