CREATE TABLE IF NOT EXISTS order_items
(
    id       SERIAL PRIMARY KEY,
    order_id            INT            NOT NULL,
    menu_item_id        INT            NOT NULL,
    quantity            INT       DEFAULT 1,
    price_at_order_time DECIMAL(10, 2) NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES menu_items (id)
);

CREATE TRIGGER order_items_set_timestamp
    BEFORE UPDATE
    ON order_items
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
