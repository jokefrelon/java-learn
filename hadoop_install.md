# Hadoop安装教程&NameNode消失解决

今天晚上就突然想搭建一个Hadoop的伪分布式玩一玩,说干就干

### 准备:CentOS 7 系统 Hadoop 2.7.7 安装包 Java SE 1.8 安装包

Hadoop 2.7.7 下载地址:https://mirrors.tuna.tsinghua.edu.cn/apache/hadoop/common/hadoop-2.7.7/

Java SE 1.8 下载地址: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html?ssSourceSiteId=otnpt 

CentOS 7下载地址: http://isoredirect.centos.org/centos/7/isos/x86_64/ 

VMware 安装系统就不用说了,先安装一个系统就可以,另一个可以直接clone

我因为已经安装完成了,就在WSL-Ubuntu里面大概敲一下代码

<hr>

## 解压Java SE 和 Hadoop的安装包

~~~shell
tar -zxvf hadoop-2.8.5.tar.gz
~~~

~~~shell
tar -zxvf jdk-8u231-linux-x64.tar.gz
~~~



## 配置环境变量

我是编辑 **.bash_profile** 让我一个用户可以使用这个环境变量,大家也可以配置 **/etc/profile**文件,全局使用此配置,作用一样

~~~shell
export JAVA_HOME=/home/jokeme/jdk1.8.0_231
export HADOOP_HOME=hadoop-2.8.5
export JRE_HOME=/home/jokeme/jdk1.8.0_231/jre

export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin   
~~~

然后:

~~~shell
  source .bash_profile
~~~

试一试

~~~shell
java -version
javac -version
hadoop -version
~~~

如果配置正确就可以弹出正确的版本号

## 更改**Hostname**和**Hosts**文件

~~~shell
sudo vim /etc/hostname
~~~

~~~properties
HadoopMaster
~~~

保存退出

~~~shell
sudo vim /etc/hosts
~~~

~~~properties
192.168.1.199   HadoopClient
192.168.1.215   HadoopMaster
~~~

根据具体的  **IP** 地址和 **Hostname** 更改 .   注意 : **Hostname** 不区分大小写

保存退出

## 关闭防火墙和SELINUX

~~~shell
systemctl disable firewalld
vim /etc/selinux/config
~~~

修改为以下内容

~~~properties
SELINUX=disabled
~~~

重启系统,使配置生效

~~~shell
reboot
~~~

## 开始配置**Hadoop**的配置文件

~~~shell
cd hadoop-2.8.5/etc/hadoop
~~~

~~~shell
vim hadoop-env.sh
~~~

编辑一下具体的 **JAVA_HOME**

~~~shell
export JAVA_HOME=/home/hadoop/jdk1.8.0_231 
~~~

保存退出

~~~shell
vim core-site.xml
~~~

~~~xml
<configuration>
	<property>
		<name>fs.defaultFS</name>
		<value>hdfs://HadoopMaster:9000</value>
	</property>

	<property>
		<name>hadoop.tmp.dir</name>
		<value>/home/hadoop/hadoop-2.7.7/tmp</value>
	</property>
</configuration>
~~~

保存退出

~~~shell
vim hdfs-site.xml
~~~

~~~xml
<configuration>
	<property>
		<name>dfs.replication</name>
		<value>1</value>
	</property>
</configuration>
~~~

保存退出

~~~shell
cp mapred-site.xml.template mapred-site.xml
vim mapred-site.xml
~~~

~~~xml
<configuration>
	<property>
		<name>mapreduce.framework.name</name>
		<value>yarn</value>
	</property>
</configuration> 
~~~

保存退出

~~~shell
vim yarn-site.xml
~~~

~~~xml
<configuration>
	<property>
		<name>yarn.resourcemanager.hostname</name>         						<value>HadoopMaster</value>
	</property>
	
	<property>
		<name>yarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
	</property>
</configuration>
~~~

保存退出


~~~shell
vim slaves
~~~

~~~properties
HadoopMaster
HadoopClient
~~~

这里需要把 **DataNode** **Hostname**都添加进来,根据具体情况修改

## 关闭虚拟机**Clone**一个新的主机出来

**Clone**完成后还需要更改 **Hostname** 和 **IP** 地址以及 **MAC** 地址

~~~shell
vim /etc/sysconfig/network-scripts/ifcfg-ens33
~~~

修改以下代码

~~~properties
IPADDR=xxx.xxx.xxx.xxx
~~~

添加以下代码

~~~properties
MACADDR=xx.xx.xx.xx.xx.xx
~~~

都是根据具体情况修改

## 配置免密登陆

~~~shell
ssh-keygen
~~~

一直回车就可以了

然后再

~~~shell
ssh-copy-id hadoop@HadoopMaster
ssh-copy-id hadoop@HadoopClient
~~~

再修改以下配置文件,允许免密登陆

~~~shell
sudo vim /etc/ssh/sshd_config
~~~

~~~properties
PubkeyAuthentication yes
~~~

HadoopMaster 上也是同样操作,不在赘述

## 启动Hadoop

回到 **HadoopMaster** 

~~~shell
cd ~/hadoop2.7.7/sbin
./start-all.sh
~~~

<hr>

安装过程大概就这些,如果启动成功就可以用 **jps** 命令查看到以下进程

~~~properties
6899 DataNode                                                              7092 SecondaryNameNode                                                     7253 ResourceManager                                                       7366 NodeManager                                                           8330 Jps                                                                   6796 NameNode 
~~~

但是我 **jps** 时少了一个 **NameNode** 节点 导致我一直进不去 **HDFS** 的 **web** 界面,这让我非常头疼,百度了半天也没有结果, 于是用 **Google** 搜了一下,说可能是我的 **NameNode** 没有格式化,于是我敲了以下命令,重启了一下Hadoop,就好了

~~~shell
hadoop namenode -format
~~~

再次进入 **web** 界面也正常了 , 8088 端口也可以访问了

<hr>
[Apache 官方文档](http://hadoop.apache.org/docs)

另外附上一份 **Apache** 的 **fs** **shell** 操作大全

~~~log
cat
使用方法：hadoop fs -cat URI [URI …]

将路径指定文件的内容输出到stdout。

示例：

hadoop fs -cat hdfs://host1:port1/file1 hdfs://host2:port2/file2
hadoop fs -cat file:///file3 /user/hadoop/file4
返回值：
成功返回0，失败返回-1。

chgrp
使用方法：hadoop fs -chgrp [-R] GROUP URI [URI …] Change group association of files. With -R, make the change recursively through the directory structure. The user must be the owner of files, or else a super-user. Additional information is in the Permissions User Guide. -->

改变文件所属的组。使用-R将使改变在目录结构下递归进行。命令的使用者必须是文件的所有者或者超级用户。更多的信息请参见HDFS权限用户指南。

chmod
使用方法：hadoop fs -chmod [-R] <MODE[,MODE]... | OCTALMODE> URI [URI …]

改变文件的权限。使用-R将使改变在目录结构下递归进行。命令的使用者必须是文件的所有者或者超级用户。更多的信息请参见HDFS权限用户指南。

chown
使用方法：hadoop fs -chown [-R] [OWNER][:[GROUP]] URI [URI ]

改变文件的拥有者。使用-R将使改变在目录结构下递归进行。命令的使用者必须是超级用户。更多的信息请参见HDFS权限用户指南。

copyFromLocal
使用方法：hadoop fs -copyFromLocal <localsrc> URI

除了限定源路径是一个本地文件外，和put命令相似。

copyToLocal
使用方法：hadoop fs -copyToLocal [-ignorecrc] [-crc] URI <localdst>

除了限定目标路径是一个本地文件外，和get命令类似。

cp
使用方法：hadoop fs -cp URI [URI …] <dest>

将文件从源路径复制到目标路径。这个命令允许有多个源路径，此时目标路径必须是一个目录。
示例：

hadoop fs -cp /user/hadoop/file1 /user/hadoop/file2
hadoop fs -cp /user/hadoop/file1 /user/hadoop/file2 /user/hadoop/dir
返回值：

成功返回0，失败返回-1。

du
使用方法：hadoop fs -du URI [URI …]

显示目录中所有文件的大小，或者当只指定一个文件时，显示此文件的大小。
示例：
hadoop fs -du /user/hadoop/dir1 /user/hadoop/file1 hdfs://host:port/user/hadoop/dir1
返回值：
成功返回0，失败返回-1。
dus
使用方法：hadoop fs -dus <args>

显示文件的大小。

expunge
使用方法：hadoop fs -expunge

清空回收站。请参考HDFS设计文档以获取更多关于回收站特性的信息。

get
使用方法：hadoop fs -get [-ignorecrc] [-crc] <src> <localdst>
复制文件到本地文件系统。可用-ignorecrc选项复制CRC校验失败的文件。使用-crc选项复制文件以及CRC信息。

示例：

hadoop fs -get /user/hadoop/file localfile
hadoop fs -get hdfs://host:port/user/hadoop/file localfile
返回值：

成功返回0，失败返回-1。

getmerge
使用方法：hadoop fs -getmerge <src> <localdst> [addnl]

接受一个源目录和一个目标文件作为输入，并且将源目录中所有的文件连接成本地目标文件。addnl是可选的，用于指定在每个文件结尾添加一个换行符。

ls
使用方法：hadoop fs -ls <args>

如果是文件，则按照如下格式返回文件信息：
文件名 <副本数> 文件大小 修改日期 修改时间 权限 用户ID 组ID
如果是目录，则返回它直接子文件的一个列表，就像在Unix中一样。目录返回列表的信息如下：
目录名 <dir> 修改日期 修改时间 权限 用户ID 组ID
示例：
hadoop fs -ls /user/hadoop/file1 /user/hadoop/file2 hdfs://host:port/user/hadoop/dir1 /nonexistentfile
返回值：
成功返回0，失败返回-1。
lsr
使用方法：hadoop fs -lsr <args>
ls命令的递归版本。类似于Unix中的ls -R。

mkdir
使用方法：hadoop fs -mkdir <paths>
接受路径指定的uri作为参数，创建这些目录。其行为类似于Unix的mkdir -p，它会创建路径中的各级父目录。

示例：

hadoop fs -mkdir /user/hadoop/dir1 /user/hadoop/dir2
hadoop fs -mkdir hdfs://host1:port1/user/hadoop/dir hdfs://host2:port2/user/hadoop/dir
返回值：

成功返回0，失败返回-1。

movefromLocal
使用方法：dfs -moveFromLocal <src> <dst>

输出一个”not implemented“信息。

mv
使用方法：hadoop fs -mv URI [URI …] <dest>

将文件从源路径移动到目标路径。这个命令允许有多个源路径，此时目标路径必须是一个目录。不允许在不同的文件系统间移动文件。
示例：

hadoop fs -mv /user/hadoop/file1 /user/hadoop/file2
hadoop fs -mv hdfs://host:port/file1 hdfs://host:port/file2 hdfs://host:port/file3 hdfs://host:port/dir1
返回值：

成功返回0，失败返回-1。

put
使用方法：hadoop fs -put <localsrc> ... <dst>

从本地文件系统中复制单个或多个源路径到目标文件系统。也支持从标准输入中读取输入写入目标文件系统。
hadoop fs -put localfile /user/hadoop/hadoopfile
hadoop fs -put localfile1 localfile2 /user/hadoop/hadoopdir
hadoop fs -put localfile hdfs://host:port/hadoop/hadoopfile
hadoop fs -put - hdfs://host:port/hadoop/hadoopfile
从标准输入中读取输入。
返回值：

成功返回0，失败返回-1。

rm
使用方法：hadoop fs -rm URI [URI …]

删除指定的文件。只删除非空目录和文件。请参考rmr命令了解递归删除。
示例：

hadoop fs -rm hdfs://host:port/file /user/hadoop/emptydir
返回值：

成功返回0，失败返回-1。

rmr
使用方法：hadoop fs -rmr URI [URI …]

delete的递归版本。
示例：

hadoop fs -rmr /user/hadoop/dir
hadoop fs -rmr hdfs://host:port/user/hadoop/dir
返回值：

成功返回0，失败返回-1。

setrep
使用方法：hadoop fs -setrep [-R] <path>

改变一个文件的副本系数。-R选项用于递归改变目录下所有文件的副本系数。

示例：

hadoop fs -setrep -w 3 -R /user/hadoop/dir1
返回值：

成功返回0，失败返回-1。

stat
使用方法：hadoop fs -stat URI [URI …]

返回指定路径的统计信息。

示例：

hadoop fs -stat path
返回值：
成功返回0，失败返回-1。

tail
使用方法：hadoop fs -tail [-f] URI

将文件尾部1K字节的内容输出到stdout。支持-f选项，行为和Unix中一致。

示例：

hadoop fs -tail pathname
返回值：
成功返回0，失败返回-1。

test
使用方法：hadoop fs -test -[ezd] URI

选项：
-e 检查文件是否存在。如果存在则返回0。
-z 检查文件是否是0字节。如果是则返回0。
-d 如果路径是个目录，则返回1，否则返回0。
示例：

hadoop fs -test -e filename
text
使用方法：hadoop fs -text <src>
将源文件输出为文本格式。允许的格式是zip和TextRecordInputStream。

touchz
使用方法：hadoop fs -touchz URI [URI …]
创建一个0字节的空文件。

示例：

hadoop -touchz pathname
返回值：
成功返回0，失败返回-1。
~~~



