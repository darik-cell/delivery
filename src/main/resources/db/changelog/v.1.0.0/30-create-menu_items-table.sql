CREATE TABLE IF NOT EXISTS menu_items
(
    id                  SERIAL PRIMARY KEY,
    restaurant_id       INT            NOT NULL,
    name                VARCHAR(100)   NOT NULL,
    description         TEXT                    DEFAULT NULL,
    price               DECIMAL(10, 2) NOT NULL,
    category            VARCHAR(50) CHECK (category IN
                                           ('Суп', 'Закуска', 'Десерт', 'Основное блюдо', 'Гарнир', 'Салат', 'Напиток')),
    image_url           VARCHAR(255)            DEFAULT NULL,
    availability_status BOOLEAN                 DEFAULT TRUE,
    calories            int            not null,
    weight              int            not null,
    created_at          TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    kitchen_id          int            not null DEFAULT 0,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    FOREIGN KEY (kitchen_id) REFERENCES kitchens (id) ON DELETE SET DEFAULT
);

CREATE TRIGGER menu_items_set_timestamp
    BEFORE UPDATE
    ON menu_items
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
