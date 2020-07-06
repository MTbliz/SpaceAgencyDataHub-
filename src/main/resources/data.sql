INSERT INTO public.roles(id, name) VALUES (10001, 'ROLE_USER'), (10002,'ROLE_MODERATOR'), (10003,'ROLE_ADMIN');
INSERT INTO public.users(id, first_name, last_name, email, password, username) VALUES (10001,'Piotr', 'Tur', 'piotrturlinski@test.pl', '$2a$10$S4l.zkuCbktuRUc2l5weaO1y/svC5NIMmSWkcG1nCH3EzDD5RMU9e', 'Piotr Turliński');
INSERT INTO public.users(id, first_name, last_name,  email, password, username) VALUES (10002, 'Michal', 'Tur', 'michalturlinski@test.pl', '$2a$10$S4l.zkuCbktuRUc2l5weaO1y/svC5NIMmSWkcG1nCH3EzDD5RMU9e', 'Michał Turliński');
INSERT INTO public.user_roles(user_id, role_id) VALUES (10001, 10003);
INSERT INTO public.user_roles(user_id, role_id) VALUES (10002, 10003);
