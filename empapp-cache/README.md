# Employees kiindulási projekt

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432  --name employees-postgres postgres
```

```shell
docker run --name employees-redis -p 6379:6379 -d redis
``` 

```shell
docker exec -it employees-redis redis-cli ping
```

```shell
docker exec -it employees-redis redis-cli --scan
```

```shell
docker exec -it employees-redis redis-cli get employee::1
```

```shell  
docker exec -it employees-redis redis-cli get "employees::SimpleKey []"
```