# Spring boot redis


# Tests
## Curl
```
curl -X GET \
  http://localhost:8080/redis \
  -H 'Postman-Token: d0e0cc86-ee2f-4b98-a99e-747ef053e0fa' \
  -H 'cache-control: no-cache'
```

## ngrinder
```
docker run -d -v ~/ngrinder-controller:/opt/ngrinder-controller -p 8081:80 -p 16001:16001 -p 12000-12009:12000-12009 ngrinder/controller:3.4
docker run -v ~/ngrinder-agent:/opt/ngrinder-agent ngrinder/agent:3.4 192.168.0.4:8081

id: admin
pwd: admin
```



