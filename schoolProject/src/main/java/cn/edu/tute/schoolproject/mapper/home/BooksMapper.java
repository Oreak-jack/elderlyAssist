package cn.edu.tute.schoolproject.mapper.home;

import cn.edu.tute.schoolproject.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BooksMapper {
    // 查询所有图书
    List<Books> getAllBooks();
    
    // 根据ID查询图书
    Books getBookById(Integer id);
    
    // 更新图书信息（主要是库存数量）
    int updateBook(Books book);
    
    // 根据分类查询图书
    List<Books> getBooksByCategory(String category);
    
    // 获取所有图书分类
    List<String> getAllCategories();

    // 根据书名或分类模糊查询
    List<Books> searchBooks(@Param("keyword") String keyword);
}
