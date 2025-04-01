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



INSERT INTO `ym-blog`.blog_system (id, homeBannerBgImageUrl, homeBannerPrintText, homeBannerTitle, newsBannerBgImageUrl,
                                   newsBannerTitle, newsBannerPrintText, slideWrapTitle, slideWrapUrl, moreImageUrl,
                                   moreTitle, moreContent, loginImageUrl, businessCardAvatarUrl, businessCardTop,
                                   businessCardText, businessCardTitle, businessCardName, businessCardBiliUrl,
                                   businessCardGitHubUrl, shareBiliUrl, shareGitHubUrl, shareGiteeUrl, shareEmailUrl,
                                   shareQQUrl, shareWxUrl, shareDyUrl, blogSystemUserName, blogCreateTime,
                                   blogRegistrationNumber, blogRegistrationNumberUrl, createTime, editTime, updateTime,
                                   isDelete)
VALUES (1904389774330777601,
        'http://localhost:9999/api/images/banner/1903819940727529473/80f28438a32e463eb51dace4694ad312.jpg',
        '秦时明月汉时关，万里长征人未还。但使龙城飞将在，不教胡马度阴山。月落乌啼霜满天，江枫渔火对愁眠。姑苏城外寒山寺，夜半钟声到客船。',
        '云猫•Blog', 'http://localhost:9999/api/images/other/1903819940727529473/e13f77338dae4c17a0de3550e8124bd3.jpg',
        '新闻•放眼世界', '月落乌啼霜满天，江枫渔火对愁眠。姑苏城外寒山寺，夜半钟声到客船。', '生活明朗。万物可爱。',
        'YunMao.com', 'http://localhost:9999/api/images/other/1903819940727529473/21c51dd056ba47db8c4131fd129922c8.jpg',
        'YunMao-Test', '测试内容',
        'http://localhost:9999/api/images/other/1903819940727529473/8fad03eef295437aa47d45a8c06e7293.jpg',
        'http://localhost:9999/api/images/banner/1903819940727529473/0cc3ea74f0a041ce88ea784fb5c55ea9.jpeg',
        '吃饱了才能睡着',
        '这有关于<b>产品、设计、开发</b>相关的问题和看法，还有<b>文章翻译</b>和<b>分享</b>。相信你可以在这里找到对你有用的<b>知识</b>和<b>教程</b>。',
        '生活明朗,万物复苏', '云猫YM', 'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0',
        'https://space.bilibili.com/3546660844079248?spm_id_from=333.1007.0.0', '云猫', '2025-03-16 12:12:13', '测试',
        'blogRegistrationNumberUrl', '2025-03-25 12:27:58', '2025-03-25 12:27:58', '2025-04-01 16:24:41', 0);

