(echo; echo PATH=~/.apportable/SDK/bin:$PATH) >> ~/.bash_profile; source ~/.bash_profile

apportable log | grep FBAndroid

adb logcat \ MSG:V *:S# FBAndroid
