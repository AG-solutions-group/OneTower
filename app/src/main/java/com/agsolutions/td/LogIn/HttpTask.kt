package com.agsolutions.td.LogIn

import android.os.AsyncTask
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

const val MAIN_URL = "https://ag-solutions-group.com"

class HttpTask (var callback: (String?) -> Unit) : AsyncTask<String, Unit, String>()  {

    override fun doInBackground(vararg params: String): String? {
        val url = URL(params[1])
        val httpClient = url.openConnection() as HttpURLConnection
        httpClient.setReadTimeout(10*1000)
        httpClient.setConnectTimeout(10*1000)
        httpClient.requestMethod = params[0]

        if (params[0] == "POST") {
            httpClient.instanceFollowRedirects = false

            httpClient.doOutput = true

            httpClient.doInput = true

            httpClient.useCaches = false

            httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8")
        }
        try {
            if (params[0] == "POST") {
                httpClient.connect()
                val os = httpClient.getOutputStream()
                val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                writer.write(params[2])
                writer.flush()
                writer.close()
                os.close()
            }
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                val stream = BufferedInputStream(httpClient.inputStream)
                val data: String = readStream(inputStream = stream)
                return data
            } else {
                println("ERROR ${httpClient.responseCode}")
                println("ERROR message ${httpClient.responseMessage}")

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            httpClient.disconnect()
        }

        return null
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }

        return stringBuilder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        callback(result)
    }
}