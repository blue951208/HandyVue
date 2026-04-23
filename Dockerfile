# 1단계: 리액트 빌드
FROM node:20 AS frontend-build
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
# 경고 무시 옵션을 추가하여 빌드 실행
RUN CI=false npm run build-only

# 2단계: 스프링 부트 빌드
FROM gradle:8-jdk17 AS backend-build
WORKDIR /app
COPY . .

COPY --from=frontend-build /frontend/dist /app/src/main/resources/static
# 실행 권한 부여
RUN chmod +x ./gradlew

RUN ./gradlew bootWar -x test --no-daemon

# 3단계: 최종 실행 이미지
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=backend-build /app/build/libs/*.war app.war
EXPOSE 8080
# Render의 PORT 환경 변수를 사용하도록 설정
ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT:8080}", "-Dspring.profiles.active=prod", "-jar", "app.war"]