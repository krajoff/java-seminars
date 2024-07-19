# Banking Server via ServerSocket with POJO
 
## Описание

Проект представляет собой тестовый банковский сервер, 
который позволяет манипулировать денежными средствами. 
Сервер предоставляет REST API для регистрации, 
аутентификации пользователей, перевода средств и 
проверки баланса. Все запросы требуют авторизации с 
использованием JWT токенов. Данные сохраняются в базе данных 
PostgreSQL с использованием JDBC. Сервер реализован на базе ServerSocket.

## Требования

### 1. Регистрация пользователя

**Endpoint:** `POST <server_url>/signup`

**Пример запроса:**
```json
{
  "login": "user_login",
  "password": "user_pass"
}
```

### 2. Aутентификация пользователя
**Endpoint:** `POST <server_url>/signin`

**Пример запроса:**
```json
{
  "login": "user_login",
  "password": "user_pass"
}
```

### 3. Перевод средств
**Endpoint:** `POST <server_url>/money`

**Пример запроса:**
```json
{
  "to": "user_b_login",
  "amount": 500
}
```
### 4. Получение текущего баланса
**Endpoint:** `GET <server_url>/money`