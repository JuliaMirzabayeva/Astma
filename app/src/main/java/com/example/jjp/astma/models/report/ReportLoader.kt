package com.example.jjp.astma.models.report

import com.example.jjp.astma.api.ApiService
import com.example.jjp.astma.api.request.ReportRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportLoader @Inject constructor(private val api: ApiService) {
    fun getReport(request: ReportRequest, callback: Callback<ResponseBody>){
        api.getReport(request).enqueue(ReportApiCallback(callback))
    }
    private inner class ReportApiCallback internal constructor(val callback: Callback<ResponseBody>) : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            callback.onResponse(call, response)
        }
    }
}