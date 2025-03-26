-- auto-generated definition
create table blog_system
(
    id                        bigint auto_increment comment '主键id'
        primary key,
    homeBannerBgImageUrl      varchar(255)                       null comment 'home banner 背景图的URL地址',
    homeBannerPrintText       varchar(128)                       null comment 'home banner 打印文字，以JSON列表格式存储',
    homeBannerTitle           varchar(255)                       null comment 'home banner 的标题',
    moreImageUrl              varchar(255)                       null comment '更多背景图片url',
    moreTitle                 varchar(64)                        null comment '更多de标题',
    moreContent               varchar(64)                        null comment '更多de内容',
    loginImageUrl             varchar(255)                       null comment '登录页面背景图',
    businessCardAvatarUrl     varchar(255)                       null comment '名片头像地址',
    businessCardTop           varchar(255)                       null comment '名片顶部展示的文字',
    businessCardText          varchar(128)                       null comment '名片展示文字，以JSON列表格式存储，html文档 支持<b></b>标签高亮',
    businessCardTitle         varchar(255)                       null comment '名片的标题',
    businessCardName          varchar(255)                       null comment '名片的名称',
    businessCardBiliUrl       varchar(255)                       null comment '名片关联的B站分享地址',
    businessCardGitHubUrl     varchar(255)                       null comment '名片关联的GitHub分享地址',
    shareBiliUrl              varchar(255)                       null comment 'B站分享地址',
    shareGitHubUrl            varchar(255)                       null comment 'GitHub分享地址',
    shareGiteeUrl             varchar(255)                       null comment 'Gitee分享地址',
    shareEmailUrl             varchar(255)                       null comment 'Email分享地址',
    shareQQUrl                varchar(255)                       null comment 'QQ分享地址',
    shareWxUrl                varchar(255)                       null comment '微信分享地址',
    shareDyUrl                varchar(255)                       null comment '抖音分享地址',
    blogSystemUserName        varchar(255)                       null comment '博客系统管理员的名称',
    blogCreateTime            datetime                           null comment '博客的创建时间',
    blogRegistrationNumber    varchar(255)                       null comment '博客的备案号',
    blogRegistrationNumberUrl varchar(255)                       null comment '博客备案号地址',
    createTime                datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime                  datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime                datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete                  tinyint  default 0                 not null comment '是否删除'
)
    comment '博客系统表' collate = utf8mb4_unicode_ci;

