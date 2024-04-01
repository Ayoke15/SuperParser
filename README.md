# ML модель для создания приоритезированного списка рекомендаций

## Содержание

- [Update Filters Endpoint](#update-filters-endpoint)
- [Get Weights Endpoint](#get-weights-endpoint)
- [Submit Filters Endpoint](#submit-filters-endpoint)
- [Submit Tenders Endpoint](#submit-tenders-endpoint)

## Update Filters Endpoint

### `POST /api/update`

Обновляет данные фильтров в системе рекомендаций на основе пользовательских событий.

#### Параметры запроса

Тело запроса должно быть в формате JSON и содержать массив объектов с полями:

- `filter`: Имя фильтра.
- `success`: Флаг успеха (boolean).

#### Пример запроса

```json
[
  {
    "filter": "some_filter",
    "success": true
  },
  {
    "filter": "another_filter",
    "success": false
  }
]
```
## Get Weights Endpoint
**GET /api/weights**

Предназначен для вычисления и получения текущих весов фильтров.

### Ответы
- **200 OK**: Возвращает веса фильтров.

**Пример успешного ответа:**
```json
{
  "endDate": 0.3505747126436782,
  "region": 0.5662650602409639,
  "startDate": 0.5026455026455027
}
```
## Submit Filters Endpoint
**POST /api/filters**

Позволяет сохранить выбранные пользователем фильтры для дальнейшего использования.

### Параметры запроса
Тело запроса в формате JSON с полями:
- `startDate`: Начальная дата (String).
- `endDate`: Конечная дата (String).
- `region`: Регион (String).
- `product`: Продукт (String).

**Пример тела запроса:**
```json
{
  "startDate": "2024-05-01",
  "endDate": "2024-05-20",
  "region": "Central",
  "product": "Laptop"
}
```
## Submit Tenders Endpoint
**POST /api/submit**

Принимает список тендеров и возвращает отсортированный список ID тендеров по приоритету.

### Параметры запроса
Тело запроса в формате JSON с массивом объектов тендеров.

**Пример тела запроса:**
```json
[
  {
    "id": 1,
    "startDate": "2024-05-05",
    "endDate": "2024-05-15",
    "region": "North",
    "name": "Tender A",
    "products": ["Laptop", "Smartphone"]
  },
  {
    "id": 2,
    "startDate": "2024-05-10",
    "endDate": "2024-05-25",
    "region": "Central",
    "name": "Tender B",
    "products": ["Laptop"]
  },
  {
    "id": 3,
    "startDate": "2024-05-01",
    "endDate": "2024-05-20",
    "region": "Central",
    "name": "Tender C",
    "products": ["Tablet", "Smartphone"]
  }
]
```
**Пример успешного ответа:**

```json
[
  2,
  1
]
```
