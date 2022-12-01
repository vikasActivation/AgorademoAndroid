package com.xrtech.agoraclassroom

class RtmTokenBuilder {
    enum class Role(var value: Int) {
        Rtm_User(1);
    }

    var mTokenCreator: AccessToken? = null

    @Throws(Exception::class)
    fun buildToken(
        appId: String?, appCertificate: String?,
        uid: String?, role: Role?, privilegeTs: Int
    ): String? {
        mTokenCreator = AccessToken(appId, appCertificate, uid, "")
        mTokenCreator!!.addPrivilege(AccessToken.Privileges.kRtmLogin, privilegeTs)
        return mTokenCreator!!.build()
    }

    fun setPrivilege(privilege: AccessToken.Privileges?, expireTs: Int) {
        mTokenCreator?.addPrivilege(privilege, expireTs)
    }

    fun initTokenBuilder(originToken: String?): Boolean {
        mTokenCreator?.fromString(originToken)
        return true
    }
}