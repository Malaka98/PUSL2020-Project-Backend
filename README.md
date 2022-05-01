# PUSL2020-Project-Backend

```sh
mvn -Dtest=UserServiceTest test
```

```sh
mvn -DskipTests package
```

```sh
docker build -f Dockerfile -t docker-spring-boot .
```

```sh
sudo docker run -d -it --network=host -p 8080:8080 docker-spring-boot
```

# API

_example login API =>_
Method = POST
```sh
http://139.59.119.228/api/login
```
```javascript
  {
    "username": "root",
    "password": "123"
  }
```
_SingUp API =>_
Method = POST
```sh  
http://139.59.119.228/api/user
```
```javascript
   {
     "name": "Rootx",
      "username": "root",
      "password": "123",
      "email": "root@gmail.com",
      "address": "localhost",
      "role": [
          {
              "id": 1,
              "name": "USER"
          },
          {
              "id": 2,
              "name": "POLICE_USER"
          }
      ]
  }
```
