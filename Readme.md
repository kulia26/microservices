# Необхідно для запуску додатку:

- docker
- docker-compose
- вільні порти ```3306, 8080, 8081, 8082, 8761```

```
cd lab2                                   // перейти в директорію проекту
sudo docker-compose up --scale client=2   // запустити за допомогою docker-compose
```

 - Eureka Server URL: http://localhost:8761
 - Service URL (instance 1): http://localhost:8081
 - Service URL (instance 2): http://localhost:8082
 - Api Gateway URL: http://localhost:8080