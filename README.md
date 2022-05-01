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

#API

```sh
example login API => 139.59.119.228/api/login
```

>  {
>    "username": "root",
>    "password": "123"
>  }
```sh  
sing up API => 139.59.119.228/api/user
```

>   {
>     "name": "Rootx",
>      "username": "root",
>      "password": "123",
>      "email": "root@gmail.com",
>      "address": "localhost",
>      "role": [
>          {
>              "id": 1,
>              "name": "USER"
>          },
>          {
>              "id": 2,
>              "name": "POLICE_USER"
>          }
>      ]
>  }
