# java-reflection-controller-annotation
**java reflection API, Annotation을 이용하여 @RequestMapping과 Parameter에 따른 DTO 동적 생성** 
1. Controller의 메소드를 위한 Annotation 정의 (RequestMapping) 
2. reflection API를 이용하여 Controller 객체의 메소드에 있는 Annotation 분석
3. 라우팅과 일치하는 Annotation을 가진 메소드를 검색
4. 검색된 메소드의 Parameter를 reflection API를 이용하여 DTO 생성

## 환경
- Windows 11
- Oracle openJDK 11.0.15
- Tomcat 9.0.71