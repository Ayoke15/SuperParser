# API Машинного обучения для создания приоритезированного списка рекомендаций

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
