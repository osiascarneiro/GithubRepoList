package com.osias.githubrepos.core.data

import com.osias.githubrepos.core.model.RequestState
import retrofit2.Response
import java.net.ConnectException

//credits to: https://stackoverflow.com/a/58670214
suspend fun<T: Any> safeAPICall(call: suspend () -> Response<T>) : RequestState<T> {
    val response = try {
        call.invoke()
    }
    catch (e:java.lang.Exception){
        e.printStackTrace()
        val message = if( e is ConnectException) "Connection Error" else "Something went wrong. Please try again."
        return RequestState.Failure(Error(message))
    }


// When connection is OK

    if(response.isSuccessful){
        return RequestState.Success(response.body()!!)
    }else{
        val error = response.errorBody()?.string()

        error?.let{
            return RequestState.Failure(Error(it))

        }
        return RequestState.Failure(Error("Something went wrong. Please try again."))
    }
}