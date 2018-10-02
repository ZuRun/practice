# 配合tracing,测试dubbo拦截器

注意:
当前使用的dubbo貌似有bug
使用zookeeper注册的时候,如果使用curator客户端,且消费者使用拦截器会导致无法获取provider

改为zkclient客户端 可绕过此bug