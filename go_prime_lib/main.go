package main

import (
	"golang.org/x/mobile/app"

	_ "github.com/hkurokawa/godroid-sample/go_prime"
	_ "golang.org/x/mobile/bind/java"
)

func main() {
	app.Run(app.Callbacks{})
}
