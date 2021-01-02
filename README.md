# smart-city
智慧城市后台服务

## modules介绍
* smart-city-parent 
    所有子模块的父模块工程,配置所有依赖的相关版本,依赖设置
* smart-commons
    整个工程的的通用模块
* smart-services
    整个工程共有服务的模块
* smart-applications
    智慧城市后台服务对外提供restFul接口服务的应用
    最终的打包使用spring-boot-maven-plugin进行打包,打包为整体jar,使用spring-boot引导启动的服务jar包

## 开发说明

在develop分支上开发