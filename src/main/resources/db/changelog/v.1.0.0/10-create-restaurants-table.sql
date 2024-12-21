CREATE TABLE IF NOT EXISTS restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into restaurants(name, address, phone, email) values ('test', 'test', 'test', 'test');

CREATE TRIGGER restaurants_set_timestamp
BEFORE UPDATE ON restaurants
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();