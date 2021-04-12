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
-- Data for Name: Employee;
--
INSERT INTO public.users VALUES ('Pharmacist','ccb953a7-d244-48bb-8627-4b2437491dc1', false, '', 'pera@peric.com', 'Pera', '123', NULL, TRUE, 'Peric', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Pharmacist', '9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27', false, '', 'mika@mikic.com', 'Mika', '123', NULL, TRUE, 'Mikic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Dermatologist', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', false, '', 'zika@zikic.com', 'Zika', '123', NULL, TRUE, 'Zikic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Dermatologist', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', false, '', 'djoka@djokic.com', 'Djoka', '123', NULL, TRUE, 'Djokic', NULL, NULL, NULL);
INSERT INTO public.users VALUES ('Dermatologist', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', false, '', 'steva@stevic.com', 'Steva', '123', NULL, TRUE, 'Stevic', NULL, NULL, NULL);

--
-- Data for Name: Drugstore;
--
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-ded43ad63843', false, 10, 'Description', 'Lav', '08:00', '18:00', '71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');
INSERT INTO public.drugstore VALUES ('2b7933e9-6523-463a-974b-dedascs63843', false, 9, 'Description', 'Sombor pharmacy','07:50', '18:40', '9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2b7933e9-6as3-463a-974b-ded43ad63843', false, 8, 'Description', 'Belgrade Health', '09:00', '19:00','9c7face6-d759-46bd-439a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2bas33e9-6523-463a-974b-ded43ad63843', false, 7, 'Description', 'Pejin medicine', '12:00', '21:00','9c7face6-d759-412d-839a-6469ae471067');
INSERT INTO public.drugstore VALUES ('2fasdfe9-6523-463a-974b-ded43ad63843', false, 6, 'Description', 'Prirodnjacka medicina', '14:00', '22:00','71bfb5bf-f58f-45a9-8dea-2820a6e4d25d');

--
-- Data for Name: Patient;
--
INSERT INTO public.users VALUES ('Patient', '8128d806-c29b-4086-aae6-877d17eeb6fa', false, NULL, 'marko@markoviccc.com', 'Marko', 'pass', NULL, false, 'Markovic', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO public.users VALUES ('Patient', '664783ca-84a1-4a2b-ae27-a2b820bc3c71', false, NULL, 'mirko@mirkovic.com', 'Mirko', 'pass', NULL, false, 'Mirkovic', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO public.users VALUES ('Patient', '393dca36-3778-4802-939c-de7a0a265c9d', false, NULL, 'petar@markovic.com', 'Petar', 'pass', NULL, false, 'Markovic', NULL, NULL, 0, 0, NULL, NULL);


--
-- Data for Name: Drugstore Admin;
--
INSERT INTO public.users VALUES ('DrugstoreAdmin', '79e53b5a-9977-46dc-b5a5-11b54ac627a2', false, '', 'kata@ketamin.com', 'Kata', '123', NULL, TRUE, 'Ketamin', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: Drugstock;
--
INSERT INTO public.drugstock VALUES ('0', false, 55, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('1', false, 922, 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('2', false, 82, 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('3', false, 27, 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6523-463a-974b-ded43ad63843');
INSERT INTO public.drugstock VALUES ('4', false, 25, 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: DrugPrice;
--
INSERT INTO public.drug_price VALUES ('0', false, '2021-01-22', 200, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('1', false, '2021-01-22', 300, '2021-01-22', 'a329071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('2', false, '2021-01-22', 400, '2021-01-22', 'd7d2d173-9f15-4bd2-979c-6933746f6be4', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('3', false, '2021-01-25', 500, '2021-01-22', 'a32asd1d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.drug_price VALUES ('4', false, '2021-01-22', 5500, '2021-01-22', 'a3ew071d-b38c-4014-9fa3-2e7367d9ee49', '2b7933e9-6as3-463a-974b-ded43ad63843');

--
-- Data for Name: DermatologistEmployment
--
INSERT INTO public.employment VALUES ('0', false, '10:00', '12:00', 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('1', false, '10:00', '12:00', '79e53b5a-9977-46dc-b5a5-11b54ac677a1', '2b7933e9-6as3-463a-974b-ded43ad63843');
INSERT INTO public.employment VALUES ('2', false, '10:00', '12:00', '68eec890-3bc5-47e3-8a5b-d3544ebbfeb3', '2b7933e9-6523-463a-974b-ded43ad63843');
