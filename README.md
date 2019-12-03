# MyBatis-Plus Samples


本工程为 MyBatis-Plus 的示例，项目结构如下：

- mybatis-plus-sample-quickstart：MyBatis-Plus 快速开始示例

# jasypt数据库加密

java -cp d:\repo\org\jasypt\jasypt\1.9.2\jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="123456" password=Abc123 algorithm=PBEWithMD5AndDES



----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.201-b09



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: 123456
password: Abc123



----OUTPUT----------------------

QM+qNMH0NUX/QMFLl8UVBg==


#修改MySQL的时区

C:\ProgramData\MySQL\MySQL Server 8.0\my.ini

最后一行加上

default-time-zone='+8:00'

MySQL服务重启

--查看时区值

show variables like '%time_zone%';

方法2：

配置JDBC连接参数

在url连接字符串后面加上?serverTimezone=UTC