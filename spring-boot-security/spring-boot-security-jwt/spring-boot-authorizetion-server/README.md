# spring boot jwt


## signup
```$xslt
curl -X POST \
  http://localhost:8080/users/signup \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "admin6",
	"email": "admin6@email.com",
	"password": "admin6",
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
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjUiLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJpYXQiOjE1ODU5MDEwOTgsImV4cCI6MTU4NTkwMTM5OH0.3eY4rkB5RVOJNuC69dD_3sdkVqNXZ_B2urRvnj-I5zQ' \
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