docker builder prune
docker compose build postgres messaging-service --no-cache
docker compose -f "docker-compose.prod.yaml" up -d postgres redis kafka
docker compose --scale postgres=2 --scale redis=1