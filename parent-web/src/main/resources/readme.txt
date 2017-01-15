作为集群测试，下面为虚拟机服务规划：
主机ip:192.168.1.199 配置两个ip别名：192.168.1.200,192.168.1.201,192.168.1.202；
应用war存放地址：/usr/local/down/ungz/software/应用名
tomcat存放地址：/usr/local/down/ungz/tomcat/应用名-tomcat

nexus===>>>192.168.1.199:8081/nexus

##############################tomcat################################
dubbo-admin-tomcat===>>> 192.168.1.199		；   shutdown.port:8005；http.port:8080 ；AJP.port:8009
cas-tomcat===>>> ip:cas.fyrj.com 			；   shutdown.port:50010；http.port:8083 ；AJP.port:50011
base-data-tomcat===>>> ip:base-data.fyrj.com ； shutdown.port:50020；http.port:8084；AJP.port:50021
cims-tomcat===>>>      ip:cims.fyrj.com ；             shutdown.port:50030；http.port:8085；AJP.port:50031


##############################zk集群#################################
zookeeper1===>>> ip:192.168.1.199；port:31001
zookeeper2===>>> ip:192.168.1.199；port:31002

############################dubbo配置################################
base-data ===>>> port:21001
cims===>>> port:21002

############################redis集群配置################################
redis集群 {(集群后没有master与slave之分了，因为根据故障转移恢复机制，都可以成为master,所以我统一称它们为node)
	位置：/usr/local/down/ungz/redis
	r1:192.168.1.200:6380 ===>>> ./conf/master.conf （node1）
	r2:192.168.1.201:6381 ===>>> ./conf/slave1.conf （node2）
	r3:192.168.1.202:6382 ===>>> ./conf/slave2.conf （node3）
}

sentinel集群{
	s1:192.168.1.199:26379 
	s2:192.168.1.199:26380 
}


#############################shell脚本###############################
pid位置：/usr/local/pid
redis-sentinel-1.pid
redis-sentinel-2.pid
redis-node1.pid
redis-node2.pid
redis-node3.pid




