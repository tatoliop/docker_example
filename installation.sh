#install docker
echo "Installing docker"
sudo apt-get update
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo apt-get install docker-compose
echo "Adding user to docker group"
sudo usermod -aG docker $USER
newgrp docker
echo "Configuring docker to start on startup"
sudo systemctl enable docker.service
sudo systemctl enable containerd.service

