# /bin/bash

cd $( dirname ${BASH_SOURCE[0]} )

cd ../../src/db/prisma

docker-compose -p crbb down

docker volume rm crbb_mysql_crbb

yarn deploy