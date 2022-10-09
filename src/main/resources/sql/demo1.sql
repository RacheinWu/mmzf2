create database mmzf;
use mmzf;
create table mzzf.student_high_info
(
    id                                          bigint            not null,
    avatar                                      varchar(512)      null comment '个人照片/头像',
    nickname                                    varchar(128)      null comment '真实姓名',
    gender                                      tinyint           null,
    minzu                                       varchar(64)       null comment '名族',
    zhengzhimianmao                             varchar(256)      null comment '政治面貌',
    phone                                       char(12)          null comment '联系方式（手机号',
    address                                     varchar(512)      null comment '籍贯',
    school                                      varchar(256)      null comment '当前院校',
    hobby                                       text              null comment '兴趣爱好、特长',
    shifou_liaojie_jiaxinagrencai_yinjinzhengce tinyint default 0 null comment '是否了解家乡人才引进政策（是/否）',
    shifou_liaojie_jiaxinagfazhan_zhuangkuan    tinyint default 0 null comment '是否了解家乡发展状况（是/否）',
    heshihedi_huode_hezhong_jiangli             text              null comment '何时何地获得何种奖励或荣誉（自填）',
    remark                                      text              null,
    gmt_modified                                datetime          null,
    status                                      tinyint default 1 null comment '默认1 正常',
    gmt_create                                  datetime          null,
    job_direction                               varchar(64)       null comment '就业岗位方向（选择 策划运营/质量工程师/研发/销售/管理/考公/事业编/设计/新媒体/教师/编程人员/其他',
    huixiang_yiyuan                             varchar(64)       null comment '回乡意愿（选择 强烈/一般/无',
    jiuye_qingkuang                             varchar(64)       null comment '就业情况（选择 已就业/待业）',
    zhiye_direction                             varchar(64)       null comment '职业方向（选择  考公、考编/企业就业/创业）',
    major_name                                  varchar(256)      null comment '专业名称（自填）',
    major_category                              varchar(256)      null comment '专业大类（选择',
    openid                                      varchar(256)      null,
    constraint student_low_info_id_uindex
        unique (id)
)
    comment '大学生信息'  charset utf8;

alter table mzzf.student_high_info
    add primary key (id);

create table mzzf.student_low12_info
(
    id                                 bigint            not null,
    avatar                             varchar(512)      null comment '个人照片/头像',
    nickname                           varchar(128)      null comment '真实姓名',
    gender                             tinyint           null,
    minzu                              varchar(64)       null comment '名族',
    zhengzhimianmao                    varchar(256)      null comment '政治面貌',
    phone                              char(12)          null comment '联系方式（手机号',
    address                            varchar(512)      null comment '籍贯',
    school                             varchar(256)      null comment '当前院校',
    grade                              varchar(128)      null comment '年级',
    shetuan                            varchar(512)      null comment '社团',
    renzhi                             varchar(512)      null comment '任职',
    hobby                              text              null comment '兴趣爱好、特长',
    aim_school                         varchar(512)      null comment '目标高校',
    mubiao_major                       varchar(512)      null,
    shifou_liaojie_gaokao_xingshi      tinyint default 0 null comment '是否了解近年高考形势、政策（是/否）',
    shifou_xuyao_xuexi_jinyan_fenxiang tinyint default 0 null comment '是否需要学习经验分享类讲座（是/否）',
    status                             tinyint default 1 null comment '默认1 正常',
    gmt_create                         datetime          null,
    gmt_modified                       datetime          null,
    openid                             varchar(128)      null,
    constraint student_low_info_id_uindex
        unique (id)
)  charset utf8;

alter table mzzf.student_low12_info
    add primary key (id);

create table mzzf.student_low3_info
(
    id                                              bigint            not null,
    avatar                                          varchar(512)      null comment '个人照片/头像',
    nickname                                        varchar(128)      null comment '真实姓名',
    gender                                          tinyint           null,
    minzu                                           varchar(64)       null comment '名族',
    zhengzhimianmao                                 varchar(256)      null comment '政治面貌',
    phone                                           char(12)          null comment '联系方式（手机号',
    address                                         varchar(512)      null comment '籍贯',
    school                                          varchar(256)      null comment '当前院校',
    grade                                           varchar(128)      null comment '年级',
    shetuan                                         varchar(512)      null comment '社团',
    renzhi                                          varchar(512)      null comment '任职',
    hobby                                           text              null comment '兴趣爱好、特长',
    aim_school                                      varchar(512)      null comment '目标高校',
    xuanke_direction                                varchar(64)       null comment '选科方向（物理/历史）',
    shifou_liaojie_gaokao_xingshi                   tinyint default 0 null comment '是否了解近年高考形势、政策（是/否）',
    shifou_xuyao_xuexi_jinyan_fenxiang              tinyint default 0 null comment '是否需要学习经验分享类讲座（是/否）',
    shifou_liaojie_zhiyuantianbao_liucheng          tinyint default 0 null comment '是否了解志愿填报流程（是/否）',
    shifou_liaojie_geyixiangzhuanye_fazhan_qianjing tinyint default 0 null comment '是否了解各意向专业发展前景（是/否）',
    status                                          tinyint default 1 null comment '默认1 正常',
    gmt_create                                      datetime          null,
    gmt_modified                                    datetime          null,
    mubiao_major                                    varchar(512)      null comment '目标专业',
    openid                                          varchar(128)      null,
    constraint student_low_info_id_uindex
        unique (id)
)
    comment '高三学生表'  charset utf8;

alter table mzzf.student_low3_info
    add primary key (id);

create table mzzf.t_activity
(
    id           bigint            not null
        primary key,
    name         varchar(512)      null comment '活动名称',
    time_start   datetime          null comment '活动开始时间',
    time_end     datetime          null comment '活动结束时间',
    address      varchar(512)      null comment '活动地点',
    gmt_modified datetime          null,
    gmt_create   datetime          null comment '创建时间',
    remark       text              null comment '备注',
    status       tinyint default 1 null comment '默认1：正常'
)
    comment '活动'  charset utf8;

create table mzzf.t_article
(
    id                    int auto_increment
        primary key,
    title                 varchar(128)       null comment '标题',
    content               text               null comment '内容（富文本）',
    status                tinyint default 1  null comment '状态: 0/1 -> 禁用/正常',
    only_fans_can_comment tinyint default 0  null comment 'Uint32 是否粉丝才可评论，0所有人可评论(默认)，1粉丝才可评论',
    author                varchar(64)        null comment '作者',
    cover_path            varchar(256)       null comment '封面地址url',
    content_source_url    varchar(256)       null comment '阅读全文的url',
    thumb_media_id        varchar(256)       null comment '图文消息的封面图片素材id（一定是永久MediaID）
',
    need_open_comment     tinyint default 0  null comment 'Uint32 是否打开评论，0不打开(默认)，1打开
',
    show_cover_pic        tinyint default 0  null comment '是否显示封面，1为显示，0为不显示',
    `index`               int                null comment '次序',
    draft_id              bigint             null comment '推文id',
    activity_id           bigint  default -1 null comment '默认-1 没有绑定，也就是其他类型；绑定的活动id''',
    gmt_create            datetime           null,
    gmt_modified          datetime           null
)
    comment '文章' charset = utf8;

create table mzzf.t_draft
(
    id           bigint auto_increment,
    status       tinyint default 1 null comment '默认1 正常',
    is_fabu      tinyint default 0 null comment '默认0 未发布',
    gmt_create   datetime          null,
    gmt_modified datetime          null,
    constraint t_draft_id_uindex
        unique (id)
)
    comment '推文'  charset utf8;

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
    comment '日志'  charset utf8;

create table mzzf.t_role
(
    id           int auto_increment,
    name         varchar(128)      null,
    remark       text              null,
    gmt_modified datetime          null,
    gmt_create   datetime          null,
    status       tinyint default 1 null comment '默认1 正常',
    constraint t_role_id_uindex
        unique (id)
)  charset utf8;

alter table mzzf.t_role
    add primary key (id);

create table mzzf.t_user
(
    id             int auto_increment
        primary key,
    openid         varchar(256)      null comment '微信联动',
    subscribe      tinyint default 0 null comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
    role_id        int     default 0 null comment '0:默认guest',
    gender         char(8)           null comment '性别',
    gmt_create     datetime          null,
    gmt_modified   datetime          null,
    nickname       varchar(256)      null comment '真实名称',
    subscribe_time varchar(15)       null comment '订阅时间',
    category_id    int               null comment '用户类型id，是学生还是xx',
    constraint t_user_openid_uindex
        unique (openid)
)
    comment '用户' charset = utf8;

create table mzzf.user_activity
(
    id           int auto_increment
        primary key,
    open_id      varchar(512)      not null comment '用户微信号',
    activity_id  bigint            not null comment '活动id',
    status       tinyint default 1 null comment '默认为1：正常',
    gmt_create   datetime          null comment '报名时间',
    gmt_modified datetime          null
)
    comment '用户and活动报名关系'  charset utf8;

create table mzzf.user_admin_apply
(
    id                   bigint            not null
        primary key,
    openid               varchar(128)      null,
    admin_level          int               null comment '申请的管理员等级',
    status               tinyint default 1 null comment '身份审核状态 默认1 通过',
    suozaidanwei         varchar(512)      null comment '所在单位',
    danweizhiwu          varchar(512)      null comment '单位职务',
    remark               text              null,
    gmt_create           datetime          null,
    gmt_modified         datetime          null,
    zhengmingcailiao_url text              null comment '证明材料url 用-分隔'
)
    comment '管理员申请表'  charset utf8;

create table mzzf.user_category
(
    id     int auto_increment
        primary key,
    name   varchar(256)      null,
    status tinyint default 1 null comment '默认1 正常 / 0不正常'
)
    comment '用户分类表：高中还是大学生还是社会人士' charset utf8;

