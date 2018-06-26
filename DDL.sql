create table repeat_policy
(
	id char(36) not null comment '任务重复策略UUID'
		primary key,
	one_time_task bit not null comment '是否一次性任务',
	every int null comment '每n天/周/月/年重复一次',
	frequency int default '0' null comment '0 for the ''day'', 5 for the ''week'', 10 for the ''month'',15 for the ''year'''
)
comment '任务重复策略：一次性活动，每n天，每n周某天，每n月某天，每n年某天，n为大于等于0的整数'
;

create table repetition_end_condition
(
	id char(36) not null comment '重复结束条件UUID'
		primary key,
	policy int default '1' null comment '1 for the ''never''（永不）
2 for the ''time''（时间）
3 for the ''frequency''（次数）',
	never bit default b'0' null comment '永不结束',
	time datetime null comment '到特定时间结束',
	frequency int null comment '达到特定次数结束'
)
comment '重复结束条件表'
;

create table role
(
	id varchar(36) not null comment '用户角色uuid'
		primary key,
	level int default '10' not null comment '0 for the user/root users
10 for the ordinary users'
)
comment '用户角色'
;

create table user
(
	id char(36) default '' not null comment '用户唯一标识符'
		primary key,
	username varchar(30) not null comment '用户名-唯一',
	nickname varchar(30) null comment '用户昵称',
	password char(32) not null comment '登录密码（口令）',
	email varchar(200) not null comment '邮箱',
	phone_number varchar(100) null comment '手机号',
	last_login_datetime datetime null comment '上次登录的时间',
	last_login_ip varchar(255) null comment '上次登录的ip',
	avatar varchar(2000) null comment '头像保存的地址',
	role_id char(36) default '0517a45e-de80-43a0-93af-8b7a25f4ecdf' not null comment '角色',
	activated bit default b'0' not null comment '用户是否已经激活',
	constraint user_username_uindex
		unique (username),
	constraint user_email_uindex
		unique (email),
	constraint user_phone_number_uindex
		unique (phone_number),
	constraint user_role_id_fk
		foreign key (role_id) references role (id)
			on update cascade on delete cascade
)
comment '用户基本信息表'
;

create table category
(
	id char(36) not null comment '任务分类表的UUID'
		primary key,
	category_name varchar(255) not null comment '分类的名字',
	user_id char(36) not null comment '用户UUID',
	parent_id char(36) null comment '父级文件夹的UUID',
	constraint category_user_id_fk
		foreign key (user_id) references user (id)
			on update cascade on delete cascade
)
comment '分类表'
;

create index category_user_id_fk
	on category (user_id)
;

create table task
(
	id char(36) not null comment '任务的UUID'
		primary key,
	title varchar(255) not null comment '任务名称/标题',
	begain_time datetime null comment '任务开始时间',
	end_time datetime null comment '任务结束时间',
	all_day_task bit default b'0' not null comment 'bool值，是否是全天任务',
	task_modified bit default b'0' not null comment '任务是否修改过',
	remind_time varchar(255) null comment '提醒时间：存一串逗号分隔的字符串，比如0,1,5,10意思就是活动开始前的0分钟，1分钟，五分钟，10分钟，分别提醒。为空就不提醒',
	repeat_policy_id char(36) not null comment '重复策略的UUID',
	repetition_end_condition_id char(36) not null comment '结束重复的条件外键UUID',
	location varchar(2000) null comment '活动地点',
	notation varchar(5000) null comment '备注',
	task_category_id char(36) not null comment '任务分类UUID',
	CONSTRAINTS task_title UNIQUE (title),
	constraint task_repeat_policy_id_fk
		foreign key (repeat_policy_id) references repeat_policy (id)
			on update cascade on delete cascade,
	constraint task_repetition_end_condition_id_fk
		foreign key (repetition_end_condition_id) references repetition_end_condition (id)
			on update cascade on delete cascade,
	constraint task_category_id_fk
		foreign key (task_category_id) references category (id)
			on update cascade on delete cascade
)
comment '任务表'
;

create index task_category_id_fk
	on task (task_category_id)
;

create index task_repeat_policy_id_fk
	on task (repeat_policy_id)
;

create index task_repetition_end_condition_id_fk
	on task (repetition_end_condition_id)
;

create index user_role_id_fk
	on user (role_id)
;

create table verification_token
(
	id char(36) not null comment '验证码的UUID'
		primary key,
	token varchar(36) not null comment 'token',
	user_id char(36) not null comment '用户id外键',
	expiryDate datetime not null comment '验证码过期时间'
)
comment '验证码'
;

