-- Таблиця магазинів
CREATE TABLE stores
(
    id       INT IDENTITY (1,1) PRIMARY KEY,
    location VARCHAR(255) NOT NULL
);

-- Таблиця користувачів
CREATE TABLE users
(
    id          BIGINT IDENTITY (1,1) PRIMARY KEY,
    Name        VARCHAR(255),
    [key]       VARCHAR(255) UNIQUE NOT NULL,
    Keylicense  VARCHAR(255),
    StoreId     INT                 NULL,
    Role        INT,
    Datecreated DATETIME2,
    FOREIGN KEY (StoreId) REFERENCES stores (id) -- активний магазин
);

-- Таблиця зв'язку між користувачами і магазинами
CREATE TABLE user_store
(
    user_id  BIGINT NOT NULL,
    store_id INT    NOT NULL,
    PRIMARY KEY (user_id, store_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES stores (id) ON DELETE CASCADE
);

-- Таблиця товарів
CREATE TABLE good
(
    good_id             INT IDENTITY (1,1) PRIMARY KEY,
    bar_code            VARCHAR(255),
    good_code           VARCHAR(255),
    name                VARCHAR(255),
    black_mail_category INT,
    box_bar_code        VARCHAR(255),
    end_sale_date       VARCHAR(255),
    excise              VARCHAR(255),
    in_matrix           BIT,
    is_excise           BIT,
    is_production       BIT,
    mask                VARCHAR(255),
    price               FLOAT,
    price_status        INT,
    provider_name       VARCHAR(255),
    reservation_type    BIT,
    stock_count         FLOAT,
    unit                VARCHAR(255)
);

-- Таблиця інвентаризацій
CREATE TABLE inventory
(
    id         BIGINT IDENTITY (1,1) PRIMARY KEY,
    type       VARCHAR(50),
    start_time DATETIME2,
    end_time   DATETIME2,
    store_id   INT,
    user_id    BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Таблиця позицій інвентаризації
CREATE TABLE inventory_items
(
    id           BIGINT IDENTITY (1,1) PRIMARY KEY,
    good_code    VARCHAR(100),
    quantity     FLOAT,
    type         INT,
    scanned_at   VARCHAR(100),
    updated_at   VARCHAR(100),
    inventory_id BIGINT,
    FOREIGN KEY (inventory_id) REFERENCES inventory (id) ON DELETE CASCADE
);
