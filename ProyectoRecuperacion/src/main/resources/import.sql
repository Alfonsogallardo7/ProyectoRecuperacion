-- Contraseña: Admin1
--insert into users (id, full_name, email, password, avatar, created_at, last_password_change_at, role) values (uuid(), 'Admin admin', 'admin@openwebinars.net','$2a$12$cNocjmlKpOA/iMQZAq5bAeV/zrTepR1xr2pKi6lIRBEiAqrV2/6Ya','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'ADMIN');


insert into usuario (id, nombre, apellidos, direccion, email, telefono, avatar, password, role) values (uuid(), 'Admin', 'admin', 'Conde de Bustillo 17', 'admin@openwebinars.net', 697854123, 'foto.png', '$2a$12$cNocjmlKpOA/iMQZAq5bAeV/zrTepR1xr2pKi6lIRBEiAqrV2/6Ya', 'ADMIN');