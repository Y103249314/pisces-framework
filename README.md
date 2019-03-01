# pisces-framework
#项目说明：
##pisces-dynamic-datasource:　
  1. spring框架下的动态数据源管理
  2. 基于mybatis和mysql
  3. 可在运行时基于特定的标识进行数据源自动切换
  4. 可在运行时根据需要在多个数据源间随意切换
  5. 读取数据库配置表动态生成数据源，可在运行时动态插入新的数据源
  6. 可用于多租户数据库隔离，或者其他分库场景
##pisces-file-service:
  1. 基于fastdfs的文件服务封装
  2. 可按分组对不同租户的storage进行隔离
  3. 实现了linux部署环境下按分组动态部署storage
