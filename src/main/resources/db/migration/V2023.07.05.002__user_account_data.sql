INSERT INTO customers
(id,
customer_name,
contact_name,
country,
customer_last_name)
VALUES
(DEFAULT,
'John',
'',
'Ukraine',
'Peterson');


INSERT INTO user_account
(id,
username,
password,
customer_id,
is_account_non_expired,
is_account_non_locked,
is_credentials_non_expired,
is_enabled,
authorities)
VALUES
(DEFAULT,
'test@gmail.com',
'$2a$10$QWlgG.OD2HYU.qv49oe/N.vAmWtFSoxuL1qTG1pCVF3WWIMBnQ7WG',
1,
true,
true,
true,
true,
'USER,SUPER_ADMIN');
