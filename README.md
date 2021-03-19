## :yellow_heart:Kakaomap Service

**:heavy_check_mark:** Kakao developer site에 개발자 계정 등록 후, 발급 받은 네이티브 앱키를 자신의 안드로이드 프로젝트에 등록을 해준다. 
**:heavy_check_mark:** 로그를 찍어 해쉬 키를 받은 후에 이 해쉬키를 카카오 개발자 계정에 프로젝트 주소와 함께 등록한다. 이때 사용하는 해쉬 키 코드는 다음과 같다. 

        fun getHashKey() {  
      var packageInfo: PackageInfo = PackageInfo()  
      try {  
      packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)  
     } catch (e: PackageManager.NameNotFoundException) {  
      e.printStackTrace()  
     }  for (signature: Signature in packageInfo.signatures) {  
      try {  
      var md: MessageDigest = MessageDigest.getInstance("SHA")  
      md.update(signature.toByteArray())  
      Log.e("KEY_HASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))  
     } catch (e: NoSuchAlgorithmException) {  
      Log.e("KEY_HASH", "Unable to get MessageDigest.signature = " + signature, e)  
     }  
     }}
**:heavy_check_mark:** 카카오 서비스를 사용하기 전에 manifest에 다음과 같이 설정해주는 것도 잊지 말자. 

    <uses-permission android:name="android.permission.INTERNET"/>  
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>  
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>


 **:heavy_check_mark:** 프로젝트에 넣어줘야 할 파일은 다음과 같다. 
 ![image](https://user-images.githubusercontent.com/58849278/111765244-7e235600-88e7-11eb-808a-a0bcabf226ea.png)

 **:heavy_check_mark:** 카카오맵을 불러온 뒤, 자신의 위치를 표시하는 마커를 추가하였다. 
 ![image](https://user-images.githubusercontent.com/58849278/111765345-972c0700-88e7-11eb-95d6-2b1de5441b79.png)
