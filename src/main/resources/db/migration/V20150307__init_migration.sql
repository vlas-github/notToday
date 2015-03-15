insert into Users (id, email, fio, isAccountNonLocked, locality, pass, registrationDate) values
('a89bb9ae-ca3e-11e4-9db4-3085a99f8ed4', 'vlas@email.com', 'Власов Илья Дмитриевич', 1, 'ru', '153b07dbd73d962ff83de065c55e01999c1d2df047d738c0976f6637673057587b6256176a5123aa', null);

insert into UserAuthorities (id, value, user_id) values
('e4229060-ca3e-11e4-b463-3085a99f8ed4', 'ROLE_USER', 'a89bb9ae-ca3e-11e4-9db4-3085a99f8ed4');