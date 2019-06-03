CREATE TABLE IF NOT EXISTS ingredient (
  id            INT PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(30) NOT NULL,
  current_stock INT
);

CREATE TABLE IF NOT EXISTS product (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(30) NOT NULL,
  price DECIMAL(6, 2),
  graphic_name varchar(30)
);

CREATE TABLE IF NOT EXISTS premade_menu (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(30),
  price DECIMAL(6, 2),
  graphic_name varchar(30)
);

CREATE TABLE IF NOT EXISTS premade_menu_product (
  premade_menu_id INT,
  product_id      INT,
  CONSTRAINT `fk_premade_menu_product_to_product_id` FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT `fk_premade_menu_product_to_premade_menu_id` FOREIGN KEY (premade_menu_id) REFERENCES premade_menu (id)
);

CREATE TABLE IF NOT EXISTS product_ingredient (
  product_id    INT,
  ingredient_id INT,
  amount        INT,
  CONSTRAINT `fk_product_ingredient_to_ingredient_id` FOREIGN KEY (ingredient_id) REFERENCES ingredient (id),
  CONSTRAINT `fk_product_ingredient_to_product_id` FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS order_item (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  product_id  INT,
  amount      INT,
  total_price DECIMAL(6, 2),
  finished    BOOL,
  CONSTRAINT `fk_order_item_product` FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS customer_order (
  id               INT PRIMARY KEY AUTO_INCREMENT,
  name             VARCHAR(30),
  telephone_number VARCHAR(15),
  finished_cooking BOOL,
  ordered          BOOL
);

CREATE TABLE IF NOT EXISTS customer_order_items (
  customer_order_id INT,
  order_item_id     INT,
  CONSTRAINT `fk_customer_order_items_to_order_item` FOREIGN KEY (order_item_id) REFERENCES order_item (id),
  CONSTRAINT `fk_customer_order_items_to_customer_order` FOREIGN KEY (customer_order_id) REFERENCES customer_order (id)
);

create table if not exists allowed_menu_products (
	premade_menu_id INT,
	menu_item_number INT,
	product_id INT,
	CONSTRAINT `fk_allowed_menu_products_premade_menu_id` FOREIGN KEY(premade_menu_id) REFERENCES premade_menu(id),
	CONSTRAINT `fk_allowed_menu_products_product_id` FOREIGN KEY(product_id) REFERENCES product(id)
);