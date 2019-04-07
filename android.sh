#!/bin/bash 
#定义IS_JENKINS标识是否是jenkins打包,默认是false
isJenkins=false

build_type=''
#定义打包类型Release   Debug

#定义版本号
app_version='2.0'

#定义code
app_code=1

#定义project的路径
path=''

#定义输出路径
outPath=''

#读取jenkins中的配置

isJenkins=$IS_JENKINS


if [ ! -n "$isJenkins" ]; then
  isJenkins=false
fi

#构建并打开文件
function build()
{
	echo "clean项目==============================================================="

	${path}./gradlew clean

	echo "clean结束==============================================================="

	echo "开始打包================================================================="
	# content=$(cat ${path}gradle.properties)
	# echo "覆盖之后读取内容：$content"
	while true
        do
        case $build_type in
            [Release]* )
                ${path}./gradlew assembleRelease
                break;;
            [Debug]* )
                ${path}./gradlew assembleDebug
                break;;
                * )     continue ;;
        esac
    done

	echo "打包结束================================================================="

	# if [[ $isJenkins=false ]]; then
		while true
        	do
        	case $build_type in
            	[Release]* )
                	outPath=${path}app/build/outputs/apk/release
                	break;;
            	[Debug]* )
                	outPath=${path}app/build/outputs/apk/debug
                	break;;
                	* )     continue ;;
        	esac
    	done
		open ${outPath}
	# fi

}

#提示用户输入版本号和code
function autoBuildInputVersion()
{

	echo "================================================="
    echo "请输入要构建的apk的版本号，当前APP_VERSION=${old_version}"
    echo "================================================="
    read -p "版本号格式如: 1.0.1) :" app_version

	echo "================================================="
    echo "请输入要构建的apk的code值，code值需要高于当前线上版本的最高值，当前APP_CODE=${old_code}"
    echo "================================================="
    read -p "请输入一个有效的整数) :" app_code

    sed -i "" "s#^APP_VERSION =.*#APP_VERSION = ${app_version}#g ; s#^APP_CODE =.*#APP_CODE = ${app_code}#g"  ${path}gradle.properties
		
	build
}


#手动打包
function buildWithNotJenkins()
{

	echo "================================================="
    echo ""
    echo "编译的版本是否用于线上？"
    echo "          [Y] 是（构建用于上线的Release版本）"
    echo "          [N] 否（构建用于测试的Debug版本）"
    echo "================================================="
    read -p "请选择:" isBuildRelease

	while true
        do
        case $isBuildRelease in
            [yY]* )
                build_type="Release"
                break;;
            [nN]* )
                build_type="Debug"
                break;;
                * )     continue ;;
        esac
    done

	#手动打包
	path='/Users/ruisi/冠e通/AndroidCustomProject/LibraryDemo/'
	content=$(cat ${path}gradle.properties)
	
	#读取文件的APP_VERSION对应的value
	old_version=`grep APP_VERSION ${path}gradle.properties|cut -d'=' -f2`

	old_code=`grep APP_CODE ${path}gradle.properties|cut -d'=' -f2`

	#提示用户是否需要重新定义版本号
	# echo "================================================="
 #    echo ""
 #    echo "是否要重新定义APP_VERSION？当前APP_VERSION=${old_version}"
 #    echo "          [Y] 是（新输入的版本号将覆盖原有项目中的版本号）"
 #    echo "          [N] 否（使用项目内置版本号构建apk）"
 #    echo "================================================="
 #    read -p "请选择:" isInputVersion

	# while true
	# 	do
	# 	case $isInputVersion in
	# 		[yY]* )
	# 			autoBuildInputVersion
	# 			break;;
	# 		[nN]* )
	# 			build
	# 			break;;
	# 			* )		continue ;;
	# 	esac
	# done

    build
}

#jenkins打包
function buildWithJenkins()
{
	#读取jenkins中的配置参数
	build_type=$BUILD_TYPE

	# app_version=$APP_VERSION

	# app_code=$APP_CODE

	path=$WORKSPACE/

	#将build.properites中的版本信息替换成jenkins中的版本信息
	# sed -i "" "s#^IS_JENKINS =.*#IS_JENKINS = ${isJenkins}#g ; s#^APP_VERSION =.*#APP_VERSION = ${app_version}#g ; s#^APP_CODE =.*#APP_CODE = ${app_code}#g"  ${path}gradle.properties
		
	build
}


if [[ $isJenkins = false ]]; then
	buildWithNotJenkins
else
	buildWithJenkins
fi













	