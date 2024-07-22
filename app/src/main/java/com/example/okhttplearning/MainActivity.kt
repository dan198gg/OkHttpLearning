package com.example.okhttplearning

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.okhttplearning.ui.theme.OkHttpLearningTheme
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val corScope= rememberCoroutineScope()
            Text(text = sendRequest())
//            LaunchedEffect(key1 = ""){
//
//            corScope.launch {
//                
//
//            }
//        }
            }
    }
}



fun sendRequest():String{
    val client=OkHttpClient()
    var body=""
    val request=Request.Builder()
        .url("https://publicobject.com/helloworld.txt").build()
    try {
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("запрос не был успешен ${response.code}-${response.message}")
                Log.i("REQUEST","${response.header("Server")}")
                body=response.body.string()
                Log.i("REQUEST", body)
            }

        })
    }catch (e:IOException){
        Log.i("OSHIBKE",e.toString())
    }
        return body
}