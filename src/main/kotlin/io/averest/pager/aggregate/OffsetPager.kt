package io.averest.pager.aggregate

import io.averest.pager.Pager
import io.averest.pager.valueObject.PaginationIdentifier

class OffsetPager(
    private val path: String,
    pagesIdentifiers: PaginationIdentifier = PaginationIdentifier("offset")
) : Pager(path, pagesIdentifiers) {
    private val pageSizeRegex = Regex(".+pageSize=(\\d+?)")

    private fun addPageSize(): Int {
        val pageSize = path.replace(pageSizeRegex, "$1")
        return pageSize.toInt()
    }

    fun incrementOffset(): String {
        val currentPageIndex = extractPageIndex()
        return replacePage(currentPageIndex + addPageSize())
    }
}
