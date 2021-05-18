--
-- Data for Name: location;
--
INSERT INTO public.location VALUES ('71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', false, 'Batajnički drum b.b.', 'Beograd', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-46bd-839a-6469ae471067', false, 'Beogradski put bb', 'Vršac', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-46bd-439a-6469ae471067', false, 'Stefana Velikog b.b.', 'Krnjaca', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-412d-839a-6469ae471067', false, 'Mite Cenica 58', 'Sombor', 'Serbia');

--
-- Data for Name: drug_type;
--

INSERT INTO public.drug_type VALUES ('5fa27c02-d6b4-431b-b3ce-9dc66588b918', false, 'They help to improve your sleep, energy, appetite, mood and negative thoughts about yourself.', 'antidepressant');
INSERT INTO public.drug_type VALUES ('cb22173a-4b0b-4f98-9c03-e19682520ff2', false, 'They help with hallucinations and delusions, anxiety, agitation and problems with mood, thinking and socialising.', 'antipsychotic');
INSERT INTO public.drug_type VALUES ('ea278714-5c0e-41d6-9028-51db5f4a9645', false, 'Benzodiazepines are sometimes used to treat anxiety and insomnia.', 'benzodiazepines');
INSERT INTO public.drug_type VALUES ('75d0c11c-e1e1-49ae-bd60-5cb175bbece7', false, 'They help to improve attention, concentration and alertness.', 'stimulant');


--
-- Data for Name: manufacturer;
--
INSERT INTO public.manufacturer VALUES ('b137c265-6e12-4b59-947e-ab5504335dc4', false, 'Galenika', '+381 11 307 1000', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.manufacturer VALUES ('d9a1b6b2-df11-4071-88c6-887411477a48', false, 'Hemofarm', '+381 13 803 100', '9c7face6-d759-46bd-839a-6469ae471067');



--
-- Data for Name: ingredient;
--
INSERT INTO public.ingredient VALUES ('ad547190-5fc0-4c84-ba8c-74315d023e38', false, 'cellulose');
INSERT INTO public.ingredient VALUES ('ee502386-fc3a-4f69-9bf2-d7a483876c58', false, 'lactose monohydrate');
INSERT INTO public.ingredient VALUES ('202c9837-c9a2-4d93-9f98-4e34bb0f3aba', false, 'colloidal anhydrous silica');
INSERT INTO public.ingredient VALUES ('0e940928-4756-403e-b624-6fe00578448f', false, 'magnesium stearate');

--
-- Data for Name: drug;
--
INSERT INTO public.drug VALUES ('d7d2d173-9f15-4bd2-979c-6933746f6be4', false, 'a', 'tablet', 'Zanax', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('6602618d-067d-487b-95f1-21cee6bb6e43', false, 'a', 'tablet', 'Prostamol uno', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', 'cb22173a-4b0b-4f98-9c03-e19682520ff2');
INSERT INTO public.drug VALUES ('a329071d-b38c-4014-9fa3-2e7367d9ee49', false, 'a', 'vaccine', 'Pfizer', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a32asd1d-b38c-4014-9fa3-2e7367d9ee49', false, 'a', 'herb', 'Caj od nane', 0, true, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a3ew071d-b38c-4014-9fa3-2e7367d9ee49', false, 'a', 'bandage', 'Nikotinski flaster', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a329a71d-b38c-4014-9fa3-2e7367d9ee49', false, 'a', 'vaccine', 'Astra-zeneca', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');

--
-- Data for Name: Drugstore;
--
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-ded43ad63843', false, 4, 'Description', 'Lav', 2000, '08:00', '18:00', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-dedascs63843', false, 3, 'Description', 'Sombor pharmacy', 2500, '07:50', '18:40', '9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2b7933e9-6as3-463a-974b-ded43ad63843', false, 2, 'Description', 'Belgrade Health', 1000, '09:00', '19:00','9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2bas33e9-6523-463a-974b-ded43ad63843', false, 1, 'Description', 'Pejin medicine', 800, '12:00', '21:00','9c7face6-d759-412d-839a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2fasdfe9-6523-463a-974b-ded43ad63843', false, 5, 'Description', 'Prirodnjacka medicina', 8000, '14:00', '22:00','71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');

--
-- Data for Name: Employee;
--
INSERT INTO public.users VALUES ('Pharmacist','ccb953a7-d244-48bb-8627-4b2437491dc1', false, '', 'pera@peric.com', 'Pera', '$2a$10$3SbblMBSK7acH373BqcAjuFK4yYkLjeIq/cCKGKL8Z.6R8tzIsJcm', NULL, TRUE, 'Peric', '07:50', '18:40', 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Pharmacist', '9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', false, '', 'mika@mikic.com', 'Mika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Mikic', '07:50', '18:40', 0, NULL,   '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Dermatologist', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', false, '', 'zika@zikic.com', 'Zika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Zikic', NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', false, '', 'djoka@djokic.com', 'Djoka', '$2a$10$ajJFXVzGMM83jmCnGxEMpuDAR2T3rpyGTIetAbuCKKuezbkVFGd2u', NULL, TRUE, 'Djokic', NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', false, '', 'steva@stevic.com', 'Steva', '$2a$10$acJM/JgRqMyULOtpJifMLex6HqfbFP8GPgOzxv6IrRBQabRkJiyKy', NULL, TRUE, 'Stevic', NULL, NULL, 0, 	NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);

--
-- Data for Name: Patient;
--
INSERT INTO public.users VALUES ('Patient', '8128d806-c29b-4086-aae6-877d17eeb6fa', false, NULL, 'marko@markoviccc.com', 'Marko', '$2a$12$unh6kPZiK0naKYjUQQTKCeTewEPE7/FhS5SjcfZMvUIwDm3bQGXYa', '060123456123', true, 'Markovic', NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '664783ca-84a1-4a2b-ae27-a2b820bc3c71', false, NULL, 'mirko@mirkovic.com', 'Mirko', '$2a$12$Fq8JFCN1p2pQAyBKEIHA7enwqoDgZLdKFLJJ3mJKZ04PdXsgRgTnG', '060555522555', true, 'Mirkovic', NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '393dca36-3778-4802-939c-de7a0a265c9d', false, NULL, 'petar@markovic.com', 'Petar', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', '06512345432', true, 'Markovic', NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);


--
-- Data for Name: Drugstore Admin;
--
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a2', false, '', 'kata@ketamin.com', 'Kata', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Ketamin', NULL, NULL, NULL, NULL, NULL, '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a3', false, '', 'admin@adminovic.com', 'Djordje', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Djordjevic', NULL, NULL, NULL, NULL, NULL, '2b7933e9-6523-463a-974b-ded43ad63843');

--
-- Data for Name: System Admin;
--
INSERT INTO public.users VALUES ('SystemAdmin', '2d2eacbf-fda8-4a50-aa05-be2be4fb2884', false, '', 'gvozden@karadjordjevic.com', 'Gvozden', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Karadjordjevic', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: Supplier;
--
INSERT INTO public.users VALUES ('Supplier', '4f3bgfza-fda8-4a50-aa05-be2be4fb2884', false, '', 'supplier@primer.com', 'Supplier', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Primer', '2b7933e9-6as3-463a-974b-ded43ad63843');


--
-- Data for Name: Drugstock;
--
INSERT INTO public.drugstock VALUES ('0', false, 55, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('1', false, 922, 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('2', false, 82, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('3', false, 27, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('4', false, 25, 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('5', false, 25, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6523-463a-974b-ded43ad63843');


--
-- Data for Name: DrugPrice;
--
INSERT INTO public.drug_price VALUES ('0', false, '2021-01-22', true, 200, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('1', false, '2021-01-22', false, 300, '2021-01-22', 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('2', false, '2021-01-22', false, 400, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('3', false, '2021-01-25', false, 500, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('4', false, '2021-01-22', false, 5500, '2021-01-22', 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('5', false, '2021-01-22', false, 5500, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6523-463a-974b-ded43ad63843');


--
-- Data for Name: DermatologistEmployment
--
INSERT INTO public.employment VALUES ('0', false, '10:00', '12:00', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('1', false, '10:00', '12:00', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('2', false, '10:00', '12:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843');
--
-- Data for Name: dermatologist_appointment; 
--
INSERT INTO public.dermatologist_appointment VALUES ('4a73ae19-2001-450a-a050-85f51717ab76', false, NULL, '2021-04-13 02:00:00', 8, 200,true,'12:00:00', '13:00:00','da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6as3-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
INSERT INTO public.dermatologist_appointment VALUES ('3effdfe3-9ca4-4789-817c-5b6a802bce2b', false, NULL, '2021-04-19 02:00:00', 10, 200,true,'16:59:00', '17:59:00','79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6as3-463a-974b-ded43ad63843', '8128d806-c29b-4086-aae6-877d17eeb6fa');
--
-- Data for Name: pharmacist_appointment; 
--
INSERT INTO public.pharmacist_appointment VALUES ('2229dbcb-7f36-4e4e-81f5-656ce14a553a', false, NULL, '2021-04-06 02:00:00', 3, 1000, true, '12:00:00', '13:00:00', '8128d806-c29b-4086-aae6-877d17eeb6fa', 'ccb953a7-d244-48bb-8627-4b2437491dc1');

--
-- Data for Name: roles; 
--
INSERT INTO public.roles VALUES ('77f0ab33-f97c-4282-bb60-c34d8d897092', false, 'ROLE_PATIENT');
INSERT INTO public.roles VALUES ('f367f6cf-e8f8-45ee-99c6-e000d7c68003', false, 'ROLE_DERMATOLOGIST');
INSERT INTO public.roles VALUES ('9f58c424-f0e2-4e95-80c8-14039b24e40f', false, 'ROLE_PHARMACIST');
INSERT INTO public.roles VALUES ('122c0bad-e159-4896-bb10-6e8b69547e2f', false, 'ROLE_SUPPLIER');
INSERT INTO public.roles VALUES ('58da0253-f5cf-4163-afdc-32f61d7471a3', false, 'ROLE_DRUGSTOREADMIN');
INSERT INTO public.roles VALUES ('3a58b85f-23fa-4c1c-a2ac-23221875d6fa', false, 'ROLE_SYSTEMADMIN');

--
-- Data for Name: user-role; 
--
--patinents
INSERT INTO public.user_role VALUES ('8128d806-c29b-4086-aae6-877d17eeb6fa', '77f0ab33-f97c-4282-bb60-c34d8d897092');
INSERT INTO public.user_role VALUES ('664783ca-84a1-4a2b-ae27-a2b820bc3c71', '77f0ab33-f97c-4282-bb60-c34d8d897092');
INSERT INTO public.user_role VALUES ('393dca36-3778-4802-939c-de7a0a265c9d', '77f0ab33-f97c-4282-bb60-c34d8d897092');

--pharmacists
INSERT INTO public.user_role VALUES ('ccb953a7-d244-48bb-8627-4b2437491dc1', '9f58c424-f0e2-4e95-80c8-14039b24e40f');
INSERT INTO public.user_role VALUES ('9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', '9f58c424-f0e2-4e95-80c8-14039b24e40f');

--dermatologists
INSERT INTO public.user_role VALUES ('68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');
INSERT INTO public.user_role VALUES ('da9e4ee3-c67c-4511-ad43-82e34d10ddc2', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac677a1', 'f367f6cf-e8f8-45ee-99c6-e000d7c68003');

--drugstore admins
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac627a2', '58da0253-f5cf-4163-afdc-32f61d7471a3');
INSERT INTO public.user_role VALUES ('79e53b5a-9977-46dc-b5a5-11b54ac627a3', '58da0253-f5cf-4163-afdc-32f61d7471a3');

--system admins
INSERT INTO public.user_role VALUES ('2d2eacbf-fda8-4a50-aa05-be2be4fb2884', '3a58b85f-23fa-4c1c-a2ac-23221875d6fa');

--suppliers
INSERT INTO public.user_role VALUES ('4f3bgfza-fda8-4a50-aa05-be2be4fb2884', '122c0bad-e159-4896-bb10-6e8b69547e2f');



--
-- Data for Name: order-stock; 
--
INSERT INTO public.order_stock VALUES ('5d341148-a753-11eb-bcbc-0242ac130002', false, 10, 'd7d2d173-9f15-4bd2-979c-6933746f6be4');
INSERT INTO public.order_stock VALUES ('82bd5b90-a753-11eb-bcbc-0242ac130002', false, 100, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49');

INSERT INTO public.order_stock VALUES ('87ac4d96-a753-11eb-bcbc-0242ac130002', false, 100, 'd7d2d173-9f15-4bd2-979c-6933746f6be4');
INSERT INTO public.order_stock VALUES ('8b941ec0-a753-11eb-bcbc-0242ac130002', false, 1000, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49');


--
-- Data for Name: drug-order; 
--
INSERT INTO public.drug_order VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', false, '2021-05-12', '12:00:00', 0, '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drug_order VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', false, '2021-04-22', '12:00:00', 1, '2bas33e9-6523-463a-974b-ded43ad63843');

--
-- Data for Name: drug-order-stock; 
--
INSERT INTO public.drug_order_stock VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', '5d341148-a753-11eb-bcbc-0242ac130002');
INSERT INTO public.drug_order_stock VALUES ('c93555b4-a753-11eb-bcbc-0242ac130002', '82bd5b90-a753-11eb-bcbc-0242ac130002');


INSERT INTO public.drug_order_stock VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', '87ac4d96-a753-11eb-bcbc-0242ac130002');
INSERT INTO public.drug_order_stock VALUES ('85038a9a-a754-11eb-bcbc-0242ac130002', '8b941ec0-a753-11eb-bcbc-0242ac130002');



