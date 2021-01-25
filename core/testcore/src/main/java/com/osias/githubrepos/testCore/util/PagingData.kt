package com.osias.githubrepos.testCore.util

import androidx.paging.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope

private val dcb = object : DifferCallback {
    override fun onChanged(position: Int, count: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}

suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val items = mutableListOf<T>()
    val job = TestCoroutineScope()
    var context: Job? = null
    val dif = object : PagingDataDiffer<T>(dcb) {

        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            newCombinedLoadStates: CombinedLoadStates,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size) { items.add(newList.getFromStorage(idx)) }
            onListPresentable()
            context?.cancel()
            return null
        }
    }
    context = job.launch { dif.collectFrom(this@collectData) }
    return items
}