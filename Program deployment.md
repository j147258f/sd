1.	sql文件夹中有sd.sql和，sdData.sql，其中前者只有表结构，后者还包括测试用的数据
2.	导入工程后，在src目录下的hibernate.cfg.xml中有数据库的相关配置，默认配置为root用户，密码123456，连接至本地数据库
3.	发布程序到Tomcat
4.	通过http://localhost:8080/sdbs/attendence/attendencealltable?year=2016&&month=07链接，可得到查询数据库中2016年7月份所有异常考勤的JSON格式数据
