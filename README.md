# uploadGithubService

## 未加密测试

``` shell

java -jar target/uploadGithubService-1.1-jar-with-dependencies.jar  -owner hhhaiai -repo Git_result -target-dir-full-name  test/nobase64.txt -content-no-base64 "测试字符串"  -token $GITHUB_TOKEN_90 -commit-messge  "测试未加密字符串"  -commit-auther "UploadGithubService" -commit-email "sanbo.xyz@gmail.com"

```

## 文件测试

``` shell

java -jar target/uploadGithubService-1.1-jar-with-dependencies.jar  -owner hhhaiai -repo Git_result -target-dir-full-name  test/useMavenWrapper.sh -native-file useMavenWrapper.sh  -token $GITHUB_TOKEN_90 -commit-messge  "test upload file"  -commit-auther "UploadGithubService" -commit-email "sanbo.xyz@gmail.com"

```