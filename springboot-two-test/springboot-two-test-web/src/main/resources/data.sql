INSERT INTO sys_user (id,user_name,pass_word) VALUES (1,'admin','admin');
INSERT INTO sys_user (id,user_name,pass_word) VALUES (2,'lihui','lihui');
INSERT INTO sys_role (id,name) VALUES (1,'ROLE_USER');
INSERT INTO sys_role (id,name) VALUES (2,'ROLE_ADMIN');
insert into sys_user_roles (sys_user_id,roles_id) values (1,1);
insert into sys_user_roles (sys_user_id,roles_id) values (2,2);