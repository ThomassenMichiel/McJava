#   first create all ingredients referenced by prodcuts are created

insert into ingredient values (1,'chicken_for_wrap',400);
insert into ingredient values (2,'wrap_for_wrap',100);
insert into ingredient values (3,'assorted_vegetables_for_wrap',100);
insert into ingredient values (4,'wrap_for_wrap_small',100);
insert into ingredient values (5,'bun_mcfish',100);
insert into ingredient values (6,'fish_patty',100);
insert into ingredient values (7,'lettuce',500);
insert into ingredient values (8,'burger_sauce_mcfish',100);
insert into ingredient values (9,'bun_hamburger',100);
insert into ingredient values (10,'hamburger_patty',100);
insert into ingredient values (11,'pickles',100);
insert into ingredient values (12,'burger_sauce_hamburger',400);
insert into ingredient values (13,'cheeseburger_cheese',400);
insert into ingredient values (14,'chicken_mcnugget',600);
insert into ingredient values (15,'veggy_mcnugget',10);
insert into ingredient values (16,'fries_small',500);
insert into ingredient values (17,'carrots_small',100);
insert into ingredient values (18,'drink_cola_25',1000);
insert into ingredient values (19,'drink_cola_zero_25',1000);
insert into ingredient values (20,'drink_cola_light_25',1000);
insert into ingredient values (21,'drink_sprite_25',1000);
insert into ingredient values (22,'drink_fanta_25',1000);
insert into ingredient values (23,'drink_applejuice_25',1000);
insert into ingredient values (24,'drink_orangejuice_25',1000);
insert into ingredient values (25,'drink_water_flat_25',1000);
insert into ingredient values (26,'drink_water_sparkling_25',1000);
insert into ingredient values (27,'drink_fristi_25',1000);
insert into ingredient values (28,'petit_lu',50);
insert into ingredient values (29,'icecream_cone',50);
insert into ingredient values (30,'ice_squeeze',50);
insert into ingredient values (31,'pineapple_chunks',50);
insert into ingredient values (32,'actimel',50);
insert into ingredient values (33,'danonino',50);
insert into ingredient values (34,'fries_medium',500);
insert into ingredient values (35,'salad_mixed_medium',100);
insert into ingredient values (36,'drink_water_flat_33',100);
insert into ingredient values (37,'drink_water_sparkling_33',100);
insert into ingredient values (38,'drink_orangejuice_33',100);
insert into ingredient values (39,'drink_cola_33',1000);
insert into ingredient values (40,'drink_cola_zero_33',1000);
insert into ingredient values (41,'drink_cola_light_33',1000);
insert into ingredient values (42,'drink_sprite_33',1000);
insert into ingredient values (43,'drink_fanta_33',1000);
insert into ingredient values (44,'fries_large',1000);
insert into ingredient values (45,'salad_mixed_large',1000);
insert into ingredient values (46,'drink_water_flat_50',100);
insert into ingredient values (47,'drink_water_sparkling_50',100);
insert into ingredient values (48,'drink_orangejuice_50',100);
insert into ingredient values (49,'drink_cola_50',1000);
insert into ingredient values (50,'drink_cola_zero_50',1000);
insert into ingredient values (51,'drink_cola_light_50',1000);
insert into ingredient values (52,'drink_sprite_50',1000);
insert into ingredient values (53,'drink_fanta_50',1000);
insert into ingredient values (54,'salad_cheese_bacon',150);
insert into ingredient values (55,'salad_pesto_mozzarella',150);
insert into ingredient values (56,'salad_ceasar',150);
insert into ingredient values (57,'bun_don_j',200);
insert into ingredient values (58,'patty_beef_100',1000);
insert into ingredient values (59,'cheeze_chrizo_mancheo',200);
insert into ingredient values (60,'burger_sauce_don_j',200);
insert into ingredient values (61,'tapenade_don_j',200);
insert into ingredient values (62,'tomatoes_sun_dried',200);
insert into ingredient values (63,'burger_salad_batavia',200);
insert into ingredient values (64,'burger_bacon_extra_long',200);
insert into ingredient values (65,'cheese_maredsous',200);
insert into ingredient values (66,'tomato_slice',500);
insert into ingredient values (67,'red_onion_slice',500);
insert into ingredient values (68,'burger_sauce_lemon_mayo',200);
insert into ingredient values (69,'bun_brioche_lineseed',200);
insert into ingredient values (70,'patty_chicken_filet',200);
insert into ingredient values (71,'burger_salad_standard',500);
insert into ingredient values (72,'bun_big_mac',500);
insert into ingredient values (73,'cheese_cheddar',500);
insert into ingredient values (74,'standard_onion_slice',500);
insert into ingredient values (75,'burger_sauce_big_mac',500);
insert into ingredient values (76,'burger_sauce_ketchup',500);
insert into ingredient values (77,'burger_sauce_mustard',500);
insert into ingredient values(78,'fries_sauce_mayo',1000);
insert into ingredient values(79,'fries_sauce_ketchup',1000);
insert into ingredient values(80,'fries_sauce_sweet mayo',1000);
insert into ingredient values(81,'fries_sauce_coctail',1000);
insert into ingredient values(82,'salad_sauce_dressing',500);
insert into ingredient values(83,'salad_sauce_coctail',500);
insert into ingredient values(84,'salad_sauce_ceasar',500);
insert into ingredient values(85,'salad_sauce_vinegrette',500);

#   create products and use product_ingredient to attach one or more ingredients to a product
#   4 premade menus are created
#   only 2 products are attached to a premade_menu by using premade_menu_product (carrots & fries are attached to the Happy Meal)
#   because thats the only thing that is default in that meal
#   all the rest in all premade menus is variable and to be chosen when ordering

    insert into product values (1,'McWrap Chicken',4,'chickwrapmout.png');
        insert into product_ingredient values (1,1,3);
        insert into product_ingredient values (1,2,1);
        insert into product_ingredient values (1,3,3);
    insert into product values (2,'mcWrap Veggie',3,'mcwrapveggie.png');
        insert into product_ingredient values (2,2,1);
        insert into product_ingredient values (2,3,1);
    insert into product values (3,'McWrap Chicken Small',2,'chickwrapmout.png');
        insert into product_ingredient values (3,1,2);
        insert into product_ingredient values (3,4,1);
        insert into product_ingredient values (3,3,2);
insert into premade_menu values (1,'Happy Meal',4,'happy_meal.jpg');
    insert into product values (5,'McFish',4,'fishburger.png');
        insert into product_ingredient values(5,5,1);
        insert into product_ingredient values(5,6,1);
        insert into product_ingredient values(5,7,1);
        insert into product_ingredient values(5,8,1);
    insert into product values (6,'Hamburger',2,'hamburger.png');
        insert into product_ingredient values(6,9,1);
        insert into product_ingredient values(6,10,1);
        insert into product_ingredient values(6,11,1);
        insert into product_ingredient values(6,12,1);
    insert into product values (7,'Cheeseburger',2.5,'cheeseburger.png');
        insert into product_ingredient values(7,9,1);
        insert into product_ingredient values(7,10,1);
        insert into product_ingredient values(7,11,1);
        insert into product_ingredient values(7,76,1);
        insert into product_ingredient values(7,13,1);
    insert into product values (8,'Chicken McNuggets Small',2,'4chickenmcnuggets.png');
        insert into product_ingredient values(8,14,4);
    insert into product values (9,'Veggy McNuggets Small',2,'vegi-nuggets.png');
        insert into product_ingredient values(9,15,4);
    insert into product values (10,'Fries Small',2,'small-fries.png');
        insert into product_ingredient values(10,16,1);
            insert into premade_menu_product values (1,10);
    insert into product values (11,'Carrots Small',0.5,'crackycarrots.png');
        insert into product_ingredient values(11,17,1);
            insert into premade_menu_product values (1,11);
    insert into product values (12,'Drink Cola 25 cl.',1,'cola.png');
        insert into product_ingredient values(12,18,1);
    insert into product values (13,'Drink Cola Zero 25 cl.',1,'colazero.png');
        insert into product_ingredient values(13,19,1);
    insert into product values (14,'Drink Cola Light 25 cl.',1,'02_colalight.png');
        insert into product_ingredient values(14,20,1);
    insert into product values (15,'Drink Sprite 25 cl.',1,'sprite.png');
        insert into product_ingredient values(15,21,1);
    insert into product values (16,'Drink Fanta 25 cl.',1,'fanta.png');
        insert into product_ingredient values(16,22,1);
    insert into product values (17,'Drink Apple Juice 25 cl.',1,'tropicana-appel-small.png');
        insert into product_ingredient values(17,23,1);
    insert into product values (18,'Drink Orange Juice 25 cl.',1,'11_tropicana_2.png');
        insert into product_ingredient values(18,24,1);
    insert into product values (19,'Drink Water 25 cl.',1,'vittel_sml.png');
        insert into product_ingredient values(19,25,1);
    insert into product values (20,'Drink Water Perrier 25 cl.',1,'perrier_sml.png');
        insert into product_ingredient values(20,26,1);
    insert into product values (21,'Drink Fristi',1,'09_fristi_2.png');
        insert into product_ingredient values(21,27,1);
    insert into product values (22,'Petit Lu',0.5,'petit-lu.png');
        insert into product_ingredient values(22,28,1);
    insert into product values (23,'IceCream Cone',0.5,'hoorntje.png');
        insert into product_ingredient values(23,29,1);
    insert into product values (24,'Ice Squeeze',0.5,'icesqueeze.png');
        insert into product_ingredient values(24,30,1);
    insert into product values (25,'Fresh Pineapple Chunks',0.5,'ananas.jpg');
        insert into product_ingredient values(25,31,1);
    insert into product values (26,'Actimel',0.5,'actimel.png');
        insert into product_ingredient values(26,32,1);
    insert into product values (27,'Danonino',0.5,'danonino.png');
        insert into product_ingredient values(27,33,1);
insert into premade_menu values (2,'Medium Menu',6,'menu.png');
    insert into product values (28,'Medium French Fries',1.5,'medium-fries.png');
        insert into product_ingredient values(28,34,1);
    insert into product values (29,'Medium Mixed Salad',2,'side-salad.png');
        insert into product_ingredient values(29,35,1);
    insert into product values (30,'Drink Water Vittel 33 cl.',1.5,'vittel_sml.png');
        insert into product_ingredient values(30,36,1);
    insert into product values (31,'Drink Water Perrier 33 cl.',1.5,'perrier_sml.png');
        insert into product_ingredient values(31,37,1);
    insert into product values (32,'Drink Tropicana 33 cl.',2,'11_tropicana_2.png');
        insert into product_ingredient values(32,38,1);
    insert into product values (33,'Drink Cola 33 cl.',1.5,'cola.png');
        insert into product_ingredient values(33,39,1);
    insert into product values (34,'Drink Cola Zero 33 cl.',1.5,'colazero.png');
        insert into product_ingredient values(33,40,1);
    insert into product values (35,'Drink Cola Light 33 cl.',1.5,'02_colalight.png');
        insert into product_ingredient values(34,41,1);
    insert into product values (36,'Drink Sprite 33 cl.',1.5,'sprite.png');
        insert into product_ingredient values(35,42,1);
    insert into product values (37,'Drink Fanta 33 cl.',1.5,'fanta.png');
        insert into product_ingredient values(37,43,1);
insert into premade_menu values (3,'Large Menu',8,'large menu.png');
    insert into product values (38,'Large French Fries',2,'large-fries.png');
        insert into product_ingredient values(38,44,1);
    insert into product values (39,'Large Mixed Salad',2,'side-salad.png');
        insert into product_ingredient values(39,45,1);
    insert into product values (40,'Drink Water Vittel 50 cl.',1.5,'vittel_sml.png');
        insert into product_ingredient values(40,46,1);
    insert into product values (41,'Drink Water Perrier 50 cl.',1.5,'perrier_sml.png');
        insert into product_ingredient values(41,47,1);
    insert into product values (42,'Drink Tropicana 50 cl.',2,'11_tropicana_2.png');
        insert into product_ingredient values(42,48,1);
    insert into product values (43,'Drink Cola 50 cl.',1.5,'cola.png');
        insert into product_ingredient values(43,49,1);
    insert into product values (44,'Drink Cola Zero 50 cl.',1.5,'colazero.png');
        insert into product_ingredient values(44,50,1);
    insert into product values (45,'Drink Cola Light 50 cl.',1.5,'02_colalight.png');
        insert into product_ingredient values(45,51,1);
    insert into product values (46,'Drink Sprite 50 cl.',1.5,'sprite.png');
        insert into product_ingredient values(46,52,1);
    insert into product values (47,'Drink Fanta 50 cl.',1.5,'fanta.png');
        insert into product_ingredient values(47,53,1);
insert into premade_menu values (4,'Salads Menu',6,'salads_menu.png');
    insert into product values (48,'Cheese and Bacon Salad',4.5,'salad_bacon.png');
        insert into product_ingredient values(48,54,1);
    insert into product values (49,'Pesto and Mozzarella Salad',4.5,'salad_moza.png');
        insert into product_ingredient values(49,55,1);
    insert into product values (50,'Ceasar Salad',5,'grilledchickensalad.png');
        insert into product_ingredient values(50,56,1);
    insert into product values (51,'Irresistible don Juan',8,'don_juan.png');
        insert into product_ingredient values(51,57,1);
        insert into product_ingredient values(51,58,2);
        insert into product_ingredient values(51,59,1);
        insert into product_ingredient values(51,60,1);
        insert into product_ingredient values(51,61,1);
        insert into product_ingredient values(51,62,1);
        insert into product_ingredient values(51,63,1);
    insert into product values (52,'Maestro generous Jack',4,'generousjack.png');
        insert into product_ingredient values(52,58,2);
        insert into product_ingredient values(52,64,2);
        insert into product_ingredient values(52,65,1);
        insert into product_ingredient values(52,66,1);
        insert into product_ingredient values(52,67,1);
        insert into product_ingredient values(52,63,1);
        insert into product_ingredient values(52,68,2);
        insert into product_ingredient values(52,69,1);
    insert into product values (53,'Maestro generous Jaqueline',4,'jacqueline.png');
        insert into product_ingredient values(53,70,2);
        insert into product_ingredient values(53,64,2);
        insert into product_ingredient values(53,65,1);
        insert into product_ingredient values(53,71,1);
        insert into product_ingredient values(53,68,2);
        insert into product_ingredient values(53,69,1);
    insert into product values (54,'Big Mac',3.5,'bigmac.png');
        insert into product_ingredient values(54,72,1);
        insert into product_ingredient values(54,58,2);
        insert into product_ingredient values(54,73,2);
        insert into product_ingredient values(54,74,2);
        insert into product_ingredient values(54,11,3);
        insert into product_ingredient values(54,71,1);
        insert into product_ingredient values(54,75,1);
    insert into product values (55,'Double Cheese',2.5,'doublecheese.png');
        insert into product_ingredient values(55,58,2);
        insert into product_ingredient values(55,9,1);
        insert into product_ingredient values(55,11,2);
        insert into product_ingredient values(55,76,1);
        insert into product_ingredient values(55,77,1);
    insert into product values (56,'Portion of mayonaise',0.5,'mayonaise.png');
        insert into product_ingredient values(56,78,1);
    insert into product values (57,'Portion of ketchup',0.5,'ketchup.png');
        insert into product_ingredient values(57,79,1);
    insert into product values (58,'Portion of sweet mayonaise',0.5,'frietsaus.png');
        insert into product_ingredient values(58,80,1);
    insert into product values (59,'Portion of coctail',0.5,'sauce_aigre_douce_reg_0.png');
        insert into product_ingredient values(59,81,1);
    insert into product values (60,'Dressing for salad',0.5,'pesto_dressing_306x134.png');
        insert into product_ingredient values(60,82,1);
    insert into product values (61,'Coctail sauce for salad',0.5,'sauce_aigre_douce_reg_0.png');
        insert into product_ingredient values(61,83,1);
    insert into product values (62,'Ceasar sauce for salad',0.5,'curry_306x134.png');
        insert into product_ingredient values(62,84,1);
    insert into product values (63,'Vinegrette for salad',0.5,'saucesmenu_0.png');
        insert into product_ingredient values(63,85,1);


#	happy meal (id = 1)

#		small sandwich or nuggets

insert into allowed_menu_products values (1,1,5);
insert into allowed_menu_products values (1,1,6);
insert into allowed_menu_products values (1,1,7);
insert into allowed_menu_products values (1,1,8);

#		drink 25 cl.
insert into allowed_menu_products values (1,2,12);
insert into allowed_menu_products values (1,2,13);
insert into allowed_menu_products values (1,2,14);
insert into allowed_menu_products values (1,2,15);
insert into allowed_menu_products values (1,2,16);
insert into allowed_menu_products values (1,2,17);
insert into allowed_menu_products values (1,2,18);
insert into allowed_menu_products values (1,2,19);
insert into allowed_menu_products values (1,2,20);
insert into allowed_menu_products values (1,2,21);

#		dessert
insert into allowed_menu_products values (1,3,22);
insert into allowed_menu_products values (1,3,23);
insert into allowed_menu_products values (1,3,24);
insert into allowed_menu_products values (1,3,25);
insert into allowed_menu_products values (1,3,26);
insert into allowed_menu_products values (1,3,27);

#		sauce
insert into allowed_menu_products values (1,4,56);
insert into allowed_menu_products values (1,4,57);
insert into allowed_menu_products values (1,4,58);
insert into allowed_menu_products values (1,4,59);

#		defaults present in curent menu (small fries and carrots)
insert into allowed_menu_products values (1,5,10);
insert into allowed_menu_products values (1,6,11);

#	Medium Menu(id = 2)

#		sandwich of your choice
insert into allowed_menu_products values (2,1,51);
insert into allowed_menu_products values (2,1,52);
insert into allowed_menu_products values (2,1,53);
insert into allowed_menu_products values (2,1,54);
insert into allowed_menu_products values (2,1,55);

#		fries or Mixed Salad
insert into allowed_menu_products values (2,2,28);
insert into allowed_menu_products values (2,2,29);

#		drink 33 cl
insert into allowed_menu_products values (2,3,30);
insert into allowed_menu_products values (2,3,31);
insert into allowed_menu_products values (2,3,32);
insert into allowed_menu_products values (2,3,33);
insert into allowed_menu_products values (2,3,34);
insert into allowed_menu_products values (2,3,35);
insert into allowed_menu_products values (2,3,36);
insert into allowed_menu_products values (2,3,37);

#		sauce
insert into allowed_menu_products values (2,4,56);
insert into allowed_menu_products values (2,4,57);
insert into allowed_menu_products values (2,4,58);
insert into allowed_menu_products values (2,4,59);


#	Large Menu (id = 3)

#		sandwich of your choice
insert into allowed_menu_products values (3,1,51);
insert into allowed_menu_products values (3,1,52);
insert into allowed_menu_products values (3,1,53);
insert into allowed_menu_products values (3,1,54);
insert into allowed_menu_products values (3,1,55);

#		fries or Mixed Salad
insert into allowed_menu_products values (3,2,38);
insert into allowed_menu_products values (3,2,39);

#		drinks 50 cl
insert into allowed_menu_products values (3,3,40);
insert into allowed_menu_products values (3,3,41);
insert into allowed_menu_products values (3,3,42);
insert into allowed_menu_products values (3,3,43);
insert into allowed_menu_products values (3,3,44);
insert into allowed_menu_products values (3,3,45);
insert into allowed_menu_products values (3,3,46);
insert into allowed_menu_products values (3,3,47);

#		sauce
insert into allowed_menu_products values (3,4,56);
insert into allowed_menu_products values (3,4,57);
insert into allowed_menu_products values (3,4,58);
insert into allowed_menu_products values (3,4,59);

#	Salads Menu (id = 4)

#		Cheese & Bacon Salad (BACON) OR Pesto & Mozzarella Salad (MOZZARELLA) OR Caesar Salad (GEGRILDE KIP)
insert into allowed_menu_products values (4,1,48);
insert into allowed_menu_products values (4,1,49);
insert into allowed_menu_products values (4,1,50);

#			drinks 33 cl

insert into allowed_menu_products values (4,2,30);
insert into allowed_menu_products values (4,2,31);
insert into allowed_menu_products values (4,2,32);
insert into allowed_menu_products values (4,2,33);
insert into allowed_menu_products values (4,2,34);
insert into allowed_menu_products values (4,2,35);
insert into allowed_menu_products values (4,2,36);
insert into allowed_menu_products values (4,2,37);

#			sauce

insert into allowed_menu_products values (4,3,60);
insert into allowed_menu_products values (4,3,61);
insert into allowed_menu_products values (4,3,62);
insert into allowed_menu_products values (4,3,63);

#	menu for burgers only with zero menu-price

insert into premade_menu values (5,'Burgers Menu',0,'bigmac.png');
	insert into allowed_menu_products values (5,1,5);
	insert into allowed_menu_products values (5,1,5);
	insert into allowed_menu_products values (5,1,6);
	insert into allowed_menu_products values (5,1,7);
	insert into allowed_menu_products values (5,1,51);
	insert into allowed_menu_products values (5,1,52);
	insert into allowed_menu_products values (5,1,53);
	insert into allowed_menu_products values (5,1,54);
	insert into allowed_menu_products values (5,1,55);

#	menu for drinks, zero menu-price

insert into premade_menu values (6,'Drinks Menu',0,'cola.png');
	insert into allowed_menu_products values (6,1,12);
	insert into allowed_menu_products values (6,1,13);
	insert into allowed_menu_products values (6,1,14);
	insert into allowed_menu_products values (6,1,15);
	insert into allowed_menu_products values (6,1,16);
	insert into allowed_menu_products values (6,1,17);
	insert into allowed_menu_products values (6,1,18);
	insert into allowed_menu_products values (6,1,19);
	insert into allowed_menu_products values (6,1,20);
	insert into allowed_menu_products values (6,1,21);
	insert into allowed_menu_products values (6,1,30);
	insert into allowed_menu_products values (6,1,31);
	insert into allowed_menu_products values (6,1,32);
	insert into allowed_menu_products values (6,1,33);
	insert into allowed_menu_products values (6,1,34);
	insert into allowed_menu_products values (6,1,35);
	insert into allowed_menu_products values (6,1,36);
	insert into allowed_menu_products values (6,1,37);
	insert into allowed_menu_products values (6,1,40);
	insert into allowed_menu_products values (6,1,41);
	insert into allowed_menu_products values (6,1,42);
	insert into allowed_menu_products values (6,1,43);
	insert into allowed_menu_products values (6,1,44);
	insert into allowed_menu_products values (6,1,45);
	insert into allowed_menu_products values (6,1,46);
	insert into allowed_menu_products values (6,1,47);

#	menu for fries, zero menu-price

insert into premade_menu values (7,'Fries Menu',0,'medium-fries.png');
	insert into allowed_menu_products values (7,1,10);
	insert into allowed_menu_products values (7,1,28);
	insert into allowed_menu_products values (7,1,38);

#	menu for wraps, zero menu-price

insert into premade_menu values (8,'Wraps Menu',0,'wrap_chicken.jpg');
	insert into allowed_menu_products values (8,1,1);
	insert into allowed_menu_products values (8,1,2);
	insert into allowed_menu_products values (8,1,3);
