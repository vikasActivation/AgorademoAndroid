package com.xrtech.agoraclassroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xrtech.agoraclassroom.databinding.ActivityMainBinding
import io.agora.agoraeducore.core.internal.launch.*
import io.agora.agoraeducore.core.internal.rte.module.impl.RteEngineImpl.setupRemoteVideo
import io.agora.classroom.sdk.AgoraClassSdkConfig
import io.agora.classroom.sdk.AgoraClassroomSDK
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine

class MainActivity : AppCompatActivity() {
    var TAG="MainActivity"
    private var mRtcEngine: RtcEngine? = null
    val mRtcEventHandler=object:IRtcEngineEventHandler(){

    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVideoCall.setOnClickListener {

        }
        binding.btnClassroomCall.setOnClickListener {
            startClassRoom()
        }


    }
    fun startClassRoom() {
        val appId = "be466ceb950d42bea13b01adea105e1b" // Pass your App ID
        val appCertificate = "5914c4e892674d989f1720af9a8223ed"
        val userId = "2882341273"
        val expireTimestamp = 0
        val token = RtmTokenBuilder()
        val result = token.buildToken(appId, appCertificate, userId, RtmTokenBuilder.Role.Rtm_User, expireTimestamp)
        println(result)
       // mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
        // Registers the local user account after initializing the Agora engine and before joining the channel.
       // mRtcEngine?.enableVideo()

        val rtmToken =result.toString() // Pass your RTM Token

        val streamState = AgoraEduStreamState(videoState = 1, audioState = 1)

        val config = AgoraEduLaunchConfig(
            "vikas", // The user name
            userId, // The user ID
            "AkRoom", // The room name
            "ENy2Ov3R5i0OkKY6e20aoh6QZzWATvpjbXlEbARYB8aKiaTeoK", // The room ID
            2,  // The user role
            0,  // The room type
            rtmToken,
            System.currentTimeMillis(), // The class start time
            1800L, // The class duration
            AgoraEduRegion.default, // The region
            null,
            null,
            streamState, // Whether students automatically send audio or video streams after they "go onto the stage"
            AgoraEduLatencyLevel.AgoraEduLatencyLevelUltraLow , // The latency level
            null,
            null
        )

        config.appId = appId
        AgoraClassroomSDK.setConfig(AgoraClassSdkConfig(appId))
        AgoraClassroomSDK.launch(this, config, AgoraEduLaunchCallback { event ->

            Log.e(TAG, ":launch-Classroom State:" + event.name)
            Log.e(TAG, ":launch-Classroom State:" + event.value)
        })

    }
}