# 📚 Zosyo（蔵書）- 社内蔵書管理システム

## 概要
社員教育のために会社が購入した書籍の在庫数・貸出状況を管理するWebアプリケーションです。
前職の総務・経理業務での課題をシステム化しました。

## 使用技術
| 分類 | 技術 |
|---|---|
| バックエンド | Java 21 / Spring Boot 4.0.6 / Spring Data JPA |
| フロントエンド | Thymeleaf / HTML / CSS / Bootstrap 5 |
| データベース | PostgreSQL 16 |
| バージョン管理 | Git / GitHub |
| ビルドツール | Maven |

## システム構成
```
PC1（開発機）   : Windows 11 / VSCode
PC2（APサーバー）: Ubuntu Server 24.04 / Spring Boot
PC3（DBサーバー）: Ubuntu Server 24.04 / PostgreSQL 16
```

## 機能一覧
- 蔵書一覧表示
- 蔵書登録
- 蔵書編集
- 蔵書削除
- タイトル・カテゴリでの検索
- 在庫数の管理（在庫あり：緑 / 在庫なし：赤）

## ER図
```
books
  ├── id          BIGSERIAL PRIMARY KEY
  ├── title       VARCHAR(200) NOT NULL
  ├── author      VARCHAR(100)
  ├── category    VARCHAR(50)
  ├── quantity    INT NOT NULL DEFAULT 1
  ├── stock       INT NOT NULL DEFAULT 1
  ├── note        VARCHAR(255)
  ├── created_at  TIMESTAMP
  └── updated_at  TIMESTAMP
```

## 起動方法
```bash
# リポジトリのクローン
git clone https://github.com/akito256a/zosyo.git

# DB接続設定
src/main/resources/application.properties を編集

# 起動
./mvnw spring-boot:run
```

## 今後の拡張予定
- 貸出・返却機能
- Spring Security によるログイン機能
- CSVエクスポート機能
- ページネーション