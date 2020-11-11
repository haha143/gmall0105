# gmall0105 本地修改版本
gmall-user用户服务8080
gmall-user-service用户服务的service层8070
gmall-user-web用户服务的web层8080

gmall-manage-service用户服务的service层8071
gmall-manage-web用户服务的web层8081

养成习惯，先赞后看！！！
项目后端Github地址：[https://github.com/haha143/gmall0105](https://github.com/haha143/gmall0105) `如果可以，可以star一波`

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



