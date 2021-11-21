# /bin/bash
cd $( dirname ${BASH_SOURCE[0]} )

npx babel-node generateRSDatamodel.js

cd ../src/db/prisma

docker-compose -p crbb up -d

echo "waiting..."
sleep 20s

npx prisma deploy