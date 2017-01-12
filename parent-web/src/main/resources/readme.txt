作为集群测试，下面为虚拟机服务规划：

nexus:ip:8081/nexus

应用war存放地址：/usr/local/down/ungz/software/应用名


##############################tomcat################################
cas-tomcat {//cas服务地址，不用了，不采用cas，自己用redis实现
		ip:cas.fyrj.com 
		shutdown.port:50010		 
		Connector.http.port:8083 		
		Connector.AJP.port:50011
}

base-data-tomcat {
		ip:base-data.fyrj.com 
		shutdown.port:50020		 
		Connector.http.port:8084		
		Connector.AJP.port:50021
}

cims-tomcat {
		ip:cims.fyrj.com 
		shutdown.port:50030		 
		Connector.http.port:8085		
		Connector.AJP.port:50031
}

##############################zk集群#################################
zookeeper1{
		ip:192.168.1.199 or 192.168.1.198
		port:30001
}

zookeeper2{
		ip:192.168.1.199 or 192.168.1.198
		port:30002
}

############################dubbo配置################################
base-data{
	port:21001
}
cims{
	port:21002
}
