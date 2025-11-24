@echo off

docker exec -it mongo /bin/bash -c "mongo -u mongoadmin -p secret --authenticationDatabase admin"