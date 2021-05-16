package io.averest.pager.aggregate

import io.averest.pager.Pager
import io.averest.pager.valueObject.PaginationIdentifier
import io.averest.pager.valueObject.Pattern

class OffsetPager(
    private val path: String,
    pagesIdentifiers: PaginationIdentifier = Pattern.offsetIdentifier
) : Pager(path, pagesIdentifiers) {
    private val pageSizeRegex = Regex(".+pageSize=(\\d+?)")

    private fun addPageSize(): Int {
        val pageSize = path.replace(pageSizeRegex, "$1")
        return pageSize.toInt()
    }

    override fun increment(): String {
        val currentPageIndex = extractPageIndex()
        return replaceIndex(currentPageIndex + addPageSize())
    }
}
