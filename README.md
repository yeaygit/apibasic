# 스프링 부트 프로젝트 설정
1. [프로젝트 생성 링크](https://start.spring.io/)
2. HELP.md -> README.md로 이름 변경 ->.gitignore파일 README.md제거
3. application.properties-> application.yml로 이름 변경
4. yml 설정으로 톰캣 포트 변경하기

```agsl
server:
    port: 8181
```

5. devTools 설정
- 파일 -> 설정 -> 빌드,실행,배포 -> 컴파일러 ->프로젝트 자동빌드 체크
- 파일 -> 설정 -> 고급설정 -> 컴파일러 -> 개발된 어플리케이션 ~~~ auto-make 체크