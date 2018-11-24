INSERT INTO dc4hb17n6gdggs.public.tag VALUES
  (32, 'Java'),
  (33, 'Python'),
  (34, 'C'),
  (35, 'VR'),
  (36, 'WEB'),
  (37, 'Game design'),
  (38, 'Javas'),
  (39, 'Javasa'),
  (40, 'Science'),
  (41, 'Web');

INSERT INTO dc4hb17n6gdggs.public.company_data VALUES
  ('96091710-cbc9-4aec-a34d-f6c364440f6e', 'Волшебная организация. Ищет новые таланты.', 'B.I.T',
   FALSE),
  ('34f7ac9f-8118-4713-b5b0-e359c21caae1', 'Волшебная организация. Ищет новые таланты.', 'B.I.T',
   FALSE),
  ('65831511-063d-4558-adcd-3714cfde62c8', '', '', FALSE);

--Не соответствует текущий модели. Требуется проверить таблицу!
INSERT INTO dc4hb17n6gdggs.public.hack VALUES
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 'Студенты', 2, 'Интересное описание.', 'BIT DAYS #1',
                                           'Санкт-Петербург', 'http://site.com', '2020-09-04',
                                           '34f7ac9f-8118-4713-b5b0-e359c21caae1', NULL, NULL, NULL,
   NULL, NULL),
  ('172a1fa5-3b8d-4e29-be8d-fc0052d3df5c', 'Школьники', 0, '', 'TEst 3', 'Россия, Санкт-Петербург,
   проспект Королёва, 20', '', NULL, '34f7ac9f-8118-4713-b5b0-e359c21caae1', '30.274025, 60.011756',
                                           NULL, NULL, NULL, NULL),
  ('37fc101e-8167-4191-b398-cb022a5d6a0c', 'Школьники', 0, '', '', '', '', '',
                                           '34f7ac9f-8118-4713-b5b0-e359c21caae1', NULL, NULL, NULL,
   NULL, NULL),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 'Студенты', 3, 'О жизни.', 'Zero Hack #51', 'Россия,
   Санкт-Петербург, Кронверкский проспект, 49', 'https://Itmo.ru', NULL,
                                           '34f7ac9f-8118-4713-b5b0-e359c21caae1',
                                           '30.310081,59.956359', '2021 -10 -31', 0, 'unverified',
   NULL),
  ('4b359bf6-f967-4089-b25c-6dc266356c20', 'Школьники', 0, '', '', '', '', '',
                                           '34f7ac9f-8118-4713-b5b0-e359c21caae1', NULL, NULL, NULL,
   NULL, NULL),
  ('fdee9aa1-c349-423c-ba9e-3f00f2b49d13', 'Школьники', 0, '', '', '', '', '',
                                           '34f7ac9f-8118-4713-b5b0-e359c21caae1', NULL, NULL, NULL,
   NULL, NULL);

INSERT INTO dc4hb17n6gdggs.public.hack_skill_tags VALUES
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 32),
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 33),
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 34),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 34),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 32),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 33),
  ('fdee9aa1-c349-423c-ba9e-3f00f2b49d13', 32),
  ('fdee9aa1-c349-423c-ba9e-3f00f2b49d13', 38),
  ('fdee9aa1-c349-423c-ba9e-3f00f2b49d13', 39);

INSERT INTO dc4hb17n6gdggs.public.hack_scope_tags VALUES
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 35),
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 36),
  ('0aa7ab58-0809-4ee2-bce8-cb946e7a759a', 37),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 40),
  ('837b23fa-4195-42f3-912a-92f043a43acc', 41);

INSERT INTO dc4hb17n6gdggs.public.user_data VALUES
  (23, 'Работяга', TRUE, 'Санкт-Петербург', '1997-03-12', 'Дмитрий', 'Куклин', 'Владимирович'),
  (27, 'Работяга', TRUE, 'Санкт-Петербург', '1997-03-12', 'Дмитрий', 'Куклин', 'Владимирович'),
  (31, '', TRUE, '', '', '', '', '');

INSERT INTO dc4hb17n6gdggs.public.status_company VALUES
  ('96091710-cbc9-4aec-a34d-f6c364440f6e', 'INDIVIDUAL'),
  ('34f7ac9f-8118-4713-b5b0-e359c21caae1', 'INDIVIDUAL'),
  ('65831511-063d-4558-adcd-3714cfde62c8', 'INDIVIDUAL');

INSERT INTO dc4hb17n6gdggs.public.sex VALUES
  (23, 'MAN'),
  (27, 'MAN'),
  (31, 'MAN');
INSERT INTO dc4hb17n6gdggs.public.education_lvl VALUES
  (22, 'PUPIL'),
  (26, 'PUPIL'),
  (30, 'PUPIL');

INSERT INTO dc4hb17n6gdggs.public.education VALUES
  (22, 4, 'BIT', 'ITMO'),
  (26, 4, 'BIT', 'ITMO'),
  (30, 1, '', '');

INSERT INTO dc4hb17n6gdggs.public.contact VALUES
  (21, 'dima@yandex.ru', '8912344311', 'Skype123'),
  (25, 'dima@yandex.ru', '8912344311', 'Skype123'),
  (29, '', '', '');

INSERT INTO dc4hb17n6gdggs.public.career VALUES
  (20, 'Bit', 'Уборщик'),
  (24, 'Bit', 'Уборщик'),
  (28, '', '');

INSERT INTO dc4hb17n6gdggs.public.profile VALUES
  ('7eaf7c29-8914-4038-b6cf-7530a42bbead', 'Admin',
   '$2a$10$0/h06uwaoFNsjpBoNFb2oO9FYxlijVbKvVzG8.20HtfoWYEeWjgDG', 28,
   '65831511-063d-4558-adcd-3714cfde62c8', 29, 30, 31),
  ('f21a5750-bae4-4107-9537-6020b1d68efd', 'Dmitry',
   '$2a$10$0/h06uwaoFNsjpBoNFb2oO9FYxlijVbKvVzG8.20HtfoWYEeWjgDG', 24,
   '34f7ac9f-8118-4713-b5b0-e359c21caae1', 25, 26, 27);

INSERT INTO dc4hb17n6gdggs.public.profile_role VALUES
  ('f21a5750-bae4-4107-9537-6020b1d68efd', 'ADMIN'),
  ('7eaf7c29-8914-4038-b6cf-7530a42bbead', 'USER');

INSERT INTO dc4hb17n6gdggs.public.request VALUES
  (1, 'a829d7e0-42ed-4bca-942a-eff9aa251fab', 'sdgdfgdf', 'be87a209-2114-45b3-9d5a-86d00060fe68');

