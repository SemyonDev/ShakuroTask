package com.semyong.shakurotask.presentation.fragments.listfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.data.helpers.DataErrorManager
import com.semyong.shakurotask.data.usecase.GetUsersUseCase
import com.semyong.shakurotask.presentation.fragments.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val dataErrorManager: DataErrorManager
) : BaseViewModel() {

    protected val _error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val mError: LiveData<String> = _error

    protected val _usersResult: MutableLiveData<LiveData<PagedList<User>>> by lazy { MutableLiveData<LiveData<PagedList<User>>>() }
    val mUsersResult: LiveData<LiveData<PagedList<User>>> = _usersResult


    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataErrorManager.throwableFlow.collect() {
                it?.let {
                    _error.postValue(it.toString())
                    Log.d("Tag", "dataErrorManager = " + it)
                }
            }
        }
    }

    fun getusers() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            _error.postValue(throwable.stackTrace.toString())
            Log.d("Tag", "coroutineExceptionHandler = " + throwable.printStackTrace())
            throwable.printStackTrace()
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val pageConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20).build()

            val dataSourceFactory = getUsersUseCase.getUsers()

            withContext(Dispatchers.Main) {
                _usersResult.postValue(LivePagedListBuilder(dataSourceFactory, pageConfig).build())
            }
        }
    }

}