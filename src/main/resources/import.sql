INSERT INTO tb_employee (employee_code, name, cpf, birth_date, password) VALUES ('EMP-1', 'Alice Silva', '12345678900', '1990-01-15', '$2a$10$Twc/fGLac9DEXOaRU9UGg.Mm1FjBJ54GZnr61P7WELg/oIt9Mf7Qu');
INSERT INTO tb_employee (employee_code, name, cpf, birth_date, password) VALUES ('EMP-2', 'Bruno Costa', '98765432100', '1985-06-22', '$2a$10$Twc/fGLac9DEXOaRU9UGg.Mm1FjBJ54GZnr61P7WELg/oIt9Mf7Qu');
INSERT INTO tb_employee (employee_code, name, cpf, birth_date, password) VALUES ('EMP-3', 'Carla Souza', '45678912300', '1992-12-08', '$2a$10$Twc/fGLac9DEXOaRU9UGg.Mm1FjBJ54GZnr61P7WELg/oIt9Mf7Qu');

INSERT INTO tb_role (authority) VALUES ('ROLE_EMPLOYEE');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_employee_role (employee_id, role_id) VALUES (1, 1);
INSERT INTO tb_employee_role (employee_id, role_id) VALUES (1, 2);
INSERT INTO tb_employee_role (employee_id, role_id) VALUES (2, 1);
INSERT INTO tb_employee_role (employee_id, role_id) VALUES (3, 1);

INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-01', '08:00:00', '12:00:00', '13:00:00', '17:00:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-02', '08:05:00', '12:05:00', '13:05:00', '17:05:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-03', '08:10:00', '12:00:00', '13:00:00', '17:10:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-04', '08:00:00', '12:00:00', '13:00:00', '17:15:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-05', '08:20:00', '12:20:00', '13:20:00', '17:30:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-06', '08:00:00', '12:00:00', '13:00:00', '17:00:00', 1, false)
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-07', '08:10:00', '12:10:00', '13:10:00', '17:20:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-08', '08:15:00', '12:15:00', '13:15:00', '17:15:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-09', '08:00:00', '12:00:00', '13:00:00', '17:00:00', 1, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-10', '08:05:00', '12:05:00', '13:05:00', '17:05:00', 1, false);

INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-01', '08:15:00', '12:15:00', '13:15:00', '17:15:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-02', '08:10:00', '12:10:00', '13:10:00', '17:10:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-03', '08:20:00', '12:20:00', '13:20:00', '17:20:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-04', '08:25:00', '12:15:00', '13:10:00', '17:25:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-05', '08:30:00', '12:25:00', '13:30:00', '17:30:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-06', '08:10:00', '12:10:00', '13:10:00', '17:10:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-07', '08:20:00', '12:20:00', '13:20:00', '17:25:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-08', '08:30:00', '12:25:00', '13:35:00', '17:40:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-09', '08:25:00', '12:20:00', '13:20:00', '17:30:00', 2, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-10', '08:15:00', '12:15:00', '13:15:00', '17:15:00', 2, false);

INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-01', '08:30:00', '12:30:00', '13:30:00', '17:30:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-02', '08:25:00', '12:20:00', '13:20:00', '17:35:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-03', '08:40:00', '12:40:00', '13:45:00', '17:40:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-04', '08:50:00', '12:45:00', '13:50:00', '17:45:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-05', '08:35:00', '12:30:00', '13:30:00', '17:50:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-06', '08:40:00', '12:40:00', '13:40:00', '17:40:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-07', '08:45:00', '12:50:00', '13:45:00', '17:45:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-08', '08:50:00', '12:45:00', '13:50:00', '17:55:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-09', '08:55:00', '12:55:00', '13:55:00', '18:00:00', 3, false);
INSERT INTO tb_time_bank (date, clock_in, lunch_out, lunch_in, clock_out, employee_code, is_deleted) VALUES ('2024-12-10', '08:40:00', '12:40:00', '13:40:00', '17:50:00', 3, false);