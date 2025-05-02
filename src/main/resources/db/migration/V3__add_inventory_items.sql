CREATE TABLE inventory_items
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    good_code    varchar(255),
    quantity     float(53),
    type         int,
    scanned_at   varchar(255),
    updated_at   varchar(255),
    inventory_id bigint,
    CONSTRAINT pk_inventory_items PRIMARY KEY (id)
)
    GO

ALTER TABLE inventory_items
    ADD CONSTRAINT FK_INVENTORY_ITEMS_ON_INVENTORY FOREIGN KEY (inventory_id) REFERENCES inventory (id)
    GO