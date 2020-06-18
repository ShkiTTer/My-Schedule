package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.responses.common.IResponseContent

interface ISearchService {
    suspend fun search(query: String?): List<IResponseContent>
}