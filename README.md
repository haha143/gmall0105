# gmall0105 本地修改版本
gmall-user用户服务8080
gmall-user-service用户服务的service层8070
gmall-user-web用户服务的web层8080

gmall-manage-service用户服务的service层8071
gmall-manage-web用户服务的web层8081

养成习惯，先赞后看！！！

@[TOC](目录)
# 谷粒商城学习笔记

## 11月4日：

### 客户端如何调用dubbo的协议： 

1.将Spring的@Service改成dubbo的@Service

2.将@Autowried注解换成@Reference（注意必须是dubbo的）

3.配置dubbo与zookeeper

```java
#dubbo中的应用容器名称
spring.dubbo.application=user-web
#dubbo通信协议的名称
spring.dubbo.protocol.name=dubbo
#zookeeper注册中心地址
spring.dubbo.registry.address=47.98.59.8:2181
#注册中心的通信协议
spring.dubbo.registry.protocol=zookeeper
#dubbo扫描路径
spring.dubbo.base-package=com.auguigu.gmall
```

4.dubbo在进行dubbo协议通讯的时候，需要实现序列化接口（封装的数据对象即我们的**bean** 

### 页面一直无法获得服务器数据解决方案：

如果出现子模块无法导入父模块的依赖，那么我们就需要先将整个子模块所依赖的父模块全部先clean，compile，install到我们对应的maven仓库之中，这样我们的子模块就能够引入我们的父模块的依赖了，，但是这时候我们一定要去本地的maven仓库里面将我们刚才install的jar包删除掉，否则每次我们运行项目，他都是自动将我们maven仓库里面的jar拿出来使用，所以就算我们对代码进行修改，也是看不出来任何修改的效果的。

### dubbo的consumer超时时间：

进行端点调试的时候，consumer默认是在三秒内，每隔一秒就重新进行一次访问，如果超过三次，那么就会报超时异常，

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111090735425.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

所以为了调试时候的方便，我们需要延长consumer的超时时间

```java
#设置comsumer的超时时间,单位是毫秒
spring.dubbo.consumer.timeout=600000
```



##  11月5日：

### SPU-standard product until

库存量单位：脑海中的实物即抽象的商品，举个例子联想拯救者电脑

### SKU-stock keeping until

库存存储单元：具体的实物，举个例子：联想拯救者Y9000x型号的电脑，具体到了联想拯救者电脑的特定类型的电脑

### pms数据结构的划分：

sku的结构：pms_sku_

spu的结构：pms_spu_

类目的结构：pms_catalog_

属性的结构：pms_attr_

### 前后端分离：

jvm--node

maven--npm

idea--vscode

spring--vue 

### 前端项目：

config:配置前端服务的IP和前端访问数据的后端的服务的IP地址

- dev.env.js:前端访问后端的数据的服务的IP地址
- index.js:前端服务器的端口 

### 前后端请求数据格式：

默认是通过json进行数据的传输

请求格式：@RequestBoby

返回格式：@ResponseBody

### 跨域问题:

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111090816435.png#pic_center)

端口号与IP不同即为跨域

 跨域注解@CrossOrigin 	
 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111090912970.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

请求头中添加了信息，这样就能够进行跨域了

## 11月6日：

### @RequestBody与@RequestParam两个注解的异同点：

@RequestBody只适用于对象数据，这种对象数据包含这两种

- 已经封装好的对象，举个例子，User user这种已经封装好的对象
- Map集合的形式

@RequestParam适用于只有单独几个数据的时候，举个例子，比如说分页查询的时候，前端是需要传递page，size两个参数的时候，问我们就只需要通过@RequestParam Integer page,@RequestParam Integer size的形式就可以将数据接受过来即可。

但是他们两者都能够将数据已 **json** 的形式进行传输。

### @GeneratedValue

@GeneratedValue(strategy = GenerationType.IDENTITY)用于表示改属性是有数据库自己产生的，主要用于自增操作

并且使用了该注解之后就能够在对象插入之后获取到该对象自动生成的主键

类似于mybatis里面的这段代码的作用：

```java 
<insert id="insertSelective" parameterType="请求对象" useGeneratedKeys="true" keyProperty="Id">
</insert>
```

这段代码同样是为了方便我们获得自增主键的值

### 通用Mapper的修改操作：

```java 
//创建例子，这个例子就是你将要修改对象的数据类型
Example example=new Example(PmsBaseAttrInfo.class);
// 创建规则，
example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
//
pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);
```

### 图片信息：

- 图片对象数据：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111090914654.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

上图即为图片的对象数据即图片本身

保存在分布式的文件系统之中**fastdfs**即保存图片本身

- 图片的源数据


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111090912952.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

上图即为图片的源数据即详细信息或属性

保存在数据库中即保存图片的名称，路径URL等详细信息

### 文件上传：

```java
<form method="post" enctype="multipart/Form-data">
    <input type="file"/>
</form>
```

## 11月7日：

### 分布式文件存储系统fastdfs安装步骤： 

- 将压缩包上传到opt目录下
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091027277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)


- 之后解压改文件

  ```java
  cd /opt
  tar -zxvf FastDFS_v5.05.tar.gz 
  ```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091027239.png#pic_center)

- 这时候我们来编译文件

```java
cd FastDFS
./make.sh
```

- 如果出现下面的错误，那么我们需要先安装这个环境**libfastcommon**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091027249.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
- 安装**libfastcommon**步骤

  - 上传文件到/usr/local目录下
  - 解压改文件

  ```java
  tar -zxvf libfastcommonV1.0.7.tar.gz
  ```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091149658.png#pic_center)

  - 进入解压好的文件夹下，开始编译

  ```java
  cd libfastcommon-1.0.7
  ./make.sh
  ```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091149778.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

  如果出现无法识别./make.sh，那么运行运行下面的代码即可

  ```java
   yum -y install zlib zlib-devel pcre pcre-devel gcc gcc-c++ openssl openssl-devel libevent libevent-devel perl unzip net-tools wget
  ```

  之后你就可以通过./make.sh命令进行编译了

  - 便已完成之后我们就需要将它安装上来

  ```java
  ./make.sh install
  ```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091222151.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
  这里我们可以看到他是默认将这个东西安装到/usr/lib64目录下的，但是我们之后Fastdfs程序默认是引用/usr/lib目录下的文件，所以我们需要将该目录下的l**ibfastcommon.so**文件赋值粘贴到/usr/lib目录下

  ```java
  cp /usr/lib64/libfastcommon.so /usr/lib
  ```

- 安装完libfastcommon之后我们再去重新编译我们的文件

```java
./make.sh
./make.sh install
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091222197.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)


这样就算编译成功了。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091404866.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

这样就算安装成功了。

- 将conf配置目录下的所有文件都拷贝到/etc/fdfs

这时候我们先去查看一下是否有该目录，我们检查之后发现是没有的

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091404775.png#pic_center)

但是当我们选择去创建该目录的时候会发现该目录其实已经存在了，

```java
mkdir /etc/fdfs
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/202011110914532.png#pic_center)

我们可以进入该目录去检验一下，可以发现我们是可以进入该目录的，

```java
cd fdfs
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091452996.png#pic_center)

这就说明我们在安装Fastdfs的时候，他就已经默认帮我们将它创建了，知识这个目录是隐藏的

所以我们可以直接将conf目录下的所有文件全部拷贝到/etc/fdfs目录下

```java
cp * /etc/fdfs
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091516320.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

- 之后我们去配置/etc/fdfs目录下去配置tracker.conf,这其中主要就是设置软件数据以及日志目录

我们需要先创建一个目录来存放fastdfs的数据以及日志

```java
mkdir /opt/fastdfs
cd /etc/fdfs
vi tracker.conf

```

将这个目录修改成我们刚才创建的那个存放数据以及日志的目录


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091532562.png#pic_center)

之后保存退出即可

- 之后我们还要去配置storage.conf文件

我们主要就是修改下面三处地方

这一处就是我们数据和日志存储的目录

![在这里插入图片描述](https://img-blog.csdnimg.cn/202011110915522.png#pic_center)

这一处使我们文件将来存储的位置，我们可以看到这里面可以设置多个文件存储位置

![在这里插入图片描述](https://img-blog.csdnimg.cn/202011110915523.png#pic_center)

这里就是修改成刚才部署tracker的那台服务器的IP就行了，因为我们这里tracker和storage是部署在同一台服务器上的，所以我们就直接用本机的IP即可，因为我们是**阿里云的服务器**，所以等会我们需要去**配置防火墙以及开通安全组规则**，否则这个22122端口是无法访问开启的


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091637994.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

配置防火墙端口号：

```java
service firewalld start
firewall-cmd --zone=public --permanent --add-port=22122/tcp
firewall-cmd --reload
firewall-cmd --list-all 

```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091637978.png#pic_center)

开通安全组：

记得入方向和出方向都需要配置


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091700302.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

- 配置fdfs_storaged和fdfs_trackerd的启动服务

先创建/usr/local/fdfs，再将安装目录下的两个服务复制到/usr/local/fdfs目录下

```
mkdir /usr/local/fdfs
cd /opt/FastDFS
cp stop.sh /usr/local/fdfs
cp restart.sh /usr/local/fdfs

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107140131778.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
之后我们就可以去/etc/init.d目录下修改fdfs_storaged和fdfs_trackerd的启动服务，这里由于我们在安装fastdfs的过程中就已经帮我们安装了fdfs_storaged和fdfs_trackerd的启动服务了，所以我们只需要修改他们的配置即可


这是`fdfs_trackerd`文件需要修改的地方

```
cd /etc/init.d
vi fdfs_trackerd

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107135257853.png#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107135749575.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107135829863.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
这是`fdfs_storaged`需要修改的地方

```
cd /etc/init.d
vi fdfs_storaged

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107140834499.png#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107140913509.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107140931322.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
将服务添加到系统服务中，并且启动

```
chkconfig --add fdfs_storaged 
chkconfig --add fdfs_trackerd 
service fdfs_storaged start
service fdfs_trackerd start
ps -ef|grep fdfs

```

如果能够看到下面的页面就说明`fdfs服务`就已经成功启动了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107141508234.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

- 测试fastdfs服务

Fastdfs有一个专门让我们用来测试的目录，我们可以通过修改该目录来测试我们的文件服务是否真的成功
首先修改该文件etc/fdfs/client.conf的以下配置：

```
vim /etc/fdfs/client.conf

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107143554519.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
这里我们通过下面的命令来上传图片测试一下

```
/usr/bin/fdfs_test  /etc/fdfs/client.conf  upload test.jpg

```

**/usr/bin/fdfs_test** 是fastdfs自带的一个测试demo
 **/etc/fdfs/client.conf** 是我们刚才配置的测试的配置，命令会读取这个配置文件的信息
 **test.jpg** 代表你要上传的文件名，是当前目录下的文件
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107152433654.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
但是这里报错了，一看就知道是端口23000没有开启，所以我们需要将这个端口开启。步骤和上面开启22122端口的步骤一样，开启端口之后，我们重新测试一次，发现我们的文件就已经成功上传了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107152349517.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
并且这里他还给我们返回了一个图片的URL。但是目前这个URL是访问不了的，因为我的服务器还没有添加Nginx进行反向代理，这个之后会加进去的，但是我们可以通过进入相应的目录来进行查看文件是否存在。查看之后发现文件的确已经存储进来了。到这里fastdfs的安装就已经基本完成了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107153005818.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

### FastDFS整合Nginx

- 在/opt目录下解压文件

```java
tar -zxvf fastdfs-nginx-module_v1.16.tar.gz

```

- 修改插件本身的配置文件

```java
vi  /fastdfs-nginx-module/src/config

```

把中间的local删掉，注意有两个文件路径


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091727304.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
- 将插件整合fastdfs的配置文件拷贝到fastdfs的配置目录下

```java
cp mod_fastdfs.conf /etc/fdfs/

```

- 修改该配置文件

  主要有下面四处修改

  - fdfs的软件安装目录

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091744865.png#pic_center)

  - fdfs的tracker的IP地址

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091744870.png#pic_center)

  - fdfs生成的URL是否使用分组

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091851492.png#pic_center)

  这个其实看我们刚才生成的图片URL就能看到包含group1这个字段

  - fdfs的文件存储路径

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091851515.png#pic_center)

### Nginx安装

```java
cd /opt/nginx-1.12.2

```

- 之后直接粘贴下面的命令

```java
./configure \
--prefix=/usr/local/nginx \
--pid-path=/var/run/nginx/nginx.pid \
--lock-path=/var/lock/nginx.lock \
--error-log-path=/var/log/nginx/error.log \
--http-log-path=/var/log/nginx/access.log \
--with-http_gzip_static_module \
--http-client-body-temp-path=/var/temp/nginx/client \
--http-proxy-temp-path=/var/temp/nginx/proxy \
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \
--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \
--http-scgi-temp-path=/var/temp/nginx/scgi \
--add-module=/opt/fastdfs-nginx-module/src

```

如果出现这个错误**configure: error: the HTTP rewrite module requires the PCRE lib**

只需要依次输入以下的命令即可解决

```java
yum -y install pcre-devel openssl openssl-devel
./configure --prefix=/usr/local/nginx
make
make install

```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091938630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111091938665.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111092024834.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111092036617.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

之后重新粘贴我们之前的代码即可完成我们Nginx

- 修改Nginx的配置文件

主要有下面两处修改，这里修改的是本机的IP地址


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111092107323.png#pic_center)

另外一个就是将我们上面配置的插件添加进来


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111092120123.png#pic_center)

- 之后我们启动Nginx服务

我们需要进入/usr/local/conf/sbin目录下

```java
#启动Nginx服务
./nginx
#重启Nginx服务
./nginx -s reload

```

因为我已经启动过了，所以我这里是用的重启nginx服务的命令


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111092131998.png#pic_center)

这样就代表Nginx服务已经启动成功了。

之后我们去浏览器里面输入你服务器的IP地址，就能够看到下面的界面了：


![在这里插入图片描述](https://img-blog.csdnimg.cn/2020111109214773.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

之后我们再无重新访问我们之前上传图片时生成的URL地址，可以发现这时候图片就可以正常访问了。


![在这里插入图片描述](https://img-blog.csdnimg.cn/2020111109220185.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

## 11月9日：

### fastDFS与springboot整合：

- clone并将客户端导入到项目

因为FastDFS不像我们其他的框架，直接就可以从Maven仓库下载他相应的依赖，然后直接跟着教程后面敲就行了，他是必须我们先从Git上面把它的客户端Clone下来，然后导入到我们的项目，才能够直接使用的。
所以我们首先需要将客户端clone下来。
找到我们的Git工具，然后以管理员身份运行，之后输入下面的命令就能够将客户端clone先来了

```java
git clone https://github.com/happyfish100/fastdfs-client-java

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109164634694.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
我们会在Git的目录下面看到这样一个文件：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109164725922.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
这就是FastDFS的客户端，之后我们就将这个客户端拷贝到我们的项目中，但是这时候他没有被识别成Maven的项目，所以我们还需要将它导入成Maven项目，按照下面的步骤即可：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109164941430.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109164941464.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
之后我们需要将这个项目install到我们的本地仓库，这一步可有可无，但是最好install一下，否则有的时候识别不出来这个项目。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109164941465.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
上面红框里面的地址就是它安装的地址

- 导入FastDFS客户端的依赖

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109165608513.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
复制这段代码到你需要使用文件上传的模块里面。**`这里有个小坑，到后面我会告诉大家怎么解决。`**

- 创建并配置Tracker的配置信息

在需要文件上传的模块的resource文件夹下面创建配置文件

```java
#tracker_server服务的地址及端口号
tracker_server=tracker服务的IP:22122

#连接超时间，默认是30秒
connect_timeout=30000

#网络通讯超时时间,默认是60秒
network_timeout=60000

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109165412429.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

- 编写上传文件的工具类

之后我们就可以正式的来编写我们的工具类了

```java
public static String uploadImage(MultipartFile multipartFile) throws IOException {
//这里是我定义的常量类
        ConstantUtil constantUtil=new ConstantUtil();
        String ImageUrl= constantUtil.getImageUrl();
        //获取tracker的配置文件路径
        String tracker=PmsUploadUtil.class.getResource("/tracker.conf").getPath();
        //读取配置文件
        try{
            ClientGlobal.init(tracker);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("配置实例化失败");
        }
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=trackerClient.getTrackerServer();
        //创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件的byte数组
        byte[]bytes=multipartFile.getBytes();
        //获取文件的后缀名
        String multipartFileName=multipartFile.getOriginalFilename();
        int index=multipartFileName.lastIndexOf(".");
        String extNamne=multipartFileName.substring(index+1);
        //使用StorageClient对象上传图片；扩展名不带“.”
        try {
            String[] strings = storageClient.upload_file(bytes, extNamne, null);
            //返回数组。包含组名和图片的路径。重组成URL链接
            for (String string : strings) {
                ImageUrl+="/"+string;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ImageUrl;
    }

```

这里还有一个注意点就是 **`FastDFS1.27`** 和 **`FastDFS1.29`** 两个版本有些许的不一样

```
在1.27的版本里面：
我们获取TrackerServer是通过trackerClient.getConnection()来获取的，
但是在1.29的版本里面：
我们获取TrackerServer是通过trackerClient.getTrackerServer()来获取的，

```

其次FastDFS中的上传函数主要是下面两种，

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109170812703.png#pic_center)
这两个函数只有第一个参数存在区别，第一是以二进制数据的形式将文件上传上去，第二个则是通过我们传入的文件的文件路径将文件上传上去。这里我选择的是通过第一种方式将文件进行上传，这里大家根据自己的实际需要进行选择。到这里我们关于文件的上传就已经编写完毕，接下来我们就可以进行测试了，但是不好意思，不出意外的话，你可能会碰到这么个bug:**`Detected both log4j-over-slf4j.jar AND slf4j-log4j12.jar on the class path, preempting StackOverflowError`**

这个bug的意思就是你项目里面同时使用了 **`log4j-over-slf4j.jar 和 slf4j-log4j12.jar`** ，刚好这两个包是冲突的，所以我们必须要将其中一个去掉，但是之前自己的项目都是能够正常运行的，所以不出意外应该是FastDfS客户端里面应该是引用了上面依赖中的一个，所以这里我们重新进FastDFS客户端的pom依赖里面就能够看到他的依赖有哪些，我们可以看到如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109171626734.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
说明就是因为FastDFS中又引入了其中的一个，所以我们需要在导入fastDFS的依赖里面将该依赖刨除掉

```java
<dependency>
    <groupId>org.csource</groupId>
    <artifactId>fastdfs-client-java</artifactId>
    <version>1.29-SNAPSHOT</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>

```

这样我们的项目基本就能运行了

- 上传演示

到了这里我们就可以来进行实际的测试一下了

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201109190540870.gif#pic_center)
可以看到图片的确上传上去了，并且后台给我们返回了FastDFS为我们生成的图片的URL地址，我们之后通过浏览器也的确能够进行访问了。
## 11月12日：

### Thymeleaf模板约束：

thymeleaf使用的是HTML的扩展标签，需要在页面上申明thymeleaf的模板约束

### 松校验+热部署：

引入thymeleaf相关联的三个依赖：

```java
  <!--热部署，松校验-->
        <dependency>
            <groupId>net.sourceforge.nekohtml</groupId>
            <artifactId>nekohtml</artifactId>
        </dependency>
        <dependency>
            <groupId>xml-apis</groupId>
            <artifactId>xml-apis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-ext</artifactId>
        </dependency>
```

之后在application.properties文件里面配置以下代码：

```java
#关闭thymeleaf缓存(热部署)
spring.thymeleaf.cache=false
#松校验
spring.thymeleaf.mode=LEGACYHTML5
```

这里的热部署不是实时的，还是需要我们先提交文件，才能看到更新的文件

提交文件快捷键：**Ctrl+shift+F9**

### Thymeleaf基本语法：

前端获取的数据都是通过后台通过ModelMap进行返回的

```java
@RequestMapping("/index")
    public String index(ModelMap modelMap){
        List<String>list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add("循环数据"+i);
        }
        modelMap.put("hello","are you ok");
        modelMap.put("list",list);
        modelMap.put("check",1);
        return "index";
    }
```

- 取值

```java
<p th:text="${hello}">默认值</p>
```

先检查thymeleaf标签，如果标签里面有值就先显示该值，否则显示默认值

- 循环

```java
<p th:each="str:${list}" th:text="${str}"></p>

<p th:each="str:${list}" >
    <input th:text="${str}" th:value="${str}" />
</p>
```

 ![img](https://img-blog.csdnimg.cn/20201112144855377.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center) 

- 判断

```java
<input type="checkbox" th:checked="${check}==2">
```

 ![img](https://img-blog.csdnimg.cn/20201112154623278.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center) 

- 传值给JavaScript

```java
<a th:href="'javascript:a(\''+${hello}+'\')'">点我</a>
<script language="JavaScript">
    function a(hello) {
        alert("js函数被调用了"+hello);
    }
</script>
```




 ![img](https://img-blog.csdnimg.cn/20201112170147853.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center) 

### 前端页面中的skuInfo?.skuDefaultImg中的？含义

这个？主要是为了防止空指针异常，如果出现我们不存在的skuinfo对象，显然页面时根本无法显示的，所以为了防止这种现象，我们必须拦截这种类似的请求

 ![img](https://img-blog.csdnimg.cn/20201112185632581.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center) 

显然这个SKU对象的id我们是没有的，所以我们必须对于这种请求进行拦截，拦截之后应该生成上述的页面，如果我们将这个？去掉的话，我们再次访问该页面就会出现下面的错误：

 ![img](https://img-blog.csdnimg.cn/20201112185932573.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center) 
## 11月24日:

### SQL语句-左连接,右连接,内连接:

显示内连接查询绑定pms_product_sale_attr 与pms_product_sale_attr_value两张表的信息

```java
SELECT
	sa.*,
	sav.*
FROM
	pms_product_sale_attr sa
	INNER JOIN pms_product_sale_attr_value sav ON sa.product_id = sav.product_id 
	AND sa.sale_attr_id = sav.sale_attr_id 
	AND sa.product_id = 70
```

查询结果:


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201124205145703.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

我们这里只是简单的先将我们需要的**SPU自带的销售属性及其属性值**全部查询出来的,但是因为我们还要做到能够直接定位到**当前SKU所固定的销售属性值**,所以我们还需要再关联一张表即pms_sku_sale_attr_value,这张表主要就是帮我们筛选出我们当前SKU对象所对应的销售属性,这里我们主要是通过左连接进行绑定

```java
SELECT
	sa.*,
	sav.* ,
	ssav.*
FROM
	pms_product_sale_attr sa
	INNER JOIN pms_product_sale_attr_value sav ON sa.product_id = sav.product_id 
	AND sa.sale_attr_id = sav.sale_attr_id 
	AND sa.product_id = 70
	LEFT JOIN pms_sku_sale_attr_value ssav ON sav.id = ssav.sale_attr_value_id 
WHERE
	ssav.sku_id = 106;
```

查询结果:


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201124205158603.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)



但是运行完成之后我们会发现,只剩下了两条数据,那么这样的话我们就无法将**SPU整个的销售属性及其属性值**取出来,显然是不行的.经过我们的排查之后我们发现这里主要就是where字段的范围太大了,我们将where 写在了最后就说明,他是需要整个数据中满足 sku_id=106的数据保留下来,但是很明显这里我们其实只是应该将我们后半段左连接中的数据中满足sku_id=106的数据保留下来,所以我们简单的修改以下代码

```java
SELECT
	sa.*,
	sav.*,
	ssav.*
FROM
	pms_product_sale_attr sa
	INNER JOIN pms_product_sale_attr_value sav ON sa.product_id = sav.product_id 
	AND sa.sale_attr_id = sav.sale_attr_id 
	AND sa.product_id = 70
	LEFT JOIN pms_sku_sale_attr_value ssav ON sav.id = ssav.sale_attr_value_id AND ssav.sku_id = 106;
```

查询结果:


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201124205158601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

可以看到这样才是我们最后需要的结果,既能将**SPU所有的销售属性及其属性值**全部查询出来,同时又能将该**SKU对象的销售属性值**取出来.两全其美.

如果对于内连接,左连接,右连接还是有不清楚的,可以去看我的这篇博客:

[Mysql中外连接,内连接,左连接,右连接的区别](https://blog.csdn.net/lovely__RR/article/details/108622731),里面有详细解释.

### ResultMap与ResultType的区别:
两者的区别主要就是在返回类型上.

在mybatis中我们可能返回的数据类型主要就是下面这两种:

- 单个集合----ResultType,ResultMap

- 多重集合----ResultMap

那么什么样的数据才叫单个集合,什么样的数据才叫多重集合呢?我们通过下面两张图,大家就能理解了:

**单个集合**:


![在这里插入图片描述](https://img-blog.csdnimg.cn/2020112516535762.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

**多重集合**:

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020112516535787.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

看完上面两张图大家就能基本知道他们的差别了,,那么他们俩的具体使用场景又是怎样的呢?

举两个例子大家就懂了

在 **`获取所有用户的数据`** 的时候显然我们只需要获得用户信息即可了,并不需要在获取其他额外的数据.

但是在 **`进行用户的角色校验`** 的时候,显然我们不仅需要获得该用户的相关信息,其次我们还需要获得该用户的角色信息,那么显然我们返回的数据就不能只包含用户信息,所以我们必须将数据封装成上述多重集合的形式,这样才能方便我们进行角色的校验.

了解完上面的概念之后,大家基本就了解了他们两者的区别了,但是大家又要问了,上面你说 **ResultMap既能用于单个集合,又能用于多重集合,那么我们为什么不全是用ResultMap呢?还要使用ResultType呢?**

这里主要是因为`ResultType虽然只针对单个集合`,但是他是`可以直接调用我们已经编写好的实体类`的,但是ResultMap则不同,它`不管如何都需要我们进行自定义`,所以主要还是用在多重集合的情况下,单个集合的情况下还是使用ResultType.

这样大家基本就能了解清楚他们俩的不同了,了解完不同之后,我们再来具体的讲解一下如何使用他们:

- `ResultType`

ResultType使用起来就比较的简单了,上面我们已经说过了,是可以直接调用我们的实体类的,所以我们基本上就是直接copy实体类的相对路径即可了,下面是一个栗子:

```java
<select id="selectAll" resultType="com.auguigu.gmall.bean.PmsProductInfo">
select * from pms_product_info
</select>
```

可以看到我们只需要将我们需要返回的实体类的路径直接赋给ResultType即可,简单方便.

- `ResultMap`

但是ResultMap相对来说就比较麻烦,其实主要就是 **`需要告诉Mybatis你是将那几个实体类进行多重组合的`** ,这样剩下的事就可以全交给mybatis来做了.还是通过下面的栗子,我们详细讲解一下.

多重集合的两个实体类:

- PmsProductSaleAttr

```java
public class PmsProductSaleAttr implements Serializable {

    @Id
    @Column
    Integer id;

    @Column
    Integer productId;

    @Column
    Integer saleAttrId;

    @Column
    String saleAttrName;

    @Transient
    List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList;
}
```

- PmsProductSaleAttrValue

```java
public class PmsProductSaleAttrValue implements Serializable {
    @Id
    @Column
    Integer id;

    @Column
    Integer productId;

    @Column
    Integer saleAttrId;

    @Column
    String saleAttrValueName;

    @Transient
    Integer isChecked;

}
```

之后我们再来看一下我们在mapper.xml文件中定义的代码:

```java
<select id="selectspuSaleAttrListCheckBySku" resultMap="selectspuSaleAttrListCheckBySkuMap">
        SELECT
            sa.id as sa_id,
            sav.id as sav_id,
            sa.*,
            sav.*,
            if(ssav.sku_id,1,0) as isChecked
        FROM
            pms_product_sale_attr sa
                INNER JOIN pms_product_sale_attr_value sav ON sa.product_id = sav.product_id
                AND sa.sale_attr_id = sav.sale_attr_id
                AND sa.product_id = #{productId}
                LEFT JOIN pms_sku_sale_attr_value ssav ON sav.id = ssav.sale_attr_value_id
                AND ssav.sku_id = #{skuId}
                ORDER BY sav.id
    </select>
    <resultMap id="selectspuSaleAttrListCheckBySkuMap" type="com.auguigu.gmall.bean.PmsProductSaleAttr" autoMapping="true">
        <result column="sa_id" property="id"></result>
        <collection property="pmsProductSaleAttrValueList" ofType="com.auguigu.gmall.bean.PmsProductSaleAttrValue" autoMapping="true">
            <result column="sav_id" property="id"></result>
        </collection>
    </resultMap>
```

在分析代码之前,我们下来看一下该SQL语句执行之后,我们获得的数据是什么样的?


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201125165357128.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)

可以看到我们获得数据中又好几个字段名称都是重复的,这样就使得mybatis很难去做匹配,所以我们重点就是告诉mybatis该如何去做匹配.

首先ResultMap里面填的就是我们下面已经定义好的ResultMap的名字,接下来我们就重点看我们是如何来定义这个ResultMap的.

首先我们先来看ResultMap部分,再来看Collection

```java
<resultMap id="selectspuSaleAttrListCheckBySkuMap" type="com.auguigu.gmall.bean.PmsProductSaleAttr" autoMapping="true">
        <result column="sa_id" property="id"></result>
</resultMap>
```

我们首先需要先给我们的ResultMap定义一个名称即id,之后我们就需要定义ResultMap的type,这里的type是我们多重集合中最外层的实体对象,之后我们就需要定义该实体对象的主键即可,column指的是我们定义的返回数据中的字段名,property则是指的是我们在实体类中定义的主键,剩下的字段我们通过autoMapping=true即可让mybatis帮我们自动处理了.

```java
<collection property="pmsProductSaleAttrValueList" ofType="com.auguigu.gmall.bean.PmsProductSaleAttrValue" autoMapping="true">
            <result column="sav_id" property="id"></result>
</collection>
```

了解完ResultMap是怎么定义的之后,collection看起来就比较简单了,这里我们就只需要看collection中的property即可,这里填的是最外层的实体类中定义的下一层集合的对象的名称,即下图所示:


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201125165357124.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVseV9fUlI=,size_16,color_FFFFFF,t_70#pic_center)
其次还有一点就是这里的type,可以看到这里的type不像上面的ResultMap一样还是type了还是用的是oftype,这样就能会更加表示出多重集合这个概念了.


