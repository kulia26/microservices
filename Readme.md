# Необхідно для запуску додатку:

- docker
- docker-compose

## Запуск
```
git pull                                                            // оновити репозиторій
cd lab5                                                             // перейти в директорію проекту
sudo docker-compose up --build --scale consumer=3 --scale client=2   // запустити за допомогою docker-compose
```

 - Eureka Server URL: http://localhost:8761
 - Service URL (instance 1): http://localhost:8081
 - Service URL (instance 2): http://localhost:8082
 - Api Gateway URL: http://localhost:8080

## Для того, щоб побачити логи consumer:
```
sudo docker logs -f [container-id]
```
