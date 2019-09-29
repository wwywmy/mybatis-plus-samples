
-- ----------------------------
-- Table structure for build
-- ----------------------------
DROP TABLE IF EXISTS `build`;
CREATE TABLE `build`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教学楼' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1178120194015498240, '软件项目管理');
INSERT INTO `course` VALUES (1178120781289361408, '物流管理');
INSERT INTO `course` VALUES (1178120782501515264, '供应链管理');
INSERT INTO `course` VALUES (1178120782769950720, '电子商务案例分析');


-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (6, '赵子骞', 24);
INSERT INTO `student` VALUES (7, '孟浩然', 24);
INSERT INTO `student` VALUES (8, '龚伟泽', 23);
INSERT INTO `student` VALUES (9, '江鹏涛', 22);
INSERT INTO `student` VALUES (10, '彭皓轩', 29);
INSERT INTO `student` VALUES (11, '邱鑫鹏', 20);
INSERT INTO `student` VALUES (12, '陈思聪', 22);


-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1178113269612990465, '0765', '雷志泽');
INSERT INTO `teacher` VALUES (1178113341218181122, '2100', '卢振家');
INSERT INTO `teacher` VALUES (1178113368455909378, '6373', '覃梓晨');


-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '姚智渊', 21, '92893775@qq.com', NULL, 1, '{\"city\":\"厦海市\",\"province\":\"广西省\"}');
INSERT INTO `user` VALUES (5, '曹建辉', 43, '91379176@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (17, '段绍齐', 43, '20620604@qq.com', NULL, 1, NULL);
INSERT INTO `user` VALUES (20, '朱炫明', 74, '41836755@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (21, '叶正豪', 29, '51178015@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (23, '侯钰轩', 53, '59403257@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (28, '曹彬', 93, '32705093@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (33, '傅志泽', 54, '14286729@qq.com', NULL, 1, NULL);
INSERT INTO `user` VALUES (34, '赖明', 91, '86340826@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (37, '侯昊焱', 66, '75369134@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (38, '李烨磊', 19, '09297357@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (42, '郑建辉', 58, '85824548@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (46, '赖瑾瑜', 60, '20098049@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (47, '贾胤祥', 42, '56937352@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (49, '覃鹏飞', 97, '04306490@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (54, '薛航', 71, '52245248@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (58, '吕志强', 36, '05882136@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (59, '万天翊', 51, '39957264@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (61, '钟思远', 53, '20112990@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (62, '杨浩', 56, '38731677@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (65, '邓绍齐', 63, '15961102@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (67, '姚明', 100, '79301326@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (69, '郭展鹏', 17, '28241617@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (70, '孔子轩', 90, '42535937@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (73, '尹涛', 93, '08328700@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (75, '金伟宸', 98, '75227213@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (78, '董天翊', 54, '99901995@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (80, '顾昊强', 70, '94332682@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (82, '金伟泽', 14, '89400590@qq.com', 'F', NULL, NULL);
INSERT INTO `user` VALUES (83, '陶绍齐', 68, '78686577@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (86, '莫振家', 45, '00866606@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (88, '崔子默', 25, '88777800@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (94, '雷鹏煊', 41, '72213835@qq.com', 'U', NULL, NULL);
INSERT INTO `user` VALUES (98, '何博涛', 6, '51784499@qq.com', 'M', NULL, NULL);
INSERT INTO `user` VALUES (100, '范伟诚', 38, '63760355@qq.com', 'F', NULL, NULL);

