#!/bin/bash

echo "即将打包(含依赖类库)!"

#  assembly插件2.2命令
#mvn assembly:assembly
# assembly插件3.x后命令
#mvn compile assembly:single
./mvnw compile assembly:single