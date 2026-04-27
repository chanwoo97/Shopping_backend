"# Shopping_backend" 

## 📊 데이터베이스 설계 (ERD)

```mermaid
erDiagram
    MEMBER ||--o{ ORDER : "places"
    MEMBER ||--o{ REVIEW : "writes"
    MEMBER ||--o{ ADDRESS : "has"
    PRODUCT ||--o{ PRODUCT_IMAGE : "contains"
    PRODUCT ||--o{ REVIEW : "has"
    PRODUCT ||--o{ CART_ITEM : "in"
    PRODUCT ||--o{ ORDER_ITEM : "included_in"
    ORDER ||--o{ ORDER_ITEM : "consists_of"

    MEMBER {
        Long id PK
        String memberId UK
        String email
        String password
        String userName
        String phone
        LocalDate birthDate
        LocalDateTime createdAt
        String role
        boolean social
    }

    PRODUCT {
        Long productId PK
        String productName
        BigDecimal price
        int stock
        double avgRate
        int rateCount
        ProductCategory productTag
    }

    ORDER {
        Long orderId PK
        Long memberId FK
        LocalDateTime orderDate
        Boolean status
        String address
        String addressDetail
        int totalPrice
        String receiverName
        String receiverPhone
    }

    ORDER_ITEM {
        Long orderItemId PK
        Long orderId FK
        Long productId FK
        int quantity
        BigDecimal price
    }

    CART_ITEM {
        Long cartItemId PK
        Long memberId FK
        Long productId FK
        int quantity
    }

    REVIEW {
        Long reviewId PK
        Long productId FK
        Long memberId FK
        String reviewContent
        int rating
        String createdAt
    }

    PRODUCT_IMAGE {
        String uuid PK
        String fileName
        boolean thumbnail
        Long productId FK
    }

    ADDRESS {
        Long id PK
        Long memberId FK
        String address
        String addressDetail
        String zipCode
    }

usecaseDiagram
    actor "User" as U
    actor "Admin" as A

    package "Shopping System" {
        usecase "Login / Signup" as UC1
        usecase "Browse Products" as UC2
        usecase "Search Product" as UC3
        usecase "Manage Cart" as UC4
        usecase "Place Order" as UC5
        usecase "Write Review" as UC6
        usecase "View My Page" as UC7
        
        usecase "Manage Products (CRUD)" as UC8
        usecase "Manage Orders" as UC9
        usecase "Image Upload" as UC10
    }

    U --> UC1
    U --> UC2
    U --> UC3
    U --> UC4
    U --> UC5
    U --> UC6
    U --> UC7

    A --> UC8
    A --> UC9
    A --> UC10
    
    A --|> U
