set -e

if [ ! -f make.bash ]; then
	echo 'make.bash must be run from $GOPATH/src/github.com/hkurokawa/godroid-sample'
	exit 1
fi

ANDROID_APP=$PWD/android_studio_app/GodroidSample/app
gobind -lang=go github.com/hkurokawa/godroid-sample > go_prime/go_prime.go
gobind -lang=java github.com/hkurokawa/godroid-sample > Prime.java && mv -f Prime.java $ANDROID_APP/src/main/java/go/prime/
(cd ../../../golang.org/x/mobile/ && ln -sf $PWD/bind/java/Seq.java $ANDROID_APP/src/main/java/go)
(cd ../../../golang.org/x/mobile/ && ln -sf $PWD/app/*.java $ANDROID_APP/src/main/java/go)
(cd go_prime_lib && CGO_ENABLED=1 GOOS=android GOARCH=arm GOARM=7 go build -ldflags="-shared" . && mv -f go_prime_lib $ANDROID_APP/src/main/jniLibs/armeabi-v7a/libgojni.so)
