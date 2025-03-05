package cn.edu.tute.schoolproject.controller.home;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.BorrowRecords;
import cn.edu.tute.schoolproject.service.home.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/return")
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    /**
     * 还书
     */
    @PostMapping("/returnBook")
    public ResponseResult<BorrowRecords> returnBook(@RequestBody Map<String, Object> params) {
        Integer bookId = (Integer) params.get("bookId");
        Integer borrowerId = (Integer) params.get("borrowerId");
        
        if (bookId == null || borrowerId == null) {
            return ResponseResult.error("参数错误");
        }
        
        return returnBookService.returnBook(bookId, borrowerId);
    }
}
