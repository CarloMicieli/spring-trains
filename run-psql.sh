#!/bin/bash
docker run --rm --name postgres-dev \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -e POSTGRES_DB=trainsdb \
  -d -p 5432:5432 -v postgres_data:/var/lib/postgresql/data postgres