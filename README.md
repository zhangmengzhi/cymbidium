# cymbidium
办公桌上一直有一个一角钱的硬币，反面图案是张荷素（春兰的一种），又和母亲的名字相合，有意思的很。cymbidium 项目为轻量化互联网应用，做些什么呢？还没有想好。

# 闲话 
坚持使用JDK8，Maven3.3，Dynamic Web Module 3.1。 另外Ace Admin（包括时间拾取器datatimepicker）对浏览器的影响 需要注意。 参考使用了多个开源项目，已尽量根据各项目要求保留版权信息，如有不妥，请联系我。

# 数据库说明 
项目使用的mysql数据库，请在generatorConfig.xml、resources/application.yml中配置数据库信息。 如果使用其他数据库，还需要在pom.xml中配置数据库JDBC连接依赖。其中generatorConfig.xml，只为开发设计， 可以利用MyBatisGenerator插件在eclipse中自动生成mybatis orm文件。 注意自动生成的model没有主键的注解，在selectByPrimaryKey 查找时会将所有字段作为查询条件，会出现null转换错误。 为了完成分页查询，还需要给model添加page、raws变量，及get/set方法。 示例使用的脚本为resources/mysql_demo.sql。建库、建用户、授权相关语句已注释，请根据实际参考。

#效率辅助说明 
cymbidium-modules项目包含大量的辅助类，可以提高开发效率。 
cymbidium-modulesweb项目为UI框架（ace admin）。

#打包说明 
mvn package 
java -jar cymbidium.jar --server.port=80

作者：张孟志 
E-mail：104446930@qq.com 
日期：2016-04-02
