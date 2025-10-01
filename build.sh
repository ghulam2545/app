#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Define colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No color

echo -e "${GREEN}Starting build process...${NC}"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo -e "${RED}Docker is not installed. Please install Docker first.${NC}"
    exit 1
fi

# Optional: Stop and remove existing containers
echo -e "${GREEN}Cleaning up previous containers...${NC}"
docker compose down

# Build and start containers in detached mode
echo -e "${GREEN}Building and starting containers...${NC}"
docker compose up --build -d

echo -e "${GREEN}Build and startup complete.${NC}"
