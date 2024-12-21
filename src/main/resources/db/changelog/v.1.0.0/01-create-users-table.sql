CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    phone VARCHAR,
    username VARCHAR UNIQUE NOT NULL,
    password_hash VARCHAR,
    address VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER user_set_timestamp
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();