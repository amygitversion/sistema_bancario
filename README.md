# sistema_bancario

## Requisitos
Tener instalado:
- Docker
- Docker-Compose

## Instrucciones

En un terminal correr:

```bash
docker-compose up -d
```

Luego se puede probar haciendo peticiones a la URL: http://localhost:8001/

## Tests

Para correr los test, ejecutar
```bash
docker-compose run api_dev mvn test
```
