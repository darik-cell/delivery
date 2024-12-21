create table if not exists ingredients
(
    id serial primary key,
    name varchar(255) unique not null,
    is_filter boolean default true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER ingredients_set_timestamp
    BEFORE UPDATE
    ON ingredients
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
