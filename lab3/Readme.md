# Необхідно для запуску додатку:

- docker
- docker-compose
- вільні порти ```5672, 8888, 3306, 8080, 8081, 8082, 8761```

## Запуск
```                                  
sudo docker-compose up --scale client=2   // запустити за допомогою docker-compose
```

## Оновлення конфігурації
```
sudo docker exec -it [lab3_configserver-CONTAINER ID] /bin/bash
cd config
echo some.value=1 > gateway.properties
git add gateway.properties 
git commit -m "update value"
```

 - Eureka Server URL: http://localhost:8761
 - Service URL (instance 1): http://localhost:8081
 - Service URL (instance 2): http://localhost:8082
 - Api Gateway URL: http://localhost:8080
 - Config Server URL: http://localhost:8888
