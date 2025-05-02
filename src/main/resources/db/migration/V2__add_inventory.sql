CREATE TABLE inventory
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    type       varchar(255),
    start_time datetime,
    end_time   datetime,
    store_id   int,
    user_id    bigint,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
)
GO

ALTER TABLE inventory
    ADD CONSTRAINT FK_INVENTORY_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (id)
GO

ALTER TABLE inventory
    ADD CONSTRAINT FK_INVENTORY_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
GO
