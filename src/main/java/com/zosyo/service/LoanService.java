package com.zosyo.service;

import com.zosyo.entity.Book;
import com.zosyo.entity.Loan;
import com.zosyo.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;

    // コンストラクタインジェクション
    public LoanService(LoanRepository loanRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
    }

    // ===========================
    // 全貸出一覧取得
    // ===========================
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return loanRepository.findAllByOrderByLoanedAtDesc();
    }

    // ===========================
    // 書籍IDで貸出一覧取得
    // ===========================
    @Transactional(readOnly = true)
    public List<Loan> findByBookId(Long bookId) {
        return loanRepository.findByBookIdOrderByLoanedAtDesc(bookId);
    }

    // ===========================
    // 貸出処理
    // ===========================
    public void loanBook(Long bookId, Loan loan) {
        Book book = bookService.findById(bookId);

        // 在庫チェック
        if (book.getStock() <= 0) {
            throw new IllegalStateException("在庫がないため貸出できません。");
        }

        // 在庫を1減らす
        book.setStock(book.getStock() - 1);
        bookService.save(book);

        // 貸出レコードを保存
        loan.setBook(book);
        loanRepository.save(loan);
    }

    // ===========================
    // 返却処理
    // ===========================
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("貸出記録が見つかりません。ID: " + loanId));

        Book book = loan.getBook();

        // 在庫を1増やす
        book.setStock(book.getStock() + 1);
        bookService.save(book);

        // 貸出レコードを削除
        loanRepository.delete(loan);
    }
}