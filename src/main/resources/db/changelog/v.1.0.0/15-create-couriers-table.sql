-- Создание таблицы couriers для отслеживания статуса курьеров
CREATE TABLE IF NOT EXISTS couriers (
    user_id INT PRIMARY KEY,
    is_on_shift BOOLEAN DEFAULT FALSE,  -- Флаг, указывающий, на смене ли курьер
    is_on_delivery BOOLEAN DEFAULT FALSE,  -- Флаг, указывающий, на доставке ли курьер
    last_shift_start TIMESTAMP NULL,   -- Время начала последней смены
    last_shift_end TIMESTAMP NULL,     -- Время окончания последней смены
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER couriers_set_timestamp
    BEFORE UPDATE ON couriers
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
