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
--
------
------ Data for Name: Drugstore;
------
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6523-463a-974b-ded43ad63843', false, 'Description', 'Lav', 2000, '08:00', '18:00', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '1');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6523-463a-974b-dedascs63843', false, 'Description', 'Sombor pharmacy', 2500, '07:50', '18:40', '9c7face6-d759-46bd-439a-6469ae471067', '2');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2b7933e9-6as3-463a-974b-ded43ad63843', false, 'Description', 'Belgrade Health', 1000, '09:00', '19:00','9c7face6-d759-46bd-439a-6469ae471067', '3');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2bas33e9-6523-463a-974b-ded43ad63843', false, 'Description', 'Pejin medicine', 800, '12:00', '21:00','9c7face6-d759-412d-839a-6469ae471067', '4');
INSERT INTO public.drugstore (id, deleted, description, name, pharmacist_appointment_price, working_hours_from, working_hours_to, location_fk, point_fk) VALUES ('2fasdfe9-6523-463a-974b-ded43ad63843', false, 'Description', 'Prirodnjacka medicina', 8000, '14:00', '22:00','71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '5');
--
------
------ Data for Name: Employee;
------
INSERT INTO public.users VALUES ('Pharmacist', 'ccb953a7-d244-48bb-8627-4b2437491dc1', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'pera@peric.com', false, 'Pera', '$2a$10$3SbblMBSK7acH373BqcAjuFK4yYkLjeIq/cCKGKL8Z.6R8tzIsJcm', NULL, TRUE, 'Peric', 0,'07:50', '18:40', 0, NULL, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Pharmacist', '9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'mika@mikic.com', false, 'Mika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Mikic', 0,'07:50', '18:40', 0, NULL,   '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('Dermatologist', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'zika@zikic.com', false, 'Zika', '$2a$10$pYcWpOc3UzGmKJeJnblKN.ZUeC6pjUb.z2gnGXW5h5S7n1OimS.yO', NULL, TRUE, 'Zikic', 0,NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'djoka@djokic.com', false, 'Djoka', '$2a$10$ajJFXVzGMM83jmCnGxEMpuDAR2T3rpyGTIetAbuCKKuezbkVFGd2u', NULL, TRUE, 'Djokic', 0,NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Dermatologist', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'steva@stevic.com', false, 'Steva', '$2a$10$acJM/JgRqMyULOtpJifMLex6HqfbFP8GPgOzxv6IrRBQabRkJiyKy', NULL, TRUE, 'Stevic', 0, NULL, NULL, 0, NULL,  '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
----
------
------ Data for Name: Patient;
------
INSERT INTO public.users VALUES ('Patient', '8128d806-c29b-4086-aae6-877d17eeb6fa', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, NULL, 'marko@markoviccc.com', false, 'Marko', '$2a$12$unh6kPZiK0naKYjUQQTKCeTewEPE7/FhS5SjcfZMvUIwDm3bQGXYa', '060123456123', true, 'Markovic',0, NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '664783ca-84a1-4a2b-ae27-a2b820bc3c71', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, NULL, 'mirko@mirkovic.com', false, 'Mirko', '$2a$12$Fq8JFCN1p2pQAyBKEIHA7enwqoDgZLdKFLJJ3mJKZ04PdXsgRgTnG', '060555522555', true, 'Mirkovic', 0,NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
INSERT INTO public.users VALUES ('Patient', '393dca36-3778-4802-939c-de7a0a265c9d', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, NULL, 'petar@markovic.com', false, 'Petar', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', '06512345432', true, 'Markovic',0, NULL, NULL, 0, 0, '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', NULL);
----
------
------ Data for Name: Drugstore Admin;
------
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a2', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'kata@ketamin.com', false, 'Kata', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Ketamin',0, NULL, NULL, NULL, NULL, NULL, '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a3', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'admin@adminovic.com', false, 'Djordje', '$2a$12$FBhkOkiW3ToB1A5i/di8HOZOylq2t7BylKgYIjvSUfgrJTP0R5ZxW', NULL, TRUE, 'Djordjevic',0, NULL, NULL, NULL, NULL, NULL, '2b7933e9-6523-463a-974b-ded43ad63843');
--
------
------ Data for Name: System Admin;
------
INSERT INTO public.users VALUES ('SystemAdmin', '2d2eacbf-fda8-4a50-aa05-be2be4fb2884', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'gvozden@karadjordjevic.com', false, 'Gvozden', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Karadjordjevic',0, '2b7933e9-6as3-463a-974b-ded43ad63843',NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.users VALUES ('SystemAdmin', '332eacbf-fda8-4a50-aa05-be2be4fb2884', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'sys@admin.com', false, 'Admin', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'System', 0,'2b7933e9-6as3-463a-974b-ded43ad63843',NULL, NULL, NULL, NULL, NULL);
--
------
------ Data for Name: Supplier;
------
INSERT INTO public.users VALUES ('Supplier', '4f3bgfza-fda8-4a50-aa05-be2be4fb2884', false, parsedatetime('12-06-2021', 'dd-MM-yyyy'), 0, '', 'supplier@primer.com', false, 'Supplier', '$2a$10$AICSnLaNooad6nAkY55K9uSSMFPiwOW9VLjU8CCVEbwcKrGZddKpq', NULL, TRUE, 'Primer', 0,'2b7933e9-6as3-463a-974b-ded43ad63843',NULL, NULL, NULL, NULL, NULL);
--
--