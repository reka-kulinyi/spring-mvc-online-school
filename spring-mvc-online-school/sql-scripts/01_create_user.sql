CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON *.* TO 'springuser'@'localhost';
# from MySQL 8
ALTER USER 'springuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springuser';
