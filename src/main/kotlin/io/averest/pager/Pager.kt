package io.averest.pager

import io.averest.pager.valueObject.PaginationIdentifier

open class Pager(
    private val path: String,
    pagesIdentifiers: PaginationIdentifier = PaginationIdentifier("\"?page\"?|seite|pageNumber"),
    private val pageRegex: Regex = Regex("(?i)(.+(${pagesIdentifiers.identifier})([=:]))\"?(\\d{1,4})\"?(.+|\$)")
) {
    init {
        if (!path.contains(Regex("(?i)${pagesIdentifiers.identifier}"))) throw Exception("unknown page structure")
    }

    fun incrementPage(): String {
        val currentPageIndex = extractPageIndex()
        return replacePage(currentPageIndex + 1)
    }

    fun removeIndexFromUrl() =
        path.replace(pageRegex, "$1$5")

    fun replacePage(index: Int) =
        path.replace(pageRegex, "$1$index$5")

    fun extractPageIndex(): Int {
        val currentPageIndex = path.replace(pageRegex, "$4")
        return currentPageIndex.toInt()
    }
}
