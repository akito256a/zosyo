package com.zosyo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 200, message = "タイトルは200文字以内で入力してください")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Size(max = 100, message = "著者名は100文字以内で入力してください")
    @Column(name = "author", length = 100)
    private String author;

    @Size(max = 50, message = "カテゴリは50文字以内で入力してください")
    @Column(name = "category", length = 50)
    private String category;

    @Min(value = 0, message = "所有冊数は0以上で入力してください")
    @Column(name = "quantity", nullable = false)
    private int quantity = 1;

    @Min(value = 0, message = "在庫数は0以上で入力してください")
    @Column(name = "stock", nullable = false)
    private int stock = 1;

    @Size(max = 255, message = "備考は255文字以内で入力してください")
    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===========================
    // ライフサイクルコールバック
    // ===========================
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ===========================
    // Getter / Setter
    // ===========================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}