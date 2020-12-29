#!/bin/sh

# Install docker
echo "deb https://download.docker.com/linux/ubuntu/ $(lsb_release -c -s) stable" | sudo tee /etc/apt/sources.list
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 7EA0A9C3F273FCD8
sudo apt update
sudo apt -yq install docker-ce

# Admit user ubuntu into the docker group
sudo groupadd docker
sudo usermod -a -G docker ubuntu

# Start and enable the docker service
sudo systemctl start docker
sudo systemctl enable docker

# Install the nginx helloworld sample
sudo docker run -d --restart always --hostname nginx.lucasjellema.com -p 3456:443 -p 3457:80 --name my-nginx nginx
