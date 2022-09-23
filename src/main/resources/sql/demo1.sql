create table mzzf.draft_article
(
    id           int auto_increment
        primary key,
    draft_id     int      null comment '草稿id',
    article_id   int      null comment '文章id',
    `index`      int      null comment '文章在总的次序',
    gmt_create   datetime null,
    gmt_modified datetime null
)
    comment '草稿·文章关系表';

create table mzzf.t_activity
(
    id           int auto_increment
        primary key,
    gmt_create   datetime     null,
    gmt_modified datetime     null,
    description  text         null comment '活动描述(富文本)',
    status       tinyint      null comment '状态 0/1 -> 已过期/进行中',
    title        varchar(256) null comment '活动标题',
    head         varchar(64)  null comment '负责人',
    address      varchar(256) null comment '活动地址'
)
    comment '活动' charset = utf8;

create table mzzf.t_article
(
    id                    int auto_increment
        primary key,
    gmt_create            datetime          null,
    gmt_modified          datetime          null,
    status                tinyint default 1 null comment '状态: 0/1 -> 禁用/正常',
    content               text              null comment '内容（富文本）',
    author                varchar(64)       null comment '作者',
    cover_path            varchar(256)      null comment '封面地址url',
    content_source_url    varchar(256)      null comment '阅读全文的url',
    thumb_media_id        varchar(256)      null comment '图文消息的封面图片素材id（一定是永久MediaID）
',
    need_open_comment     tinyint default 0 null comment 'Uint32 是否打开评论，0不打开(默认)，1打开
',
    only_fans_can_comment tinyint default 0 null comment 'Uint32 是否粉丝才可评论，0所有人可评论(默认)，1粉丝才可评论',
    show_cover_pic        tinyint default 0 null comment '是否显示封面，1为显示，0为不显示'
)
    comment '文章' charset = utf8;

create table mzzf.t_draft
(
    id           int auto_increment,
    gmt_create   datetime null,
    gmt_modified datetime null,
    constraint t_draft_id_uindex
        unique (id)
);

create table mzzf.t_file
(
    id           bigint       not null
        primary key,
    suffix       varchar(128) null comment '文件后缀名 .xxx',
    path         varchar(512) null comment '保存地址',
    name         varchar(512) null comment 'UUID保存的名字',
    old_name     varchar(512) null comment '上传时的名字',
    type         varchar(128) null comment '枚举类',
    size         int          null comment '字节大小',
    gmt_create   datetime     null,
    gmt_modified datetime     null
)
    comment '文件' charset = utf8;

create table mzzf.t_info
(
    id           int auto_increment
        primary key,
    description  varchar(128)      null,
    value        varchar(512)      null,
    status       tinyint default 1 null,
    gmt_create   datetime          null,
    gmt_modified datetime          null,
    name         varchar(128)      null
)
    comment '固定的选项信息';

create table mzzf.t_log
(
    id           int auto_increment
        primary key,
    url          varchar(256)      null comment '接口地址',
    content      text              null comment '请求内容',
    result       tinyint default 0 null,
    ip           varchar(55)       null,
    gmt_create   datetime          null,
    gmt_modified datetime          null
)
    comment '日志';

create table mzzf.t_majority
(
    id           int auto_increment
        primary key,
    description  varchar(128)      null,
    status       tinyint default 1 null,
    gmt_create   datetime          null,
    gmt_modified datetime          null
);

create table mzzf.t_role
(
    id           int auto_increment,
    name         varchar(128) null,
    description  varchar(128) null,
    gmt_create   datetime     null,
    gmt_modified datetime     null,
    constraint t_role_id_uindex
        unique (id)
);

create table mzzf.t_user
(
    id              int auto_increment
        primary key,
    gmt_create      datetime          null,
    gmt_modified    datetime          null,
    tag_id          varchar(64)       null comment '人群标签id',
    avatar          varchar(256)      null comment '头像图片存储路径',
    openid          varchar(256)      null comment '微信联动',
    in_school       varchar(256)      null comment '在读院校',
    graduate_school varchar(256)      null comment '毕业院校',
    academy_id      int               null comment '专业大类id',
    majority        varchar(256)      null comment '专业名称',
    status          tinyint default 1 null comment '状态: 0/1 -> 不正常/正常',
    nationality_id  int               null comment '民族',
    identity_state  int               null comment '身份地位',
    nickname        varchar(55)       null comment '姓名',
    gender          char(4)           null comment '性别',
    contact         varchar(256)      null comment '联系方式',
    birthday        date              null,
    address         varchar(256)      null comment '籍贯',
    is_admin        tinyint default 0 null comment '是否管理员？ 0/1 -> 否/是',
    subscribe_time  varchar(15)       null comment '订阅时间',
    subscribe       tinyint default 0 null comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
    role_id         int     default 0 null comment '0:默认guest',
    constraint t_user_openid_uindex
        unique (openid)
)
    comment '用户' charset = utf8;

