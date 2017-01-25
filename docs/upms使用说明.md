##upms使用说明

####upms功能说明
- 可维护银河系统模块和模块下项目
- 可新增和维护用户
- 可新增和维护角色
- 可新增和维护菜单
- 可新增和维护URL
- 可配置角色和用户关系
- 可配置角色和菜单关系
- 可配置角色和URL关系
- 可配置用户和菜单关系
- 可配置用户可URL关系
- 可配置项目和URL关系
- 可根据用户获取某一项目下的可访问权限信息

####名词解释
- 银河：东区技术中心开发的一体化运行运维平台
- 模块：银河系统下对应的各个系统，如运维管理系统，运营管理系统，系统管理等
- 项目：模块下所包含的各个项目，如系统管理下包含upms系统
- 公共资源：率属于某一项目，所有用户登录该项目都可以访问的资源
- 特别授权：如某一用户张三，关联角色角色管理，原则上张三可以访问角色管理菜单和菜单下对应的URL，单是业务需要张三可以添加其他用户，需要在张三所对应的角色之外特别授权张三可访问用户管理菜单和对应的URL，此行为即特别授权

####使用说明
1. [用户管理](http://upms.eastdc.cn:82/#/system/users/list)
   用户管理下可以新增修改删除用户信息，可以配置用户和菜单的特别授权关系，可以配置用户和URL的特别授权关系。
2. [角色管理](http://upms.eastdc.cn:82/#/system/roles/list)
   角色管理下可以新增修改删除角色，可以配置角色关联的用户，可以分配角色可访问的菜单和URL
3. [模块管理](http://upms.eastdc.cn:82/#/system/resources)
   模块管理下，可新增，修改，删除模块下的项目，可配置某一项目下的菜单和菜单下的URL
4. [公共资源管理](http://upms.eastdc.cn:82/#/system/module/list)
   公共资源管理下可以新增修改删除项目所对应的公共可访问资源（URL）

####使用具体示例图
1. 用户管理
   新增用户：
   ![](http://i.imgur.com/PIRo5aI.png)
   修改用户：
   ![](http://i.imgur.com/YifIotK.png)
   用户特别授权菜单：
   ![](http://i.imgur.com/kpOMYQ4.png)
   用户特别授权资源：
   ![](http://i.imgur.com/hpxIUWR.png)
2. 角色管理
   新增角色:
   ![](http://i.imgur.com/aR8bms3.png)
   修改角色：
   ![](http://i.imgur.com/nGxmVbx.png)
   配置角色用户：
   ![](http://i.imgur.com/06wZBuT.png)
   配置角色菜单：
   ![](http://i.imgur.com/ruIjfA7.png)
   配置角色资源：
   ![](http://i.imgur.com/pxdtyyN.png)
3. 模块管理
   新增项目：
   ![](http://i.imgur.com/Lgeu5tU.png)
   修改项目：
   ![](http://i.imgur.com/qvbXo0R.png)
   配置项目资源：
   ![](http://i.imgur.com/JFf6ZwJ.png)
   新增菜单：
   ![](http://i.imgur.com/cSENXur.png)
   修改菜单：
   ![](http://i.imgur.com/MgkJtQz.png)
   新增资源（URL）
   ![](http://i.imgur.com/TkqsTXr.png)
   修改资源（URL）
   ![](http://i.imgur.com/drYDc0U.png)
3. 公共资源管理
   新增公共资源：
   ![](http://i.imgur.com/Z2b1wvi.png)
   修改公共资源：
   ![](http://i.imgur.com/3gVZ380.png)