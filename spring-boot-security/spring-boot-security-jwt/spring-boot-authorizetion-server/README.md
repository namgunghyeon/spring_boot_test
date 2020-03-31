# spring boot jwt


## signup
```$xslt
curl -X POST \
  http://localhost:8080/users/signup \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "admin5",
	"email": "admin5@email.com",
	"password": "admin5",
	"name": "test",
	"roleNames": ["ROLE_USER", "ROLE_ADMIN"]
}'
```

## signin
```$xslt
curl -X POST \
  http://localhost:8080/users/signin \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "admin5",
	"password": "admin5"
}'
```
## me
```$xslt
curl -X GET \
  http://localhost:8080/users/me \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjUiLCJhdXRoIjoiUk9MRV9VU0VSLFJPTEVfQURNSU4iLCJpYXQiOjE1ODU2NTc2NjksImV4cCI6MTU4NTY1Nzk2OX0.xTrWAbpwLVlp99lWjd1HrhwPo867lttwMCXohFnbxww' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```

## me2
```$xslt
curl -X GET \
  http://localhost:8080/users/me2 \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjUiLCJhdXRoIjoiUk9MRV9VU0VSLFJPTEVfQURNSU4iLCJpYXQiOjE1ODU2NTgzMTcsImV4cCI6MTU4NTY1ODYxN30.MBoUcPwff63RdOdSWGYGz2sRh3BP316qKXKzPKHbFLo' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```

## refresh
```$xslt
curl -X GET \
  'http://localhost:8080/users/refresh?=' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTU4MTc0NzY4NiwiZXhwIjoxNTgxNzQ3OTg2fQ.AEuHo54x_xmh9Zigdu86wPUicTvueN7pGk-Ta6U47Pg' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```