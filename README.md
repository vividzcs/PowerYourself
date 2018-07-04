## 项目概述
1. 功能
[![](http://data.nwuer.com/poweryourself_usecase.png)](http://data.nwuer.com/poweryourself_usecase.png)

2. 技术栈
> springboot  
mybatis  
quartz  
freemarker

3. 参与人员
> 邓正成  前后端代码编写  
曾小港  文档编写  
彭东旭  文档编写  
王翼翡  文档编写  


## PowerYourself工作笔记
### Work Done By **邓正成**

#### 周五
> 
- 前端页面的选取    DONE
- 前端页面的修改    DONE
- 编写合适的前端页面    DONE
- 计划变更,要一个人写,改前后端分离为偏后端

===================
### 后端
===================

#### 周六
> 
- 增加分类    DONE
- 删除分类    DONE
- 展示分类    DONE
- 增加任务    DONE
- 删除任务    DONE
- 展示任务    DONE

#### 周日
> 
- 首页信息的展示
    - 任务展示    DONE
    - 按分类展示(只展示正常的)    DONE
- 任务分页    DONE

#### 周一
> 
- 发送邮件    DONE
- 统计    DONE
- 管理后台: 只用于对用户的查删
    - 人员分页    DONE
    - 人员冻结    DONE
    - 人员激活    DONE
    - 人员删除    DONE
- 忘记密码时重置密码    DONE
- 过滤器: 首先判断在cache里看是否有，new一个临时的userdto放session里，如果有就去到对应的页面    DONE
- 已完成任务展示    DONE
- 过期任务展示    DONE
- 每次查询时试着更改任务状态    DONE
- 人员搜索    DONE

#### 周二
> 
- 个人信息修改    DONE
- 修改注册        DONE
- 激活    DONE
- 分类的编辑        DONE
- 任务的编辑    DONE
    - 修复了错误提示不出现    DONE
- 未激活不能登录    DONE
- 进度条的完整    DONE
- 添加尾部信息    DONE
- 完成上线    DONE

**只记录了少部分的工作,详细看项目**

> 
部署项目
目前知道的两种方式：
(1)nohup java -jar demo.jar &
(2)nohup java -jar demo.jar & > log.file 2>&1 &
备注:如果 Maven 运行过程出现内存溢出，则可以添加下面参数：export JAVA_OPTS=-Xmx1024m -XX:MaxPermSize=128M -Djava.security.egd=file:/dev/./urandom

### 维护部分

#### 周三

- 修复搜索用户会搜索到管理员自己        Solved
- 修复有的日期显示的是12小时制        Solved
- 修复访问出现TeatPage情况        Solved
- 修复修改信息时得验证
    - 用户名        Solved
    - 邮箱        Solved
- 163 邮件对发送得要求太严格,无故报554DT:SPM 换到qq邮箱        Solved

#### 周四

- 修复添加任务时为过去时间会报错的bug        Solved
- 将页头的左侧统一成PowerYourself        Solved
- 添加进度条hover显示的title,进度条内只显示10个字        Solved
- 增加添加,更新任务时的提示/建议        Solved
- 增加链接时效提示        Solved

#### 周日

- 修复搜索展示列表的报错        Solved
- 修复首页不正确的url导致有关任务操作失败        Solved

#### 周三
- 由于我们允许过期任务,完成任务的再次修改,所以需要在修改时将任务的状态重置为正常状态       Solved
