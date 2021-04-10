package io.averest.pager.aggregator

import io.averest.pager.Pager
import io.averest.pager.valueObject.PaginationIdentifier

class OffsetPager(
    private val url: String,
    pagesIdentifiers: PaginationIdentifier = PaginationIdentifier("offset")
) : Pager(url, pagesIdentifiers) {
    private fun addPageSize(): Int{
        val pageSize = url.replace(Regex(".+pageSize=(\\d+?)"),"$1")
        return pageSize.toInt()
    }


    fun incrementOffset(): String {
        val currentPageIndex = extractPageIndex()
        return replacePage(currentPageIndex + addPageSize())
    }
}
