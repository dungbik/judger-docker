# 충북대학교 SW중심대학사업단 Online Judge API 서버

## 패키지 설치
```
$ npm i
```

## .env 파일 생성 및 설정(프로젝트 루트 디렉터리에 생성)
```
# 개발 시에는 development로 설정 배포 시는 production으로 설정
NODE_ENV=<development|production>

PORT=<포트 번호>

# 개발 시에는 debug, 배포 시에는 info로 설정
LOG_LEVEL=<debug|info>

# 사용 가능한 API 버전들을 콤마(,)로 구분하여 나열(예: v1,v2)
API_VERSIONS=v1

# 인증 서버 설정
AUTH_APP_HOST=<인증 서버의 URL>

# MongoDB 설정
MONGO_URI=<MongoDB의 URI>
MONGO_POOL_SIZE=4

PM2_INSTANCE=
PM2_EXEC_MODE=
```

## 프로젝트 구조

### 1. index.js
- 실행 시작점(Entry Point)

### 2. app.js
- 익스프레스 애플리케이션의 주요 미들웨어 설정

### 3. env 디렉터리
- 프로젝트의 주요 설정 값들을 내보내는 모듈
- .env 파일로부터 가져옴

### 4. errors 디렉터리
- index.js: 주요 에러들을 정의한 파일
- handlers.js: 에러처리와 관련된 미들웨어 정의

### 5. models 디렉터리
- helpers: MongoDB에 Collection 생성시 필요한 도구 함수 모음
- schemas: 데이터베이스 Collection 생성을 위한 스키마를 정의, 이곳에 ~.schema.js 파일을 추가하면 자동으로 Collection 생성
  * <모델명>.schema.js 파일에서 <모델명>은 케밥 표기법으로 작성(예: user info -> user-info.schema.js)
  * 위 파일로 모델 생성시 코드에서 사용하는 모델명은 파스칼표기법으로 변경됨(예: user-info.schema.js => UserInfo)
  * 위 파일로 모델 생성시 MongoDB의 Collection 명은 -가 빠진 형태로 생성(예: user-info.schema.js => userinfo)
- schemas/helpers: 스키마 생성을 위한 도구 함수 모음
- schemas/mapper: 요청의 쿼리 스트링에서 각 쿼리들을 모델의 데이터 타입에 맞게 매핑하는 함수 모음
- schemas/plugins: mongoose 스키마에서 사용할 커스텀 플러그인 모음
- index.js: schemas 디렉터리에 정의된 ~.schema.js 파일을 이용하여 자동으로 모델 생성하여 내보내기 함

#### 5-1. 프로젝트에서 모델 가져오기
user-info.schema.js 파일에 스키마를 정의했을 경우

```javascript
const { UserInfo } = require('../../models'); // models 디렉터리의 상대 경로

const some = async (req, res, next) => {
  try {
    const userInfo = await UserInfo.findOne();
    res.json(userInfo);
  } catch (e) {
    next(e); // 에러 발생시 next 함수로 에러 전달 --> 에러 처리 미들웨어 실행
  }
}
```

### 6. pm2
서버 배포시 pm2의 실행 스크립트
package.json의 start 스크립트로 실행
```shell
$ npm start
```

### 7. routes
라우팅 설정
- utils 디렉터리: 응답 처리를 위한 도구 모음
- vN(v1 등) 디렉터리: API 라우팅을 버전별로 나눔
  * v1 디렉터리 아래에 디렉터리를 추가하면 해당 디렉터리명으로 라우팅 엔드 포인트가 설정됨  
    예) user-info 디렉터리 생성시 /v1/user-info 경로로 설정  
    생성한 디렉터리는 index.js 파일이 있어야 하며 다음과 같은 구조로 작성
```javascript
const { Router } = require('express');

const router = Router();

router.get('/<path>', ...);
router.post('/<path>', ...);
...
module.exports = router;
```
- index.js: .env 파일에 설정된 버전들에 해당하는 디렉터리에 있는 엔드 포인트들을 자동으로 로딩
- middlewares.js: 라우팅 전반에서 사용하는 미들웨어들 모음


### 8. utils 디렉터리
- logger.js: 로그를 위한 모듈
  * error: 에러 로그 출력
  * info: 일반 로그 출력
  * debug: 디버깅을 위한 로그 출력
- 사용 예
```javascript
const {error, info, debug} = require('../../utils/logger'); // logger.js 가 있는 위치의 상대경로

error('에러 로그');
info('일반 로그');
debug('디버깅용 로그');
```

