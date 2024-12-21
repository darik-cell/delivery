CREATE TABLE IF NOT EXISTS users_roles
(
    user_id int not null,
    role varchar(255) not null CHECK (role IN ('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_COURIER', 'ROLE_MANAGER')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role)
);

CREATE TRIGGER users_roles_set_timestamp
    BEFORE UPDATE
    ON users_roles
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- Создание триггера для автоматического добавления записи в couriers при назначении роли ROLE_COURIER
CREATE TRIGGER add_courier_record
    AFTER INSERT ON users_roles
    FOR EACH ROW
EXECUTE FUNCTION add_courier_record_fn();

-- Создание триггера для удаления записи из couriers при удалении роли ROLE_COURIER
CREATE TRIGGER remove_courier_record
    AFTER DELETE ON users_roles
    FOR EACH ROW
EXECUTE FUNCTION remove_courier_record_fn();
