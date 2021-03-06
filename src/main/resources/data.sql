--
-- Data for Name: location;
--
INSERT INTO public.location (id, deleted, address, city, country) VALUES ('71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', false, 'Batajnički drum b.b.', 'Beograd', 'Serbia');
INSERT INTO public.location (id, deleted, address, city, country) VALUES ('9c7face6-d759-46bd-839a-6469ae471067', false, 'Beogradski put bb', 'Vršac', 'Serbia');
INSERT INTO public.location (id, deleted, address, city, country) VALUES ('9c7face6-d759-46bd-439a-6469ae471067', false, 'Stefana Velikog b.b.', 'Krnjaca', 'Serbia');
INSERT INTO public.location (id, deleted, address, city, country) VALUES ('9c7face6-d759-412d-839a-6469ae471067', false, 'Mite Cenica 58', 'Sombor', 'Serbia');

----
---- Data for Name: drug_type;
----

INSERT INTO public.drug_type (id, deleted, description, name) VALUES ('5fa27c02-d6b4-431b-b3ce-9dc66588b918', false, 'They help to improve your sleep, energy, appetite, mood and negative thoughts about yourself.', 'antidepressant');
INSERT INTO public.drug_type (id, deleted, description, name) VALUES ('cb22173a-4b0b-4f98-9c03-e19682520ff2', false, 'They help with hallucinations and delusions, anxiety, agitation and problems with mood, thinking and socialising.', 'antipsychotic');
INSERT INTO public.drug_type (id, deleted, description, name) VALUES ('ea278714-5c0e-41d6-9028-51db5f4a9645', false, 'Benzodiazepines are sometimes used to treat anxiety and insomnia.', 'benzodiazepines');
INSERT INTO public.drug_type (id, deleted, description, name) VALUES ('75d0c11c-e1e1-49ae-bd60-5cb175bbece7', false, 'They help to improve attention, concentration and alertness.', 'stimulant');


----
---- Data for Name: manufacturer;
----
INSERT INTO public.manufacturer (id, deleted, name, phone_number, location_fk) VALUES ('b137c265-6e12-4b59-947e-ab5504335dc4', false, 'Galenika', '+381 11 307 1000', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.manufacturer (id, deleted, name, phone_number, location_fk) VALUES ('d9a1b6b2-df11-4071-88c6-887411477a48', false, 'Hemofarm', '+381 13 803 100', '9c7face6-d759-46bd-839a-6469ae471067');
--
--
--
----
---- Data for Name: ingredient;
----
INSERT INTO public.ingredient (id, deleted, name) VALUES ('ad547190-5fc0-4c84-ba8c-74315d023e38', false, 'cellulose');
INSERT INTO public.ingredient (id, deleted, name) VALUES ('ee502386-fc3a-4f69-9bf2-d7a483876c58', false, 'lactose monohydrate');
INSERT INTO public.ingredient (id, deleted, name) VALUES ('202c9837-c9a2-4d93-9f98-4e34bb0f3aba', false, 'colloidal anhydrous silica');
INSERT INTO public.ingredient (id, deleted, name) VALUES ('0e940928-4756-403e-b624-6fe00578448f', false, 'magnesium stearate');

----
---- Data for Name: drug;
----
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('d7d2d173-9f15-4bd2-979c-6933746f6be4', 'AS2222', false, 'a', 'tablet', 'Zanax', 200, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('6602618d-067d-487b-95f1-21cee6bb6e43', 'AR2222', false, 'a', 'tablet', 'Prostamol uno', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', 'cb22173a-4b0b-4f98-9c03-e19682520ff2');
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('a329071d-b38c-4014-9fa3-2e7367d9ee49', 'AG2222', false, 'a', 'vaccine', 'Pfizer', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('a32asd1d-b38c-4014-9fa3-2e7367d9ee49', 'AZ2222', false, 'a', 'herb', 'Caj od nane', 0, true, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('a3ew071d-b38c-4014-9fa3-2e7367d9ee49', 'ZS2222', false, 'a', 'bandage', 'Nikotinski flaster', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug (id, code, deleted, description, form, name, point, receipt, manufacturer_fk, drug_type_fk) VALUES ('a329a71d-b38c-4014-9fa3-2e7367d9ee49', 'BS2222', false, 'a', 'vaccine', 'Astra-zeneca', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');

----
---- Data for Name: Point;
----

INSERT INTO public.point (id, deleted, latitude, longitude) VALUES ('1', false, 44.8125, 20.4612);
INSERT INTO public.point (id, deleted, latitude, longitude) VALUES ('2', false, 44.8125, 20.4612);
INSERT INTO public.point (id, deleted, latitude, longitude) VALUES ('3', false, 44.8125, 20.4612);
INSERT INTO public.point (id, deleted, latitude, longitude) VALUES ('4', false, 44.8125, 20.4612);
INSERT INTO public.point (id, deleted, latitude, longitude) VALUES ('5', false, 44.8125, 20.4612);

----
---- Data for Name: Drugstore;
----
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6523-463a-974b-ded43ad63843', false, 'Description', 'Lav', 2000, '08:00', '18:00', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '1');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6523-463a-974b-dedascs63843', false, 'Description', 'Sombor pharmacy', 2500, '07:50', '18:40', '9c7face6-d759-46bd-439a-6469ae471067', '2');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6as3-463a-974b-ded43ad63843', false, 'Description', 'Belgrade Health', 1000, '09:00', '19:00','9c7face6-d759-46bd-439a-6469ae471067', '3');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2bas33e9-6523-463a-974b-ded43ad63843', false, 'Description', 'Pejin medicine', 800, '12:00', '21:00','9c7face6-d759-412d-839a-6469ae471067', '4');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2fasdfe9-6523-463a-974b-ded43ad63843', false, 'Description', 'Prirodnjacka medicina', 8000, '14:00', '22:00','71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '5');

----
---- Data for Name: Employee;
----
INSERT INTO public.users VALUES ('Pharmacist', 'ccb953a7-d244-48bb-8627-4b2437491dc1', false, '12-06-2021', 0, '', 'pera@peric.com', false, 'Pera', '$2a$10$3SbblMBSK7acH373BqcAjuFK4yYkLjeIq/cCKGKL8Z.6R8tzIsJcm', NULL, TRUE, 'Peric', 0,'07:50', '18:40', 0, NULL, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Pharmacist', '9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', false, '12-06-2021', 0, '', 'mika@mikic.com', false, 'Mika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Mikic', 0,'07:50', '18:40', 0, NULL,   '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Dermatologist', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', false, '12-06-2021', 0, '', 'zika@zikic.com', false, 'Zika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Zikic', 0,NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', false, '12-06-2021', 0, '', 'djoka@djokic.com', false, 'Djoka', '$2a$10$ajJFXVzGMM83jmCnGxEMpuDAR2T3rpyGTIetAbuCKKuezbkVFGd2u', NULL, TRUE, 'Djokic', 0,NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', false, '12-06-2021', 0, '', 'steva@stevic.com', false, 'Steva', '$2a$10$acJM/JgRqMyULOtpJifMLex6HqfbFP8GPgOzxv6IrRBQabRkJiyKy', NULL, TRUE, 'Stevic', 0, NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
--
----
---- Data for Name: Patient;
----
INSERT INTO public.users VALUES ('Patient', '8128d806-c29b-4086-aae6-877d17eeb6fa', false, '12-06-2021', 0, NULL, 'marko@markoviccc.com', false, 'Marko', '$2a$12$unh6kPZiK0naKYjUQQTKCeTewEPE7/FhS5SjcfZMvUIwDm3bQGXYa', '060123456123', true, 'Markovic',0, NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '664783ca-84a1-4a2b-ae27-a2b820bc3c71', false, '12-06-2021', 0, NULL, 'mirko@mirkovic.com', false, 'Mirko', '$2a$12$Fq8JFCN1p2pQAyBKEIHA7enwqoDgZLdKFLJJ3mJKZ04PdXsgRgTnG', '060555522555', true, 'Mirkovic', 0,NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '393dca36-3778-4802-939c-de7a0a265c9d', false, '12-06-2021', 0, NULL, 'petar@markovic.com', false, 'Petar', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', '06512345432', true, 'Markovic',0, NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
--
----
---- Data for Name: Drugstore Admin;
----
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a2', false, '12-06-2021', 0, '', 'kata@ketamin.com', false, 'Kata', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Ketamin',0, NULL, NULL, NULL, NULL, NULL, '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a3', false, '12-06-2021', 0, '', 'admin@adminovic.com', true, 'Djordje', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Djordjevic',0, NULL, NULL, NULL, NULL, NULL, '2b7933e9-6523-463a-974b-ded43ad63843');

----
---- Data for Name: System Admin;
----
INSERT INTO public.users VALUES ('SystemAdmin', '2d2eacbf-fda8-4a50-aa05-be2be4fb2884', false, '12-06-2021', 0, '', 'gvozden@karadjordjevic.com', false, 'Gvozden', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Karadjordjevic',0, '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('SystemAdmin', '332eacbf-fda8-4a50-aa05-be2be4fb2884', false, '12-06-2021', 0, '', 'sys@admin.com', false, 'Admin', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'System', 0,'2b7933e9-6as3-463a-974b-ded43ad63843');

----
---- Data for Name: Supplier;
----
INSERT INTO public.users VALUES ('Supplier', '4f3bgfza-fda8-4a50-aa05-be2be4fb2884', false, '12-06-2021', 0, '', 'supplier@primer.com', false, 'Supplier', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Primer', 0,'2b7933e9-6as3-463a-974b-ded43ad63843');


------
------ Data for Name: Drugstock;
------
INSERT INTO public.supplierstock VALUES ('0', false, '12-06-2021', 0, 0, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '4f3bgfza-fda8-4a50-aa05-be2be4fb2884');
INSERT INTO public.supplierstock VALUES ('1', false, '12-06-2021', 0, 0, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '4f3bgfza-fda8-4a50-aa05-be2be4fb2884');

------
------ Data for Name: Drugstock;
------
INSERT INTO public.drugstock VALUES ('0', false, '12-06-2021', 0, 55, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('1', false, '12-06-2021', 0, 922, 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('2', false, '12-06-2021', 0, 82, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('3', false, '12-06-2021', 0, 27, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('4', false, '12-06-2021', 0, 25, 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('5', false, '12-06-2021', 0, 25, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6523-463a-974b-ded43ad63843');

------
------ Data for Name: DrugPrice;
------
INSERT INTO public.drug_price VALUES ('0', false, '12-06-2021', 0, '2021-01-22', true, 200, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('1', false, '12-06-2021', 0, '2021-01-22', false, 300, '2021-01-22', 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('2', false, '12-06-2021', 0, '2021-01-22', false, 400, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('3', false, '12-06-2021', 0, '2021-01-25', false, 500, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('4', false, '12-06-2021', 0, '2021-01-22', false, 5500, '2021-01-22', 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('5', false, '12-06-2021', 0, '2021-01-22', false, 5500, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6523-463a-974b-ded43ad63843');
--
--
------
------ Data for Name: DermatologistEmployment
------
INSERT INTO public.employment VALUES ('0', false, '12-06-2021', 0, '10:00', '12:00', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('1', false, '12-06-2021', 0, '10:00', '12:00', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('2', false, '12-06-2021', 0, '10:00', '12:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('3', false, '12-06-2021', 0, '10:00', '12:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2fasdfe9-6523-463a-974b-ded43ad63843');

----
------ Data for Name: dermatologist_appointment; 
------

------
------ Data for Name: roles; 
------
INSERT INTO public.roles VALUES ('77f0ab33-f97c-4282-bb60-c34d8d897092', false, '12-06-2021', 0, 'ROLE_PATIENT');
INSERT INTO public.roles VALUES ('f367f6cf-e8f8-45ee-99c6-e000d7c68003', false, '12-06-2021', 0, 'ROLE_DERMATOLOGIST');
INSERT INTO public.roles VALUES ('9f58c424-f0e2-4e95-80c8-14039b24e40f', false, '12-06-2021', 0, 'ROLE_PHARMACIST');
INSERT INTO public.roles VALUES ('122c0bad-e159-4896-bb10-6e8b69547e2f', false, '12-06-2021', 0, 'ROLE_SUPPLIER');
INSERT INTO public.roles VALUES ('58da0253-f5cf-4163-afdc-32f61d7471a3', false, '12-06-2021', 0, 'ROLE_DRUGSTOREADMIN');
INSERT INTO public.roles VALUES ('3a58b85f-23fa-4c1c-a2ac-23221875d6fa', false, '12-06-2021', 0, 'ROLE_SYSTEMADMIN');
--
------
------ Data for Name: user-role; 
------
------patinents
INSERT INTO public.user_role VALUES ('8128d806-c29b-4086-aae6-877d17eeb6fa', '77f0ab33-f97c-4282-bb60-c34d8d897092');
INSERT INTO public.user_role VALUES ('664783ca-84a1-4a2b-ae27-a2b820bc3c71', '77f0ab33-f97c-4282-bb60-c34d8d897092');
INSERT INTO public.user_role VALUES ('393dca36-3778-4802-939c-de7a0a265c9d', '77f0ab33-f97c-4282-bb60-c34d8d897092');

------pharmacists
INSERT INTO public.user_role VALUES ('ccb953a7-d244-48bb-8627-4b2437491dc1', '9f58c424-f0e2-4e95-80c8-14039b24e40f');
INSERT INTO public.user_role VALUES ('9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', '9f58c424-f0e2-4e95-80c8-14039b24e40f');
--
------dermatologists
INSERT INTO public.user_role VALUES ('68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');
INSERT INTO public.user_role VALUES ('da9e4ee3-c67c-4511-ad43-82e34d10ddc2', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac677a1', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');
--
------drugstore admins
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac627a2', '58da0253-f5cf-4163-afdc-32f61d7471a3');
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac627a3', '58da0253-f5cf-4163-afdc-32f61d7471a3');

------system admins
INSERT INTO public.user_role VALUES ('2d2eacbf-fda8-4a50-aa05-be2be4fb2884', '3a58b85f-23fa-4c1c-a2ac-23221875d6fa');
--
------suppliers
INSERT INTO public.user_role VALUES ('4f3bgfza-fda8-4a50-aa05-be2be4fb2884', '122c0bad-e159-4896-bb10-6e8b69547e2f');

------
------ Data for Name: order-stock; 
------
INSERT INTO public.order_stock VALUES ('5d341148-a753-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 10, 'd7d2d173-9f15-4bd2-979c-6933746f6be4');
INSERT INTO public.order_stock VALUES ('82bd5b90-a753-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 100, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49');
--
INSERT INTO public.order_stock VALUES ('87ac4d96-a753-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 100, 'd7d2d173-9f15-4bd2-979c-6933746f6be4');
INSERT INTO public.order_stock VALUES ('8b941ec0-a753-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 1000, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49');
INSERT INTO public.order_stock VALUES ('8b941ec0-a753-11eb-bcbc-0242ac130005', false, '12-06-2021', 0, 255, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49');
----
----
------
------ Data for Name: drug-order; 
------
INSERT INTO public.drug_order VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, '2021-05-12', '12:00:00', 0, '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drug_order VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, '2021-04-22', '12:00:00', 1, '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drug_order VALUES ('85038a9a-a754-11eb-bcbc-0242ac130003', false, '12-06-2021', 0, '2021-05-22', '12:00:00', 1, '2bas33e9-6523-463a-974b-ded43ad63843');
----
------
------ Data for Name: drug-order-stock; 
------
INSERT INTO public.drug_order_stock VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', '5d341148-a753-11eb-bcbc-0242ac130002');
INSERT INTO public.drug_order_stock VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', '82bd5b90-a753-11eb-bcbc-0242ac130002');
--
----
INSERT INTO public.drug_order_stock VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', '87ac4d96-a753-11eb-bcbc-0242ac130002');
INSERT INTO public.drug_order_stock VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', '8b941ec0-a753-11eb-bcbc-0242ac130002');

INSERT INTO public.drug_order_stock VALUES ('85038a9a-a754-11eb-bcbc-0242ac130003', '8b941ec0-a753-11eb-bcbc-0242ac130005');
--
------
------ Data for Name: offers; 
------
INSERT INTO public.offer VALUES ('1', false, '12-06-2021', 0, '2021-06-28', '12:00:00', 2500, 1, '85038a9a-a754-11eb-bcbc-0242ac130002',  '4f3bgfza-fda8-4a50-aa05-be2be4fb2884');
INSERT INTO public.offer VALUES ('2', false, '12-06-2021', 0, '2021-06-29', '12:00:00', 5500, 1, '85038a9a-a754-11eb-bcbc-0242ac130002',  '4f3bgfza-fda8-4a50-aa05-be2be4fb2884');
----
----
----
INSERT INTO public.loyalty_configuration VALUES (1, 10, 20);
----
----
INSERT INTO public.patient_category VALUES ('55038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0,  0, 'Basic', 0);
INSERT INTO public.patient_category VALUES ('444038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0,  5, 'Bronze', 200);
INSERT INTO public.patient_category VALUES ('33038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 10, 'Silver', 500);
INSERT INTO public.patient_category VALUES ('22038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 15, 'Gold', 700);
--
INSERT INTO public.complaint VALUES ('33038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, true, 'Nisam zadovoljan farmaceutom', 0, '2b7933e9-6523-463a-974b-ded43ad63843', 'ccb953a7-d244-48bb-8627-4b2437491dc1', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.complaint VALUES ('11038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, false, 'Stvarno mi se ne svidja kako pristupa pacijentima', 0, '2b7933e9-6523-463a-974b-ded43ad63843', 'ccb953a7-d244-48bb-8627-4b2437491dc1', '393dca36-3778-4802-939c-de7a0a265c9d');
--
------ Data for dermatology rates
----
INSERT INTO public.rating_dermatologist VALUES ('1', false, '12-06-2021', 0, 2, '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.rating_dermatologist VALUES ('2', false, '12-06-2021', 0, 5, '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '664783ca-84a1-4a2b-ae27-a2b820bc3c71');
----
------ Data for pharmacy rates
----
INSERT INTO public.rating_pharmacist VALUES ('3', false, '12-06-2021', 0, 2, '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.rating_pharmacist VALUES ('4', false, '12-06-2021', 0, 2, '664783ca-84a1-4a2b-ae27-a2b820bc3c71', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.rating_pharmacist VALUES ('5', false, '12-06-2021', 0, 5, '393dca36-3778-4802-939c-de7a0a265c9d', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
--
INSERT INTO public.reply VALUES ('99038a9a-a754-11eb-bcbc-0242ac130002', false, '12-06-2021', 0, 'Dobice otkaz', '33038a9a-a754-11eb-bcbc-0242ac130002', '2d2eacbf-fda8-4a50-aa05-be2be4fb2884');
--
----Data for drug reservations
--
INSERT INTO public.dermatologist_appointment VALUES ('9b91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-06-15 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9a91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-16 13:41:32.959006', 0, NULL, '2021-06-16 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9c91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-17 13:41:32.959006', 0, NULL, '2021-06-17 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9d91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-18 13:41:32.959006', 0, NULL, '2021-06-18 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9f91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-19 13:41:32.959006', 0, NULL, '2021-06-16 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9g91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-20 13:41:32.959006', 0, NULL, '2021-06-17 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);
INSERT INTO public.dermatologist_appointment VALUES ('9j91de3c-3995-4bf8-b2ad-eaee77567bae', false, '2021-06-21 13:41:32.959006', 0, NULL, '2021-06-18 11:30:00', 30, 100, false, '11:00:00', '11:30:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843', NULL);

INSERT INTO public.drug_reservation VALUES ('01', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-05-26', true, 5000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('02', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-02-26', true, 55000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('03', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-01-26', true, 8000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('04', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-03-26', true, 12000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('05', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-04-26', true, 4000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('06', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-03-26', true, 30000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('07', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-02-26', true, 21000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('08', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-01-26', true, 24000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('09', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-05-26', true, 9000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('010', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-02-26', true, 3000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('011', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-01-26', true, 10000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('012', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2021-04-26', true, 10000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');

INSERT INTO public.drug_reservation VALUES ('11', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-05-26', true, 8000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('12', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-02-26', true, 52000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('13', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-01-26', true, 3000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('14', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-03-26', true, 22000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('15', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-04-26', true, 1000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('16', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-07-26', true, 34000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('17', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-08-26', true, 22000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('18', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-09-26', true, 29000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('19', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-10-26', true, 2000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('110', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-06-26', true, 3200, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('111', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-11-26', true, 1000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('112', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2020-12-26', true, 4000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');

INSERT INTO public.drug_reservation VALUES ('21', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-05-26', true, 2500, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('22', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-02-26', true, 1000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('23', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-01-26', true, 8000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('24', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-03-26', true, 14000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('25', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-04-26', true, 4500, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('26', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-07-26', true, 27000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('27', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-08-26', true, 20000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('28', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-09-26', true, 16000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('29', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-10-26', true, 2600, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('210', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-06-26', true, 3400, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('211', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-11-26', true, 11000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('212', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2019-12-26', true, 9000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');


INSERT INTO public.drug_reservation VALUES ('31', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2018-05-26', true, 50000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('32', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2017-02-26', true, 88000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('33', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2016-01-26', true, 82000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('34', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2015-03-26', true, 75000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('35', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2014-04-26', true, 49000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('36', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2013-07-26', true, 90000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('37', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2012-08-26', true, 56000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('38', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2011-09-26', true, 42000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('39', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2010-10-26', true, 52000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('310', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2009-10-26', true, 35000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('311', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2008-11-26', true, 16000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');
INSERT INTO public.drug_reservation VALUES ('312', false, '2021-06-15 13:41:32.959006', 0, 2, 'nekiKod1', '2007-12-26', true, 100000, 1, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843', '393dca36-3778-4802-939c-de7a0a265c9d');

----
---- Data for Name: pharmacist_appointment; 
----

INSERT INTO public.pharmacist_appointment VALUES ('1', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-04-06 02:00:00', 3, 8000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('2', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-03-06 02:00:00', 3, 5000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('3', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-05-06 02:00:00', 3, 2000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('4', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-02-06 02:00:00', 3, 2000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('5', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-02-06 02:00:00', 3, 2500, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('6', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-01-06 02:00:00', 3, 12000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('7', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-05-06 02:00:00', 3, 16000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('8', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-01-06 02:00:00', 3, 1000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');

INSERT INTO public.pharmacist_appointment VALUES ('9', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2020-04-06 02:00:00', 3, 20000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('10', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2020-02-06 02:00:00', 3, 15000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('11', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2020-05-06 02:00:00', 3, 16000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('12', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2020-09-06 02:00:00', 3, 22000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('13', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2020-10-06 02:00:00', 3, 10000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');

INSERT INTO public.pharmacist_appointment VALUES ('14', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 16000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('15', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 12000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('16', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 5000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('17', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 9000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('18', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 22000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('19', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 10000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('20', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 2000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('21', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 4000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('22', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 42000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('23', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 10000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('24', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2019-04-06 02:00:00', 3, 5000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');

INSERT INTO public.pharmacist_appointment VALUES ('25', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2018-04-06 02:00:00', 3, 20000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('26', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2017-04-06 02:00:00', 3, 32000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('27', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2016-04-06 02:00:00', 3, 90000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('28', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2015-04-06 02:00:00', 3, 5000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('29', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2014-04-06 02:00:00', 3, 52000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('30', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2014-04-06 02:00:00', 3, 20000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('31', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2012-04-06 02:00:00', 3, 25000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('32', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2011-04-06 02:00:00', 3, 52000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');

INSERT INTO public.pharmacist_appointment VALUES ('33', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-06-28 10:00:00', 2, 52000, false, '10:10:00', '10:12:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('34', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-06-28 10:00:00', 5, 52000, false, '10:15:00', '10:20:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');
INSERT INTO public.pharmacist_appointment VALUES ('35', false,'2021-06-15 13:41:32.959006', 0,  NULL, '2021-06-29 10:00:00', 5, 52000, false, '10:15:00', '10:20:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');


----
---- Data for Name: dermatologist_appointment; 
----

INSERT INTO public.dermatologist_appointment VALUES ('2', false, '2021-06-15 13:41:32.959006', 0,  NULL, '2021-02-16 02:00:00', 10, 500,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('3', false, '2021-06-15 13:41:32.959006', 0,  NULL, '2021-03-13 02:00:00', 8, 2500,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('4', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-01-16 02:00:00', 10, 1200,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('5', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-01-13 02:00:00', 8, 2200,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('6', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-02-16 02:00:00', 10, 900,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('7', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-04-13 02:00:00', 8, 2000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('8', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-02-16 02:00:00', 10, 2500,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');

INSERT INTO public.dermatologist_appointment VALUES ('9', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-04-13 02:00:00', 8, 2000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('10', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-02-16 02:00:00', 10, 1200,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('11', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-03-13 02:00:00', 8, 1500,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('12', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-01-16 02:00:00', 10, 1500,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('13', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-05-13 02:00:00', 8, 4000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('14', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-06-16 02:00:00', 10, 2000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('15', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-07-13 02:00:00', 8, 2500,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('16', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-08-16 02:00:00', 10, 2500,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('17', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-09-13 02:00:00', 8, 1000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('18', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-10-16 02:00:00', 10, 1000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('19', false, '2021-06-15 13:41:32.959006', 0, NULL, '2020-11-16 02:00:00', 10, 1000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');

INSERT INTO public.dermatologist_appointment VALUES ('20', false, '2021-06-15 13:41:32.959006', 0, NULL, '2019-04-13 02:00:00', 8, 2000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('21', false, '2021-06-15 13:41:32.959006', 0, NULL, '2019-02-16 02:00:00', 10, 5000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('22', false, '2021-06-15 13:41:32.959006', 0, NULL, '2019-09-13 02:00:00', 8, 9000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('23', false, '2021-06-15 13:41:32.959006', 0, NULL, '2019-10-16 02:00:00', 10, 5000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('24', false, '2021-06-15 13:41:32.959006', 0, NULL, '2019-10-13 02:00:00', 8, 4000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');

INSERT INTO public.dermatologist_appointment VALUES ('25', false, '2021-06-15 13:41:32.959006', 0, NULL, '2018-04-13 02:00:00', 8, 25000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('26', false, '2021-06-15 13:41:32.959006', 0, NULL, '2017-02-16 02:00:00', 10, 42000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('27', false, '2021-06-15 13:41:32.959006', 0, NULL, '2016-04-13 02:00:00', 8, 44000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('28', false, '2021-06-15 13:41:32.959006', 0, NULL, '2015-02-16 02:00:00', 10, 28000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('29', false, '2021-06-15 13:41:32.959006', 0, NULL, '2014-04-13 02:00:00', 8, 55000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('30', false, '2021-06-15 13:41:32.959006', 0, NULL, '2013-02-16 02:00:00', 10, 105000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('31', false, '2021-06-15 13:41:32.959006', 0, NULL, '2012-04-13 02:00:00', 8, 45000,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('32', false, '2021-06-15 13:41:32.959006', 0, NULL, '2011-02-16 02:00:00', 10, 12000,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');

INSERT INTO public.dermatologist_appointment VALUES ('33', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-06-28 02:00:00', 2, 45000,false,'10:10:00', '10:12:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('34', false, '2021-06-15 13:41:32.959006', 0, NULL, '2021-06-28 02:00:00', 5, 12000,false,'10:15:00', '10:20:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6523-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
