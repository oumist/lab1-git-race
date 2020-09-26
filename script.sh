#!/bin/bash
#author: Hayk Kocharyan
#docker build, push and run

echo
echo
echo "       ...   WELCOME    ..."
echo
echo
echo "      Let's BUILD the image"
read -p "Enter image name: " name

build="docker build -t $name ."
$build
if [ $? -ne 0 ];
then
    echo "docker build finished Unsuccessful "
    exit 1
fi
echo
echo
echo "      PUSHING IMAGE TO DOCKER HUB..."
echo
echo
read -p "Enter your docker username: " dockerUser
read -p "Enter pushing repo name: " repo
repoNoSpaces=$(echo "$repo" | tr -d '[[:space:]]')
taggin="docker tag $name $dockerUser/$repoNoSpaces:latest"
$tagging
if [ $? -ne 0 ];
then
    echo "docker tag executed unsuccessful "
    exit 1
fi
push="docker push $dockerUser/$repoNoSpaces"
$push
if [ $? -ne 0 ];
then
    echo "    !!!!!IMAGE CAN'T BE PUSHED"
    echo "docker push finished unsuccessful "
    exit 1
else
    echo "pushed Successfullly"
fi
echo
echo
echo "RUNNING DOCKER IMAGE ON PORT 8080"
run="docker run -i -p 8080:8080 $name"
$run
if [ $? -eq 1 ];
then
    echo "docker run can't be executed successful "
    exit 1
fi