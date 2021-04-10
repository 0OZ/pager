package io.averest.pager

import io.averest.pager.valueObject.PaginationIdentifier

open class Pager(
    private val url: String,
    pagesIdentifiers: PaginationIdentifier = PaginationIdentifier("\"?page\"?|seite|pageNumber"),
) {
    private val pageRegex = Regex("(?i)(.+(${pagesIdentifiers.identifier})([=:]))\"?(\\d{1,4})\"?(.+|\$)")

    init {
        if (!url.contains(Regex("(?i)${pagesIdentifiers.identifier}"))) throw Exception("missing page structure")
    }

    fun incrementPage(): String {
        val currentPageIndex = extractPageIndex()
        return replacePage(currentPageIndex + 1)
    }

    fun removeIndexFromUrl() =
        url.replace(pageRegex, "$1$5")

    fun replacePage(index: Int) =
        url.replace(pageRegex, "$1$index$5")

    fun extractPageIndex(): Int {
        val currentPageIndex = url.replace(pageRegex, "$4")
        return currentPageIndex.toInt()
    }
}
