package com.zosyo.repository;

import com.zosyo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // タイトルで部分一致検索（大文字小文字を区別しない）
    List<Book> findByTitleContainingIgnoreCase(String title);

    // カテゴリで検索
    List<Book> findByCategory(String category);

    // タイトルとカテゴリで複合検索
//修正前
    /* @Query("SELECT b FROM Book b WHERE " +
           "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:category IS NULL OR b.category = :category)")
    List<Book> searchBooks(@Param("title") String title,
                           @Param("category") String category); */
// 修正後
@Query("SELECT b FROM Book b WHERE " +
       "(:title IS NULL OR b.title LIKE %:title%) AND " +
       "(:category IS NULL OR b.category = :category)")
List<Book> searchBooks(@Param("title") String title,
                       @Param("category") String category);
}