package com.xrtech.agoraclassroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.agora.agoraeducore.core.internal.launch.AgoraEduLatencyLevel
import io.agora.agoraeducore.core.internal.launch.AgoraEduLaunchCallback
import io.agora.agoraeducore.core.internal.launch.AgoraEduLaunchConfig
import io.agora.agoraeducore.core.internal.launch.AgoraEduRegion
import io.agora.agoraeducore.core.internal.launch.AgoraEduStreamState
import io.agora.classroom.sdk.AgoraClassSdkConfig
import io.agora.classroom.sdk.AgoraClassroomSDK

class MainActivity : AppCompatActivity() {
    var TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startClassRoom()
    }
    fun startClassRoom() {
        val appId = "be466ceb950d42bea13b01adea105e1b" // Pass your App ID
        val rtmToken = "006be466ceb950d42bea13b01adea105e1bIAC+pFDgLE3uTGN4EHyMOENcWEzOrGIiUe7Lf/iIlJEkApJ1GzEAAAAAEAAbbpWuHBgHYwEA6AMcGAdj" // Pass your RTM Token
        val streamState = AgoraEduStreamState(videoState = 1, audioState = 1)

        val config = AgoraEduLaunchConfig(
            "xiaoming", // The user name
            "xiaoming2", // The user ID
            " AkRoom", // The room name
            "V8g6krHuKzKkPayOJiEOvhHRYqlQhn6AEGVTq6uh4I0wLjoI7S", // The room ID
            2,  // The user role
            4,  // The room type
            rtmToken,
            System.currentTimeMillis(), // The class start time
            1800L, // The class duration
            AgoraEduRegion.na, // The region
            null,
            null,
            streamState, // Whether students automatically send audio or video streams after they "go onto the stage"
            AgoraEduLatencyLevel.AgoraEduLatencyLevelUltraLow, // The latency level
            null,
            null
        )

        config.appId = appId
        AgoraClassroomSDK.setConfig(AgoraClassSdkConfig(appId))
        AgoraClassroomSDK.launch(this, config, AgoraEduLaunchCallback { event ->

            Log.e(TAG, ":launch-Classroom State:" + event.name)
        })
    }
}