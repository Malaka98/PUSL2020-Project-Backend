# PUSL2020-Project-Backend

mvn -Dtest=UserServiceTest test

mvn -DskipTests package

docker build -f Dockerfile -t docker-spring-boot .

sudo docker run -d -it --network=host -p 8080:8080 docker-spring-boot


API

example login API => 139.59.119.228/api/login

  {
    "username": "root",
    "password": "123"
  }
  
sing up API => 139.59.119.228/api/user

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
