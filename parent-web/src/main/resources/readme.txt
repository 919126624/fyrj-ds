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
zookeeper1===>>> ip:192.168.1.199；port:30001
zookeeper2===>>> ip:192.168.1.199；port:30002

############################dubbo配置################################
base-data ===>>> port:21001
cims===>>> port:21002

############################redis集群配置################################
redis集群 {
	位置：/usr/local/down/ungz/redis
	r1:192.168.1.199:6379 ===>>> redis-sentinel  ;./conf/sentinel.conf
	r2:192.168.1.200:6380 ===>>> redis-master    ;./conf/master.conf
	r3:192.168.1.201:6381 ===>>> redis-slave-1   ;./conf/slave1.conf
	r4:192.168.1.202:6382 ===>>> redis-slave-2   ;./conf/slave2.conf
}

sentinel集群{
	s1:192.168.1.199:26379 
	s2:192.168.1.199:26380 
}




