#!/bin/bash
#author: Hayk Kocharyan
#this script helps you to build this project, 
#push the docker image to DockerHub and run the app.
#It's interactive, so every user can use it with his own 
#credentials



echo
echo
echo -en '       \033[92m WELCOME YOU CHOSE \033[0m'


#this function build a docker image
# $1 docker image name
build (){
    echo
    echo -e '\033[92m           ...BUILDING IMAGE... \033[0m'
    echo
    build="docker build -t $1 ."
    echo $build
    if [ $? -ne 0 ];
    then
        echo; echo
        echo -e '\033[33m docker build finished Unsuccessful \033[0m'
        echo; echo
        exit 1
    else
        echo; echo
        echo -e '\033[93m docker build finished Successful \033[0m'
        echo; echo
    fi
}


#push docker image to dockerhub
#$1 docker image name
push (){
    echo
    echo -e '\033[92m           ...PUSHING IMAGE TO DOCKER HUB... \033[0m'
    echo
    read -p "Enter your docker username: " dockerUser
    read -p "Enter pushing repository name: " repo
    echo
    repoNoSpaces=$(echo "$repo" | tr -d '[[:space:]]')
    tagging="docker tag $1 $dockerUser/$repoNoSpaces:latest"
    echo $tagging
    $tagging
    if [ $? -ne 0 ];
    then
        echo "  docker tag executed unsuccessful "
        exit 1
    fi
    push="docker push $dockerUser/$repoNoSpaces"
    $push
    if [ $? -ne 0 ];
    then
        echo; echo    
        echo -e '\033[33m !!!!!IMAGE CAN NOT BE PUSHED!!!\033[0m'
        echo -e '\033[33m Pushed Successfullly!!!\033[0m'
        echo; echo
    else
        echo; echo
        echo -e "\033[93m Pushed Successfullly!!!\033[0m"
        echo; echo
    fi
}


# this function runs a docker image passsed as the first parameter
# $1 doker image name
run (){
    echo
    echo -en '\033[92m          ...RUNNING... \033[0m'
    echo -en '\033[93m '$1' \033[0m'
    echo " ON PORT 8080"
    echo
    echo
    # run="docker run -i -p 8080:8080 $name"
    run="docker run -i -p 8080:8080 $1"
    $run
    if [ $? -ne 0 ];
    then
        echo; echo
        echo "          !!!!!   docker run CAN NOT be executed successful "
        exit 1
    fi
}

if [ "$1" == "R" ];
then
    echo -e '\033[1m RUN option \033[0m'
    echo 'you chose RUN option'
    read -p "Enter image name: " nameSpaces
    name=$(echo "$nameSpaces" | tr -d '[[:space:]]')
    if [ "$name" != "" ];
    then
        run $name
    else
        echo "Yooooo the name is empty! "
        exit 1
    fi
elif [ "$1" == "B" ];
then
    echo -e '\033[1m BUILD option \033[0m'
    read -p "Enter image name: " nameSpaces
    name=$(echo "$nameSpaces" | tr -d '[[:space:]]')
    if [ "$name" != "" ];
    then
        build $name
    else
        echo "Yooooo the name is empty! "
        exit 1
    fi
elif [ "$1" == "P" ];
then
    echo -e '\033[1m PUSH option \033[0m'
    read -p "Enter image name: " nameSpaces
    name=$(echo "$nameSpaces" | tr -d '[[:space:]]')
    if [ "$name" != "" ];
    then
        push $name
    else
        echo "Yooooo the name is empty! "
        exit 1
    fi
elif [ "$1" == "BR" ];
then
    echo -e '\033[1m BUILD & RUN option \033[0m'
    echo
    read -p "Enter image name: " nameSpaces
    name=$(echo "$nameSpaces" | tr -d '[[:space:]]')
    build $name
    wait
    run $name
elif [ "$1" == "-A" ];
then
    echo -e '\033[1m BUILD, PUSH & RUN option \033[0m'
    echo
    read -p "Enter image name: " nameSpaces
    name=$(echo "$nameSpaces" | tr -d '[[:space:]]')
    (build $name && push $name) 
else
    echo -e '\033[92m HELP MENU\033[0m'
    echo -e '\033[31m  USAGE... \033[0m'; echo
    echo -e '\033[33m ./buildPushRun -B     for BUILD  \033[0m'
    echo -e '\033[33m ./buildPushRun -P     for PUSH \033[0m'
    echo -e '\033[33m ./buildPushRun -R     for RUN \033[0m'
    echo -e '\033[33m ./buildPushRun -BR    for BUILD & RUN \033[0m'
    echo -e '\033[33m ./buildPushRun -A     for BUILD, PUSH & RUN \033[0m'
    echo -e '\033[33m ./buildPushRun        for HELP \033[0m'

    echo -e '\033[31m thanks! @hayk99 \033[0m'
fi    