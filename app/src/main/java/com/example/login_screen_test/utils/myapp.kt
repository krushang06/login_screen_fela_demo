import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse


class myapp : Application() {
//    private lateinit var androidSnooper: AndroidSnooper
//
//    override fun onCreate() {
//        super.onCreate()
//        Log.e("myapp", "onCreate: ")
//
//        androidSnooper = AndroidSnooper.instance
//
//        fun createAndroidSnooopermyapp(httpRequest: HttpRequest, httpResponse: HttpResponse) {
//
//            val url = httpRequest.toString()
//
//            val httpCall: HttpCall = HttpCall.Builder()
//                .withUrl(url)
//                .withPayload(httpRequest.toString())
//                .withMethod(httpRequest.toString())
//                .withResponseBody(httpResponse.toString())
//                .withStatusCode(httpResponse.toString().toString().toInt())
//                .withStatusText(httpResponse.toString())
//                .build()
//
//            Log.d("HTTP_REQUEST", "Created HttpCall object: $httpCall")
//            AndroidSnooper.instance.record(httpCall)
//            Log.d("HTTP_REQUEST", "Recorded HttpCall: $httpCall")
//        }
//    }
}

