1. install npm on your PC (see tutorial...)

	1.1 ubuntu - https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-ubuntu-16-04
	
	1.2 other - https://www.npmjs.com/package/npm
2. install git (perhaps you have it) 

3. install Android sdk https://developer.android.com/studio/install.html?pkg=tools
4. write ANTROID_HOME
EXAMPLE
    
    ```
    export ANDROID_HOME=/home/ivpion/dev/androidSDK	
	export PATH=$ANDROID_HOME/tools:$PATH
	export PATH=$ANDROID_HOME/platform-tools:$PATH
	```
	
5. accept all license using anrdoid sdk manager //todo add more details for this point

6. PATH to all android tools(see in example 4)

7. settings emulator(or connect your android device <- recommended)

8. install cordova 
	https://cordova.apache.org/docs/en/7.x/guide/platforms/ubuntu/index.html
	
	(also see how to install cordova on other OS)
	
8. build app ->  ```npm run dev build```

9. build cordova app -> ```cordova build --device```

10. start app on emulator or your device -> ```cordova run android --device```
https://developers.google.com/web/tools/chrome-devtools/remote-debugging/?utm_source=dcc&utm_medium=redirect&utm_campaign=2016q3
(for debugging android app)