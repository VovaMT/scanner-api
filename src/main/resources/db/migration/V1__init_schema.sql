CREATE TABLE stores
(
    id       INT IDENTITY (1,1) PRIMARY KEY,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE users
(
    Id          BIGINT IDENTITY (1,1) PRIMARY KEY,
    Name        NVARCHAR(255),
    [Key]       NVARCHAR(255),
    KeyLicense  NVARCHAR(255),
    StoreId     INT NULL,
    DateCreated DATETIME,
    CONSTRAINT FK_users_store FOREIGN KEY (StoreId) REFERENCES stores (id)
);

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