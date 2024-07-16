docker run --name mariadb -d \
  -p 3306:3306 \
  --restart=always \
  -v "$(pwd)/data":/var/lib/mysql \
  -e MARIADB_ROOT_PASSWORD=devpass \
  -e MARIADB_USER=dev \
  -e MARIADB_PASSWORD=devpass \
  -e MARIADB_DATABASE=orm \
  mariadb:11.4.2-noble
