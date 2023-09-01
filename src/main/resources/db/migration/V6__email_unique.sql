delete from author;
alter table author add constraint email_unique_constraint unique(email);