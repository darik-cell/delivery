CREATE OR REPLACE FUNCTION add_courier_record_fn()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.role = 'ROLE_COURIER' THEN
        INSERT INTO couriers(user_id) VALUES (NEW.user_id);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

