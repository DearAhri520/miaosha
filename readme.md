改进:
1. fastjson->protobuf
2. id自增->雪花算法
3. 采用登录拦截器实现登录拦截

笔记:
1. 2次md5: 
第一次MD5: 用户端MD5,fromPass = MD5(明文密码+固定salt) , 避免用户密码在网络当中明文传递
第二次MD5: 服务端MD5,dbPass = MD5(fromPass+随机salt) , 避免数据库泄露时,通过彩虹表等方式反查密码

2. JSR303参数校验

