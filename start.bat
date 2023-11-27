docker builder prune
docker compose build postgres messaging-service --no-cache
docker compose -f "docker-compose.yaml" up -d api-gateway content-manager-service user-service media-service  messaging-service postgres redis eureka zookeeper kafka control-center
docker compose --scale admin-api=2 --scale main-api=3