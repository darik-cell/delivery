CREATE TABLE IF NOT EXISTS
    orders
(
    id               SERIAL PRIMARY KEY,
    client_id        INT            NOT NULL,
    restaurant_id    INT            NOT NULL,
    status           VARCHAR(20) DEFAULT 'в обработке' CHECK (status IN
                                                              ('в обработке', 'готовится', 'назначен курьер', 'в пути',
                                                               'доставлен', 'отменен')),
    total_price      DECIMAL(10, 2) NOT NULL,
    payment_method   VARCHAR(20) DEFAULT 'Карта' CHECK (payment_method IN ('Карта', 'Электронные кошельки', 'Наличные')),
    payment_status   VARCHAR(20) DEFAULT 'Не оплачено' CHECK (payment_status IN ('Оплачено', 'Не оплачено')),
    order_time       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    delivery_address VARCHAR(255)   NOT NULL,
    delivery_time    TIMESTAMP,
    created_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    courier_id       INT         DEFAULT NULL,
    FOREIGN KEY (client_id) REFERENCES users (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    FOREIGN KEY (courier_id) REFERENCES couriers (user_id)
);

CREATE TRIGGER orders_set_timestamp
    BEFORE UPDATE
    ON orders
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
