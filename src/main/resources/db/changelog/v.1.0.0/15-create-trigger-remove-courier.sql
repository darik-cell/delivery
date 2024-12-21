CREATE OR REPLACE FUNCTION remove_courier_record_fn()
RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.role = 'ROLE_COURIER' THEN
        DELETE FROM couriers WHERE user_id = OLD.user_id;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;