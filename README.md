# UploadGithubService

## 未加密测试
``` shell
java -jar target/uploadGithubService-1.0-jar-with-dependencies.jar  -o hhhaiai -r Git_result  -p n_test.txt  -t ${github_token}  -c "cax" -m "test text" -a "gittest" -l "sanbo.xyz@gmail.com"
```

## 文件测试

``` shell
java -jar target/uploadGithubService-1.0-jar-with-dependencies.jar  -o hhhaiai -r Git_result  -p test.txt  -t  ${github_token}  -f mvnw  -m "test file" -a "gittest" -l "sanbo.xyz@gmail.com"
```


