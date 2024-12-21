-- Вставка ресторана (один ресторан)
INSERT INTO restaurants (name, address, phone, email)
VALUES ('Мой ресторан', 'ул. Тестовая, д. 1', '1234567890', 'info@example.com');

-- Предполагаем, что ID ресторана = 1 (SERIAL, но в тестовой БД при пустой таблице будет 1)

-- Хэш пароля "password" предварительно полученный через BCrypt (пример)
-- Можно использовать любой корректный BCrypt-хэш. Ниже пример хэша.
-- $2a$10$7QH5aExu.xte9zk6GcJUZOzG2.9ShVrKCbZm0LMCHcJqYK1i1CG7i
-- Вставляем пользователей (admin, courier, manager, customer)
INSERT INTO users (name, phone, username, password_hash, address)
VALUES ('Иван Иванов', '79991234567', 'admin', '$2a$10$RNk3wb3ZC1AnjaCMpukYueoddWjdr2zu54TKG6z0XVo4jDYgXAgKa',
        'Адрес администратора'),
       ('Петр Петров', '79990001122', 'courier', '$2a$10$RNk3wb3ZC1AnjaCMpukYueoddWjdr2zu54TKG6z0XVo4jDYgXAgKa',
        'Адрес курьера'),
       ('Сергей Сергеев', '79993334455', 'manager', '$2a$10$RNk3wb3ZC1AnjaCMpukYueoddWjdr2zu54TKG6z0XVo4jDYgXAgKa',
        'Адрес менеджера'),
       ('Алексей Алексеев', '79994445566', 'customer', '$2a$10$RNk3wb3ZC1AnjaCMpukYueoddWjdr2zu54TKG6z0XVo4jDYgXAgKa',
        'Адрес клиента');

-- Предполагаем, что:
-- admin имеет id=1,
-- courier имеет id=2,
-- manager имеет id=3,
-- customer имеет id=4.

-- Назначаем роли
INSERT INTO users_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_COURIER'), -- Эта вставка сгенерирует запись в couriers
       (3, 'ROLE_MANAGER'),
       (4, 'ROLE_CUSTOMER');

-- Вставка ингредиентов
INSERT INTO ingredients (name, is_filter)
VALUES ('Курица', true),
       ('Картофель', true),
       ('Морковь', true),
       ('Яйцо', true),
       ('Горошек', true),
       ('Яблоко', true);

-- Допустим, id ингредиентов будут от 1 до 6 в порядке вставки

-- Вставка позиций меню (menu_items)
-- Категории: 'Суп', 'Салат', 'Напиток'
INSERT INTO menu_items (restaurant_id, name, description, price, category, calories, weight, availability_status)
VALUES (1, 'Суп с курицей', 'Сытный куриный суп', 150.00, 'Суп', 200, 300, true),
       (1, 'Салат "Оливье"', 'Классический оливье', 120.00, 'Салат', 150, 200, true),
       (1, 'Напиток "Компот"', 'Домашний компот', 50.00, 'Напиток', 100, 250, true);

-- Предположим id супа = 1, салата = 2, компота = 3 в таблице menu_items

-- Связки menu_items_ingredients
-- Суп с курицей: Курица(1), Картофель(2), Морковь(3)
INSERT INTO menu_items_ingredients (ingredient_id, menu_item_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

-- Салат Оливье: Картофель(2), Морковь(3), Яйцо(4), Горошек(5)
INSERT INTO menu_items_ingredients (ingredient_id, menu_item_id)
VALUES (2, 2),
       (3, 2),
       (4, 2),
       (5, 2);

-- Компот: Яблоко(6)
INSERT INTO menu_items_ingredients (ingredient_id, menu_item_id)
VALUES (6, 3);

-- Создадим заказ от клиента (id=4) в ресторане (id=1)
INSERT INTO orders (client_id, restaurant_id, status, total_price, payment_method, payment_status, delivery_address)
VALUES (4, 1, 'в обработке', 320.00, 'Карта', 'Не оплачено', 'ул. Заказная, д. 2');

-- Предположим id заказа = 1

-- Добавим order_items для заказа №1:
-- Суп (150), Салат (120), Компот (50)
INSERT INTO order_items (order_id, menu_item_id, quantity, price_at_order_time)
VALUES (1, 1, 1, 150.00),
       (1, 2, 1, 120.00),
       (1, 3, 1, 50.00);
