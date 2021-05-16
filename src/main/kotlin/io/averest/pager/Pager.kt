package io.averest.pager

import io.averest.pager.valueObject.PaginationIdentifier
import io.averest.pager.valueObject.Pattern.pageIdentifier

open class Pager(
    private val path: String,
    pagesIdentifiers: PaginationIdentifier = pageIdentifier,
    private val pageRegex: Regex = Regex("(?i)(.+(${pagesIdentifiers.identifier})([=:]))\"?(\\d{1,4})\"?(.+|\$)")
) {
    init {
        if (!path.contains(Regex("(?i)${pagesIdentifiers.identifier}"))) throw Exception("unknown page structure:\t$path")
    }

    open fun increment(): String {
        val currentPageIndex = extractPageIndex()
        return replaceIndex(currentPageIndex + 1)
    }

    fun removeIndexFromUrl() =
        path.replace(pageRegex, "$1$5")

    fun replaceIndex(index: Int) =
        path.replace(pageRegex, "$1$index$5")

    fun extractPageIndex(): Int {
        val currentPageIndex = path.replace(pageRegex, "$4")
        return currentPageIndex.toInt()
    }
}
