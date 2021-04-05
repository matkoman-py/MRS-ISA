--
-- Data for Name: location;
--
INSERT INTO public.location VALUES ('71bfb5bf-f58f-45a9-8dea-2820a6e4d25d', 'Batajnički drum b.b.', 'Beograd', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-46bd-839a-6469ae471067', 'Beogradski put bb', 'Vršac', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-46bd-439a-6469ae471067', 'Stefana Velikog b.b.', 'Krnjaca', 'Serbia');
INSERT INTO public.location VALUES ('9c7face6-d759-412d-839a-6469ae471067', 'Mite Cenica 58', 'Sombor', 'Serbia');

--
-- Data for Name: drug_type;
--
INSERT INTO public.drug_type VALUES ('5fa27c02-d6b4-431b-b3ce-9dc66588b918', 'They help to improve your sleep, energy, appetite, mood and negative thoughts about yourself.', 'antidepressant');
INSERT INTO public.drug_type VALUES ('cb22173a-4b0b-4f98-9c03-e19682520ff2', 'They help with hallucinations and delusions, anxiety, agitation and problems with mood, thinking and socialising.', 'antipsychotic');
INSERT INTO public.drug_type VALUES ('ea278714-5c0e-41d6-9028-51db5f4a9645', 'Benzodiazepines are sometimes used to treat anxiety and insomnia.', 'benzodiazepines');
INSERT INTO public.drug_type VALUES ('75d0c11c-e1e1-49ae-bd60-5cb175bbece7', 'They help to improve attention, concentration and alertness.', 'stimulant');

--
-- Data for Name: manufacturer;
--
INSERT INTO public.manufacturer VALUES ('b137c265-6e12-4b59-947e-ab5504335dc4', 'Galenika', '+381 11 307 1000', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.manufacturer VALUES ('d9a1b6b2-df11-4071-88c6-887411477a48', 'Hemofarm', '+381 13 803 100', '9c7face6-d759-46bd-839a-6469ae471067');

--
-- Data for Name: ingredient;
--
INSERT INTO public.ingredient VALUES ('ad547190-5fc0-4c84-ba8c-74315d023e38', 'cellulose');
INSERT INTO public.ingredient VALUES ('ee502386-fc3a-4f69-9bf2-d7a483876c58', 'lactose monohydrate');
INSERT INTO public.ingredient VALUES ('202c9837-c9a2-4d93-9f98-4e34bb0f3aba', 'colloidal anhydrous silica');
INSERT INTO public.ingredient VALUES ('0e940928-4756-403e-b624-6fe00578448f', 'magnesium stearate');

--
-- Data for Name: drug;
--
INSERT INTO public.drug VALUES ('d7d2d173-9f15-4bd2-979c-6933746f6be4', 'a', 'tablet', 'Zanax', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('6602618d-067d-487b-95f1-21cee6bb6e43', 'a', 'tablet', 'Prostamol uno', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', 'cb22173a-4b0b-4f98-9c03-e19682520ff2');
INSERT INTO public.drug VALUES ('a329071d-b38c-4014-9fa3-2e7367d9ee49', 'a', 'vaccine', 'Pfizer', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a32asd1d-b38c-4014-9fa3-2e7367d9ee49', 'a', 'herb', 'Caj od nane', 0, true, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a3ew071d-b38c-4014-9fa3-2e7367d9ee49', 'a', 'bandage', 'Nikotinski flaster', 0, false, 'b137c265-6e12-4b59-947e-ab5504335dc4', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');
INSERT INTO public.drug VALUES ('a329a71d-b38c-4014-9fa3-2e7367d9ee49', 'a', 'vaccine', 'Astra-zeneca', 0, true, 'd9a1b6b2-df11-4071-88c6-887411477a48', '5fa27c02-d6b4-431b-b3ce-9dc66588b918');

--
-- Data for Name: Employee;
--
INSERT INTO public.users VALUES ('Pharmacist','ccb953a7-d244-48bb-8627-4b2437491dc1', '', 'pera@peric.com', 'Pera', '123', NULL, TRUE, 'Peric', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Pharmacist', '9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', '', 'mika@mikic.com', 'Mika', '123', NULL, TRUE, 'Mikic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Pharmacist', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '', 'zika@zikic.com', 'Zika', '123', NULL, TRUE, 'Zikic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Dermatologist', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '', 'djoka@djokic.com', 'Djoka', '123', NULL, TRUE, 'Djokic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Dermatologist', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', '', 'steva@stevic.com', 'Steva', '123', NULL, TRUE, 'Stevic', NULL, NULL, NULL);

--
-- Data for Name: Drugstore;
--
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-ded43ad63843', 10, 'Description', 'Lav', '08:00', '18:00', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-dedascs63843', 9, 'Description', 'Sombor pharmacy','07:50', '18:40', '9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2b7933e9-6as3-463a-974b-ded43ad63843', 8, 'Description', 'Belgrade Health', '09:00', '19:00','9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2bas33e9-6523-463a-974b-ded43ad63843', 7, 'Description', 'Pejin medicine', '12:00', '21:00','9c7face6-d759-412d-839a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2fasdfe9-6523-463a-974b-ded43ad63843', 6, 'Description', 'Prirodnjacka medicina', '14:00', '22:00','71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');

--
-- Data for Name: Drugstore Admin;
--
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a2', '', 'kata@ketamin.com', 'Kata', '123', NULL, TRUE, 'Ketamin', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: Drugstock;
--
INSERT INTO public.drugstock VALUES ('0', 55, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('1', 922, 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('2', 82, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('3', 27, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('4', 25, 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: DrugPrice;
--
INSERT INTO public.drug_price VALUES ('0', '2021-01-22', 200, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('1', '2021-01-22', 300, '2021-01-22', 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('2', '2021-01-22', 400, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('3', '2021-01-25', 500, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('4', '2021-01-22', 5500, '2021-01-22', 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');

