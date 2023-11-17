# Lunch_Menu_Open_API

DATA FORM : JSON

SERVICE KEY : REQURIED

---

#### REST(URL)
[GET] app/key=test/lunch?call=1

#### Description

The menu information stored in the DB is randomly returned as the value of call, and the default value is 1.

#### Response message
```
{
    "menuNum": "7",
    "menuName": "김치찌개",
    "useYn": "Y"
}
```

---

#### REST(URL)
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

#### REST(URL)
[POST] app/key=test/lunch/햄버거

#### Description

Returns all data that can be printed among the menus stored in the DB.

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
