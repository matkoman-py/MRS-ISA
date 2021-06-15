create table absence_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, admin_comment varchar(255), end_date timestamp not null, reason varchar(255) not null, start_date timestamp not null, status int4 not null, emlpoyee_fk varchar(255) not null, primary key (id))
create table complaint (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, has_reply boolean not null, text varchar(255), type int4 not null, drugstore_fk varchar(255) not null, employee_fk varchar(255), patient_fk varchar(255) not null, primary key (id))
create table dermatologist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8 not null, processed boolean, time time not null, time_end time, dermatologist_fk varchar(255) not null, drugstore_fk varchar(255) not null, patient_id varchar(255), primary key (id))
create table drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, code varchar(255) not null, daily_dose int4 default 0, description varchar(255) not null, form varchar(255) not null, name varchar(255) not null, point int4 not null, rating float8 default 0, receipt boolean not null, weight float8 default 0, manufacturer_fk varchar(255) not null, drug_type_fk varchar(255) not null, primary key (id))
create table drug_ingredients (drug_id varchar(255) not null, ingredient_id varchar(255) not null)
create table drug_order (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, deadline_date date not null, deadline_time time not null, status int4 not null, drugstore_id varchar(255), primary key (id))
create table drug_order_stock (drug_order_id varchar(255) not null, stock_id varchar(255) not null)
create table drug_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, handled boolean not null, drug_id varchar(255), drugstore_id varchar(255), primary key (id))
create table drug_reservation (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, confirmation_code varchar(255), date varchar(255) not null, e_receipt boolean, price float8, status int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), patient_id varchar(255), primary key (id))
create table drug_price (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, end_date timestamp not null, is_promotion boolean not null, price int4 not null, start_date timestamp not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, pharmacist_appointment_price int4, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), location_fk varchar(255) not null, point_fk varchar(255), primary key (id))
create table drug_type (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, primary key (id))
create table employment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, working_hours_from varchar(255), working_hours_to varchar(255), dermatologist_id varchar(255), drugstore_id varchar(255), primary key (id))
create table ingredient (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, primary key (id))
create table location (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, address varchar(255) not null, city varchar(255), country varchar(255), primary key (id))
create table loyalty_configuration (id int4 not null, dermatologist_points_gained int4 not null, pharmacist_points_gained int4 not null, primary key (id))
create table manufacturer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, phone_number varchar(255) not null, location_fk varchar(255) not null, primary key (id))
create table offer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, delivery_date date not null, delivery_time time not null, price int4 not null, status int4 not null, drug_order_id varchar(255), supplier_id varchar(255), primary key (id))
create table order_stock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_id varchar(255), primary key (id))
create table patient_allergens (patient_id varchar(255) not null, ingredient_id varchar(255) not null)
create table patient_patient_category (category_id varchar(255), patient_id varchar(255) not null, primary key (patient_id))
create table patient_category (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, discount int4 not null, name varchar(255) not null, requiered_points int4 not null, primary key (id))
create table penalty (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, patient_id varchar(255) not null, reservation_id varchar(255) not null, type varchar(255) not null, primary key (id))
create table pharmacist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8, processed boolean, time time not null, time_end time, patient_id varchar(255), pharmacist_fk varchar(255) not null, primary key (id))
create table point (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, latitude float8 not null, longitude float8 not null, primary key (id))
create table rating_dermatologist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, dermatologist_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drug_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_pharmacist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, patient_fk varchar(255), pharmacist_fk varchar(255), primary key (id))
create table reply (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, text varchar(255), complaint_id varchar(255), system_admin_id varchar(255), primary key (id))
create table roles (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255), primary key (id))
create table subscription (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table substitutions (original_id varchar(255) not null, sub_id varchar(255) not null)
create table supplierstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), supplier_fk varchar(255), primary key (id))
create table user_role (user_id varchar(255) not null, role_id varchar(255) not null)
create table users (type varchar(31) not null, id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, activation_code varchar(255), email varchar(255) not null, first_login boolean, name varchar(255) not null, password varchar(255) not null, phone_number varchar(255), status boolean, surname varchar(255) not null, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), penalty_counter int4, points int4, location_fk varchar(255), drugstore_fk varchar(255), primary key (id))
alter table if exists drug add constraint UK_gp2v51p5n48ip1i7dl9ejujhh unique (code)
alter table if exists drug add constraint UK_n3gpc2w6g0on102a9kq3jyjr7 unique (name)
alter table if exists drug_order_stock add constraint UK_ncenlxj1yd0ichiyo0qhu5uty unique (stock_id)
alter table if exists drug_type add constraint UK_bqmlmwbf90qpxw7tnn4sd7cqf unique (name)
alter table if exists ingredient add constraint UK_bcuaj97y3iu3t2vj26jg6hijj unique (name)
alter table if exists manufacturer add constraint UK_fvhf6l0xkf8hnay7lvwimnwu1 unique (name)
alter table if exists patient_category add constraint UK_jf6ir4xvyru0if7qu02efb5bb unique (name)
alter table if exists penalty add constraint UK_8rr5ex9j6nu0hmq7o0goq6dif unique (reservation_id)
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table if exists absence_request add constraint FKs3p0vhnja3rd4kugogisovwgr foreign key (emlpoyee_fk) references users
alter table if exists complaint add constraint FKg60tto4c36jgxhpuwgj6wxcm1 foreign key (drugstore_fk) references drugstore
alter table if exists complaint add constraint FKf6rvtde1q6tmiplkfi5p3ao11 foreign key (employee_fk) references users
alter table if exists complaint add constraint FK2cii8wpffo7yqsl2fihumxjdn foreign key (patient_fk) references users
alter table if exists dermatologist_appointment add constraint FK2qdr94c2vb4toh8omsrsw9rpl foreign key (dermatologist_fk) references users
alter table if exists dermatologist_appointment add constraint FKpxuqsrsrqeh30o5c9pk1wvudq foreign key (drugstore_fk) references drugstore
alter table if exists dermatologist_appointment add constraint FKtb9enfopwaatat4vobwpuop7q foreign key (patient_id) references users
alter table if exists drug add constraint FKr6cxbx7doh317hkoa8h33s9wv foreign key (manufacturer_fk) references manufacturer
alter table if exists drug add constraint FKbvfy6g4dp0iogrmxbqhs8v77n foreign key (drug_type_fk) references drug_type
alter table if exists drug_ingredients add constraint FK62f19b7js51k3x7xpvll5fger foreign key (ingredient_id) references ingredient
alter table if exists drug_ingredients add constraint FKi6lw29isg1nxaofovk9pv8lw6 foreign key (drug_id) references drug
alter table if exists drug_order add constraint FK6uv47ih4flq1lgd8yavk7f2k foreign key (drugstore_id) references drugstore
alter table if exists drug_order_stock add constraint FKpa5cffxamip9jxjw9lxc3m9yc foreign key (stock_id) references order_stock
alter table if exists drug_order_stock add constraint FKhj5y4xcj94877fwgbpvyhcjoi foreign key (drug_order_id) references drug_order
alter table if exists drug_request add constraint FKg3hm1hycd0lsu926t033dhkth foreign key (drug_id) references drug
alter table if exists drug_request add constraint FKdcs41qonfhft2xck2nnhgp2gg foreign key (drugstore_id) references drugstore
alter table if exists drug_reservation add constraint FK1bvngw6x5m7smehfwcdm451wg foreign key (drug_fk) references drug
alter table if exists drug_reservation add constraint FK2blygc05ep18jxl44tc5ig9sl foreign key (drugstore_fk) references drugstore
alter table if exists drug_reservation add constraint FKsgmc3afgpqkns8yddx0d7k2de foreign key (patient_id) references users
alter table if exists drug_price add constraint FKokaaehleq1dvwlvyjwj3ui7co foreign key (drug_fk) references drug
alter table if exists drug_price add constraint FKrvf2n2a5w26iv5gi9xl6vcqlb foreign key (drugstore_fk) references drugstore
alter table if exists drugstock add constraint FKswcae47hqbqohb7hpy6kx4jv4 foreign key (drug_fk) references drug
alter table if exists drugstock add constraint FKlav6vkxsnh120bi10b2jbhoxo foreign key (drugstore_fk) references drugstore
alter table if exists drugstore add constraint FKhywx6vgabr7jksxvpjnolad0i foreign key (location_fk) references location
alter table if exists drugstore add constraint FKh6bqpw1gpl43ybqwcdohhc4ix foreign key (point_fk) references point
alter table if exists employment add constraint FKalb6x3nfmu0fr5vfw56d6j2w3 foreign key (dermatologist_id) references users
alter table if exists employment add constraint FKhtr4qo6nronigabp9q5t0dffy foreign key (drugstore_id) references drugstore
alter table if exists manufacturer add constraint FKo1ehc9giba8fxt4og2i6l9pgj foreign key (location_fk) references location
alter table if exists offer add constraint FK6tutt8t76dxvetap1ik0f3qqf foreign key (drug_order_id) references drug_order
alter table if exists offer add constraint FKi95lu4da3u8avav5a91gpgwqf foreign key (supplier_id) references users
alter table if exists order_stock add constraint FKdsj0ukgjdxgb0due28xjbau1s foreign key (drug_id) references drug
alter table if exists patient_allergens add constraint FKou65rosy34r4fk9l2txbduj5b foreign key (ingredient_id) references ingredient
alter table if exists patient_allergens add constraint FKfo16gl5pnv8yrsgescrabg66w foreign key (patient_id) references users
alter table if exists patient_patient_category add constraint FKk7hflyjxvp60ydwj5dkv9uny1 foreign key (category_id) references patient_category
alter table if exists patient_patient_category add constraint FKtobpdcj3l55fwvhewng12js7f foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK93kwkt6rey47jcbmliul3tyv7 foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK7ykhs5l9icf0qj2pnftlhj1q9 foreign key (pharmacist_fk) references users
alter table if exists rating_dermatologist add constraint FKcou0umfnwihwith9wk2ecr71b foreign key (dermatologist_fk) references users
alter table if exists rating_dermatologist add constraint FK2xyrxxh8y8txpxhylsfqn1p8k foreign key (patient_fk) references users
alter table if exists rating_drug add constraint FK47fgr07062ajworf4qpomdug6 foreign key (drug_fk) references drug
alter table if exists rating_drug add constraint FKgq0g0gji98m3cmswmug28hil8 foreign key (patient_fk) references users
alter table if exists rating_drugstore add constraint FKqsom3qj9m3o7fik8u3yg99pwx foreign key (drugstore_fk) references drugstore
alter table if exists rating_drugstore add constraint FKlnexgxon4f2lg3oqfop7f67en foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FKn5lopm23hs74nwcttsom8r6i9 foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FK7uaq53v1nn97e76o4ugmljtt1 foreign key (pharmacist_fk) references users
alter table if exists reply add constraint FKem45mx7hxg8c1tuxem8usuija foreign key (complaint_id) references complaint
alter table if exists reply add constraint FK1ei80spjbt33ngewt32xir1of foreign key (system_admin_id) references users
alter table if exists subscription add constraint FKsw9o0cfxea0w2cs41u3a7kxim foreign key (drugstore_fk) references drugstore
alter table if exists subscription add constraint FKd379sams9nbljfkc8e2q0lubn foreign key (patient_fk) references users
alter table if exists substitutions add constraint FKd3bbrphob0fwexq1692tclj6e foreign key (sub_id) references drug
alter table if exists substitutions add constraint FKnantvup811dkvphnyod2jtacr foreign key (original_id) references drug
alter table if exists supplierstock add constraint FKj3e041v76436g3v172c3u3skg foreign key (drug_fk) references drug
alter table if exists supplierstock add constraint FK8eoklygcak827g1tgi0fjwt07 foreign key (supplier_fk) references users
alter table if exists user_role add constraint FKt7e7djp752sqn6w22i6ocqy6q foreign key (role_id) references roles
alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users
alter table if exists users add constraint FKk72lm2l67su202d9e5xw6oa5w foreign key (location_fk) references location
alter table if exists users add constraint FKo9877ui2pny9winafv0h2m6b0 foreign key (drugstore_fk) references drugstore
create table absence_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, admin_comment varchar(255), end_date timestamp not null, reason varchar(255) not null, start_date timestamp not null, status int4 not null, emlpoyee_fk varchar(255) not null, primary key (id))
create table complaint (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, has_reply boolean not null, text varchar(255), type int4 not null, drugstore_fk varchar(255) not null, employee_fk varchar(255), patient_fk varchar(255) not null, primary key (id))
create table dermatologist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8 not null, processed boolean, time time not null, time_end time, dermatologist_fk varchar(255) not null, drugstore_fk varchar(255) not null, patient_id varchar(255), primary key (id))
create table drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, code varchar(255) not null, daily_dose int4 default 0, description varchar(255) not null, form varchar(255) not null, name varchar(255) not null, point int4 not null, rating float8 default 0, receipt boolean not null, weight float8 default 0, manufacturer_fk varchar(255) not null, drug_type_fk varchar(255) not null, primary key (id))
create table drug_ingredients (drug_id varchar(255) not null, ingredient_id varchar(255) not null)
create table drug_order (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, deadline_date date not null, deadline_time time not null, status int4 not null, drugstore_id varchar(255), primary key (id))
create table drug_order_stock (drug_order_id varchar(255) not null, stock_id varchar(255) not null)
create table drug_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, handled boolean not null, drug_id varchar(255), drugstore_id varchar(255), primary key (id))
create table drug_reservation (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, confirmation_code varchar(255), date varchar(255) not null, e_receipt boolean, price float8, status int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), patient_id varchar(255), primary key (id))
create table drug_price (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, end_date timestamp not null, is_promotion boolean not null, price int4 not null, start_date timestamp not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, pharmacist_appointment_price int4, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), location_fk varchar(255) not null, point_fk varchar(255), primary key (id))
create table drug_type (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, primary key (id))
create table employment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, working_hours_from varchar(255), working_hours_to varchar(255), dermatologist_id varchar(255), drugstore_id varchar(255), primary key (id))
create table ingredient (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, primary key (id))
create table location (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, address varchar(255) not null, city varchar(255), country varchar(255), primary key (id))
create table loyalty_configuration (id int4 not null, dermatologist_points_gained int4 not null, pharmacist_points_gained int4 not null, primary key (id))
create table manufacturer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, phone_number varchar(255) not null, location_fk varchar(255) not null, primary key (id))
create table offer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, delivery_date date not null, delivery_time time not null, price int4 not null, status int4 not null, drug_order_id varchar(255), supplier_id varchar(255), primary key (id))
create table order_stock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_id varchar(255), primary key (id))
create table patient_allergens (patient_id varchar(255) not null, ingredient_id varchar(255) not null)
create table patient_patient_category (category_id varchar(255), patient_id varchar(255) not null, primary key (patient_id))
create table patient_category (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, discount int4 not null, name varchar(255) not null, requiered_points int4 not null, primary key (id))
create table penalty (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, patient_id varchar(255) not null, reservation_id varchar(255) not null, type varchar(255) not null, primary key (id))
create table pharmacist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8, processed boolean, time time not null, time_end time, patient_id varchar(255), pharmacist_fk varchar(255) not null, primary key (id))
create table point (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, latitude float8 not null, longitude float8 not null, primary key (id))
create table rating_dermatologist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, dermatologist_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drug_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_pharmacist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, patient_fk varchar(255), pharmacist_fk varchar(255), primary key (id))
create table reply (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, text varchar(255), complaint_id varchar(255), system_admin_id varchar(255), primary key (id))
create table roles (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255), primary key (id))
create table subscription (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table substitutions (original_id varchar(255) not null, sub_id varchar(255) not null)
create table supplierstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), supplier_fk varchar(255), primary key (id))
create table user_role (user_id varchar(255) not null, role_id varchar(255) not null)
create table users (type varchar(31) not null, id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, activation_code varchar(255), email varchar(255) not null, first_login boolean, name varchar(255) not null, password varchar(255) not null, phone_number varchar(255), status boolean, surname varchar(255) not null, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), penalty_counter int4, points int4, location_fk varchar(255), drugstore_fk varchar(255), primary key (id))
alter table if exists drug add constraint UK_gp2v51p5n48ip1i7dl9ejujhh unique (code)
alter table if exists drug add constraint UK_n3gpc2w6g0on102a9kq3jyjr7 unique (name)
alter table if exists drug_order_stock add constraint UK_ncenlxj1yd0ichiyo0qhu5uty unique (stock_id)
alter table if exists drug_type add constraint UK_bqmlmwbf90qpxw7tnn4sd7cqf unique (name)
alter table if exists ingredient add constraint UK_bcuaj97y3iu3t2vj26jg6hijj unique (name)
alter table if exists manufacturer add constraint UK_fvhf6l0xkf8hnay7lvwimnwu1 unique (name)
alter table if exists patient_category add constraint UK_jf6ir4xvyru0if7qu02efb5bb unique (name)
alter table if exists penalty add constraint UK_8rr5ex9j6nu0hmq7o0goq6dif unique (reservation_id)
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table if exists absence_request add constraint FKs3p0vhnja3rd4kugogisovwgr foreign key (emlpoyee_fk) references users
alter table if exists complaint add constraint FKg60tto4c36jgxhpuwgj6wxcm1 foreign key (drugstore_fk) references drugstore
alter table if exists complaint add constraint FKf6rvtde1q6tmiplkfi5p3ao11 foreign key (employee_fk) references users
alter table if exists complaint add constraint FK2cii8wpffo7yqsl2fihumxjdn foreign key (patient_fk) references users
alter table if exists dermatologist_appointment add constraint FK2qdr94c2vb4toh8omsrsw9rpl foreign key (dermatologist_fk) references users
alter table if exists dermatologist_appointment add constraint FKpxuqsrsrqeh30o5c9pk1wvudq foreign key (drugstore_fk) references drugstore
alter table if exists dermatologist_appointment add constraint FKtb9enfopwaatat4vobwpuop7q foreign key (patient_id) references users
alter table if exists drug add constraint FKr6cxbx7doh317hkoa8h33s9wv foreign key (manufacturer_fk) references manufacturer
alter table if exists drug add constraint FKbvfy6g4dp0iogrmxbqhs8v77n foreign key (drug_type_fk) references drug_type
alter table if exists drug_ingredients add constraint FK62f19b7js51k3x7xpvll5fger foreign key (ingredient_id) references ingredient
alter table if exists drug_ingredients add constraint FKi6lw29isg1nxaofovk9pv8lw6 foreign key (drug_id) references drug
alter table if exists drug_order add constraint FK6uv47ih4flq1lgd8yavk7f2k foreign key (drugstore_id) references drugstore
alter table if exists drug_order_stock add constraint FKpa5cffxamip9jxjw9lxc3m9yc foreign key (stock_id) references order_stock
alter table if exists drug_order_stock add constraint FKhj5y4xcj94877fwgbpvyhcjoi foreign key (drug_order_id) references drug_order
alter table if exists drug_request add constraint FKg3hm1hycd0lsu926t033dhkth foreign key (drug_id) references drug
alter table if exists drug_request add constraint FKdcs41qonfhft2xck2nnhgp2gg foreign key (drugstore_id) references drugstore
alter table if exists drug_reservation add constraint FK1bvngw6x5m7smehfwcdm451wg foreign key (drug_fk) references drug
alter table if exists drug_reservation add constraint FK2blygc05ep18jxl44tc5ig9sl foreign key (drugstore_fk) references drugstore
alter table if exists drug_reservation add constraint FKsgmc3afgpqkns8yddx0d7k2de foreign key (patient_id) references users
alter table if exists drug_price add constraint FKokaaehleq1dvwlvyjwj3ui7co foreign key (drug_fk) references drug
alter table if exists drug_price add constraint FKrvf2n2a5w26iv5gi9xl6vcqlb foreign key (drugstore_fk) references drugstore
alter table if exists drugstock add constraint FKswcae47hqbqohb7hpy6kx4jv4 foreign key (drug_fk) references drug
alter table if exists drugstock add constraint FKlav6vkxsnh120bi10b2jbhoxo foreign key (drugstore_fk) references drugstore
alter table if exists drugstore add constraint FKhywx6vgabr7jksxvpjnolad0i foreign key (location_fk) references location
alter table if exists drugstore add constraint FKh6bqpw1gpl43ybqwcdohhc4ix foreign key (point_fk) references point
alter table if exists employment add constraint FKalb6x3nfmu0fr5vfw56d6j2w3 foreign key (dermatologist_id) references users
alter table if exists employment add constraint FKhtr4qo6nronigabp9q5t0dffy foreign key (drugstore_id) references drugstore
alter table if exists manufacturer add constraint FKo1ehc9giba8fxt4og2i6l9pgj foreign key (location_fk) references location
alter table if exists offer add constraint FK6tutt8t76dxvetap1ik0f3qqf foreign key (drug_order_id) references drug_order
alter table if exists offer add constraint FKi95lu4da3u8avav5a91gpgwqf foreign key (supplier_id) references users
alter table if exists order_stock add constraint FKdsj0ukgjdxgb0due28xjbau1s foreign key (drug_id) references drug
alter table if exists patient_allergens add constraint FKou65rosy34r4fk9l2txbduj5b foreign key (ingredient_id) references ingredient
alter table if exists patient_allergens add constraint FKfo16gl5pnv8yrsgescrabg66w foreign key (patient_id) references users
alter table if exists patient_patient_category add constraint FKk7hflyjxvp60ydwj5dkv9uny1 foreign key (category_id) references patient_category
alter table if exists patient_patient_category add constraint FKtobpdcj3l55fwvhewng12js7f foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK93kwkt6rey47jcbmliul3tyv7 foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK7ykhs5l9icf0qj2pnftlhj1q9 foreign key (pharmacist_fk) references users
alter table if exists rating_dermatologist add constraint FKcou0umfnwihwith9wk2ecr71b foreign key (dermatologist_fk) references users
alter table if exists rating_dermatologist add constraint FK2xyrxxh8y8txpxhylsfqn1p8k foreign key (patient_fk) references users
alter table if exists rating_drug add constraint FK47fgr07062ajworf4qpomdug6 foreign key (drug_fk) references drug
alter table if exists rating_drug add constraint FKgq0g0gji98m3cmswmug28hil8 foreign key (patient_fk) references users
alter table if exists rating_drugstore add constraint FKqsom3qj9m3o7fik8u3yg99pwx foreign key (drugstore_fk) references drugstore
alter table if exists rating_drugstore add constraint FKlnexgxon4f2lg3oqfop7f67en foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FKn5lopm23hs74nwcttsom8r6i9 foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FK7uaq53v1nn97e76o4ugmljtt1 foreign key (pharmacist_fk) references users
alter table if exists reply add constraint FKem45mx7hxg8c1tuxem8usuija foreign key (complaint_id) references complaint
alter table if exists reply add constraint FK1ei80spjbt33ngewt32xir1of foreign key (system_admin_id) references users
alter table if exists subscription add constraint FKsw9o0cfxea0w2cs41u3a7kxim foreign key (drugstore_fk) references drugstore
alter table if exists subscription add constraint FKd379sams9nbljfkc8e2q0lubn foreign key (patient_fk) references users
alter table if exists substitutions add constraint FKd3bbrphob0fwexq1692tclj6e foreign key (sub_id) references drug
alter table if exists substitutions add constraint FKnantvup811dkvphnyod2jtacr foreign key (original_id) references drug
alter table if exists supplierstock add constraint FKj3e041v76436g3v172c3u3skg foreign key (drug_fk) references drug
alter table if exists supplierstock add constraint FK8eoklygcak827g1tgi0fjwt07 foreign key (supplier_fk) references users
alter table if exists user_role add constraint FKt7e7djp752sqn6w22i6ocqy6q foreign key (role_id) references roles
alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users
alter table if exists users add constraint FKk72lm2l67su202d9e5xw6oa5w foreign key (location_fk) references location
alter table if exists users add constraint FKo9877ui2pny9winafv0h2m6b0 foreign key (drugstore_fk) references drugstore
create table absence_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, admin_comment varchar(255), end_date timestamp not null, reason varchar(255) not null, start_date timestamp not null, status int4 not null, emlpoyee_fk varchar(255) not null, primary key (id))
create table complaint (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, has_reply boolean not null, text varchar(255), type int4 not null, drugstore_fk varchar(255) not null, employee_fk varchar(255), patient_fk varchar(255) not null, primary key (id))
create table dermatologist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8 not null, processed boolean, time time not null, time_end time, dermatologist_fk varchar(255) not null, drugstore_fk varchar(255) not null, patient_id varchar(255), primary key (id))
create table drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, code varchar(255) not null, daily_dose int4 default 0, description varchar(255) not null, form varchar(255) not null, name varchar(255) not null, point int4 not null, rating float8 default 0, receipt boolean not null, weight float8 default 0, manufacturer_fk varchar(255) not null, drug_type_fk varchar(255) not null, primary key (id))
create table drug_ingredients (drug_id varchar(255) not null, ingredient_id varchar(255) not null)
create table drug_order (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, deadline_date date not null, deadline_time time not null, status int4 not null, drugstore_id varchar(255), primary key (id))
create table drug_order_stock (drug_order_id varchar(255) not null, stock_id varchar(255) not null)
create table drug_request (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, handled boolean not null, drug_id varchar(255), drugstore_id varchar(255), primary key (id))
create table drug_reservation (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, confirmation_code varchar(255), date varchar(255) not null, e_receipt boolean, price float8, status int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), patient_id varchar(255), primary key (id))
create table drug_price (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, end_date timestamp not null, is_promotion boolean not null, price int4 not null, start_date timestamp not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), drugstore_fk varchar(255), primary key (id))
create table drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, pharmacist_appointment_price int4, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), location_fk varchar(255) not null, point_fk varchar(255), primary key (id))
create table drug_type (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, description varchar(255) not null, name varchar(255) not null, primary key (id))
create table employment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, working_hours_from varchar(255), working_hours_to varchar(255), dermatologist_id varchar(255), drugstore_id varchar(255), primary key (id))
create table ingredient (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, primary key (id))
create table location (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, address varchar(255) not null, city varchar(255), country varchar(255), primary key (id))
create table loyalty_configuration (id int4 not null, dermatologist_points_gained int4 not null, pharmacist_points_gained int4 not null, primary key (id))
create table manufacturer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255) not null, phone_number varchar(255) not null, location_fk varchar(255) not null, primary key (id))
create table offer (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, delivery_date date not null, delivery_time time not null, price int4 not null, status int4 not null, drug_order_id varchar(255), supplier_id varchar(255), primary key (id))
create table order_stock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_id varchar(255), primary key (id))
create table patient_allergens (patient_id varchar(255) not null, ingredient_id varchar(255) not null)
create table patient_patient_category (category_id varchar(255), patient_id varchar(255) not null, primary key (patient_id))
create table patient_category (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, discount int4 not null, name varchar(255) not null, requiered_points int4 not null, primary key (id))
create table penalty (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, patient_id varchar(255) not null, reservation_id varchar(255) not null, type varchar(255) not null, primary key (id))
create table pharmacist_appointment (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, appointment_report varchar(255), date timestamp not null, duration int4 not null, price float8, processed boolean, time time not null, time_end time, patient_id varchar(255), pharmacist_fk varchar(255) not null, primary key (id))
create table point (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, latitude float8 not null, longitude float8 not null, primary key (id))
create table rating_dermatologist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, dermatologist_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drug (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drug_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_drugstore (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table rating_pharmacist (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, rating int4 not null, patient_fk varchar(255), pharmacist_fk varchar(255), primary key (id))
create table reply (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, text varchar(255), complaint_id varchar(255), system_admin_id varchar(255), primary key (id))
create table roles (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, name varchar(255), primary key (id))
create table subscription (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, drugstore_fk varchar(255), patient_fk varchar(255), primary key (id))
create table substitutions (original_id varchar(255) not null, sub_id varchar(255) not null)
create table supplierstock (id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, amount int4 not null, drug_fk varchar(255), supplier_fk varchar(255), primary key (id))
create table user_role (user_id varchar(255) not null, role_id varchar(255) not null)
create table users (type varchar(31) not null, id varchar(255) not null, deleted boolean default false not null, updated timestamp, version int8 default 0 not null, activation_code varchar(255), email varchar(255) not null, first_login boolean, name varchar(255) not null, password varchar(255) not null, phone_number varchar(255), status boolean, surname varchar(255) not null, rating float8 default 0, working_hours_from varchar(255), working_hours_to varchar(255), penalty_counter int4, points int4, location_fk varchar(255), drugstore_fk varchar(255), primary key (id))
alter table if exists drug add constraint UK_gp2v51p5n48ip1i7dl9ejujhh unique (code)
alter table if exists drug add constraint UK_n3gpc2w6g0on102a9kq3jyjr7 unique (name)
alter table if exists drug_order_stock add constraint UK_ncenlxj1yd0ichiyo0qhu5uty unique (stock_id)
alter table if exists drug_type add constraint UK_bqmlmwbf90qpxw7tnn4sd7cqf unique (name)
alter table if exists ingredient add constraint UK_bcuaj97y3iu3t2vj26jg6hijj unique (name)
alter table if exists manufacturer add constraint UK_fvhf6l0xkf8hnay7lvwimnwu1 unique (name)
alter table if exists patient_category add constraint UK_jf6ir4xvyru0if7qu02efb5bb unique (name)
alter table if exists penalty add constraint UK_8rr5ex9j6nu0hmq7o0goq6dif unique (reservation_id)
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table if exists absence_request add constraint FKs3p0vhnja3rd4kugogisovwgr foreign key (emlpoyee_fk) references users
alter table if exists complaint add constraint FKg60tto4c36jgxhpuwgj6wxcm1 foreign key (drugstore_fk) references drugstore
alter table if exists complaint add constraint FKf6rvtde1q6tmiplkfi5p3ao11 foreign key (employee_fk) references users
alter table if exists complaint add constraint FK2cii8wpffo7yqsl2fihumxjdn foreign key (patient_fk) references users
alter table if exists dermatologist_appointment add constraint FK2qdr94c2vb4toh8omsrsw9rpl foreign key (dermatologist_fk) references users
alter table if exists dermatologist_appointment add constraint FKpxuqsrsrqeh30o5c9pk1wvudq foreign key (drugstore_fk) references drugstore
alter table if exists dermatologist_appointment add constraint FKtb9enfopwaatat4vobwpuop7q foreign key (patient_id) references users
alter table if exists drug add constraint FKr6cxbx7doh317hkoa8h33s9wv foreign key (manufacturer_fk) references manufacturer
alter table if exists drug add constraint FKbvfy6g4dp0iogrmxbqhs8v77n foreign key (drug_type_fk) references drug_type
alter table if exists drug_ingredients add constraint FK62f19b7js51k3x7xpvll5fger foreign key (ingredient_id) references ingredient
alter table if exists drug_ingredients add constraint FKi6lw29isg1nxaofovk9pv8lw6 foreign key (drug_id) references drug
alter table if exists drug_order add constraint FK6uv47ih4flq1lgd8yavk7f2k foreign key (drugstore_id) references drugstore
alter table if exists drug_order_stock add constraint FKpa5cffxamip9jxjw9lxc3m9yc foreign key (stock_id) references order_stock
alter table if exists drug_order_stock add constraint FKhj5y4xcj94877fwgbpvyhcjoi foreign key (drug_order_id) references drug_order
alter table if exists drug_request add constraint FKg3hm1hycd0lsu926t033dhkth foreign key (drug_id) references drug
alter table if exists drug_request add constraint FKdcs41qonfhft2xck2nnhgp2gg foreign key (drugstore_id) references drugstore
alter table if exists drug_reservation add constraint FK1bvngw6x5m7smehfwcdm451wg foreign key (drug_fk) references drug
alter table if exists drug_reservation add constraint FK2blygc05ep18jxl44tc5ig9sl foreign key (drugstore_fk) references drugstore
alter table if exists drug_reservation add constraint FKsgmc3afgpqkns8yddx0d7k2de foreign key (patient_id) references users
alter table if exists drug_price add constraint FKokaaehleq1dvwlvyjwj3ui7co foreign key (drug_fk) references drug
alter table if exists drug_price add constraint FKrvf2n2a5w26iv5gi9xl6vcqlb foreign key (drugstore_fk) references drugstore
alter table if exists drugstock add constraint FKswcae47hqbqohb7hpy6kx4jv4 foreign key (drug_fk) references drug
alter table if exists drugstock add constraint FKlav6vkxsnh120bi10b2jbhoxo foreign key (drugstore_fk) references drugstore
alter table if exists drugstore add constraint FKhywx6vgabr7jksxvpjnolad0i foreign key (location_fk) references location
alter table if exists drugstore add constraint FKh6bqpw1gpl43ybqwcdohhc4ix foreign key (point_fk) references point
alter table if exists employment add constraint FKalb6x3nfmu0fr5vfw56d6j2w3 foreign key (dermatologist_id) references users
alter table if exists employment add constraint FKhtr4qo6nronigabp9q5t0dffy foreign key (drugstore_id) references drugstore
alter table if exists manufacturer add constraint FKo1ehc9giba8fxt4og2i6l9pgj foreign key (location_fk) references location
alter table if exists offer add constraint FK6tutt8t76dxvetap1ik0f3qqf foreign key (drug_order_id) references drug_order
alter table if exists offer add constraint FKi95lu4da3u8avav5a91gpgwqf foreign key (supplier_id) references users
alter table if exists order_stock add constraint FKdsj0ukgjdxgb0due28xjbau1s foreign key (drug_id) references drug
alter table if exists patient_allergens add constraint FKou65rosy34r4fk9l2txbduj5b foreign key (ingredient_id) references ingredient
alter table if exists patient_allergens add constraint FKfo16gl5pnv8yrsgescrabg66w foreign key (patient_id) references users
alter table if exists patient_patient_category add constraint FKk7hflyjxvp60ydwj5dkv9uny1 foreign key (category_id) references patient_category
alter table if exists patient_patient_category add constraint FKtobpdcj3l55fwvhewng12js7f foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK93kwkt6rey47jcbmliul3tyv7 foreign key (patient_id) references users
alter table if exists pharmacist_appointment add constraint FK7ykhs5l9icf0qj2pnftlhj1q9 foreign key (pharmacist_fk) references users
alter table if exists rating_dermatologist add constraint FKcou0umfnwihwith9wk2ecr71b foreign key (dermatologist_fk) references users
alter table if exists rating_dermatologist add constraint FK2xyrxxh8y8txpxhylsfqn1p8k foreign key (patient_fk) references users
alter table if exists rating_drug add constraint FK47fgr07062ajworf4qpomdug6 foreign key (drug_fk) references drug
alter table if exists rating_drug add constraint FKgq0g0gji98m3cmswmug28hil8 foreign key (patient_fk) references users
alter table if exists rating_drugstore add constraint FKqsom3qj9m3o7fik8u3yg99pwx foreign key (drugstore_fk) references drugstore
alter table if exists rating_drugstore add constraint FKlnexgxon4f2lg3oqfop7f67en foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FKn5lopm23hs74nwcttsom8r6i9 foreign key (patient_fk) references users
alter table if exists rating_pharmacist add constraint FK7uaq53v1nn97e76o4ugmljtt1 foreign key (pharmacist_fk) references users
alter table if exists reply add constraint FKem45mx7hxg8c1tuxem8usuija foreign key (complaint_id) references complaint
alter table if exists reply add constraint FK1ei80spjbt33ngewt32xir1of foreign key (system_admin_id) references users
alter table if exists subscription add constraint FKsw9o0cfxea0w2cs41u3a7kxim foreign key (drugstore_fk) references drugstore
alter table if exists subscription add constraint FKd379sams9nbljfkc8e2q0lubn foreign key (patient_fk) references users
alter table if exists substitutions add constraint FKd3bbrphob0fwexq1692tclj6e foreign key (sub_id) references drug
alter table if exists substitutions add constraint FKnantvup811dkvphnyod2jtacr foreign key (original_id) references drug
alter table if exists supplierstock add constraint FKj3e041v76436g3v172c3u3skg foreign key (drug_fk) references drug
alter table if exists supplierstock add constraint FK8eoklygcak827g1tgi0fjwt07 foreign key (supplier_fk) references users
alter table if exists user_role add constraint FKt7e7djp752sqn6w22i6ocqy6q foreign key (role_id) references roles
alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users
alter table if exists users add constraint FKk72lm2l67su202d9e5xw6oa5w foreign key (location_fk) references location
alter table if exists users add constraint FKo9877ui2pny9winafv0h2m6b0 foreign key (drugstore_fk) references drugstore
