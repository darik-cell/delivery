create table if not exists menu_items_ingredients
(
    ingredient_id bigint not null,
    menu_item_id bigint not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key (ingredient_id, menu_item_id),
    foreign key (ingredient_id) references ingredients (id),
    foreign key (menu_item_id) references menu_items (id) on delete cascade
);

CREATE TRIGGER menu_items_ingredients_set_timestamp
    BEFORE UPDATE
    ON menu_items_ingredients
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
