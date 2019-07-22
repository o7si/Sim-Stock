**域名本地解析**
```
在 hosts 中添加 127.0.0.1 Sim-Stock.com 进行引入
```

**配置文件 nginx/conf/vhost/xxx.conf**
```
# 前台 http://localhost:8848/
# 后台 http://localhost:8080/
server {
	# http://Sim-Stock.com:80
	listen 80;
	server_name Sim-Stock.com;
	
	# http://Sim-Stock.com:80/
	# http://localhost:8848/
	location / {
		proxy_pass http://localhost:8848/;
	}
	
	# http://Sim-Stock.com:80/Sim-Stock/
	# http://localhost:8080/Sim-Stock/
	location /Sim-Stock {
		proxy_pass http://localhost:8080/Sim-Stock/;
	}
}
```

**引入配置**
```
在 nginx/conf/nginx.conf 中添加 include vhost/*.conf; 进行引入。
```