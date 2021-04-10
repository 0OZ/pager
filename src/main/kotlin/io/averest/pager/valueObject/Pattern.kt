package io.averest.pager.valueObject

object Pattern {
    val offsetIdentifier = PaginationIdentifier("offset")
    val pageIdentifier = PaginationIdentifier("\"?page\"?|seite|pageNumber")
}
