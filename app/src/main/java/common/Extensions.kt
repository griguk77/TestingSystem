package common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> LiveData<T>.distinctUntilChanged(compare: T?.(T?) -> Boolean = { this == it }): LiveData<T> = MediatorLiveData<T>().also { mediator ->
    mediator.addSource(this) { newValue ->
        if(!newValue.compare(value)) {
            mediator.postValue(newValue)
        }
    }
}