#### Delete created containers and images ####
#!/bin/bash
docker rm $(docker ps -a -q) -v -f
