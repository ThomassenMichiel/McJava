CREATE TABLE IF NOT EXISTS ingredient(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  current_stock INT
);

CREATE TABLE IF NOT EXISTS product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  price DECIMAL(6,2)
);

CREATE TABLE IF NOT EXISTS menu(
  id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE IF NOT EXISTS product_ingredient(
  ingredient_id INT,
  product_id INT,
  amount INT,
  CONSTRAINT `fk_product_ingredient_to_ingredient_id` FOREIGN KEY (ingredient_id) REFERENCES ingredient(id),
  CONSTRAINT `fk_product_ingredient_to_product_id` FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS menu_product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT,
  menu_id INT,
  CONSTRAINT `fk_menu_product_to_menu_id` FOREIGN KEY (menu_id) REFERENCES menu(id),
  CONSTRAINT `fk_menu_product_to_product_id` FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS order_item(
  id INT PRIMARY KEY AUTO_INCREMENT,
  amount INT,
  total_price DECIMAL(6,2),
  finished BOOL
);

CREATE TABLE IF NOT EXISTS customer_order(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),
  telephone_number VARCHAR(15),
  finished_cooking BOOL,
  ordered BOOL
);

CREATE TABLE IF NOT EXISTS customer_order_items(
  customer_order_id INT,
  order_item_id INT,
  CONSTRAINT `fk_customer_order_items_to_order_item` FOREIGN KEY (order_item_id) REFERENCES order_item(id),
  CONSTRAINT `fk_customer_order_items_to_customer_order` FOREIGN KEY (customer_order_id) REFERENCES customer_order(id)
);