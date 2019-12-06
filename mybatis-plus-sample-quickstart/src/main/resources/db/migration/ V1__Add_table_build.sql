use `flyway`;
CREATE TABLE `build`
(
   `id` bigint (20) NOT NULL,
   `name` varchar (255) DEFAULT NULL,
   PRIMARY KEY (`id`)
)
ENGINE= InnoDB DEFAULT CHARSET= utf8mb4 COMMENT= '教学楼';