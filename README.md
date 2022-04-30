# PUSL2020-Project-Backend

mvn -DskipTests package
docker build -f Dockerfile -t docker-spring-boot .
sudo docker run -d -it --network=host -p 8080:8080 docker-spring-boot
