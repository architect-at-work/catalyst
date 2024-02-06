#!/bin/bash

# Get current timestamp
timestamp=$(date +%Y%m%d%H%M%S)

# Create migration file

touch "service/src/main/resources/db/migration/V${timestamp}__$1.sql";