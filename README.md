# Totion

Notion 과 Tistory 를 연동하기 위한 Web Service

현재 깃허브에 존재하는 연동 기능은 업데이트가 없거나, 지원이 부실하기 때문에 직접 만드는 서비스.

## 21.08.23 문제점

- Notion API가 아직 정식 Release가 아닌 Beta 중이라 Code block을 가져오도록 지원하지 않는다. 개발 정보 공유가 많은 특성상 반드시 필요한 핵심기능이라 추가될 때까지 기다려야 할 듯.
- Tistory API는 글 작성 post 요청 시 content 내용을 body가 아닌 queryParam으로 받는다. 때문에 내용이 좀만 길어져도 HTTP 414 Long... 에러를 뱉어내는지라 Java에서 이걸 해결할 수 있는 방법이 있는지 모색 중.
- Tistory API는 JSON이 아닌 XML을 기본(...)으로 돌려준다. 옵션을 줘서 JSON으로 받으려하면 내용이 부실해진다. JSON을 기본으로하는 Notion API와 규격이 다르기 때문에 어쩔 수 없는 변환과정이 필요하다.

위와 같은 어려움들로 인해 Notion API가 공식 릴리즈 될 때까지만이라도 기다려보려한다.
