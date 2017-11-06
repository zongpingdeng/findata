invalidate metadata;
use %database%;
create table IF NOT EXISTS %tableName%_stage1 like %tableName%;
insert overwrite table %tableName%_stage1 select * from %tableName%_current_month;
insert overwrite table  %tableName%_current_month select * from %tableName%_stage1 t where t.id not in (select t1.id from %tableName% t1);
insert into %tableName%_current_month select * from %tableName%;