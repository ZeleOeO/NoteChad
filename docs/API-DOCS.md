## 📚 Endpoints

### `POST` `/api/notes/update-note/{id}`

**Operation:** `updateNote`  
**Tags:** note-controller

#### 🧾 Parameters

| Name | In   | Type      | Required |
|------|------|-----------|----------|
| id   | path | `integer` | True     |

#### 📦 Request Body

**Content Type:** `application/json`

- Schema: `string`

#### 📤 Responses

| Code | Description | Schema                                            |
|------|-------------|---------------------------------------------------|
| 200  | OK          | [ApiResponseNoteViewDTO](#apiresponsenoteviewdto) |

---

### `POST` `/api/notes/new-note`

**Operation:** `createNote`  
**Tags:** note-controller

#### 🧾 Parameters

_None_

#### 📦 Request Body

**Content Type:** `application/json`

- Schema: `string`

#### 📤 Responses

| Code | Description | Schema                                            |
|------|-------------|---------------------------------------------------|
| 200  | OK          | [ApiResponseNoteViewDTO](#apiresponsenoteviewdto) |

---

### `POST` `/api/auth/signup`

**Operation:** `addAuthor`  
**Tags:** auth-controller

#### 🧾 Parameters

_None_

#### 📦 Request Body

**Content Type:** `application/json`

- Schema: [AuthorCreateRequest](#authorcreaterequest)

#### 📤 Responses

| Code | Description | Schema                                                |
|------|-------------|-------------------------------------------------------|
| 200  | OK          | [ApiResponseAuthorViewDTO](#apiresponseauthorviewdto) |

---

### `POST` `/api/auth/login`

**Operation:** `login`  
**Tags:** auth-controller

#### 🧾 Parameters

_None_

#### 📦 Request Body

**Content Type:** `application/json`

- Schema: [AuthorLoginRequest](#authorloginrequest)

#### 📤 Responses

| Code | Description | Schema                                  |
|------|-------------|-----------------------------------------|
| 200  | OK          | [ApiResponseString](#apiresponsestring) |

---

### `GET` `/api/notes/{author}`

**Operation:** `getNoteByAuthor`  
**Tags:** note-controller

#### 🧾 Parameters

| Name   | In   | Type     | Required |
|--------|------|----------|----------|
| author | path | `string` | True     |

#### 📦 Request Body

_None_

#### 📤 Responses

| Code | Description | Schema                                            |
|------|-------------|---------------------------------------------------|
| 200  | OK          | [ApiResponseNoteViewDTO](#apiresponsenoteviewdto) |

---

### `GET` `/api/authors/{id}`

**Operation:** `getAuthorById`  
**Tags:** author-controller

#### 🧾 Parameters

| Name | In   | Type      | Required |
|------|------|-----------|----------|
| id   | path | `integer` | True     |

#### 📦 Request Body

_None_

#### 📤 Responses

| Code | Description | Schema                                                |
|------|-------------|-------------------------------------------------------|
| 200  | OK          | [ApiResponseAuthorViewDTO](#apiresponseauthorviewdto) |

---

### `GET` `/api/authors/all`

**Operation:** `getAllAuthors`  
**Tags:** author-controller

#### 🧾 Parameters

_None_

#### 📦 Request Body

_None_

#### 📤 Responses

| Code | Description | Schema                                                        |
|------|-------------|---------------------------------------------------------------|
| 200  | OK          | [ApiResponseListAuthorViewDTO](#apiresponselistauthorviewdto) |

---

### `DELETE` `/api/notes/delete-note/{id}`

**Operation:** `deleteNote`  
**Tags:** note-controller

#### 🧾 Parameters

| Name | In   | Type      | Required |
|------|------|-----------|----------|
| id   | path | `integer` | True     |

#### 📦 Request Body

_None_

#### 📤 Responses

| Code | Description | Schema                                  |
|------|-------------|-----------------------------------------|
| 200  | OK          | [ApiResponseString](#apiresponsestring) |

---

## 📦 Schemas

### ApiResponseNoteViewDTO

<a name="apiresponsenoteviewdto"></a>

| Field   | Type      |
|---------|-----------|
| status  | `integer` |
| message | `string`  |
| data    | `object`  |

### NoteViewDTO

<a name="noteviewdto"></a>

| Field     | Type      |
|-----------|-----------|
| id        | `integer` |
| title     | `string`  |
| content   | `string`  |
| createdAt | `string`  |
| updatedAt | `string`  |
| author    | `string`  |

### AuthorCreateRequest

<a name="authorcreaterequest"></a>

| Field           | Type     |
|-----------------|----------|
| displayName     | `string` |
| username        | `string` |
| password        | `string` |
| confirmPassword | `string` |

### ApiResponseAuthorViewDTO

<a name="apiresponseauthorviewdto"></a>

| Field   | Type      |
|---------|-----------|
| status  | `integer` |
| message | `string`  |
| data    | `object`  |

### AuthorViewDTO

<a name="authorviewdto"></a>

| Field       | Type      |
|-------------|-----------|
| id          | `integer` |
| displayName | `string`  |
| userName    | `string`  |

### AuthorLoginRequest

<a name="authorloginrequest"></a>

| Field    | Type     |
|----------|----------|
| username | `string` |
| password | `string` |

### ApiResponseString

<a name="apiresponsestring"></a>

| Field   | Type      |
|---------|-----------|
| status  | `integer` |
| message | `string`  |
| data    | `string`  |

### ApiResponseListAuthorViewDTO

<a name="apiresponselistauthorviewdto"></a>

| Field   | Type      |
|---------|-----------|
| status  | `integer` |
| message | `string`  |
| data    | `array`   |
