DROP TABLE IF EXISTS fiction;

CREATE TABLE fiction(
  id BIGINT(40) NOT NULL COMMENT '主键ID',
  name VARCHAR(20) NOT NULL COMMENT '小说名称',
  author VARCHAR(20) NOT NULL COMMENT '作者',
  intro VARCHAR(200) DEFAULT NULL COMMENT '作品简介',
  total INT(6) NOT NULL DEFAULT 0 COMMENT '章节总数',
  last_updated DATETIME DEFAULT NULL COMMENT '最后更新日期',
  origin VARCHAR(20) NOT NULL COMMENT '来源',
  url VARCHAR(200) NOT NULL COMMIT '链接地址',
  status TINYINT(1) NOT NULL DEFAULT '1' COMMENT '0无效,1有效',
  create_time DATETIME DEFAULT NULL COMMENT '创建时间',
  update_time DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='小说表';


DROP TABLE IF EXISTS chapter;

CREATE TABLE chapter(
  id BIGINT(40) NOT NULL COMMENT '主键ID',
  fiction_id BIGINT(40) NOT NULL COMMENT '小说ID',
  number VARCHAR(20) NOT NULL COMMENT '章节号',
  title VARCHAR(20) NOT NULL COMMENT '章节标题',
  content TEXT DEFAULT NULL COMMENT '章节内容',
  url VARCHAR(200) NOT NULL COMMIT '链接地址',
  previous_chapter BIGINT(40) DEFAULT NULL COMMENT '上一章ID',
  next_chapter BIGINT(40) DEFAULT NULL COMMENT '下一章ID',
  status TINYINT(1) NOT NULL DEFAULT '1' COMMENT '0无效,1有效',
  create_time DATETIME DEFAULT NULL COMMENT '创建时间',
  update_time DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='小说章节表';