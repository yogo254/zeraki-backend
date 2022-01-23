
INSERT INTO `app_user` (`id`, `account_type`, `auth_username`, `email`, `firstname`, `lastname`, `password`, `timestamp`) VALUES
	('b37517bd-88fd-4351-bd4a-4f2075d1b031', 0, 'admin@zeraki.com', 'admin@zeraki.com', 'admin', 'super', '$2a$10$3m.32gCBeAHlvFklfQTUHe83LhkG8mkEHWwyhEF33tHbgsAbpFAiK', '2022-01-22 13:22:36');

INSERT INTO `institution` (`id`, `name`, `location`, `keywords`, `timestamp`) VALUES
	('124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Maseno University', 'Kisumu', 'Kisumu Maseno University', '2022-01-22 23:19:42'),
	('7c27ea51-a956-4668-a128-71eb82c6394a', 'University Of Nairobi', 'Nairobi Kenya', 'Nairobi Kenya University Of Nairobi', '2022-01-22 22:35:56'),
	('d15ced99-0a35-4d4c-8e69-de6615dde093', 'Moi University', 'Eldoret', 'Eldoret Moi University', '2022-01-22 23:19:17');

INSERT INTO `course` (`id`, `institution_id`, `name`, `keywords`, `timestamp`) VALUES
	('8a16a3e9-5ff5-4b2a-a219-f40f7be754a9', '124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Information Technology', 'Information Technology', '2022-01-23 01:33:24'),
	('a7432372-5b91-4a6a-8e47-d110f8601a85', '124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Computer Science Edited', 'Computer Science Edited', '2022-01-23 01:06:32');



INSERT INTO `student` (`id`, `name`, `course_id`, `gender`, `timestamp`, `keywords`) VALUES
	('993a4af7-a4cd-4593-ac06-fcd8d26a8d01', 'Chando Steve Yogo edited', 'a7432372-5b91-4a6a-8e47-d110f8601a85', NULL, '2022-01-23 13:07:57', 'Chando Steve Yogo editedComputer Science Edited Kisumu Maseno University');

INSERT INTO `token` (`id`, `refresh_token`, `token`) VALUES
	('b37517bd-88fd-4351-bd4a-4f2075d1b031', 'wr091yx87n3qyg0uehe0sp7hhrox48uhvun873k7mqe8vda5vj', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB6ZXJha2kuY29tIiwiZXhwIjoxNjQyOTM1NDg0fQ.Ytxh9h_xWB9Wr4f0_Mh6_alqrFTJq3pSInktzyzh0_wU4g_Ij5AO41gkkOZ9-6HmHaBoGdWNvJxHnBv6IbtLhQ');

INSERT INTO `user_role` (`id`, `role`, `timestamp`, `user_id`) VALUES
	('2276a2b3-3806-4498-b631-94a6416e45a7', 'ROLE_ADMIN', NULL, 'b37517bd-88fd-4351-bd4a-4f2075d1b031');

