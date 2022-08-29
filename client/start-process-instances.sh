#!/bin/bash

for value in {1..13000..4}
do
echo $value
curl --location --request POST 'localhost:8088/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw "{\"variables\": {\"firstname\": {\"value\": \"$value\"},\"lastname\": {\"value\": \"$value\"}}}"
echo
let "second = value + 1"
echo $second
curl --location --request POST 'localhost:8089/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw "{\"variables\": {\"firstname\": {\"value\": \"$second\"},\"lastname\": {\"value\": \"$second\"}}}"
echo
let "third = value + 2"
echo $third
curl --location --request POST 'localhost:8090/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw "{\"variables\": {\"firstname\": {\"value\": \"$third\"},\"lastname\": {\"value\": \"$third\"}}}"
echo
let "fourth = value + 3"
echo $fourth
curl --location --request POST 'localhost:8091/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw "{\"variables\": {\"firstname\": {\"value\": \"$fourth\"},\"lastname\": {\"value\": \"$fourth\"}}}"
echo
done

echo All done