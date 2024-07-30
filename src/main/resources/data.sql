INSERT INTO public.users
("password", username)
VALUES('555', 'rafa'), ('123', 'boss')
ON CONFLICT(username) DO NOTHING;
--INSERT INTO public.users
--("password", username)
--VALUES('123', 'boss') ON CONFLICT(username) DO NOTHING;

--INSERT INTO public.task
--(description, user_id)
--VALUES('tarefa 999 teste', 1), ('tarefa 123 teste', 1), ('tarefa 736 teste', 1)
--ON CONFLICT(description) DO NOTHING;

INSERT INTO public.task
(description, user_id)
VALUES('tarefa 999 teste', 1);
INSERT INTO public.task
(description, user_id)
VALUES('tarefa 123 teste', 1);
INSERT INTO public.task
(description, user_id)
VALUES('tarefa 736 teste', 1);