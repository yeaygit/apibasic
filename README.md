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

## 로그 레벨(중요도) 정리
1. trace : 코드를 추적하고 기능적 부분을 찾을 때
2. debug : 코드 블록의 문제를 점검하고 진단하고 해결할 때 도움이 되는 정보
3. info : 표준 로그 레벨, 서비스 시작이나 종료 정보
4. warn : 에러는 아닌데 좀 수상한 정보
5. error: 애플리케이션이 작동하지 않는 수준의 정보
6. fatal: 장애수주의 심각한 오류