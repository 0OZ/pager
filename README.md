# pager
Kotlin pagination Regex


### Example

```kotlin
   Pager("""https://www.example.com/search/stuff?sorting=2&pagenumber=1&foo=ksadk""").incrementPage()
   // https://www.example.com/search/stuff?sorting=2&pagenumber=2&foo=ksadk
```
