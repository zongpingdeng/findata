#!/bin/sh
#This file is auto gen by data-inc-sync framework, please do not edit!!
sqoop import --connect %connectString%  --username %username% --password %password% --table %sourceTable%  --hive-overwrite --as-parquetfile  --compression-codec=snappy --warehouse-dir=/user/hive/warehouse/ --hive-import -m 1 --hive-database %targetDatabase%  --where " %where% "
impala-shell -f %merge_sql_file%