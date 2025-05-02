CREATE TABLE inventory_items
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    good_code  varchar(255),
    quantity   float(53),
    type       int,
    scanned_at varchar(255),
    updated_at varchar(255),
    device_key varchar(255),
    CONSTRAINT pk_inventory_items PRIMARY KEY (id)
)
