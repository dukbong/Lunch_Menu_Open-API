# Lunch_Menu_Open_API

DATA FORM : JSON

SERVICE KEY : REQURIED

---

#### REST(URL) : 원하는 만큼 메뉴 보기
[GET] app/key=test/lunch?call=1

#### Description

It is randomly selected and returned from the menus that can be printed from the DB.

If call is omitted, the default value is 1, and if it is larger than the number of menus, only the number of menus is returned.

#### Response message
```
{
    "menuNum": "7",
    "menuName": "김치찌개",
    "useYn": "Y"
}
```

---

#### REST(URL) : 전체 메뉴 보기
[GET] app/key=test/lunch/all

#### Description

Returns all data that can be printed among the menus stored in the DB.

#### Response message
```
[
    {
        "menuNum": "1",
        "menuName": "감자탕",
        "useYn": "Y"
    },
    {
        "menuNum": "2",
        "menuName": "짜장면",
        "useYn": "Y"
    },
    {
        "menuNum": "3",
        "menuName": "순대국",
        "useYn": "Y"
    }
]
```

---

#### REST(URL) : 메뉴 추가하기
[POST] app/key=test/lunch/햄버거

#### Description

You can save the desired menu to the DB, and a message will be displayed depending on success.

#### [SUCCESS] Response message
```
{
    "act": "Add Menu",
    "menuName": "햄버거",
    "code": 1,
    "status": "SUCCESS"
}
```
#### [FAILED] Response message
```
{
    "act": "Add Menu",
    "menuName": "햄버거",
    "code": -1,
    "status": "FAILED"
}
```

---

#### REST(URL) : 메뉴 삭제하기
[DELETE] app/key=test/lunch/햄버거

#### Description

This operation requires permission, and you can delete the desired menu from the DB, and a message will be displayed depending on whether it was successful or not.

#### [SUCCESS] Response message
```
{
    "act": "Delete Menu",
    "menuName": "물갈비",
    "code": 1,
    "status": "SUCCESS"
}
```
#### [FAILED] Response message
```
// If there is no information to delete in the DB
{
    "act": "Delete Menu",
    "menuName": "물",
    "code": -1,
    "status": "FAILED"
}
```
```
// If you do not have permission
{
    "act": "Delete",
    "menuName": null,
    "code": 0,
    "status": "관리자만 삭제가 가능합니다."
}
```

---

#### REST(URL) : 서비스 키 발급 (테스트)
[GET] app/apiKey_issuance

#### Description

This is the URL where the service key can be issued and will be modified during actual operation.

#### [SUCCESS] Response message
```
{
    "keyNum": "59",
    "keyValue": "63d9a131-87b1-4d2a-a5fe-2e4d7fc3699a",
    "keyIssued": "2023-11-17",
    "keyMaxAge": "23/11/18",
    "status": "SUCCESS",
    "note": "발급 완료"
}
```

---
